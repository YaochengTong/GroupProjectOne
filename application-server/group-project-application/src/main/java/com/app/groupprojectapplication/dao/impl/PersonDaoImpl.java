package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.dao.IPersonDao;
import com.app.groupprojectapplication.domain.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Repository
@Transactional
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

    @Override
    public Integer getAge(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        String query = "SELECT FLOOR(datediff(current_date(), date(p.dob))/365) AS DateDiff FROM hr_db.person p WHERE p.id = " + id;
        Integer age = ((BigInteger)session.createNativeQuery(query).list().get(0)).intValue();
        return age;
    }


}


