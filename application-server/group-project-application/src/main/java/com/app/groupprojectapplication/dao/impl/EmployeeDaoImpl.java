package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.dao.IEmployeeDao;
import com.app.groupprojectapplication.domain.Employee;
import com.app.groupprojectapplication.domain.Person;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class EmployeeDaoImpl implements IEmployeeDao {

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public List<Employee> getEmployee() {
        Session session = sessionFactory.getCurrentSession();
        List<Employee> employeeList = session.createQuery("FROM Employee").getResultList();
        return employeeList;
    }

    @Override
    @Async("taskExecutor")
    @Transactional
    public CompletableFuture<Employee> getEmployeeById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        //Employee employee = session.get(Employee.class, id);
        //Session session = sessionFactory.getCurrentSession();
        String hql = "select e FROM Employee e join fetch e.person WHERE e.id=:employee_Id";
        Query query = session.createQuery(hql);
        query.setParameter("employee_Id", id);
        if (query.getResultList().size() != 1) {
            return null;
        }
        Employee result = (Employee) query.getResultList().get(0);
        return CompletableFuture.completedFuture(result);
    }

    @Override
    public Integer insertEmployee(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        Integer id = (Integer) session.save(employee);
        return id;
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

        return (Integer) query.getResultList().get(0);
    }

    @Override
    public void updateEmployee(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(employee);
    }

    @Override
    public void mergeEmployee(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(employee);
    }

    public Employee getEmployeeByPerson(Person p) {
        Session session = sessionFactory.getCurrentSession();
        List<Employee> resultList = session.createQuery("FROM Employee WHERE person.id = " + p.getId()).getResultList();
        return resultList.get(0);
    }

    @Override
    public Integer getHouseIdByEmployee(Employee e) {
        Session session = sessionFactory.getCurrentSession();
        int personId = e.getPerson().getId();
        List<Integer> resultList = new ArrayList<>();
        try {
            resultList = session.createNativeQuery("SELECT house_id FROM employee WHERE person_id =" + personId)
                .getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (resultList == null) { return null; }
        return resultList.get(0);
    }

    @Override
    public void updateAvatarPath(Integer employeeId, String result) {
        Session session = sessionFactory.getCurrentSession();
        System.out.println(result);
        String query = "update Employee e set e.avartar = ?1 where e.id = ?2";
        session.createQuery(query).setParameter(1, result).setParameter(2, employeeId).executeUpdate();
        return;
    }

}
