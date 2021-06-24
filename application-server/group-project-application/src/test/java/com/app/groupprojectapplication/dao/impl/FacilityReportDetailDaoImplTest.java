package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.domain.FacilityReport;
import com.app.groupprojectapplication.domain.FacilityReportDetail;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class FacilityReportDetailDaoImplTest {

    @Mock
    SessionFactory sessionFactory;
    @InjectMocks
    FacilityReportDetailDaoImpl facilityReportDetailDaoImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetFacilityReportDetailById() {
        FacilityReportDetail result = facilityReportDetailDaoImpl.getFacilityReportDetailById(Integer.valueOf(0));
        Assertions.assertEquals(new FacilityReportDetail(), result);
    }

    @Test
    void testGetFacilityReportByReportDetailId() {
        FacilityReport result = facilityReportDetailDaoImpl.getFacilityReportByReportDetailId(Integer.valueOf(0));
        Assertions.assertEquals(new FacilityReport(), result);
    }

    @Test
    void testInsertFailicyReportDetail() {
        facilityReportDetailDaoImpl.insertFailicyReportDetail(new FacilityReportDetail());
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
