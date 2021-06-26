package com.app.groupprojectapplication.dao;

import com.app.groupprojectapplication.dao.impl.ApplicationWorkflowDaoImpl;
import com.app.groupprojectapplication.domain.ApplicationWorkflow;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IApplicationWorkFlowDao {
    ApplicationWorkflow getApplicationWorkFlowById(Integer id);
    List<ApplicationWorkflow> getApplicationWorkFlowByStatus(String status);
    void insertApplicationWorkFlow(ApplicationWorkflow applicationWorkflow);
    CompletableFuture<List<ApplicationWorkflow>> getApplicationWorkFlowByUserIdAndApplicationType(Integer employeeId, String applicationType);
    List<ApplicationWorkflow> getApplicationWorkFlowByApplicationType(String applicationType);
    boolean updateApplicationWorkFlowById(Integer id, ApplicationWorkflow workflow);
    List<ApplicationWorkflow>  getApplicationWorkFlowByUserId(Integer id);
}
