package com.app.groupprojectapplication.dao;

import com.app.groupprojectapplication.domain.Permission;

import java.util.List;

public interface IPermissionDao {
    Permission getPermissionById(Integer id);
    List<Permission> getPermissionsByLastModifiedUserId(Integer user_id);
    void insertPermission(Permission permission);
}
