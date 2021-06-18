package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.dao.IPersonDao;
import com.app.groupprojectapplication.domain.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;


@Repository
public class PersonDaoImpl implements IPersonDao {

    @Autowired
    protected SessionFactory sessionFactory;


    @Override
    public Person getPersonById(Integer id) {
        Session session = sessionFactory.openSession();
        Person person = session.get(Person.class, id);
        session.close();
        return person;
    }

    @Override
    public void insertPerson(Person person) {
        Session session = sessionFactory.openSession();
        session.save(person);
        session.close();
    }

    @Override
    public void updatePerson(Person person) {
        Session session = sessionFactory.openSession();
        session.saveOrUpdate(person);
        session.close();
    }

    @Override
    public Integer getAge(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        String query = "SELECT FLOOR(datediff(current_date(), date(p.dob))/365) AS DateDiff FROM hr_db.person p WHERE p.id = " + id;
        Integer age = ((BigInteger)session.createNativeQuery(query).list().get(0)).intValue();
        return age;
    }


}


