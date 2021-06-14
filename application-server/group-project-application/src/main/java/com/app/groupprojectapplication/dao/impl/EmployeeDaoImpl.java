package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.dao.IEmployeeDao;
import com.app.groupprojectapplication.domain.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class EmployeeDaoImpl implements IEmployeeDao {

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public Employee getEmployeeById(Integer id) {
        Session session = sessionFactory.openSession();
//        Employee = session.get(Employee.class, id);
        return null;
    }

    @Override
    public void insertEmployee(Object[] info) {

    }

    @Override
    public void deleteUserById(Integer id) {

    }

    @Override
    public void updateEmployeeById(Integer id) {

    }
}
