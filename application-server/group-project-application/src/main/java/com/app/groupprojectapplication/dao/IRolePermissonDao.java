package com.app.groupprojectapplication.dao;

import com.app.groupprojectapplication.domain.Permission;
import com.app.groupprojectapplication.domain.Role;
import com.app.groupprojectapplication.domain.RolePermission;

import java.util.List;

public interface IRolePermissonDao {
    RolePermission getRolePermissionById(Integer id);
    List<RolePermission> getRolePermissionByPermissionId(Integer permission_id);
    List<RolePermission> getRolePermissionByLastModifiedUserId(Integer user_id);
    List<RolePermission> getRolePermissionByRoleId(Integer role_id);
    void insertRolePermission(RolePermission rolePermission);
}
