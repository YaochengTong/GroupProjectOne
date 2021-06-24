package com.app.groupprojectapplication.service.impl;

import com.app.groupprojectapplication.dao.IApplicationWorkFlowDao;
import com.app.groupprojectapplication.dao.IEmployeeDao;
import com.app.groupprojectapplication.dao.IUserDao;
import com.app.groupprojectapplication.dao.IVisaStatusDao;
import com.app.groupprojectapplication.domain.ApplicationWorkflow;
import com.app.groupprojectapplication.domain.Employee;
import com.app.groupprojectapplication.domain.statusTableElement.NameInfo;
import com.app.groupprojectapplication.domain.statusTableElement.StatusTableElement;
import com.app.groupprojectapplication.domain.statusTableElement.VisaInfo;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class StatusTableElementImplTest {

    @Mock
    StatusTableElement statusTableElement;
    @Mock
    IEmployeeDao iEmployeeDao;
    @Mock
    IUserDao iUserDao;
    @Mock
    IVisaStatusDao iVisaStatusDao;
    @Mock
    IApplicationWorkFlowDao iApplicationWorkFlowDao;
    @InjectMocks
    StatusTableElementImpl statusTableElementImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetStatus() {
        when(statusTableElement.getVisaInfo())
            .thenReturn(new VisaInfo("visaType", null, "nextStep", Integer.valueOf(0)));
        when(iEmployeeDao.getEmployeeById(anyInt())).thenReturn(new Employee());
        when(iUserDao.getEmployeeIdByUserId(anyInt())).thenReturn(Integer.valueOf(0));
        when(iVisaStatusDao.getVisaAuthorizationLeftDay(anyInt())).thenReturn(Integer.valueOf(0));
        when(iApplicationWorkFlowDao.getApplicationWorkFlowByUserIdAndApplicationType(anyInt(), anyString()))
            .thenReturn(Arrays.<ApplicationWorkflow>asList(new ApplicationWorkflow()));

        List<StatusTableElement> result = statusTableElementImpl.getStatus();
        Assertions.assertEquals(Arrays.<StatusTableElement>asList(new StatusTableElement(Integer.valueOf(0),
            new NameInfo("fullName", "firstName", "lastName", Integer.valueOf(0), "email", "title"),
            new VisaInfo("visaType", null, "nextStep", Integer.valueOf(0)), Integer.valueOf(0))), result);
    }

    @Test
    void testGetStatusByUserId() {
        when(iEmployeeDao.getEmployeeById(anyInt())).thenReturn(new Employee());
        when(iUserDao.getEmployeeIdByUserId(anyInt())).thenReturn(Integer.valueOf(0));
        when(iVisaStatusDao.getVisaAuthorizationLeftDay(anyInt())).thenReturn(Integer.valueOf(0));
        when(iApplicationWorkFlowDao.getApplicationWorkFlowByUserIdAndApplicationType(anyInt(), anyString()))
            .thenReturn(Arrays.<ApplicationWorkflow>asList(new ApplicationWorkflow()));

        StatusTableElement result = statusTableElementImpl.getStatusByUserId(Integer.valueOf(0));
        Assertions.assertEquals(new StatusTableElement(Integer.valueOf(0),
            new NameInfo("fullName", "firstName", "lastName", Integer.valueOf(0), "email", "title"),
            new VisaInfo("visaType", null, "nextStep", Integer.valueOf(0)), Integer.valueOf(0)), result);
    }

    @Test
    void testGetFullName() {
        String result = statusTableElementImpl.getFullName(null);
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    void testDetermineNextStep() {
        String result = statusTableElementImpl.determineNextStep("currentStep");
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    void testSetVisaInfo() {
        when(iVisaStatusDao.getVisaAuthorizationLeftDay(anyInt())).thenReturn(Integer.valueOf(0));
        when(iApplicationWorkFlowDao.getApplicationWorkFlowByUserIdAndApplicationType(anyInt(), anyString()))
            .thenReturn(Arrays.<ApplicationWorkflow>asList(new ApplicationWorkflow()));

        VisaInfo result = statusTableElementImpl.setVisaInfo(Integer.valueOf(0), new Employee());
        Assertions.assertEquals(new VisaInfo("visaType", null, "nextStep", Integer.valueOf(0)), result);
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
