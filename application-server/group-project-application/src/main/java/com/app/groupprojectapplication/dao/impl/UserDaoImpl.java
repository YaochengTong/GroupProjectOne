package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.dao.IUserDao;
import com.app.groupprojectapplication.domain.Person;
import com.app.groupprojectapplication.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Shida Sheng
 */
@Repository
public class  UserDaoImpl implements IUserDao {

    User user;

    @Autowired
    protected SessionFactory sessionFactory;


    @Override
    public User getUserById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        user = session.get(User.class, id);
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
    public void updateUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        User currentUser = session.get(User.class, user.getId());
        currentUser.setRoles(user.getRoles());
        currentUser.setModificationDate(new Timestamp(System.currentTimeMillis()));
        currentUser.setPerson(user.getPerson());
        ts.commit();
        session.close();
    }

    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        List<User> users = session.createQuery("FROM User").getResultList();
        return users;
    }

    @Override
    public Integer getEmployeeIdByUserId(Integer userId){
        Session session = sessionFactory.openSession();
        String hql = "select u.person.id FROM User u where u.id=:user_id";
        Query query = session.createQuery(hql);
        query.setParameter("user_id", userId);
        if(query.getResultList().size() != 1)
            return null;
        Integer person_id = (Integer) query.getResultList().get(0);
        String hql2 = "select e.id FROM Employee e where e.person.id=:person_id";
        query = session.createQuery(hql2);
        query.setParameter("person_id", person_id);
        if(query.getResultList().size() != 1)
            return null;
        return (Integer) query.getResultList().get(0);
    }
}
