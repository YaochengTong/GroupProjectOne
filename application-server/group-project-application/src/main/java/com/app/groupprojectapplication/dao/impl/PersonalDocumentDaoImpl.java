package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.dao.IPersonalDocumentDao;
import com.app.groupprojectapplication.domain.Employee;
import com.app.groupprojectapplication.domain.PersonalDocument;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class PersonalDocumentDaoImpl implements IPersonalDocumentDao {

    PersonalDocument pd;

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public PersonalDocument getPersonalDocumentById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        pd = session.get(PersonalDocument.class, id);
        return pd;
    }

    @Override
    public List<PersonalDocument> getPersonalDocumentsByEmployeeId(Integer employee_id) {
        Session session = sessionFactory.getCurrentSession();
        List<PersonalDocument> personalDocumentsList = session.createQuery("From PersonalDocument pd WHERE pd.employee.id = " + employee_id).getResultList();
        return personalDocumentsList;
    }

    @Override
    public List<PersonalDocument> getPersonalDocumentsByUserId(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        List<PersonalDocument> personalDocumentsList = session.createQuery("From PersonalDocument pd WHERE pd.user.id = " + id).getResultList();
        return personalDocumentsList;
    }

    @Override
    public void insertPersonalDocument(PersonalDocument personalDocument) {
        Session session = sessionFactory.getCurrentSession();
        session.save(personalDocument);
    }
}
