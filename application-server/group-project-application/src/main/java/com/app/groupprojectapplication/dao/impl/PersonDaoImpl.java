package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.dao.IPersonDao;
import com.app.groupprojectapplication.domain.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class PersonDaoImpl implements IPersonDao {

    @Autowired
    protected SessionFactory sessionFactory;


    @Override
    public Person getPersonById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);
        return person;
    }

    @Override
    public void insertPerson(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.save(person);
    }

    @Override
    public void updatePerson(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(person);
    }


}


