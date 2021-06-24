package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.domain.ApplicationWorkflow;
import java.util.Arrays;
import java.util.List;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class ApplicationWorkflowDaoImplTest {

    @Mock
    ApplicationWorkflow applicationWorkflow;
    @Mock
    SessionFactory sessionFactory;
    @InjectMocks
    ApplicationWorkflowDaoImpl applicationWorkflowDaoImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetApplicationWorkFlowById() {
        ApplicationWorkflow result = applicationWorkflowDaoImpl.getApplicationWorkFlowById(11);
        Assertions.assertEquals(new ApplicationWorkflow(), result);
    }

    @Test
    void testGetApplicationWorkFlowByStatus() {
        List<ApplicationWorkflow> result = applicationWorkflowDaoImpl.getApplicationWorkFlowByStatus("status");
        Assertions.assertEquals(Arrays.<ApplicationWorkflow>asList(new ApplicationWorkflow()), result);
    }

    @Test
    void testInsertApplicationWorkFlow() {
        applicationWorkflowDaoImpl.insertApplicationWorkFlow(new ApplicationWorkflow());
    }

    @Test
    void testGetApplicationWorkFlowByUserIdAndApplicationType() {
        when(applicationWorkflow.getCreateDate()).thenReturn(null);
        when(applicationWorkflow.toString()).thenReturn("toStringResponse");

        List<ApplicationWorkflow> result = applicationWorkflowDaoImpl
            .getApplicationWorkFlowByUserIdAndApplicationType(Integer.valueOf(0), "applicationType");
        Assertions.assertEquals(Arrays.<ApplicationWorkflow>asList(new ApplicationWorkflow()), result);
    }

    @Test
    void testGetApplicationWorkFlowByApplicationType() {
        List<ApplicationWorkflow> result = applicationWorkflowDaoImpl
            .getApplicationWorkFlowByApplicationType("applicationType");
        Assertions.assertEquals(Arrays.<ApplicationWorkflow>asList(new ApplicationWorkflow()), result);
    }

    @Test
    void testUpdateApplicationWorkFlowById() {
        when(applicationWorkflow.getStatus()).thenReturn("getStatusResponse");
        when(applicationWorkflow.getComments()).thenReturn("getCommentsResponse");

        boolean result = applicationWorkflowDaoImpl
            .updateApplicationWorkFlowById(Integer.valueOf(0), new ApplicationWorkflow());
        Assertions.assertEquals(true, result);
    }

    @Test
    void testGetApplicationWorkFlowByUserId() {
        List<ApplicationWorkflow> result = applicationWorkflowDaoImpl
            .getApplicationWorkFlowByUserId(Integer.valueOf(0));
        Assertions.assertEquals(Arrays.<ApplicationWorkflow>asList(new ApplicationWorkflow()), result);
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
