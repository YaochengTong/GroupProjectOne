package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.dao.IContactDao;
import com.app.groupprojectapplication.domain.Contact;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.service.spi.SessionFactoryServiceContributor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository()
@Transactional
public class ContactDaoImpl implements IContactDao {

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public void insertContact(Contact contact) {
        Session session = sessionFactory.getCurrentSession();
        session.save(contact);
    }

    @Override
    public List<Contact> getContactByPersonId(Integer person_id) {
        Session session = sessionFactory.getCurrentSession();
        List<Contact> contactList = session.createQuery("FROM Contact c WHERE c.person.id = " + person_id).getResultList();
        return contactList;

    }

    @Override
    public List<Contact> getEmergencyByPersonId(Integer person_id) {
        Session session = sessionFactory.getCurrentSession();
        List<Contact> contactList = session.createQuery("FROM Contact c WHERE (c.person.id = " + person_id + ") AND ( c.isEmergency = 1)").getResultList();
        return contactList;
    }
}
