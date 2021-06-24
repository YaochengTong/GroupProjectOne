package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.domain.ApplicationWorkflow;
import java.util.Arrays;
import java.util.List;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class ApplicationWorkflowDaoImplTest2 {

    @Mock
    ApplicationWorkflow applicationWorkflow;
    @Mock
    SessionFactory sessionFactory;
    @InjectMocks
    ApplicationWorkflowDaoImpl applicationWorkflowDaoImpl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetApplicationWorkFlowById() throws Exception {
        ApplicationWorkflow result = applicationWorkflowDaoImpl.getApplicationWorkFlowById(Integer.valueOf(0));
        Assert.assertEquals(new ApplicationWorkflow(), result);
    }

    @Test
    public void testGetApplicationWorkFlowByStatus() throws Exception {
        List<ApplicationWorkflow> result = applicationWorkflowDaoImpl.getApplicationWorkFlowByStatus("status");
        Assert.assertEquals(Arrays.<ApplicationWorkflow>asList(new ApplicationWorkflow()), result);
    }

    @Test
    public void testInsertApplicationWorkFlow() throws Exception {
        applicationWorkflowDaoImpl.insertApplicationWorkFlow(new ApplicationWorkflow());
    }

    @Test
    public void testGetApplicationWorkFlowByUserIdAndApplicationType() throws Exception {
        when(applicationWorkflow.getCreateDate()).thenReturn(null);
        when(applicationWorkflow.toString()).thenReturn("toStringResponse");

        List<ApplicationWorkflow> result = applicationWorkflowDaoImpl
            .getApplicationWorkFlowByUserIdAndApplicationType(Integer.valueOf(0), "applicationType");
        Assert.assertEquals(Arrays.<ApplicationWorkflow>asList(new ApplicationWorkflow()), result);
    }

    @Test
    public void testGetApplicationWorkFlowByApplicationType() throws Exception {
        List<ApplicationWorkflow> result = applicationWorkflowDaoImpl
            .getApplicationWorkFlowByApplicationType("applicationType");
        Assert.assertEquals(Arrays.<ApplicationWorkflow>asList(new ApplicationWorkflow()), result);
    }

    @Test
    public void testUpdateApplicationWorkFlowById() throws Exception {
        when(applicationWorkflow.getStatus()).thenReturn("getStatusResponse");
        when(applicationWorkflow.getComments()).thenReturn("getCommentsResponse");

        boolean result = applicationWorkflowDaoImpl
            .updateApplicationWorkFlowById(Integer.valueOf(0), new ApplicationWorkflow());
        Assert.assertEquals(true, result);
    }

    @Test
    public void testGetApplicationWorkFlowByUserId() throws Exception {
        List<ApplicationWorkflow> result = applicationWorkflowDaoImpl
            .getApplicationWorkFlowByUserId(Integer.valueOf(0));
        Assert.assertEquals(Arrays.<ApplicationWorkflow>asList(new ApplicationWorkflow()), result);
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
