package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.dao.IContactDao;
import com.app.groupprojectapplication.domain.Contact;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository()
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
        List<Contact> contactList = session.createQuery("FROM Contact c WHERE c.person.id = " + person_id)
            .getResultList();
        return contactList;
    }

    @Override
    public List<Contact> getEmergencyByPersonId(Integer person_id) {
        Session session = sessionFactory.getCurrentSession();
        List<Contact> contactList = session
            .createQuery("FROM Contact c WHERE (c.person.id = " + person_id + ") AND ( c.isEmergency = 1)")
            .getResultList();
        return contactList;
    }

    @Override
    public int getPersonIdByContactId(Integer contactId) {
        Session session = sessionFactory.getCurrentSession();
        List<Integer> resultList = session
            .createNativeQuery("SELECT person_id FROM contact JOIN person WHERE contact.person_id = person.id")
            .getResultList();

        return resultList.get(0);
    }

}
