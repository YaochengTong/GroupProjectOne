package com.app.groupprojectapplication.dao;

import com.app.groupprojectapplication.domain.User;

/**
 * Shida Sheng
 */
public interface IUserDao {
    User getUserById(Integer id);
    void insertUser(User user);
    void deleteUserById(Integer id);
    void updateUser(User user);
    Integer getEmployeeIdByUserId(Integer userId);
}
