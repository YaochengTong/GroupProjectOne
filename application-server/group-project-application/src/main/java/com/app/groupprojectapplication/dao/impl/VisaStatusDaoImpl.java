package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.dao.IVisaStatusDao;
import com.app.groupprojectapplication.domain.VisaStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VisaStatusDaoImpl implements IVisaStatusDao {

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public void insertVisa(VisaStatus visaStatus) {
        Session session = sessionFactory.getCurrentSession();
        session.save(visaStatus);
    }

    @Override
    public List<VisaStatus> getVisaByType(String visaType) {
        Session session = sessionFactory.getCurrentSession();
        List<VisaStatus> visaStatusList = session.createQuery("FROM VisaStatus v WHERE v.visaType = '" + visaType + "'").getResultList();
        return visaStatusList;
    }
}
