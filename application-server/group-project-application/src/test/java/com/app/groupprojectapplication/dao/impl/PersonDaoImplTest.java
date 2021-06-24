package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.domain.Person;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class PersonDaoImplTest {

    @Mock
    SessionFactory sessionFactory;
    @InjectMocks
    PersonDaoImpl personDaoImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetPersonById() {
        Person result = personDaoImpl.getPersonById(Integer.valueOf(0));
        Assertions.assertEquals(
            new Person(Integer.valueOf(0), "firstName", "lastName", "middleName", "email", "primaryPhone",
                "alternatePhone", "gender", "ssn", null), result);
    }

    @Test
    void testInsertPerson() {
        Integer result = personDaoImpl.insertPerson(
            new Person(Integer.valueOf(0), "firstName", "lastName", "middleName", "email", "primaryPhone",
                "alternatePhone", "gender", "ssn", null));
        Assertions.assertEquals(Integer.valueOf(0), result);
    }

    @Test
    void testUpdatePerson() {
        personDaoImpl.updatePerson(
            new Person(Integer.valueOf(0), "firstName", "lastName", "middleName", "email", "primaryPhone",
                "alternatePhone", "gender", "ssn", null));
    }

    @Test
    void testGetAge() {
        Integer result = personDaoImpl.getAge(Integer.valueOf(0));
        Assertions.assertEquals(Integer.valueOf(0), result);
    }

    @Test
    void testGetPhoneByPersonId() {
        String result = personDaoImpl.getPhoneByPersonId(Integer.valueOf(0));
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    void testGetPersonIdByFullName() {
        Person result = personDaoImpl.getPersonIdByFullName("fullName");
        Assertions.assertEquals(
            new Person(Integer.valueOf(0), "firstName", "lastName", "middleName", "email", "primaryPhone",
                "alternatePhone", "gender", "ssn", null), result);
    }

    @Test
    void testUpdatePhoneById() {
        personDaoImpl.updatePhoneById("phone", Integer.valueOf(0));
    }

    @Test
    void testMergePerson() {
        personDaoImpl.mergePerson(
            new Person(Integer.valueOf(0), "firstName", "lastName", "middleName", "email", "primaryPhone",
                "alternatePhone", "gender", "ssn", null));
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
