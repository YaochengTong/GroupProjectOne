package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.dao.IEmployeeDao;
import com.app.groupprojectapplication.domain.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;


@Repository
@Transactional
public class EmployeeDaoImpl implements IEmployeeDao {

    Employee employee;

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public List<Employee> getEmployee() {
        Session session = sessionFactory.getCurrentSession();
        List<Employee> employeeList = session.createQuery("FROM Employee").getResultList();
        return employeeList;
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        employee = session.get(Employee.class, id);
        return employee;
    }

    @Override
    public void insertEmployee(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.save(employee);
    }

    @Override
    public void deleteUserById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Employee newEmployee = new Employee();
        newEmployee.setId(id);
        session.delete(newEmployee);
    }

    @Override
    public Integer getUserIdByEmployeeId(Integer employeeId) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select e.person.id FROM Employee e WHERE e.id=:employee_Id";
        Query query = session.createQuery(hql);
        query.setParameter("employee_Id", employeeId);
        if (query.getResultList().size() != 1) {
            return null;
        }
        Integer person_id = (Integer) query.getResultList().get(0);
        String hql2 = "SELECT u.id FROM User u WHERE u.person.id=:person_id";
        query = session.createQuery(hql2);
        query.setParameter("person_id", person_id);
        if (query.getResultList().size() != 1) {
            return null;
        }


        return (Integer)  query.getResultList().get(0);
    }

    @Override
    public void updateEmployee(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(employee);
    }


}