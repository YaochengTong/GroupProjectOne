package com.app.groupprojectapplication.dao;

import com.app.groupprojectapplication.domain.Employee;
import com.app.groupprojectapplication.domain.House;
import com.app.groupprojectapplication.domain.Person;
import java.util.List;

public interface IEmployeeDao {
    List<Employee> getEmployee();
    Employee getEmployeeById(Integer id);
    Integer insertEmployee(Employee employee);
    void deleteUserById(Integer id);
    Integer getUserIdByEmployeeId(Integer employeeId);
    void updateEmployee(Employee employee);


    void mergeEmployee(Employee employee);

    Employee getEmployeeByPerson(Person p);
    Integer getHouseIdByEmployee(Employee e);

    void updateAvatarPath(Integer employeeId, String result);
    //String getTitleById(Integer id);
    //String getManagerId(Integer id);
    //String getStartDate(Integer id);
    //String getEndDate(Integer id);
    //String getAvartar(Integer id);
    //String getCar(Integer id);
}
