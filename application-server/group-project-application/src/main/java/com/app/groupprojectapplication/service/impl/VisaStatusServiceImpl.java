package com.app.groupprojectapplication.service.impl;

import com.app.groupprojectapplication.dao.*;
import com.app.groupprojectapplication.domain.*;
import com.app.groupprojectapplication.domain.homeElement.VisaInfo;
import com.app.groupprojectapplication.domain.visaStatusManagement.VisaStatusInfo;
import com.app.groupprojectapplication.file.AmazonS3FileService;
import com.app.groupprojectapplication.service.IVisaStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class VisaStatusServiceImpl implements IVisaStatusService {

    private final String applicationType = "visa status application";

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
    public List<VisaStatusInfo> getVisaInfo() {
        List<VisaStatusInfo> visaStatusInfoList = new ArrayList<>();
        List<User> users = iUserDao.getAllUsers();
        int index = 0;

//        // official code:
//        for (User user : users) {
//            VisaStatusInfo visaStatusInfo = getVisaInfoByUserId(user.getId(), index);
//            if (visaStatusInfo != null) {visaStatusInfoList.add(visaStatusInfo); index++;}
//        }

        // for test usage: because incomplete database
        for (Integer userId: Arrays.asList(556, 557,558, 89)) {
            VisaStatusInfo visaStatusInfo = getVisaInfoByUserId(userId, index);
            if (visaStatusInfo != null) {visaStatusInfoList.add(visaStatusInfo); index++;}
        }

        return visaStatusInfoList;
    }

    @Override
    @Transactional
    public VisaStatusInfo getVisaInfoByUserId(Integer userId, Integer index) {
        User user = iUserDao.getUserById(userId);
        VisaStatusInfo visaStatusInfo = null;
        try {
            Employee employee = iEmployeeDao.getEmployeeById(iUserDao.getEmployeeIdByUserId(user.getId()));
            Person person = iUserDao.getPersonByUserId(userId);
            visaStatusInfo = new VisaStatusInfo();
            visaStatusInfo.setUserId(userId);
            visaStatusInfo.setIdx(index);
            visaStatusInfo.setFullName(setFullName(person));
            visaStatusInfo.setWorkAuthorization(iVisaStatusDao.getVisaTypeByEmployeeId(employee.getId()));
            visaStatusInfo.setAuthorizationStartDate(employee.getVisaStartDate().toString().substring(0, 10));
            visaStatusInfo.setAuthorizationEndDate(employee.getVisaEndDate().toString().substring(0, 10));
            visaStatusInfo.setAuthorizationDayLeft(iVisaStatusDao.getVisaAuthorizationLeftDay(employee.getId()));
            visaStatusInfo.setDocumentReceived(amazonS3FileService.printFilesInOneFolder(String.valueOf(user.getId())));
            visaStatusInfo.setNextStep(determineNextStep(iApplicationWorkFlowDao.getApplicationWorkFlowByUserIdAndApplicationType(userId, applicationType).getStatus()));

        } catch (Exception e) {
            System.err.println("No such employee with user id "+ userId);
        }

        return visaStatusInfo;
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

    public String determineNextStep(String currentStep) {
        String nextStep;
        switch (currentStep) {
            case "OPT Receipt": nextStep = "OPT EAD"; break;
            case "OPT EAD": nextStep = "I-983 for OPT STEM"; break;
            case "I-983": nextStep = "I-20 after I-983 Submitted"; break;
            case "I-20": nextStep = "OPT STEM Receipt"; break;
            case "OPT STEM Receipt": nextStep = "OPT STEP EAD"; break;
            case "OPT STEM EAD": nextStep="No Further Action Needed"; break;
            default:
                throw new IllegalStateException("Unexpected value: " + currentStep);
        }
        return nextStep;
    }


}
