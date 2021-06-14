package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.dao.IPersonalDocumentDao;
import com.app.groupprojectapplication.domain.Employee;
import com.app.groupprojectapplication.domain.PersonalDocument;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonalDocumentDaoImpl implements IPersonalDocumentDao {

    PersonalDocument pd;

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public PersonalDocument getPersonalDocumentById(Integer id) {
        Session session = sessionFactory.openSession();
        pd = session.get(PersonalDocument.class, id);
        session.close();
        return pd;
    }

    @Override
    public List<PersonalDocument> getPersonalDocumentsByEmployeeId(Integer employee_id) {
        Session session = sessionFactory.openSession();
        List<PersonalDocument> personalDocumentsList = session.createQuery("From PersonalDocument pd WHERE pd.employee.id = " + employee_id).getResultList();
        session.close();
        return personalDocumentsList;
    }

    @Override
    public List<PersonalDocument> getPersonalDocumentsByUserId(Integer id) {
        Session session = sessionFactory.openSession();
        List<PersonalDocument> personalDocumentsList = session.createQuery("From PersonalDocument pd WHERE pd.user.id = " + id).getResultList();
        session.close();
        return personalDocumentsList;
    }

    @Override
    public void insertPersonalDocument(PersonalDocument personalDocument) {
        Session session = sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        session.save(personalDocument);
        ts.commit();
        session.close();
    }
}
