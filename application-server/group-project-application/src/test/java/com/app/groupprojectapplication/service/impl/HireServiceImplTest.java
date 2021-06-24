package com.app.groupprojectapplication.service.impl;

import com.app.groupprojectapplication.dao.IAddressDao;
import com.app.groupprojectapplication.dao.IApplicationWorkFlowDao;
import com.app.groupprojectapplication.dao.IContactDao;
import com.app.groupprojectapplication.dao.IEmployeeDao;
import com.app.groupprojectapplication.dao.IPersonDao;
import com.app.groupprojectapplication.dao.IPersonalDocumentDao;
import com.app.groupprojectapplication.dao.IRegistrationTokenDao;
import com.app.groupprojectapplication.dao.IUserDao;
import com.app.groupprojectapplication.dao.IUserRoleDao;
import com.app.groupprojectapplication.dao.IVisaStatusDao;
import com.app.groupprojectapplication.domain.Address;
import com.app.groupprojectapplication.domain.ApplicationWorkflow;
import com.app.groupprojectapplication.domain.Employee;
import com.app.groupprojectapplication.domain.Person;
import com.app.groupprojectapplication.domain.PersonalDocument;
import com.app.groupprojectapplication.domain.User;
import com.app.groupprojectapplication.domain.UserRole;
import com.app.groupprojectapplication.email.EmailService;
import com.app.groupprojectapplication.file.AmazonS3FileService;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.Mockito.*;

class HireServiceImplTest {

    @Mock
    IUserDao iUserDao;
    @Mock
    IEmployeeDao iEmployeeDao;
    @Mock
    IPersonalDocumentDao iPersonalDocumentDao;
    @Mock
    IPersonDao iPersonDao;
    @Mock
    IContactDao iContactDao;
    @Mock
    IVisaStatusDao iVisaStatusDao;
    @Mock
    IRegistrationTokenDao iRegistrationTokenDao;
    @Mock
    IApplicationWorkFlowDao iApplicationWorkFlowDao;
    @Mock
    IAddressDao iAddressDao;
    @Mock
    IUserRoleDao iUserRoleDao;
    @Mock
    EmailService emailService;
    @Mock
    AmazonS3FileService amazonS3FileService;
    @InjectMocks
    HireServiceImpl hireServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGenerateAToken() {
        when(iUserDao.getEmployeeIdByUserId(anyInt())).thenReturn(Integer.valueOf(0));
        when(iEmployeeDao.getEmployeeById(anyInt())).thenReturn(new Employee());

        boolean result = hireServiceImpl.generateAToken("email", Integer.valueOf(0));
        Assertions.assertEquals(true, result);
    }

    @Test
    void testOnboardSubmission() throws IOException {
        when(iUserDao.getUserById(anyInt())).thenReturn(new User("username", "email", "password"));
        when(iUserDao.getEmployeeIdByUserId(anyInt())).thenReturn(Integer.valueOf(0));
        when(iEmployeeDao.getEmployeeById(anyInt())).thenReturn(new Employee());
        when(iEmployeeDao.insertEmployee(any())).thenReturn(Integer.valueOf(0));
        when(iPersonDao.insertPerson(any())).thenReturn(Integer.valueOf(0));
        when(iVisaStatusDao.insertVisa(any())).thenReturn(Integer.valueOf(0));
        when(amazonS3FileService.upload(any(), anyString())).thenReturn("uploadResponse");

        Map<String, Object> result = hireServiceImpl.onboardSubmission(Arrays.<MultipartFile>asList(null),
            new HashMap<String, Object>() {{put("String", "paramMap");}});
        Assertions
            .assertEquals(new HashMap<String, Object>() {{put("String", "replaceMeWithExpectedResult");}}, result);
    }

    @Test
    void testGetOnboardApplications() {
        when(iUserDao.getEmployeeIdByUserId(anyInt())).thenReturn(Integer.valueOf(0));
        when(iEmployeeDao.getEmployeeById(anyInt())).thenReturn(new Employee());
        when(iPersonalDocumentDao.getPersonalDocumentsByUserId(anyInt()))
            .thenReturn(Arrays.<PersonalDocument>asList(new PersonalDocument()));
        when(iPersonDao.getPersonById(anyInt())).thenReturn(
            new Person(Integer.valueOf(0), "firstName", "lastName", "middleName", "email", "primaryPhone",
                "alternatePhone", "gender", "ssn", null));
        when(iVisaStatusDao.getVisaTypeByEmployeeId(anyInt())).thenReturn("getVisaTypeByEmployeeIdResponse");
        when(iApplicationWorkFlowDao.getApplicationWorkFlowByApplicationType(anyString()))
            .thenReturn(Arrays.<ApplicationWorkflow>asList(new ApplicationWorkflow()));
        when(iApplicationWorkFlowDao.getApplicationWorkFlowByUserId(anyInt()))
            .thenReturn(Arrays.<ApplicationWorkflow>asList(new ApplicationWorkflow()));
        when(iAddressDao.getAddressByPersonId(anyInt())).thenReturn(Arrays
            .<Address>asList(new Address("addressLine1", "addressLine2", "city", "zipCode", "stateName", "stateAbbr")));

        Map<String, Object> result = hireServiceImpl
            .getOnboardApplications(new HashMap<String, Object>() {{put("String", "paramMap");}});
        Assertions
            .assertEquals(new HashMap<String, Object>() {{put("String", "replaceMeWithExpectedResult");}}, result);
    }

    @Test
    void testAuditApplications() {
        when(iUserDao.getUserById(anyInt())).thenReturn(new User("username", "email", "password"));
        when(iApplicationWorkFlowDao.updateApplicationWorkFlowById(anyInt(), any())).thenReturn(true);
        when(iUserRoleDao.getUserRoleByUserId(anyInt())).thenReturn(Arrays.<UserRole>asList(new UserRole()));
        when(iUserRoleDao.updateUserRole(any())).thenReturn(true);

        Map<String, Object> result = hireServiceImpl
            .auditApplications(new HashMap<String, Object>() {{put("String", "paramMap");}});
        Assertions
            .assertEquals(new HashMap<String, Object>() {{put("String", "replaceMeWithExpectedResult");}}, result);
    }

    @Test
    void testOnboardReSubmission() {
        Map<String, Object> result = hireServiceImpl.onboardReSubmission(Arrays.<MultipartFile>asList(null),
            new HashMap<String, Object>() {{put("String", "paramMap");}});
        Assertions
            .assertEquals(new HashMap<String, Object>() {{put("String", "replaceMeWithExpectedResult");}}, result);
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
