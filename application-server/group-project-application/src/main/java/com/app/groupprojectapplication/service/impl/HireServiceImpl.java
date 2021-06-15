package com.app.groupprojectapplication.service.impl;

import com.app.groupprojectapplication.dao.IEmployeeDao;
import com.app.groupprojectapplication.dao.IRegistrationTokenDao;
import com.app.groupprojectapplication.dao.IUserDao;
import com.app.groupprojectapplication.domain.Employee;
import com.app.groupprojectapplication.domain.RegistrationToken;
import com.app.groupprojectapplication.email.EmailService;
import com.app.groupprojectapplication.service.IHireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.UUID;

@Service
public class HireServiceImpl implements IHireService {

    @Autowired
    private IUserDao iUserDao;

    @Autowired
    private IEmployeeDao iEmployeeDao;

    @Autowired
    private IRegistrationTokenDao iRegistrationTokenDao;

    @Autowired
    private EmailService emailService;

    @Override
    public boolean generateAToken(String email, Integer userId) {
        //generate a UUID
        UUID token = UUID.randomUUID();

        //store the email and token into database
        Integer employeeId = iUserDao.getEmployeeIdByUserId(userId);
        if(employeeId == null)
            return false;
        Employee employee = iEmployeeDao.getEmployeeById(employeeId);
        if(employee == null)
            return false;
        RegistrationToken registrationToken = new RegistrationToken();
        registrationToken.setEmployee(employee);
        registrationToken.setToken(token.toString());
        registrationToken.setEmail(email);
        long timeStamp = System.currentTimeMillis();
        java.sql.Date expirationDate = new java.sql.Date(timeStamp + 3 * 60 * 60);
        registrationToken.setValidUntil(new Timestamp(expirationDate.getTime()));
        iRegistrationTokenDao.insertRegistrationToke(registrationToken);

        //send the token through email
        emailService.sendMail(email, "Registration Token", token.toString());

        return true;
    }
}
