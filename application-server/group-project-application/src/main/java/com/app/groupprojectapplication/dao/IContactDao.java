package com.app.groupprojectapplication.dao;

import com.app.groupprojectapplication.domain.Contact;

import java.util.List;

public interface IContactDao {
    void insertContact(Contact contact);
    List<Contact> getContactByPersonId(Integer person_id);
    List<Contact> getEmergencyByPersonId(Integer person_id);

}
