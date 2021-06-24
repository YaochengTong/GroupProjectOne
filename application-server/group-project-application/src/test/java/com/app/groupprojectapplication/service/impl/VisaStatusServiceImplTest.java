package com.app.groupprojectapplication.service.impl;

import com.app.groupprojectapplication.dao.IApplicationWorkFlowDao;
import com.app.groupprojectapplication.dao.IEmployeeDao;
import com.app.groupprojectapplication.dao.IPersonDao;
import com.app.groupprojectapplication.dao.IPersonalDocumentDao;
import com.app.groupprojectapplication.dao.IUserDao;
import com.app.groupprojectapplication.dao.IVisaStatusDao;
import com.app.groupprojectapplication.domain.ApplicationWorkflow;
import com.app.groupprojectapplication.domain.Employee;
import com.app.groupprojectapplication.domain.Person;
import com.app.groupprojectapplication.domain.User;
import com.app.groupprojectapplication.domain.visaStatusManagement.DocumentInfo;
import com.app.groupprojectapplication.domain.visaStatusManagement.VisaStatusInfo;
import com.app.groupprojectapplication.file.AmazonS3FileService;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.Mockito.*;

class VisaStatusServiceImplTest {

    @Mock
    Map<String, List<String>> map;
    @Mock
    IUserDao iUserDao;
    @Mock
    IEmployeeDao iEmployeeDao;
    @Mock
    IVisaStatusDao iVisaStatusDao;
    @Mock
    IPersonDao iPersonDao;
    @Mock
    AmazonS3FileService amazonS3FileService;
    @Mock
    IApplicationWorkFlowDao iApplicationWorkFlowDao;
    @Mock
    IPersonalDocumentDao iPersonalDocumentDao;
    @InjectMocks
    VisaStatusServiceImpl visaStatusServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetVisaInfoList() {
        when(iUserDao.getUserById(anyInt())).thenReturn(new User("username", "email", "password"));
        when(iUserDao.getAllUsers()).thenReturn(Arrays.<User>asList(new User("username", "email", "password")));
        when(iUserDao.getEmployeeIdByUserId(anyInt())).thenReturn(Integer.valueOf(0));
        when(iUserDao.getPersonByUserId(anyInt()))
            .thenReturn(new Person(null, "firstName", "lastName", "middleName", null, null, null, null, null, null));
        when(iEmployeeDao.getEmployeeById(anyInt())).thenReturn(new Employee());
        when(iVisaStatusDao.getVisaAuthorizationLeftDay(anyInt())).thenReturn(Integer.valueOf(0));
        when(iVisaStatusDao.getVisaTypeByEmployeeId(anyInt())).thenReturn("getVisaTypeByEmployeeIdResponse");
        when(amazonS3FileService.printFilesInOneFolder(anyString())).thenReturn(Arrays.<String>asList("String"));
        when(iApplicationWorkFlowDao.getApplicationWorkFlowByUserIdAndApplicationType(anyInt(), anyString()))
            .thenReturn(Arrays.<ApplicationWorkflow>asList(new ApplicationWorkflow()));

        List<VisaStatusInfo> result = visaStatusServiceImpl.getVisaInfoList();
        Assertions.assertEquals(Arrays.<VisaStatusInfo>asList(
            new VisaStatusInfo("fullName", "workAuthorization", "authorizationStartDate", "authorizationEndDate",
                Integer.valueOf(0), Arrays.<DocumentInfo>asList(new DocumentInfo("name", "date")), "nextStep",
                Integer.valueOf(0), Integer.valueOf(0), "message", "currStep")), result);
    }

    @Test
    void testGetVisaInfo() {
        when(iUserDao.getUserById(anyInt())).thenReturn(new User("username", "email", "password"));
        when(iUserDao.getEmployeeIdByUserId(anyInt())).thenReturn(Integer.valueOf(0));
        when(iUserDao.getPersonByUserId(anyInt()))
            .thenReturn(new Person(null, "firstName", "lastName", "middleName", null, null, null, null, null, null));
        when(iEmployeeDao.getEmployeeById(anyInt())).thenReturn(new Employee());
        when(iVisaStatusDao.getVisaAuthorizationLeftDay(anyInt())).thenReturn(Integer.valueOf(0));
        when(iVisaStatusDao.getVisaTypeByEmployeeId(anyInt())).thenReturn("getVisaTypeByEmployeeIdResponse");
        when(amazonS3FileService.printFilesInOneFolder(anyString())).thenReturn(Arrays.<String>asList("String"));
        when(iApplicationWorkFlowDao.getApplicationWorkFlowByUserIdAndApplicationType(anyInt(), anyString()))
            .thenReturn(Arrays.<ApplicationWorkflow>asList(new ApplicationWorkflow()));

        VisaStatusInfo result = visaStatusServiceImpl.getVisaInfo(Integer.valueOf(0));
        Assertions.assertEquals(
            new VisaStatusInfo("fullName", "workAuthorization", "authorizationStartDate", "authorizationEndDate",
                Integer.valueOf(0), Arrays.<DocumentInfo>asList(new DocumentInfo("name", "date")), "nextStep",
                Integer.valueOf(0), Integer.valueOf(0), "message", "currStep"), result);
    }

    @Test
    void testGetVisaInfoByUserId() {
        when(iUserDao.getUserById(anyInt())).thenReturn(new User("username", "email", "password"));
        when(iUserDao.getEmployeeIdByUserId(anyInt())).thenReturn(Integer.valueOf(0));
        when(iUserDao.getPersonByUserId(anyInt()))
            .thenReturn(new Person(null, "firstName", "lastName", "middleName", null, null, null, null, null, null));
        when(iEmployeeDao.getEmployeeById(anyInt())).thenReturn(new Employee());
        when(iVisaStatusDao.getVisaAuthorizationLeftDay(anyInt())).thenReturn(Integer.valueOf(0));
        when(iVisaStatusDao.getVisaTypeByEmployeeId(anyInt())).thenReturn("getVisaTypeByEmployeeIdResponse");
        when(amazonS3FileService.printFilesInOneFolder(anyString())).thenReturn(Arrays.<String>asList("String"));
        when(iApplicationWorkFlowDao.getApplicationWorkFlowByUserIdAndApplicationType(anyInt(), anyString()))
            .thenReturn(Arrays.<ApplicationWorkflow>asList(new ApplicationWorkflow()));

        VisaStatusInfo result = visaStatusServiceImpl.getVisaInfoByUserId(Integer.valueOf(0), Integer.valueOf(0));
        Assertions.assertEquals(
            new VisaStatusInfo("fullName", "workAuthorization", "authorizationStartDate", "authorizationEndDate",
                Integer.valueOf(0), Arrays.<DocumentInfo>asList(new DocumentInfo("name", "date")), "nextStep",
                Integer.valueOf(0), Integer.valueOf(0), "message", "currStep"), result);
    }

    @Test
    void testFindEmailByUserId() {
        when(iUserDao.getPersonByUserId(anyInt()))
            .thenReturn(new Person(null, null, null, null, "email", null, null, null, null, null));

        String result = visaStatusServiceImpl.findEmailByUserId(Integer.valueOf(0));
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    void testUpdateInfo() {
        when(iUserDao.getEmployeeIdByUserId(anyInt())).thenReturn(Integer.valueOf(0));
        when(iUserDao.getPersonByUserId(anyInt()))
            .thenReturn(new Person(null, "firstName", "lastName", null, null, null, null, null, null, null));
        when(iEmployeeDao.getEmployeeById(anyInt())).thenReturn(new Employee());

        boolean result = visaStatusServiceImpl.updateInfo(new HashMap<String, Object>() {{put("String", "params");}});
        Assertions.assertEquals(true, result);
    }

    @Test
    void testUploadAndUpdate() throws IOException {
        when(iUserDao.getUserById(anyInt())).thenReturn(new User("username", "email", "password"));
        when(amazonS3FileService.upload(any(), anyString())).thenReturn("uploadResponse");

        Map<String, Object> result = visaStatusServiceImpl.uploadAndUpdate(Arrays.<MultipartFile>asList(null),
            new HashMap<String, Object>() {{put("String", "paramMap");}}, Integer.valueOf(0));
        Assertions
            .assertEquals(new HashMap<String, Object>() {{put("String", "replaceMeWithExpectedResult");}}, result);
    }

    @Test
    void testSetFullName() {
        String result = visaStatusServiceImpl.setFullName(null);
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    void testMessage() {
        String result = visaStatusServiceImpl.message("currentStep", Integer.valueOf(0));
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    void testSetDocumentInfo() {
        when(amazonS3FileService.printFilesInOneFolder(anyString())).thenReturn(Arrays.<String>asList("String"));

        List<DocumentInfo> result = visaStatusServiceImpl.setDocumentInfo(Integer.valueOf(0));
        Assertions.assertEquals(Arrays.<DocumentInfo>asList(new DocumentInfo("name", "date")), result);
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
