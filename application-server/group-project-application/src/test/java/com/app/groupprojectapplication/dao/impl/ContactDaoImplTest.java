package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.domain.Contact;
import java.util.Arrays;
import java.util.List;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class ContactDaoImplTest {

    @Mock
    SessionFactory sessionFactory;
    @InjectMocks
    ContactDaoImpl contactDaoImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetContactById() {
        Contact result = contactDaoImpl.getContactById(Integer.valueOf(0));
        Assertions.assertEquals(new Contact("relationship", "title", (byte) 0, (byte) 0, (byte) 0), result);
    }

    @Test
    void testInsertContact() {
        contactDaoImpl.insertContact(new Contact("relationship", "title", (byte) 0, (byte) 0, (byte) 0));
    }

    @Test
    void testGetContactByPersonId() {
        List<Contact> result = contactDaoImpl.getContactByPersonId(Integer.valueOf(0));
        Assertions
            .assertEquals(Arrays.<Contact>asList(new Contact("relationship", "title", (byte) 0, (byte) 0, (byte) 0)),
                result);
    }

    @Test
    void testGetEmergencyByPersonId() {
        List<Contact> result = contactDaoImpl.getEmergencyByPersonId(Integer.valueOf(0));
        Assertions
            .assertEquals(Arrays.<Contact>asList(new Contact("relationship", "title", (byte) 0, (byte) 0, (byte) 0)),
                result);
    }

    @Test
    void testGetPersonIdByContactId() {
        int result = contactDaoImpl.getPersonIdByContactId(Integer.valueOf(0));
        Assertions.assertEquals(0, result);
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
