package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.domain.VisaStatus;
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

class VisaStatusDaoImplTest {

    @Mock
    SessionFactory sessionFactory;
    @InjectMocks
    VisaStatusDaoImpl visaStatusDaoImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testInsertVisa() {
        Integer result = visaStatusDaoImpl.insertVisa(new VisaStatus("visaType", (byte) 0, null));
        Assertions.assertEquals(Integer.valueOf(0), result);
    }

    @Test
    void testGetVisaById() {
        VisaStatus result = visaStatusDaoImpl.getVisaById(Integer.valueOf(0));
        Assertions.assertEquals(new VisaStatus("visaType", (byte) 0, null), result);
    }

    @Test
    void testGetVisaByType() {
        List<VisaStatus> result = visaStatusDaoImpl.getVisaByType("visaType");
        Assertions.assertEquals(Arrays.<VisaStatus>asList(new VisaStatus("visaType", (byte) 0, null)), result);
    }

    @Test
    void testGetVisaAuthorizationLeftDay() {
        Integer result = visaStatusDaoImpl.getVisaAuthorizationLeftDay(Integer.valueOf(0));
        Assertions.assertEquals(Integer.valueOf(0), result);
    }

    @Test
    void testGetVisaTypeByEmployeeId() {
        String result = visaStatusDaoImpl.getVisaTypeByEmployeeId(Integer.valueOf(0));
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    void testUpdateVisaStatus() {
        visaStatusDaoImpl.updateVisaStatus(new VisaStatus("visaType", (byte) 0, null));
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
