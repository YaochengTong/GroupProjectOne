package com.app.groupprojectapplication.service.impl;

import com.app.groupprojectapplication.dao.IEmployeeDao;
import com.app.groupprojectapplication.dao.IHouseDao;
import com.app.groupprojectapplication.dao.IPersonDao;
import com.app.groupprojectapplication.dao.IVisaStatusDao;
import com.app.groupprojectapplication.domain.Employee;
import com.app.groupprojectapplication.domain.Person;
import com.app.groupprojectapplication.domain.homeElement.HomeElement;
import com.app.groupprojectapplication.domain.homeElement.HousingInfo;
import com.app.groupprojectapplication.domain.homeElement.NameInfo;
import com.app.groupprojectapplication.domain.homeElement.VisaInfo;
import com.app.groupprojectapplication.service.IHomeElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.concurrent.ExecutionException;


@Service
public class HomeElementImpl implements IHomeElementService {

    private HomeElement homeElement;

    @Autowired
    IEmployeeDao iEmployeeDao;

    @Autowired
    IHouseDao iHouseDao;

    @Autowired
    IPersonDao iPersonDao;

    @Autowired
    IVisaStatusDao iVisaStatusDao;

    @Override
    @Transactional
    public HomeElement getHomeElementByEmployeeId(Integer employeeId) throws ExecutionException, InterruptedException {
        Employee employee = iEmployeeDao.getEmployeeById(employeeId).get();
        return getHomeElementByEmployee(employee);
    }

    private HomeElement getHomeElementByEmployee(Employee employee) {
        homeElement = new HomeElement();
        Person person = employee.getPerson();
        homeElement.setEmployeeId(employee.getId());
        homeElement.setNameInfo(setNameInfo(person, employee));
        homeElement.setVisaInfo(setVisaInfo(employee));
        homeElement.setHousingInfo(setHousingInfo(employee));


        return homeElement;
    }

    private HousingInfo setHousingInfo(Employee employee) {
        HousingInfo housingInfo = new HousingInfo();
        housingInfo.setAddress(employee.getHouse().getAddress());
        housingInfo.setNumberOfRoommate(employee.getHouse().getNumberOfPerson());

        return housingInfo;
    }

    private VisaInfo setVisaInfo(Employee employee) {
        VisaInfo visaInfo = new VisaInfo();
        visaInfo.setVisaType(employee.getVisaStatus().getVisaType());

        return visaInfo;
    }

    private NameInfo setNameInfo(Person person, Employee employee) {
        NameInfo nameInfo = new NameInfo();
        nameInfo.setFirstName(person.getFirstName());
        nameInfo.setLastName(person.getLastName());
        nameInfo.setFullName(getFullName(person));
        nameInfo.setTitle(employee.getTitle());
        nameInfo.setSSN(Integer.parseInt(person.getSsn()));

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
}
