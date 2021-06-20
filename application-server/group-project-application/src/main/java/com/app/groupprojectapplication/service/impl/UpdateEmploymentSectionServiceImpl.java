package com.app.groupprojectapplication.service.impl;

import com.app.groupprojectapplication.dao.IEmployeeDao;
import com.app.groupprojectapplication.dao.IUserDao;
import com.app.groupprojectapplication.dao.IVisaStatusDao;
import com.app.groupprojectapplication.domain.Employee;
import com.app.groupprojectapplication.domain.VisaStatus;
import com.app.groupprojectapplication.service.IUpdateEmploymentSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;


@Service
@Transactional
public class UpdateEmploymentSectionServiceImpl implements IUpdateEmploymentSectionService {

    @Autowired
    IUserDao iUserDao;

    @Autowired
    IEmployeeDao iEmployeeDao;

    @Autowired
    IVisaStatusDao iVisaStatusDao;

    @Override
    public boolean updateEmployment(String title, String workAuthorization, String authorizationStartDate, String authorizationEndDate, String employmentStartDate, String employmentEndDate, Integer userId) {
        Integer employeeId = iUserDao.getEmployeeIdByUserId(userId);
        Employee employee = iEmployeeDao.getEmployeeById(employeeId);

        employee.setTitle(title);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp ASD = Timestamp.valueOf(authorizationStartDate);
        Timestamp AED = Timestamp.valueOf(authorizationEndDate);
        Timestamp ESD = Timestamp.valueOf(employmentStartDate);
        Timestamp EED = Timestamp.valueOf(employmentEndDate);
        employee.setVisaStartDate(ASD);
        employee.setVisaEndDate(AED);
        employee.setStartDate(ESD);
        employee.setEndDate(EED);

        VisaStatus visaStatus = employee.getVisaStatus();
        visaStatus.setVisaType(workAuthorization);
        iVisaStatusDao.updateVisaStatus(visaStatus);
        iEmployeeDao.updateEmployee(employee);
        return true;
    }
}
