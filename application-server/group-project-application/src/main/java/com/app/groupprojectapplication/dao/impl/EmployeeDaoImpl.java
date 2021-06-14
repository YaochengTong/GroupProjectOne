package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.dao.IEmployeeDao;
import com.app.groupprojectapplication.domain.Employee;
import com.app.groupprojectapplication.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDaoImpl implements IEmployeeDao {

    Employee employee;
    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public Employee getEmployeeById(Integer id) {
        Session session = sessionFactory.openSession();
        employee = session.get(Employee.class, id);
        return employee;
    }

    @Override
    public void insertEmployee(Employee employee) {
        Session session = sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        session.save(employee);
        ts.commit();
        session.close();
    }

    @Override
    public void deleteUserById(Integer id) {
        Session session = sessionFactory.openSession();
        Employee employee = new Employee();
        employee.setId(id);

        Transaction ts = session.beginTransaction();
        session.delete(employee);
        ts.commit();
        session.close();
    }

}
