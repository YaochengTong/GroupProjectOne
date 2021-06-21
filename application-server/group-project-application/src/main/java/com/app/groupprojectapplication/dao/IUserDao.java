package com.app.groupprojectapplication.dao;

import com.app.groupprojectapplication.domain.Employee;
import com.app.groupprojectapplication.domain.House;
import com.app.groupprojectapplication.domain.Person;
import com.app.groupprojectapplication.domain.User;

import java.util.List;

/**
 * Shida Sheng
 */
public interface IUserDao {
    User getUserById(Integer id);
    void insertUser(User user);
    void deleteUserById(Integer id);
    void updateUser(User user);
    List<User> getAllUsers();
    Integer getEmployeeIdByUserId(Integer userId);
    Integer getPersonIdByUserId(Integer userId);
    Person getPersonByUserId(Integer userId);

    void addHouse(House h);

}
