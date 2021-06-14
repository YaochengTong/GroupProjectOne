package com.app.groupprojectapplication.dao;

import com.app.groupprojectapplication.domain.Role;

import java.util.List;

public interface IRoleDao {
    Role getRoleById(Integer id);
    List<Role> getRolesByLastModifiedUserId(Integer user_id);
    void insertRole(Role role);
}
