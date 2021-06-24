package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.domain.PersonalDocument;
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

class PersonalDocumentDaoImplTest {

    @Mock
    PersonalDocument pd;
    @Mock
    SessionFactory sessionFactory;
    @InjectMocks
    PersonalDocumentDaoImpl personalDocumentDaoImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetPersonalDocumentById() {
        PersonalDocument result = personalDocumentDaoImpl.getPersonalDocumentById(Integer.valueOf(0));
        Assertions.assertEquals(new PersonalDocument(), result);
    }

    @Test
    void testGetPersonalDocumentsByEmployeeId() {
        List<PersonalDocument> result = personalDocumentDaoImpl.getPersonalDocumentsByEmployeeId(Integer.valueOf(0));
        Assertions.assertEquals(Arrays.<PersonalDocument>asList(new PersonalDocument()), result);
    }

    @Test
    void testGetPersonalDocumentsByUserId() {
        List<PersonalDocument> result = personalDocumentDaoImpl.getPersonalDocumentsByUserId(Integer.valueOf(0));
        Assertions.assertEquals(Arrays.<PersonalDocument>asList(new PersonalDocument()), result);
    }

    @Test
    void testInsertPersonalDocument() {
        personalDocumentDaoImpl.insertPersonalDocument(new PersonalDocument());
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
