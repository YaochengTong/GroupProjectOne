package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.dao.IRegistrationTokenDao;
import com.app.groupprojectapplication.domain.RegistrationToken;
import com.app.groupprojectapplication.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class RegistrationTokenDaoImpl implements IRegistrationTokenDao {


    RegistrationToken registrationToken;

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public RegistrationToken getRegistrationTokenById(Integer id) {
        Session session = sessionFactory.openSession();
        registrationToken = session.get(RegistrationToken.class, id);
        session.close();
        return registrationToken;
    }

    @Override
    public void insertRegistrationToke(RegistrationToken registrationToken) {
        Session session = sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        session.save(registrationToken);
        ts.commit();
        session.close();
    }
}
