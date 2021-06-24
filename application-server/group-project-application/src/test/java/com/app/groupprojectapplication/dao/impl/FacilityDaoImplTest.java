package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.domain.Employee;
import com.app.groupprojectapplication.domain.Facility;
import com.app.groupprojectapplication.domain.FacilityReport;
import com.app.groupprojectapplication.domain.House;
import java.util.Arrays;
import java.util.List;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class FacilityDaoImplTest {

    @Mock
    SessionFactory sessionFactory;
    @InjectMocks
    FacilityDaoImpl facilityDaoImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testInsertFacility() {
        facilityDaoImpl.insertFacility(new Facility());
    }

    @Test
    void testGetFacilityByHouseId() {
        List<Facility> result = facilityDaoImpl.getFacilityByHouseId(Integer.valueOf(0));
        Assertions.assertEquals(Arrays.<Facility>asList(new Facility()), result);
    }

    @Test
    void testGetNumOfTypeByHouseId() {
        int result = facilityDaoImpl.getNumOfTypeByHouseId(Integer.valueOf(0), "f_type");
        Assertions.assertEquals(0, result);
    }

    @Test
    void testGetAllFacilityReportByHouseId() {
        List<FacilityReport> result = facilityDaoImpl.getAllFacilityReportByHouseId(new Employee());
        Assertions.assertEquals(Arrays.<FacilityReport>asList(new FacilityReport()), result);
    }

    @Test
    void testGetFacilityTypeById() {
        String result = facilityDaoImpl.getFacilityTypeById(Integer.valueOf(0));
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    void testGetFacilityDescriptionById() {
        String result = facilityDaoImpl.getFacilityDescriptionById(Integer.valueOf(0));
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    void testGetHouseById() {
        House result = facilityDaoImpl.getHouseById(Integer.valueOf(0));
        Assertions.assertEquals(new House("address", 0), result);
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
