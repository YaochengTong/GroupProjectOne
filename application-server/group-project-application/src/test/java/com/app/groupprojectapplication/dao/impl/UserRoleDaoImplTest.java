package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.domain.UserRole;
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

class UserRoleDaoImplTest {

    @Mock
    SessionFactory sessionFactory;
    @InjectMocks
    UserRoleDaoImpl userRoleDaoImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetUserRoleById() {
        UserRole result = userRoleDaoImpl.getUserRoleById(Integer.valueOf(0));
        Assertions.assertEquals(new UserRole(), result);
    }

    @Test
    void testGetUserRoleByUserId() {
        List<UserRole> result = userRoleDaoImpl.getUserRoleByUserId(Integer.valueOf(0));
        Assertions.assertEquals(Arrays.<UserRole>asList(new UserRole()), result);
    }

    @Test
    void testGetUserRoleByRoleId() {
        List<UserRole> result = userRoleDaoImpl.getUserRoleByRoleId(Integer.valueOf(0));
        Assertions.assertEquals(Arrays.<UserRole>asList(new UserRole()), result);
    }

    @Test
    void testInsertUserRole() {
        userRoleDaoImpl.insertUserRole(new UserRole());
    }

    @Test
    void testUpdateUserRole() {
        boolean result = userRoleDaoImpl.updateUserRole(new UserRole());
        Assertions.assertEquals(true, result);
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
