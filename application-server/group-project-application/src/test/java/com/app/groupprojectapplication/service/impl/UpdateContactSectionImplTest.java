package com.app.groupprojectapplication.service.impl;

import com.app.groupprojectapplication.dao.IEmployeeDao;
import com.app.groupprojectapplication.dao.IPersonDao;
import com.app.groupprojectapplication.dao.IUserDao;
import com.app.groupprojectapplication.domain.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class UpdateContactSectionImplTest {

    @Mock
    IEmployeeDao iEmployeeDao;
    @Mock
    IPersonDao iPersonDao;
    @Mock
    IUserDao iUserDao;
    @InjectMocks
    UpdateContactSectionImpl updateContactSectionImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testUpdateContactInfo() {
        when(iUserDao.getPersonByUserId(anyInt())).thenReturn(
            new Person(null, null, null, null, "email", "primaryPhone", "alternatePhone", null, null, null));

        boolean result = updateContactSectionImpl
            .updateContactInfo("cellPhone", "personalEmail", "workEmail", "workPhone", Integer.valueOf(0));
        Assertions.assertEquals(true, result);
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
