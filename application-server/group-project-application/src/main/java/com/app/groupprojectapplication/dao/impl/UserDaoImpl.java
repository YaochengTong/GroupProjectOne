package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.dao.IUserDao;
import com.app.groupprojectapplication.domain.House;
import com.app.groupprojectapplication.domain.Person;
import com.app.groupprojectapplication.domain.User;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Shida Sheng
 */
@Repository
@Transactional
public class UserDaoImpl implements IUserDao {

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    @Async("taskExecutor")
    @Transactional
    public CompletableFuture<User> getUserById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, id);
        //        Session session = sessionFactory.getCurrentSession();
        //        String hql = "select u FROM User u join fetch u.person where u.id=:user_id";
        //        Query query = session.createQuery(hql);
        //        query.setParameter("user_id", id);
        //        if(query.getResultList().size() != 1)
        //            return null;
        //        return (User) query.getResultList().get(0);
        return CompletableFuture.completedFuture(user);
    }

    @Override
    public void insertUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Override
    public void deleteUserById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        User newUser = new User();
        newUser.setId(id);
        session.delete(newUser);
    }

    @Override
    public void updateUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        List<User> users = session.createQuery("FROM User").getResultList();
        return users;
    }

    @Override
    @Async("taskExecutor")
    @Transactional
    public CompletableFuture<Integer> getEmployeeIdByUserId(Integer userId) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select u.person.id FROM User u where u.id=:user_id";
        Query query = session.createQuery(hql);
        query.setParameter("user_id", userId);
        if (query.getResultList().size() != 1) { return null; }
        Integer person_id = (Integer) query.getResultList().get(0);
        String hql2 = "select e.id FROM Employee e where e.person.id=:person_id";
        query = session.createQuery(hql2);
        query.setParameter("person_id", person_id);
        if (query.getResultList().size() != 1) { return null; }
        Integer result = (Integer) query.getResultList().get(0);
        return CompletableFuture.completedFuture(result);
    }

    @Override
    public Integer getPersonIdByUserId(Integer userId) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT u.person.id FROM User u WHERE u.id=:user_id";
        Query query = session.createQuery(hql);
        query.setParameter("user_id", userId);
        if (query.getResultList().size() != 1) {
            return null;
        }
        Integer person_id = (Integer) query.getResultList().get(0);

        return person_id;
    }

    @Override
    @Async("taskExecutor")
    @Transactional
    public CompletableFuture<Person> getPersonByUserId(Integer userId) {
        Session session = sessionFactory.getCurrentSession();
        String query = "SELECT u.person FROM User u WHERE u.id = " + userId;
        Person person = (Person) session.createQuery(query).getResultList().get(0);
        return CompletableFuture.completedFuture(person);
    }

    @Override
    public void addHouse(House h) {
        Session session = sessionFactory.getCurrentSession();
        session.save(h);
    }

}


