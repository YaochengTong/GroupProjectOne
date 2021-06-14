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
    public void insertUser(Object[] info) {
        Session session = sessionFactory.openSession();
        User newUser = new User();
        newUser.setId((int)info[0]);
        newUser.setUsername((String)info[1]);
        newUser.setEmail((String)info[2]);
        newUser.setPassword((String)info[3]);

        Timestamp date1 = new Timestamp(102, 12, 24, 05, 12, 30, 00);
        newUser.setCreateDate(date1);
        Timestamp date2 = new Timestamp(120, 4, 24, 15,24,20,00);
        newUser.setModificationDate(date2);
        Transaction ts = session.beginTransaction();
        session.save(newUser);
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
