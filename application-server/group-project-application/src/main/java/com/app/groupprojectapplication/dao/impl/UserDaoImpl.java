package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.dao.IUserDao;
import com.app.groupprojectapplication.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

/**
 * Shida Sheng
 */
@Repository
public class UserDaoImpl implements IUserDao {

    User user;

    @Autowired
    protected SessionFactory sessionFactory;


    @Override
    public User getUserById(Integer id) {
        Session session = sessionFactory.openSession();
        user = session.get(User.class, id);
        session.close();
        return user;
    }


    @Override
    public void insertUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        session.save(user);
        ts.commit();
        session.close();

    }

    @Override
    public void deleteUserById(Integer id) {
        Session session = sessionFactory.openSession();
        User newUser = new User();
        newUser.setId(id);

        Transaction ts = session.beginTransaction();
        session.delete(newUser);
        ts.commit();
        session.close();
    }

    @Override
    public void updateUser(Integer id) {
        Session session = sessionFactory.openSession();
        User newUser = session.get(User.class, id);
        newUser.setUsername("updated");

        Transaction ts = session.beginTransaction();
        ts.commit();
        session.close();
    }
}
