package com.app.groupprojectapplication.dao.impl;

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

class HouseDaoImplTest {

    @Mock
    SessionFactory sessionFactory;
    @InjectMocks
    HouseDaoImpl houseDaoImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetHouseById() {
        House result = houseDaoImpl.getHouseById(Integer.valueOf(0));
        Assertions.assertEquals(new House("address", 0), result);
    }

    @Test
    void testInsertHouse() {
        houseDaoImpl.insertHouse(new House("address", 0));
    }

    @Test
    void testGetAllHouse() {
        List<House> result = houseDaoImpl.getAllHouse();
        Assertions.assertEquals(Arrays.<House>asList(new House("address", 0)), result);
    }

    @Test
    void testGetContactIdByHouseId() {
        int result = houseDaoImpl.getContactIdByHouseId(Integer.valueOf(0));
        Assertions.assertEquals(0, result);
    }

    @Test
    void testUpdateHouseAddressByHouseId() {
        houseDaoImpl.updateHouseAddressByHouseId("address", Integer.valueOf(0));
    }

    @Test
    void testUpdateHouseNumberOfPersonByHouseId() {
        houseDaoImpl.updateHouseNumberOfPersonByHouseId(Integer.valueOf(0), Integer.valueOf(0));
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
