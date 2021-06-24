package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.domain.Employee;
import com.app.groupprojectapplication.domain.Person;
import java.util.Arrays;
import java.util.List;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class EmployeeDaoImplTest {

    @Mock
    SessionFactory sessionFactory;
    @InjectMocks
    EmployeeDaoImpl employeeDaoImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetEmployee() {
        List<Employee> result = employeeDaoImpl.getEmployee();
        Assertions.assertEquals(Arrays.<Employee>asList(new Employee()), result);
    }

    @Test
    void testGetEmployeeById() {
        Employee result = employeeDaoImpl.getEmployeeById(Integer.valueOf(0));
        Assertions.assertEquals(new Employee(), result);
    }

    @Test
    void testInsertEmployee() {
        Integer result = employeeDaoImpl.insertEmployee(new Employee());
        Assertions.assertEquals(Integer.valueOf(0), result);
    }

    @Test
    void testDeleteUserById() {
        employeeDaoImpl.deleteUserById(Integer.valueOf(0));
    }

    @Test
    void testGetUserIdByEmployeeId() {
        Integer result = employeeDaoImpl.getUserIdByEmployeeId(Integer.valueOf(0));
        Assertions.assertEquals(Integer.valueOf(0), result);
    }

    @Test
    void testUpdateEmployee() {
        employeeDaoImpl.updateEmployee(new Employee());
    }

    @Test
    void testMergeEmployee() {
        employeeDaoImpl.mergeEmployee(new Employee());
    }

    @Test
    void testGetEmployeeByPerson() {
        Employee result = employeeDaoImpl
            .getEmployeeByPerson(new Person(Integer.valueOf(0), null, null, null, null, null, null, null, null, null));
        Assertions.assertEquals(new Employee(), result);
    }

    @Test
    void testGetHouseIdByEmployee() {
        Integer result = employeeDaoImpl.getHouseIdByEmployee(new Employee());
        Assertions.assertEquals(Integer.valueOf(0), result);
    }

    @Test
    void testUpdateAvatarPath() {
        employeeDaoImpl.updateAvatarPath(Integer.valueOf(0), "result");
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
