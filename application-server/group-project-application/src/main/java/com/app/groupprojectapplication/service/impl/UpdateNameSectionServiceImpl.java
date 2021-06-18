package com.app.groupprojectapplication.service.impl;

import com.app.groupprojectapplication.dao.IEmployeeDao;
import com.app.groupprojectapplication.dao.IPersonDao;
import com.app.groupprojectapplication.dao.IUserDao;
import com.app.groupprojectapplication.domain.Employee;
import com.app.groupprojectapplication.domain.Person;
import com.app.groupprojectapplication.service.IUpdateNameSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class UpdateNameSectionServiceImpl implements IUpdateNameSectionService {

    @Autowired
    IPersonDao iPersonDao;

    @Autowired
    IEmployeeDao iEmployeeDao;

    @Autowired
    IUserDao iUserDao;

    @Override
    public boolean updateFullName(String fullName, Integer userId) {
       Person person = iUserDao.getPersonByUserId(userId);
       String firstName = fullName.split(" ")[0];
       String lastName = fullName.split(" ")[1];
       person.setFirstName(firstName);
       person.setLastName(lastName);
       iPersonDao.updatePerson(person);

       return true;
    }

    @Override
    public boolean updateAge(Integer age, Integer userId) {

        return false;
    }

    @Override
    public boolean updateSSN(Integer ssn, Integer userId) {
        Person person = iUserDao.getPersonByUserId(userId);
        person.setSsn(String.valueOf(ssn));
        iPersonDao.updatePerson(person);
        return true;
    }

    @Override
    public boolean updateAvatar(String avatar, Integer userId) {
        Integer employeeId = iUserDao.getEmployeeIdByUserId(userId);
        Employee employee = iEmployeeDao.getEmployeeById(employeeId);
        employee.setAvartar(avatar);
        iEmployeeDao.updateEmployee(employee);

        return true;
    }

    @Override
    public boolean updateDOB(Timestamp DOB, Integer userId) {

        return false;
    }


    @Override
    public boolean updatePreferredName(String preferredName, Integer userId) {

        return false;
    }
}
