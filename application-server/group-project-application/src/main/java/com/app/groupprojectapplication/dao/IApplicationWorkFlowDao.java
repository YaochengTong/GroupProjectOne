package com.app.groupprojectapplication.dao;

import com.app.groupprojectapplication.dao.impl.ApplicationWorkflowDaoImpl;
import com.app.groupprojectapplication.domain.ApplicationWorkflow;

import java.util.List;

public interface IApplicationWorkFlowDao {
    ApplicationWorkflow getApplicationWorkFlowById(Integer id);
    List<ApplicationWorkflow> getApplicationWorkFlowByStatus(String status);
    void insertApplicationWorkFlow(ApplicationWorkflow applicationWorkflow);

}
