package com.app.groupprojectapplication.dao;

import com.app.groupprojectapplication.domain.Employee;

public interface IEmployeeDao {
    Employee getEmployeeById(Integer id);
    void insertEmployee(Object[] info);
    void deleteUserById(Integer id);
    void updateEmployeeById(Integer id);
}
