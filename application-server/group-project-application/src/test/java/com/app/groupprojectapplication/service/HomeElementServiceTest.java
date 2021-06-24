package com.app.groupprojectapplication.service;

import com.app.groupprojectapplication.dao.impl.EmployeeDaoImpl;
import com.app.groupprojectapplication.domain.Employee;
import com.app.groupprojectapplication.domain.House;
import com.app.groupprojectapplication.domain.Person;
import com.app.groupprojectapplication.domain.VisaStatus;
import com.app.groupprojectapplication.domain.homeElement.HomeElement;
import com.app.groupprojectapplication.domain.homeElement.HousingInfo;
import com.app.groupprojectapplication.domain.homeElement.NameInfo;
import com.app.groupprojectapplication.domain.homeElement.VisaInfo;
import com.app.groupprojectapplication.service.impl.HomeElementImpl;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class HomeElementServiceTest {

    private static final Gson gson = new Gson();

    @Mock
    EmployeeDaoImpl employeeDao;

    @InjectMocks
    HomeElementImpl homeService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetHomeElementByEmployeeId() throws Exception {
        Employee expected = new Employee();
        Person person = new Person();
        person.setSsn("112112");
        person.setFirstName("a");
        person.setLastName("a");
        expected.setPerson(person);
        VisaStatus visaStatus = new VisaStatus();
        expected.setVisaStatus(visaStatus);
        House house = new House();
        expected.setHouse(house);

        HomeElement expectedElement = new HomeElement();
        expectedElement.setEmployeeId(0);
        expectedElement.setHousingInfo(new HousingInfo());
        NameInfo nameInfo = new NameInfo();
        nameInfo.setSSN(112112);
        nameInfo.setFullName("a a");
        nameInfo.setLastName("a");
        nameInfo.setFirstName("a");
        expectedElement.setNameInfo(nameInfo);
        expectedElement.setVisaInfo(new VisaInfo());

        Mockito.when(employeeDao
                .getEmployeeById(1))
                .thenReturn(expected);
        Assertions.assertEquals(gson.toJson(expectedElement),
                gson.toJson(homeService.getHomeElementByEmployeeId(1)));
    }
}
