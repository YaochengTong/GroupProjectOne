package com.app.groupprojectapplication.dao;

import com.app.groupprojectapplication.domain.User;
import com.app.groupprojectapplication.domain.UserRole;

import java.util.List;

public interface IUserRoleDao {
    UserRole getUserRoleById(Integer id);
    List<UserRole> getUserRoleByUserId(Integer user_id);
    List<UserRole> getUserRoleByRoleId(Integer roleId);
//    List<UserRole> getUserRoleByLastModifiedUserId(Integer lastUserId);
    void insertUserRole(UserRole userRole);
    boolean updateUserRole(UserRole userRole);
}
