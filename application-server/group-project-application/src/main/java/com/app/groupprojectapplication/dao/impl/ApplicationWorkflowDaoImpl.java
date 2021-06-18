package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.dao.IApplicationWorkFlowDao;
import com.app.groupprojectapplication.domain.ApplicationWorkflow;
import com.app.groupprojectapplication.domain.RegistrationToken;
import com.app.groupprojectapplication.domain.User;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class ApplicationWorkflowDaoImpl implements IApplicationWorkFlowDao {


    ApplicationWorkflow applicationWorkflow;

    @Autowired
    protected SessionFactory sessionFactory;


    @Override
    public ApplicationWorkflow getApplicationWorkFlowById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        applicationWorkflow = session.get(ApplicationWorkflow.class, id);
        return applicationWorkflow;
    }

    @Override
    public List<ApplicationWorkflow> getApplicationWorkFlowByStatus(String status) {
        Session session = sessionFactory.getCurrentSession();
        List<ApplicationWorkflow> applicationWorkflowList = session.createQuery("From ApplicationWorkflow aw WHERE aw.status = " + status).getResultList();
        return applicationWorkflowList;
    }

    @Override
    public void insertApplicationWorkFlow(ApplicationWorkflow applicationWorkflow) {
        Session session = sessionFactory.getCurrentSession();
        session.save(applicationWorkflow);
    }

    @Override
    public ApplicationWorkflow getApplicationWorkFlowByUserIdAndApplicationType(Integer userId, String applicationType) {
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM ApplicationWorkflow WHERE user.id = :userId AND type = :type";
        ApplicationWorkflow applicationWorkflow = (ApplicationWorkflow) session.createQuery(query).setParameter("userId", userId).setParameter("type", applicationType).getResultList().get(0);
        session.setFlushMode(FlushMode.MANUAL);
        return applicationWorkflow;
    }
}