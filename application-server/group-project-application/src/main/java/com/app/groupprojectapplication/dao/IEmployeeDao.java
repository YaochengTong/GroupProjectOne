package com.app.groupprojectapplication.dao;

import com.app.groupprojectapplication.domain.Employee;

import java.util.List;

public interface IEmployeeDao {
    List<Employee> getEmployee();
    Employee getEmployeeById(Integer id);
    void insertEmployee(Employee employee);
    void deleteUserById(Integer id);
    Integer getUserIdByEmployeeId(Integer employeeId);
}