package com.app.groupprojectapplication.service.impl;

import com.app.groupprojectapplication.dao.IEmployeeDao;
import com.app.groupprojectapplication.dao.IPersonDao;
import com.app.groupprojectapplication.dao.IUserDao;
import com.app.groupprojectapplication.domain.Employee;
import com.app.groupprojectapplication.domain.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class UpdateNameSectionServiceImplTest {

    @Mock
    IPersonDao iPersonDao;
    @Mock
    IEmployeeDao iEmployeeDao;
    @Mock
    IUserDao iUserDao;
    @InjectMocks
    UpdateNameSectionServiceImpl updateNameSectionServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testUpdateFullName() {
        when(iUserDao.getPersonByUserId(anyInt()))
            .thenReturn(new Person(null, "firstName", "lastName", null, null, null, null, null, null, null));

        boolean result = updateNameSectionServiceImpl.updateFullName("fullName", Integer.valueOf(0));
        Assertions.assertEquals(true, result);
    }

    @Test
    void testUpdateAge() {
        when(iUserDao.getPersonByUserId(anyInt()))
            .thenReturn(new Person(null, null, null, null, null, null, null, null, null, null));

        boolean result = updateNameSectionServiceImpl.updateAge(Integer.valueOf(0), Integer.valueOf(0));
        Assertions.assertEquals(true, result);
    }

    @Test
    void testUpdateSSN() {
        when(iUserDao.getPersonByUserId(anyInt()))
            .thenReturn(new Person(null, null, null, null, null, null, null, null, "ssn", null));

        boolean result = updateNameSectionServiceImpl.updateSSN(Integer.valueOf(0), Integer.valueOf(0));
        Assertions.assertEquals(true, result);
    }

    @Test
    void testUpdateAvatar() {
        when(iEmployeeDao.getEmployeeById(anyInt())).thenReturn(new Employee());
        when(iUserDao.getEmployeeIdByUserId(anyInt())).thenReturn(Integer.valueOf(0));

        boolean result = updateNameSectionServiceImpl.updateAvatar("avatar", Integer.valueOf(0));
        Assertions.assertEquals(true, result);
    }

    @Test
    void testUpdateDOB() {
        when(iUserDao.getPersonByUserId(anyInt())).thenReturn(
            new Person(Integer.valueOf(0), "firstName", "lastName", "middleName", "email", "primaryPhone",
                "alternatePhone", "gender", "ssn", null));

        boolean result = updateNameSectionServiceImpl.updateDOB("DOB", Integer.valueOf(0));
        Assertions.assertEquals(true, result);
    }

    @Test
    void testUpdatePreferredName() {
        boolean result = updateNameSectionServiceImpl.updatePreferredName("preferredName", Integer.valueOf(0));
        Assertions.assertEquals(true, result);
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
