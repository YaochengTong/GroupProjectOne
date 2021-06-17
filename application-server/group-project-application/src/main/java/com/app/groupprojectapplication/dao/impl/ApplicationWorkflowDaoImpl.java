package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.dao.IApplicationWorkFlowDao;
import com.app.groupprojectapplication.domain.ApplicationWorkflow;
import com.app.groupprojectapplication.domain.RegistrationToken;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ApplicationWorkflowDaoImpl implements IApplicationWorkFlowDao {


    ApplicationWorkflow applicationWorkflow;

    @Autowired
    protected SessionFactory sessionFactory;


    @Override
    public ApplicationWorkflow getApplicationWorkFlowById(Integer id) {
        Session session = sessionFactory.openSession();
        applicationWorkflow = session.get(ApplicationWorkflow.class, id);
        session.close();
        return applicationWorkflow;
    }

    @Override
    public List<ApplicationWorkflow> getApplicationWorkFlowByStatus(String status) {
        Session session = sessionFactory.openSession();
        List<ApplicationWorkflow> applicationWorkflowList = session.createQuery("From ApplicationWorkflow aw WHERE aw.status = " + status).getResultList();
        session.close();
        return applicationWorkflowList;
    }

    @Override
    public void insertApplicationWorkFlow(ApplicationWorkflow applicationWorkflow) {
        Session session = sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        session.save(applicationWorkflow);
        ts.commit();
        session.close();
    }

    @Override
    public ApplicationWorkflow getApplicationWorkFlowByUserIdAndApplicationType(Integer userId, String applicationType) {
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM ApplicationWorkflow WHERE user.id = :userId AND type = :type";
        ApplicationWorkflow applicationWorkflow = (ApplicationWorkflow) session.createQuery(query).setParameter("userId", userId).setParameter("type", applicationType).getResultList().get(0);
        return applicationWorkflow;
    }
}
