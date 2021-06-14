package com.app.groupprojectapplication.dao;

import com.app.groupprojectapplication.domain.User;

/**
 * Shida Sheng
 */
public interface IUserDao {
    User getUserById(Integer id);
    void insertUser(Object[] info);
    void deleteUserById(Integer id);
    void updateUser(Integer id);
}
