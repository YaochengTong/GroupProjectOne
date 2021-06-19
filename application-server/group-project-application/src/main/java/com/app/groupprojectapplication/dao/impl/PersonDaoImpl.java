package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.dao.IPersonDao;
import com.app.groupprojectapplication.domain.Person;
import java.math.BigInteger;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;

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
    public Integer insertPerson(Person person) {
        Session session = sessionFactory.getCurrentSession();
        Integer id = (Integer) session.save(person);
        return id;
    }

    @Override
    public void updatePerson(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(person);
    }

    @Override
    public Integer getAge(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        String query =
            "SELECT FLOOR(datediff(current_date(), date(p.dob))/365) AS DateDiff FROM hr_db.person p WHERE p.id = "
                + id;
        Integer age = ((BigInteger) session.createNativeQuery(query).list().get(0)).intValue();
        return age;
    }

    @Override
    public String getPhoneByPersonId(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        List<String> resultList = session.createQuery("SELECT primaryPhone FROM Person WHERE id =" + id)
            .getResultList();
        return resultList.get(0);
    }

    @Override
    public Person getPersonIdByFullName(String fullName) {
        String firstName = fullName.split(" ")[0];
        String lastName = fullName.split(" ")[1];

        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT p FROM Person p WHERE p.firstName =: first_name AND p.lastName =:last_name ";
        Query query = session.createQuery(hql);
        query.setParameter("first_name", firstName);
        query.setParameter("last_name", lastName);
        if(query.getResultList().size() != 1) {
            return null;
        }
        Person person = (Person) (query).getResultList().get(0);

        return person;
    }

}


