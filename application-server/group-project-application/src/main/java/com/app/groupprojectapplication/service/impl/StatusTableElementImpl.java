package com.app.groupprojectapplication.service.impl;

import com.app.groupprojectapplication.dao.IEmployeeDao;
import com.app.groupprojectapplication.dao.IUserDao;
import com.app.groupprojectapplication.dao.IVisaStatusDao;
import com.app.groupprojectapplication.domain.Employee;
import com.app.groupprojectapplication.domain.Person;
import com.app.groupprojectapplication.domain.statusTableElement.NameInfo;
import com.app.groupprojectapplication.domain.statusTableElement.StatusTableElement;
import com.app.groupprojectapplication.domain.statusTableElement.StatusTableElement;
import com.app.groupprojectapplication.domain.statusTableElement.VisaInfo;
import com.app.groupprojectapplication.service.IStatusTableElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatusTableElementImpl implements IStatusTableElementService {

    private StatusTableElement statusTableElement;

    @Autowired
    IEmployeeDao iEmployeeDao;

    @Autowired
    IUserDao iUserDao;

    @Autowired
    IVisaStatusDao iVisaStatusDao;

    @Override
    @Transactional
    public List<StatusTableElement> getStatus() {
         List<StatusTableElement> statusList = new ArrayList<>();
         List<Employee> employeeList = iEmployeeDao.getEmployee();
         for (Employee employee : employeeList) {
             statusList.add(getStatusByEmployee(employee));
         }

         return statusList;
    }

    @Override
    @Transactional
    public StatusTableElement getStatusByEmployeeId(Integer userId) {
        Integer employeeId = iUserDao.getEmployeeIdByUserId(userId);
        Employee employee = iEmployeeDao.getEmployeeById(employeeId);
        return getStatusByEmployee(employee);
    }


    private StatusTableElement getStatusByEmployee(Employee employee) {
        statusTableElement = new StatusTableElement();
        Person person = employee.getPerson();
        statusTableElement.setEmployeeId(employee.getId());
        statusTableElement.setNameInfo(setNameInfo(employee, person));
        statusTableElement.setVisaInfo(setVisaInfo(employee));

        return statusTableElement;
    }

    private NameInfo setNameInfo(Employee employee, Person person) {
        NameInfo nameInfo = new NameInfo();
        nameInfo.setFirstName(person.getFirstName());
        nameInfo.setLastName(person.getLastName());
        nameInfo.setFullName(getFullName(person));
        nameInfo.setSSN(Integer.parseInt(person.getSsn()));
        nameInfo.setTitle(employee.getTitle());

        return nameInfo;
    }

    public String getFullName(Person person) {
        String fullName;
        if (person.getMiddleName() != null && person.getMiddleName().length() > 0){
            fullName = person.getFirstName() + " " + person.getMiddleName() + " " + person.getLastName();
        } else {
            fullName = person.getFirstName() + " " + person.getLastName();
        }
        return  fullName;
    }

    public VisaInfo setVisaInfo(Employee employee) {
        VisaInfo visaInfo = new VisaInfo();
        visaInfo.setVisaType(employee.getVisaStatus().getVisaType());
        visaInfo.setExpirationDate(employee.getVisaEndDate());

        return visaInfo;
    }
}
