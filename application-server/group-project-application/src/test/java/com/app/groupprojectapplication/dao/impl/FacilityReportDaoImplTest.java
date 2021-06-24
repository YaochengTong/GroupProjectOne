package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.domain.Employee;
import com.app.groupprojectapplication.domain.FacilityReport;
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

class FacilityReportDaoImplTest {

    @Mock
    SessionFactory sessionFactory;
    @InjectMocks
    FacilityReportDaoImpl facilityReportDaoImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetFacilityReportsByEmployeeId() {
        List<FacilityReport> result = facilityReportDaoImpl.getFacilityReportsByEmployeeId(Integer.valueOf(0));
        Assertions.assertEquals(Arrays.<FacilityReport>asList(new FacilityReport()), result);
    }

    @Test
    void testGetFacilityReportsByStatus() {
        List<FacilityReport> result = facilityReportDaoImpl.getFacilityReportsByStatus("status");
        Assertions.assertEquals(Arrays.<FacilityReport>asList(new FacilityReport()), result);
    }

    @Test
    void testInsertFacilityReport() {
        facilityReportDaoImpl.insertFacilityReport(new FacilityReport());
    }

    @Test
    void testGetEmployeeById() {
        Employee result = facilityReportDaoImpl.getEmployeeById(1);
        Assertions.assertEquals(new Employee(), result);
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
