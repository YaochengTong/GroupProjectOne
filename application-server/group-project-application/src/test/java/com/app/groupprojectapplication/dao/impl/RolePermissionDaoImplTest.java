package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.domain.RolePermission;
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

class RolePermissionDaoImplTest {

    @Mock
    RolePermission rolePermission;
    @Mock
    SessionFactory sessionFactory;
    @InjectMocks
    RolePermissionDaoImpl rolePermissionDaoImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetRolePermissionById() {
        RolePermission result = rolePermissionDaoImpl.getRolePermissionById(Integer.valueOf(0));
        Assertions.assertEquals(new RolePermission(), result);
    }

    @Test
    void testGetRolePermissionByPermissionId() {
        List<RolePermission> result = rolePermissionDaoImpl.getRolePermissionByPermissionId(Integer.valueOf(0));
        Assertions.assertEquals(Arrays.<RolePermission>asList(new RolePermission()), result);
    }

    @Test
    void testGetRolePermissionByLastModifiedUserId() {
        List<RolePermission> result = rolePermissionDaoImpl.getRolePermissionByLastModifiedUserId(Integer.valueOf(0));
        Assertions.assertEquals(Arrays.<RolePermission>asList(new RolePermission()), result);
    }

    @Test
    void testGetRolePermissionByRoleId() {
        List<RolePermission> result = rolePermissionDaoImpl.getRolePermissionByRoleId(Integer.valueOf(0));
        Assertions.assertEquals(Arrays.<RolePermission>asList(new RolePermission()), result);
    }

    @Test
    void testInsertRolePermission() {
        rolePermissionDaoImpl.insertRolePermission(new RolePermission());
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
