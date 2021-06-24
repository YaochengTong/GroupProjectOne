package com.app.groupprojectapplication.service.impl;

import com.app.groupprojectapplication.dao.IEmployeeDao;
import com.app.groupprojectapplication.dao.IUserDao;
import com.app.groupprojectapplication.dao.IVisaStatusDao;
import com.app.groupprojectapplication.domain.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class UpdateEmploymentSectionServiceImplTest {

    @Mock
    IUserDao iUserDao;
    @Mock
    IEmployeeDao iEmployeeDao;
    @Mock
    IVisaStatusDao iVisaStatusDao;
    @InjectMocks
    UpdateEmploymentSectionServiceImpl updateEmploymentSectionServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testUpdateEmployment() {
        when(iUserDao.getEmployeeIdByUserId(anyInt())).thenReturn(Integer.valueOf(0));
        when(iEmployeeDao.getEmployeeById(anyInt())).thenReturn(new Employee());

        boolean result = updateEmploymentSectionServiceImpl
            .updateEmployment("title", "workAuthorization", "authorizationStartDate", "authorizationEndDate",
                "employmentStartDate", "employmentEndDate", Integer.valueOf(0));
        Assertions.assertEquals(true, result);
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
