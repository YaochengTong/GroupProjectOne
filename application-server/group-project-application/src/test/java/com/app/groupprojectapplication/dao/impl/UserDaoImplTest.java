package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.domain.House;
import com.app.groupprojectapplication.domain.Person;
import com.app.groupprojectapplication.domain.User;
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

class UserDaoImplTest {

    @Mock
    SessionFactory sessionFactory;
    @InjectMocks
    UserDaoImpl userDaoImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetUserById() {
        User result = userDaoImpl.getUserById(Integer.valueOf(0));
        Assertions.assertEquals(new User("username", "email", "password"), result);
    }

    @Test
    void testInsertUser() {
        userDaoImpl.insertUser(new User("username", "email", "password"));
    }

    @Test
    void testDeleteUserById() {
        userDaoImpl.deleteUserById(Integer.valueOf(0));
    }

    @Test
    void testUpdateUser() {
        userDaoImpl.updateUser(new User("username", "email", "password"));
    }

    @Test
    void testGetAllUsers() {
        List<User> result = userDaoImpl.getAllUsers();
        Assertions.assertEquals(Arrays.<User>asList(new User("username", "email", "password")), result);
    }

    @Test
    void testGetEmployeeIdByUserId() {
        Integer result = userDaoImpl.getEmployeeIdByUserId(Integer.valueOf(0));
        Assertions.assertEquals(Integer.valueOf(0), result);
    }

    @Test
    void testGetPersonIdByUserId() {
        Integer result = userDaoImpl.getPersonIdByUserId(Integer.valueOf(0));
        Assertions.assertEquals(Integer.valueOf(0), result);
    }

    @Test
    void testGetPersonByUserId() {
        Person result = userDaoImpl.getPersonByUserId(Integer.valueOf(0));
        Assertions.assertEquals(
            new Person(Integer.valueOf(0), "firstName", "lastName", "middleName", "email", "primaryPhone",
                "alternatePhone", "gender", "ssn", null), result);
    }

    @Test
    void testAddHouse() {
        userDaoImpl.addHouse(new House("address", 0));
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
