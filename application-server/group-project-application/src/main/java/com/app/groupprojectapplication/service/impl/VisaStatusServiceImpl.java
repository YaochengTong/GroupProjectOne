package com.app.groupprojectapplication.service.impl;

import com.app.groupprojectapplication.dao.*;
import com.app.groupprojectapplication.domain.*;
import com.app.groupprojectapplication.domain.homeElement.VisaInfo;
import com.app.groupprojectapplication.domain.visaStatusManagement.DocumentInfo;
import com.app.groupprojectapplication.domain.visaStatusManagement.VisaStatusInfo;
import com.app.groupprojectapplication.file.AmazonS3FileService;
import com.app.groupprojectapplication.service.IVisaStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class VisaStatusServiceImpl implements IVisaStatusService {

    private final String applicationType = "visa status application";
    // map: <Key: current step, Value: [next step, message, order]>
    private final Map<String, List<String>> map = Stream.of(new Object[][] {
            {"OPT Receipt", Arrays.asList("OPT EAD", "Please upload a copy of your OPT EAD", "1")},
            {"OPT EAD", Arrays.asList("I-983 for OPT STEM", "Please download and fill your I-983 form", "2")},
            {"I-983 Filled", Arrays.asList("I-983 Waiting for HR", "Waiting for HR to sign the I-983", "3")},
            {"I-983 Signed", Arrays.asList("I-20 after I-983 Submitted", "Please send the I-983 with all necessary documents to your school and upload the new I-20", "4")},
            {"I-20", Arrays.asList("OPT STEM Receipt", "Please upload your OPT STEM Receipt", "5")},
            {"OPT STEM Receipt", Arrays.asList("OPT STEP EAD", "Please upload a copy of your OPT STEM EAD", "6")},
            {"OPT STEM EAD", Arrays.asList("No Further Action Needed", null, "7")},
    }).collect(Collectors.toMap(data -> (String)data[0], data -> (List<String>)data[1]));

    @Autowired
    IUserDao iUserDao;

    @Autowired
    IEmployeeDao iEmployeeDao;

    @Autowired
    IVisaStatusDao iVisaStatusDao;

    @Autowired
    IPersonDao iPersonDao;

    @Autowired
    AmazonS3FileService amazonS3FileService;

    @Autowired
    IApplicationWorkFlowDao iApplicationWorkFlowDao;

    @Override
    @Transactional
    public List<VisaStatusInfo> getVisaInfoList() {
        List<VisaStatusInfo> visaStatusInfoList = new ArrayList<>();
        List<User> users = iUserDao.getAllUsers();
        int index = 0;

//        // official code:
//        for (User user : users) {
//            VisaStatusInfo visaStatusInfo = getVisaInfoByUserId(user.getId(), index);
//            if (visaStatusInfo != null) {visaStatusInfoList.add(visaStatusInfo); index++;}
//        }

        // for test usage: because of incomplete database
        for (Integer userId: Arrays.asList(556, 557,558, 89)) {
            VisaStatusInfo visaStatusInfo = getVisaInfoByUserId(userId, index);
            if (visaStatusInfo != null) {visaStatusInfoList.add(visaStatusInfo); index++;}
        }

        return visaStatusInfoList;
    }

    @Override
    @Transactional
    public VisaStatusInfo getVisaInfo(Integer userId) {
        return getVisaInfoByUserId(userId, 0);
    }

    public VisaStatusInfo getVisaInfoByUserId(Integer userId, Integer index) {
        User user = iUserDao.getUserById(userId);
        VisaStatusInfo visaStatusInfo = null;
        try {
            Employee employee = iEmployeeDao.getEmployeeById(iUserDao.getEmployeeIdByUserId(user.getId()));
            Person person = iUserDao.getPersonByUserId(userId);
            Integer dayLeft = iVisaStatusDao.getVisaAuthorizationLeftDay(employee.getId());
            String currentStep = getCurrentStep(iApplicationWorkFlowDao.getApplicationWorkFlowByUserIdAndApplicationType(userId, applicationType));
            System.out.println(currentStep);
            visaStatusInfo = new VisaStatusInfo();
            visaStatusInfo.setUserId(userId);
            visaStatusInfo.setIdx(index);
            visaStatusInfo.setFullName(setFullName(person));
            visaStatusInfo.setWorkAuthorization(iVisaStatusDao.getVisaTypeByEmployeeId(employee.getId()));
            visaStatusInfo.setAuthorizationStartDate(employee.getVisaStartDate().toString().substring(0, 10));
            visaStatusInfo.setAuthorizationEndDate(employee.getVisaEndDate().toString().substring(0, 10));
            visaStatusInfo.setAuthorizationDayLeft(dayLeft);
            visaStatusInfo.setDocumentReceived(setDocumentInfo(userId));
            visaStatusInfo.setNextStep(map.get(currentStep).get(0));
            visaStatusInfo.setMessage(message(currentStep, dayLeft));
            visaStatusInfo.setCurrStep(map.get(currentStep).get(2));
        } catch (Exception e) {
            System.err.println("No such employee with user id "+ userId);
        }

        return visaStatusInfo;
    }

    private String getCurrentStep(List<ApplicationWorkflow> applicationWorkflowList) {
        System.out.println(applicationWorkflowList);
        applicationWorkflowList.sort((a,b) -> {
            return map.get(a.getStatus()).get(2).compareTo(map.get(b.getStatus()).get(2));
        });
        System.out.println(applicationWorkflowList);
        return applicationWorkflowList.get(0).getStatus();
    }

    @Override
    @Transactional
    public String findEmailByUserId(Integer userId) {
        Person person = iUserDao.getPersonByUserId(userId);
        return person.getEmail();
    }

    @Override
    public boolean updateInfo(Map<String, Object> params) {
        String fullName = params.get("fullName").toString();
        Integer userId = Integer.parseInt(params.get("userId").toString());
        String AED = params.get("authorizationEndDate").toString();
        String ASD = params.get("authorizationStartDate").toString();
        String visaType = params.get("workAuthorization").toString();

        String firstName = fullName.split(" ")[0];
        String lastName = fullName.split(" ")[1];
        Person person = iUserDao.getPersonByUserId(userId);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        iPersonDao.updatePerson(person);


        Integer employeeId = iUserDao.getEmployeeIdByUserId(userId);
        Employee employee = iEmployeeDao.getEmployeeById(employeeId);
        Timestamp formatASD = Timestamp.valueOf(ASD + " 00:00:00");
        Timestamp formatAED = Timestamp.valueOf(AED + " 00:00:00");
        employee.setVisaEndDate(formatAED);
        employee.setVisaStartDate(formatASD);
        iEmployeeDao.updateEmployee(employee);


        VisaStatus visaStatus = employee.getVisaStatus();
        visaStatus.setVisaType(visaType);
        iVisaStatusDao.updateVisaStatus(visaStatus);
        return true;
    }

    public String setFullName(Person person) {
        String fullName;
        if (person.getMiddleName() == null) {
            fullName = person.getFirstName() + " " + person.getLastName();
        } else {
            fullName = person.getFirstName() + " " + person.getMiddleName() + " " + person.getLastName();
        }
        return fullName;
    }

    public String message(String currentStep, Integer dayLeft) {
        if (currentStep.equals("OPT EAD") && dayLeft >= 100) return null;
        return map.get(currentStep).get(1);
    }

    public List<DocumentInfo> setDocumentInfo(Integer userId) {
        List<String> documents = amazonS3FileService.printFilesInOneFolder(String.valueOf(userId));
        List<DocumentInfo> documentInfoList = new ArrayList<>();
        for (String document : documents) {
            String currentStep = document.split("_")[0];
            String date = document.split("_")[1];
            DocumentInfo documentInfo = new DocumentInfo(document, date);
            documentInfoList.add(documentInfo);
        }
        return documentInfoList;
    }


}
