package com.app.groupprojectapplication.service.impl;

import com.app.groupprojectapplication.dao.IContactDao;
import com.app.groupprojectapplication.dao.IEmployeeDao;
import com.app.groupprojectapplication.dao.IPersonDao;
import com.app.groupprojectapplication.dao.IUserDao;
import com.app.groupprojectapplication.domain.Contact;
import com.app.groupprojectapplication.domain.Employee;
import com.app.groupprojectapplication.domain.Person;
import com.app.groupprojectapplication.domain.profile.AddressSection;
import com.app.groupprojectapplication.domain.profile.ContactInfoSection;
import com.app.groupprojectapplication.domain.profile.DocumentSection;
import com.app.groupprojectapplication.domain.profile.EmergencyContact;
import com.app.groupprojectapplication.domain.profile.EmergencyContactList;
import com.app.groupprojectapplication.domain.profile.EmploymentSection;
import com.app.groupprojectapplication.domain.profile.NameSection;
import com.app.groupprojectapplication.domain.profile.Profile;
import com.app.groupprojectapplication.domain.profile.Summary;
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

class ProfileServiceImplTest {

    @Mock
    Profile profile;
    @Mock
    IEmployeeDao iEmployeeDao;
    @Mock
    IContactDao iContactDao;
    @Mock
    IPersonDao iPersonDao;
    @Mock
    IUserDao iUserDao;
    @Mock
    AmazonS3FileService amazonS3FileService;
    @InjectMocks
    ProfileServiceImpl profileServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetProfile() {
        when(iEmployeeDao.getEmployee()).thenReturn(Arrays.<Employee>asList(new Employee()));
        when(iEmployeeDao.getUserIdByEmployeeId(anyInt())).thenReturn(Integer.valueOf(0));
        when(iContactDao.getEmergencyByPersonId(anyInt()))
            .thenReturn(Arrays.<Contact>asList(new Contact("relationship", "title", (byte) 0, (byte) 0, (byte) 0)));
        when(iPersonDao.getPersonById(anyInt())).thenReturn(
            new Person(Integer.valueOf(0), "firstName", "lastName", "middleName", "email", "primaryPhone",
                "alternatePhone", null, "ssn", null));
        when(iPersonDao.getAge(anyInt())).thenReturn(Integer.valueOf(0));
        when(amazonS3FileService.printFilesInOneFolder(anyString())).thenReturn(Arrays.<String>asList("String"));

        List<Profile> result = profileServiceImpl.getProfile();
        Assertions.assertEquals(Arrays.<Profile>asList(new Profile(Integer.valueOf(0),
            new NameSection("fullName", "preferredName", "Avatar", null, Integer.valueOf(0), "SSN"),
            new AddressSection(new HashMap<String, String>() {{put("String", "String");}},
                               new HashMap<String, String>() {{put("String", "String");}}),
            new ContactInfoSection("personalEmail", "workEmail", "ceilphone", "workPhone"),
            new EmploymentSection("workAuthorization", null, null, null, null, "title"), new EmergencyContactList(
            new EmergencyContact("fullName", "phone",
                new AddressSection(new HashMap<String, String>() {{put("String", "String");}},
                                   new HashMap<String, String>() {{put("String", "String");}})),
            new EmergencyContact("fullName", "phone",
                new AddressSection(new HashMap<String, String>() {{put("String", "String");}},
                                   new HashMap<String, String>() {{put("String", "String");}}))),
            Arrays.<DocumentSection>asList(new DocumentSection("name", "path")))), result);
    }

    @Test
    void testGetProfileByEmployeeId() {
        when(iEmployeeDao.getEmployeeById(anyInt())).thenReturn(new Employee());
        when(iEmployeeDao.getUserIdByEmployeeId(anyInt())).thenReturn(Integer.valueOf(0));
        when(iContactDao.getEmergencyByPersonId(anyInt()))
            .thenReturn(Arrays.<Contact>asList(new Contact("relationship", "title", (byte) 0, (byte) 0, (byte) 0)));
        when(iPersonDao.getPersonById(anyInt())).thenReturn(
            new Person(Integer.valueOf(0), "firstName", "lastName", "middleName", "email", "primaryPhone",
                "alternatePhone", null, "ssn", null));
        when(iPersonDao.getAge(anyInt())).thenReturn(Integer.valueOf(0));
        when(iUserDao.getEmployeeIdByUserId(anyInt())).thenReturn(Integer.valueOf(0));
        when(amazonS3FileService.printFilesInOneFolder(anyString())).thenReturn(Arrays.<String>asList("String"));

        Profile result = profileServiceImpl.getProfileByEmployeeId(Integer.valueOf(0));
        Assertions.assertEquals(new Profile(Integer.valueOf(0),
            new NameSection("fullName", "preferredName", "Avatar", null, Integer.valueOf(0), "SSN"),
            new AddressSection(new HashMap<String, String>() {{put("String", "String");}},
                               new HashMap<String, String>() {{put("String", "String");}}),
            new ContactInfoSection("personalEmail", "workEmail", "ceilphone", "workPhone"),
            new EmploymentSection("workAuthorization", null, null, null, null, "title"), new EmergencyContactList(
            new EmergencyContact("fullName", "phone",
                new AddressSection(new HashMap<String, String>() {{put("String", "String");}},
                                   new HashMap<String, String>() {{put("String", "String");}})),
            new EmergencyContact("fullName", "phone",
                new AddressSection(new HashMap<String, String>() {{put("String", "String");}},
                                   new HashMap<String, String>() {{put("String", "String");}}))),
            Arrays.<DocumentSection>asList(new DocumentSection("name", "path"))), result);
    }

    @Test
    void testSetNameSection() {
        when(iPersonDao.getAge(anyInt())).thenReturn(Integer.valueOf(0));

        NameSection result = profileServiceImpl.setNameSection(new Employee(), null);
        Assertions.assertEquals(new NameSection("fullName", "preferredName", "Avatar", null, Integer.valueOf(0), "SSN"),
            result);
    }

    @Test
    void testSetAddressSection() {
        AddressSection result = profileServiceImpl.setAddressSection(null);
        Assertions.assertEquals(new AddressSection(new HashMap<String, String>() {{put("String", "String");}},
                                                   new HashMap<String, String>() {{put("String", "String");}}), result);
    }

    @Test
    void testGetFullName() {
        String result = profileServiceImpl.getFullName(null);
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    void testGetSummary() {
        when(iEmployeeDao.getEmployee()).thenReturn(Arrays.<Employee>asList(new Employee()));

        List<Summary> result = profileServiceImpl.getSummary();
        Assertions.assertEquals(Arrays
                .<Summary>asList(new Summary(Integer.valueOf(0), Integer.valueOf(0), "fullName", "SSN", null,
                    "visaType")),
            result);
    }

    @Test
    void testUploadAvatar() throws IOException {
        when(iUserDao.getEmployeeIdByUserId(anyInt())).thenReturn(Integer.valueOf(0));
        when(amazonS3FileService.upload(any(), anyString())).thenReturn("uploadResponse");

        Map<String, Object> result = profileServiceImpl.uploadAvatar(Arrays.<MultipartFile>asList(null),
            new HashMap<String, Object>() {{put("String", "paramMap");}});
        Assertions
            .assertEquals(new HashMap<String, Object>() {{put("String", "replaceMeWithExpectedResult");}}, result);
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
