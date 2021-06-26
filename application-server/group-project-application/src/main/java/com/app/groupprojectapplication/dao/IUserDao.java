package com.app.groupprojectapplication.dao;

import com.app.groupprojectapplication.domain.Employee;
import com.app.groupprojectapplication.domain.House;
import com.app.groupprojectapplication.domain.Person;
import com.app.groupprojectapplication.domain.User;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Shida Sheng
 */
public interface IUserDao {
    CompletableFuture<User> getUserById(Integer id);
    void insertUser(User user);
    void deleteUserById(Integer id);
    void updateUser(User user);
    List<User> getAllUsers();
    CompletableFuture<Integer> getEmployeeIdByUserId(Integer userId);
    Integer getPersonIdByUserId(Integer userId);
    CompletableFuture<Person> getPersonByUserId(Integer userId);

    void addHouse(House h);

}
