package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.domain.Role;
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

class RoleDaoImplTest {

    @Mock
    Role role;
    @Mock
    SessionFactory sessionFactory;
    @InjectMocks
    RoleDaoImpl roleDaoImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetRoleById() {
        Role result = roleDaoImpl.getRoleById(Integer.valueOf(0));
        Assertions.assertEquals(new Role(), result);
    }

    @Test
    void testGetRolesByLastModifiedUserId() {
        List<Role> result = roleDaoImpl.getRolesByLastModifiedUserId(Integer.valueOf(0));
        Assertions.assertEquals(Arrays.<Role>asList(new Role()), result);
    }

    @Test
    void testInsertRole() {
        roleDaoImpl.insertRole(new Role());
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
