package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.dao.IApplicationWorkFlowDao;
import com.app.groupprojectapplication.domain.ApplicationWorkflow;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.sql.Timestamp;
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
    public List<ApplicationWorkflow> getApplicationWorkFlowByUserIdAndApplicationType(Integer userId, String applicationType) {
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM ApplicationWorkflow WHERE user.id = :userId AND type = :type";
        List<ApplicationWorkflow> applicationWorkflowList = session.createQuery(query).setParameter("userId", userId).setParameter("type", applicationType).getResultList();
        session.setFlushMode(FlushMode.MANUAL);
        applicationWorkflowList.sort((a,b) -> { return b.getCreateDate().compareTo(a.getCreateDate()); });
        return applicationWorkflowList;
    }

    @Override
    public List<ApplicationWorkflow> getApplicationWorkFlowByApplicationType(String applicationType) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select awf FROM " +
                "ApplicationWorkflow awf " +
                "join fetch awf.user as user " +
                " join fetch user.person as person" +
                //" join fetch person.contacts " +
                " WHERE awf.type = :type";
        Query<ApplicationWorkflow> query = session.createQuery(hql);
        query.setParameter("type", applicationType);
        List<ApplicationWorkflow> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public boolean updateApplicationWorkFlowById(Integer id, ApplicationWorkflow workflow) {
        Session session = sessionFactory.getCurrentSession();
        ApplicationWorkflow currentWorkflow = session.get(ApplicationWorkflow.class, id);
        currentWorkflow.setStatus(workflow.getStatus());
        currentWorkflow.setComments(workflow.getComments());
        currentWorkflow.setModificationDate(new Timestamp(System.currentTimeMillis()));
        return true;
    }


    @Override
    public List<ApplicationWorkflow>  getApplicationWorkFlowByUserId(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select awf FROM " +
                "ApplicationWorkflow awf " +
                "join fetch awf.user as user " +
                " join fetch user.person as person" +
                " WHERE awf.user.id = :id and awf.type= 'Onboarding' " ;
        Query<ApplicationWorkflow> query = session.createQuery(hql);
        query.setParameter("id", id);
        List<ApplicationWorkflow> list = query.getResultList();
        return list;
    }
}