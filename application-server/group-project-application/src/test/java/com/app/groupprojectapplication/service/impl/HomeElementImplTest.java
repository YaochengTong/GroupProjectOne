package com.app.groupprojectapplication.service.impl;

import com.app.groupprojectapplication.dao.IEmployeeDao;
import com.app.groupprojectapplication.dao.IHouseDao;
import com.app.groupprojectapplication.dao.IPersonDao;
import com.app.groupprojectapplication.dao.IVisaStatusDao;
import com.app.groupprojectapplication.domain.Employee;
import com.app.groupprojectapplication.domain.homeElement.HomeElement;
import com.app.groupprojectapplication.domain.homeElement.HousingInfo;
import com.app.groupprojectapplication.domain.homeElement.NameInfo;
import com.app.groupprojectapplication.domain.homeElement.VisaInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class HomeElementImplTest {

    @Mock
    HomeElement homeElement;
    @Mock
    IEmployeeDao iEmployeeDao;
    @Mock
    IHouseDao iHouseDao;
    @Mock
    IPersonDao iPersonDao;
    @Mock
    IVisaStatusDao iVisaStatusDao;
    @InjectMocks
    HomeElementImpl homeElementImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetHomeElementByEmployeeId() {
        when(iEmployeeDao.getEmployeeById(anyInt())).thenReturn(new Employee());

        HomeElement result = homeElementImpl.getHomeElementByEmployeeId(Integer.valueOf(0));
        Assertions.assertEquals(new HomeElement(Integer.valueOf(0), new HousingInfo("address", Integer.valueOf(0)),
                new NameInfo("fullName", "firstName", "lastName", Integer.valueOf(0), "title"), new VisaInfo(
                    "visaType")),
            result);
    }

    @Test
    void testGetFullName() {
        String result = homeElementImpl.getFullName(null);
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
