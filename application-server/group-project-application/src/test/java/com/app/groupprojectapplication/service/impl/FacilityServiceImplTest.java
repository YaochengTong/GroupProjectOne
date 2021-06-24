package com.app.groupprojectapplication.service.impl;

import com.app.groupprojectapplication.dao.IEmployeeDao;
import com.app.groupprojectapplication.dao.IFacilityReportDao;
import com.app.groupprojectapplication.dao.IUserDao;
import com.app.groupprojectapplication.domain.Employee;
import com.app.groupprojectapplication.domain.FacilityReport;
import com.app.groupprojectapplication.domain.HouseElement.HouseFacilityReportDetail;
import com.app.groupprojectapplication.domain.HouseElement.HouseFacilityReportInfo;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class FacilityServiceImplTest {

    @Mock
    IUserDao iUserDao;
    @Mock
    IFacilityReportDao iFacilityReportDao;
    @Mock
    IEmployeeDao iEmployeeDao;
    @InjectMocks
    FacilityServiceImpl facilityServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllFacilityReportByHouseId() {
        List<FacilityReport> result = facilityServiceImpl.getAllFacilityReportByHouseId(Integer.valueOf(0));
        Assertions.assertEquals(Arrays.<FacilityReport>asList(new FacilityReport()), result);
    }

    @Test
    void testAddFacilityReportByUserId() {
        when(iUserDao.getEmployeeIdByUserId(anyInt())).thenReturn(Integer.valueOf(0));
        when(iEmployeeDao.getEmployeeById(anyInt())).thenReturn(new Employee());

        facilityServiceImpl.addFacilityReportByUserId(
            new HouseFacilityReportInfo(Integer.valueOf(0), "houseFacilityReportTitle", "houseFacilityReportDate",
                "houseFacilityReportDescription", "houseFacilityReportStatus", Arrays.<HouseFacilityReportDetail>asList(
                new HouseFacilityReportDetail(Integer.valueOf(0), Integer.valueOf(0), "comments", "createDate"))),
            Integer.valueOf(0));
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
