package com.app.groupprojectapplication.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.app.groupprojectapplication.domain.House;
import com.app.groupprojectapplication.domain.HouseElement.HouseEmployeeInfo;
import com.app.groupprojectapplication.domain.HouseElement.HouseFacilityInfo;
import com.app.groupprojectapplication.domain.HouseElement.HouseFacilityReportDetail;
import com.app.groupprojectapplication.domain.HouseElement.HouseFacilityReportInfo;
import com.app.groupprojectapplication.domain.HouseElement.HousePageInfo;
import com.app.groupprojectapplication.service.IHouseService;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HousingController.class)
class HousingControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IHouseService mockIHouseService;

    @Test
    void testPostAllHouse() throws Exception {
        // Setup

        // Configure IHouseService.getAllHouse(...).
        final List<HousePageInfo> housePageInfos = Arrays.asList(new HousePageInfo(0, "address", "landlord", "phone", 0,
            Arrays.asList(new HouseEmployeeInfo(0, "employeeName", "employeePhone", "employeeEmail", "employeeCar")),
            Arrays.asList(new HouseFacilityInfo(0, 0, 0, 0, 0, Arrays.asList(
                new HouseFacilityReportInfo(0, "houseFacilityReportTitle", "houseFacilityReportDate",
                    "houseFacilityReportDescription", "houseFacilityReportStatus",
                    Arrays.asList(new HouseFacilityReportDetail(0, 0, "comments", "createDate"))))))));
        when(mockIHouseService.getAllHouse()).thenReturn(housePageInfos);

        // Run the test
        final MockHttpServletResponse response = mockMvc
            .perform(get("/housing/get-houses").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }

    @Test
    void testPostAllHouse_IHouseServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockIHouseService.getAllHouse()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc
            .perform(get("/housing/get-houses").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }

    @Test
    void testGetHouseById() throws Exception {
        // Setup

        // Configure IHouseService.getHouseById(...).
        final HousePageInfo housePageInfo = new HousePageInfo(0, "address", "landlord", "phone", 0,
            Arrays.asList(new HouseEmployeeInfo(0, "employeeName", "employeePhone", "employeeEmail", "employeeCar")),
            Arrays.asList(new HouseFacilityInfo(0, 0, 0, 0, 0, Arrays.asList(
                new HouseFacilityReportInfo(0, "houseFacilityReportTitle", "houseFacilityReportDate",
                    "houseFacilityReportDescription", "houseFacilityReportStatus",
                    Arrays.asList(new HouseFacilityReportDetail(0, 0, "comments", "createDate")))))));
        when(mockIHouseService.getHouseById(0)).thenReturn(housePageInfo);

        // Run the test
        final MockHttpServletResponse response = mockMvc
            .perform(get("/housing/get-house/{houseId}", 0).accept(MediaType.APPLICATION_JSON)).andReturn()
            .getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }

    @Test
    void testUpdateHouseById() throws Exception {
        // Setup

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(
            post("/housing/update-house").content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
        verify(mockIHouseService).updateHouse(any(HousePageInfo.class));
    }

    @Test
    void testGetHouseByUserId() throws Exception {
        // Setup
        when(mockIHouseService.getHouseByUserId(0)).thenReturn(new House("address", 0));

        // Configure IHouseService.getHouseById(...).
        final HousePageInfo housePageInfo = new HousePageInfo(0, "address", "landlord", "phone", 0,
            Arrays.asList(new HouseEmployeeInfo(0, "employeeName", "employeePhone", "employeeEmail", "employeeCar")),
            Arrays.asList(new HouseFacilityInfo(0, 0, 0, 0, 0, Arrays.asList(
                new HouseFacilityReportInfo(0, "houseFacilityReportTitle", "houseFacilityReportDate",
                    "houseFacilityReportDescription", "houseFacilityReportStatus",
                    Arrays.asList(new HouseFacilityReportDetail(0, 0, "comments", "createDate")))))));
        when(mockIHouseService.getHouseById(0)).thenReturn(housePageInfo);

        // Run the test
        final MockHttpServletResponse response = mockMvc
            .perform(get("/housing/get-user-house/{userId}", 0).accept(MediaType.APPLICATION_JSON)).andReturn()
            .getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }

    @Test
    void testGetTestHouses() throws Exception {
        // Setup
        when(mockIHouseService.getAllTestHouses()).thenReturn(Arrays.asList(new House("address", 0)));

        // Run the test
        final MockHttpServletResponse response = mockMvc
            .perform(get("/housing/test-houses").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }

    @Test
    void testGetTestHouses_IHouseServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockIHouseService.getAllTestHouses()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc
            .perform(get("/housing/test-houses").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }

    @Test
    void testAddHouseByUserId() throws Exception {
        // Setup

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(
            post("/housing/add-house/{userId}", 0).content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
        verify(mockIHouseService).addHouseByUserId(any(HousePageInfo.class), eq(0));
    }

}
