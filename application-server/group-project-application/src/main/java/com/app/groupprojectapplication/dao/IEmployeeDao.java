package com.app.groupprojectapplication.dao;

import com.app.groupprojectapplication.domain.Employee;

public interface IEmployeeDao {
    Employee getEmployeeById(Integer id);
    void insertEmployee(Employee employee);
    void deleteUserById(Integer id);
}
