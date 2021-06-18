package com.app.groupprojectapplication.service.impl;

import com.app.groupprojectapplication.dao.IApplicationWorkFlowDao;
import com.app.groupprojectapplication.dao.IEmployeeDao;
import com.app.groupprojectapplication.dao.IUserDao;
import com.app.groupprojectapplication.dao.IVisaStatusDao;
import com.app.groupprojectapplication.domain.*;
import com.app.groupprojectapplication.domain.homeElement.VisaInfo;
import com.app.groupprojectapplication.domain.visaStatusManagement.VisaStatusInfo;
import com.app.groupprojectapplication.file.AmazonS3FileService;
import com.app.groupprojectapplication.service.IVisaStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class VisaStatusServiceImpl implements IVisaStatusService {

    private final String applicationType = "visa status application";

    @Autowired
    IUserDao iUserDao;

    @Autowired
    IEmployeeDao iEmployeeDao;

    @Autowired
    IVisaStatusDao iVisaStatusDao;

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
        for (User user : users) {
            VisaStatusInfo visaStatusInfo = getVisaInfoByUserId(user.getId(), index);
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
