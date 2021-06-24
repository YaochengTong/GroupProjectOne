package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.domain.Permission;
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

class PermissonDaoImplTest {

    @Mock
    Permission permission;
    @Mock
    SessionFactory sessionFactory;
    @InjectMocks
    PermissonDaoImpl permissonDaoImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetPermissionById() {
        Permission result = permissonDaoImpl.getPermissionById(Integer.valueOf(0));
        Assertions.assertEquals(new Permission(), result);
    }

    @Test
    void testGetPermissionsByLastModifiedUserId() {
        List<Permission> result = permissonDaoImpl.getPermissionsByLastModifiedUserId(Integer.valueOf(0));
        Assertions.assertEquals(Arrays.<Permission>asList(new Permission()), result);
    }

    @Test
    void testInsertPermission() {
        permissonDaoImpl.insertPermission(new Permission());
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
