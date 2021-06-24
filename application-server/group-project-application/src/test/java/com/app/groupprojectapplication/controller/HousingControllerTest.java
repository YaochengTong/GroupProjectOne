package com.app.groupprojectapplication.controller;

import com.app.groupprojectapplication.domain.House;
import com.app.groupprojectapplication.domain.HouseElement.HouseEmployeeInfo;
import com.app.groupprojectapplication.domain.HouseElement.HouseFacilityInfo;
import com.app.groupprojectapplication.domain.HouseElement.HousePageInfo;
import com.app.groupprojectapplication.service.IHouseService;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class HousingControllerTest {

    @Mock
    IHouseService iHouseService;
    @InjectMocks
    HousingController housingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testPostAllHouse() {
        when(iHouseService.getAllHouse()).thenReturn(Arrays.<HousePageInfo>asList(
            new HousePageInfo(Integer.valueOf(0), "address", "landlord", "phone", Integer.valueOf(0),
                Arrays.<HouseEmployeeInfo>asList(null), Arrays.<HouseFacilityInfo>asList(null))));

        Map<String, Object> result = housingController.postAllHouse();
        Assertions
            .assertEquals(new HashMap<String, Object>() {{put("String", "replaceMeWithExpectedResult");}}, result);
    }

    @Test
    void testGetHouseById() {
        when(iHouseService.getHouseById(anyInt())).thenReturn(
            new HousePageInfo(Integer.valueOf(0), "address", "landlord", "phone", Integer.valueOf(0),
                Arrays.<HouseEmployeeInfo>asList(null), Arrays.<HouseFacilityInfo>asList(null)));

        Map<String, Object> result = housingController.getHouseById(Integer.valueOf(0));
        Assertions
            .assertEquals(new HashMap<String, Object>() {{put("String", "replaceMeWithExpectedResult");}}, result);
    }

    @Test
    void testUpdateHouseById() {
        Map<String, Object> result = housingController.updateHouseById(
            new HousePageInfo(Integer.valueOf(0), "address", "landlord", "phone", Integer.valueOf(0),
                Arrays.<HouseEmployeeInfo>asList(null), Arrays.<HouseFacilityInfo>asList(null)));
        Assertions
            .assertEquals(new HashMap<String, Object>() {{put("String", "replaceMeWithExpectedResult");}}, result);
    }

    @Test
    void testGetHouseByUserId() {
        when(iHouseService.getHouseById(anyInt())).thenReturn(
            new HousePageInfo(Integer.valueOf(0), "address", "landlord", "phone", Integer.valueOf(0),
                Arrays.<HouseEmployeeInfo>asList(null), Arrays.<HouseFacilityInfo>asList(null)));
        when(iHouseService.getHouseByUserId(anyInt())).thenReturn(new House("address", 0));

        String result = housingController.getHouseByUserId(Integer.valueOf(0));
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    void testGetTestHouses() {
        when(iHouseService.getAllTestHouses()).thenReturn(Arrays.<House>asList(new House("address", 0)));

        Map<String, Object> result = housingController.getTestHouses();
        Assertions
            .assertEquals(new HashMap<String, Object>() {{put("String", "replaceMeWithExpectedResult");}}, result);
    }

    @Test
    void testAddHouseByUserId() {
        String result = housingController.addHouseByUserId(Integer.valueOf(0),
            new HousePageInfo(Integer.valueOf(0), "address", "landlord", "phone", Integer.valueOf(0),
                Arrays.<HouseEmployeeInfo>asList(null), Arrays.<HouseFacilityInfo>asList(null)));
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
