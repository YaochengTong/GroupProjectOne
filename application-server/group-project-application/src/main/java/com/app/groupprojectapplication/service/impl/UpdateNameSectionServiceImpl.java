package com.app.groupprojectapplication.service.impl;

import com.app.groupprojectapplication.dao.IEmployeeDao;
import com.app.groupprojectapplication.dao.IPersonDao;
import com.app.groupprojectapplication.dao.IUserDao;
import com.app.groupprojectapplication.domain.Employee;
import com.app.groupprojectapplication.domain.Person;
import com.app.groupprojectapplication.service.IUpdateNameSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.*;

@Service
@Transactional
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
        Person person = iUserDao.getPersonByUserId(userId);
        Calendar calendar = Calendar.getInstance();
        Integer year = calendar.get(Calendar.YEAR);
        Integer month = calendar.get(Calendar.MONTH) + 1;

        Integer birthYear = year - age;
        Timestamp birthday = Timestamp.valueOf(birthYear.toString() + "-" + month.toString() + "-01 00:00:00");
        person.setDob(birthday);
        System.out.println("Set age to " + age);

        return true;
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
        System.out.println("avatar");
        System.out.println(avatar);
        Integer employeeId = iUserDao.getEmployeeIdByUserId(userId);
        Employee employee = iEmployeeDao.getEmployeeById(employeeId);
        employee.setAvartar(avatar);
//        iEmployeeDao.updateEmployee(employee);

        return true;
    }

    @Override
    public boolean updateDOB(String DOB, Integer userId) {
        Person person = iUserDao.getPersonByUserId(userId);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Timestamp dob = Timestamp.valueOf(DOB);
//        person.setDob(dob);
//        iPersonDao.updatePerson(person);

        return true;

    }


    @Override
    public boolean updatePreferredName(String preferredName, Integer userId) {
        System.out.println("Set Preferred Name: " + preferredName);
        return true;
    }
}
