package com.app.groupprojectapplication.dao;

import com.app.groupprojectapplication.domain.User;

import java.util.List;

/**
 * Shida Sheng
 */
public interface IUserDao {
    User getUserById(Integer id);
    void insertUser(User user);
    void deleteUserById(Integer id);
    void updateUser(Integer id);
    List<User> getAllUsers();
    Integer getEmployeeIdByUserId(Integer userId);
}
