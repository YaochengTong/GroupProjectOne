package com.app.groupprojectapplication.service.impl;

import com.app.groupprojectapplication.dao.IContactDao;
import com.app.groupprojectapplication.dao.IEmployeeDao;
import com.app.groupprojectapplication.dao.IFacilityDao;
import com.app.groupprojectapplication.dao.IFacilityReportDao;
import com.app.groupprojectapplication.dao.IFacilityReportDetailDao;
import com.app.groupprojectapplication.dao.IHouseDao;
import com.app.groupprojectapplication.dao.IPersonDao;
import com.app.groupprojectapplication.dao.IUserDao;
import com.app.groupprojectapplication.domain.Contact;
import com.app.groupprojectapplication.domain.Employee;
import com.app.groupprojectapplication.domain.Facility;
import com.app.groupprojectapplication.domain.FacilityReport;
import com.app.groupprojectapplication.domain.FacilityReportDetail;
import com.app.groupprojectapplication.domain.House;
import com.app.groupprojectapplication.domain.HouseElement.HouseEmployeeInfo;
import com.app.groupprojectapplication.domain.HouseElement.HouseFacilityInfo;
import com.app.groupprojectapplication.domain.HouseElement.HouseFacilityReportDetail;
import com.app.groupprojectapplication.domain.HouseElement.HouseFacilityReportInfo;
import com.app.groupprojectapplication.domain.HouseElement.HousePageInfo;
import com.app.groupprojectapplication.domain.Person;
import com.app.groupprojectapplication.domain.User;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class HouseServiceImplTest {

    @Mock
    IHouseDao iHouseDao;
    @Mock
    IFacilityDao iFacilityDao;
    @Mock
    IContactDao iContactDao;
    @Mock
    IPersonDao iPersonDao;
    @Mock
    IEmployeeDao iEmployeeDao;
    @Mock
    IFacilityReportDetailDao iFacilityReportDetailDao;
    @Mock
    IFacilityReportDao iFacilityReportDao;
    @Mock
    IUserDao iUserDao;
    @InjectMocks
    HouseServiceImpl houseServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetHouseById() {
        when(iHouseDao.getHouseById(anyInt())).thenReturn(new House("address", 0));
        when(iHouseDao.getContactIdByHouseId(anyInt())).thenReturn(0);
        when(iFacilityDao.getNumOfTypeByHouseId(anyInt(), anyString())).thenReturn(0);
        when(iFacilityDao.getAllFacilityReportByHouseId(any()))
            .thenReturn(Arrays.<FacilityReport>asList(new FacilityReport()));
        when(iContactDao.getPersonIdByContactId(anyInt())).thenReturn(0);
        when(iPersonDao.getPhoneByPersonId(anyInt())).thenReturn("getPhoneByPersonIdResponse");

        HousePageInfo result = houseServiceImpl.getHouseById(Integer.valueOf(0));
        Assertions.assertEquals(
            new HousePageInfo(Integer.valueOf(0), "address", "landlord", "phone", Integer.valueOf(0), Arrays
                .<HouseEmployeeInfo>asList(
                    new HouseEmployeeInfo(Integer.valueOf(0), "employeeName", "employeePhone", "employeeEmail",
                        "employeeCar")), Arrays.<HouseFacilityInfo>asList(
                new HouseFacilityInfo(Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0),
                    Integer.valueOf(0), Arrays.<HouseFacilityReportInfo>asList(null)))), result);
    }

    @Test
    void testGetAllHouse() {
        when(iHouseDao.getHouseById(anyInt())).thenReturn(new House("address", 0));
        when(iHouseDao.getAllHouse()).thenReturn(Arrays.<House>asList(new House("address", 0)));
        when(iHouseDao.getContactIdByHouseId(anyInt())).thenReturn(0);
        when(iFacilityDao.getNumOfTypeByHouseId(anyInt(), anyString())).thenReturn(0);
        when(iFacilityDao.getAllFacilityReportByHouseId(any()))
            .thenReturn(Arrays.<FacilityReport>asList(new FacilityReport()));
        when(iContactDao.getPersonIdByContactId(anyInt())).thenReturn(0);
        when(iPersonDao.getPhoneByPersonId(anyInt())).thenReturn("getPhoneByPersonIdResponse");

        List<HousePageInfo> result = houseServiceImpl.getAllHouse();
        Assertions.assertEquals(Arrays.<HousePageInfo>asList(
            new HousePageInfo(Integer.valueOf(0), "address", "landlord", "phone", Integer.valueOf(0), Arrays
                .<HouseEmployeeInfo>asList(
                    new HouseEmployeeInfo(Integer.valueOf(0), "employeeName", "employeePhone", "employeeEmail",
                        "employeeCar")), Arrays.<HouseFacilityInfo>asList(
                new HouseFacilityInfo(Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0),
                    Integer.valueOf(0), Arrays.<HouseFacilityReportInfo>asList(null))))), result);
    }

    @Test
    void testGenerateRandomLandlord() {
        String result = houseServiceImpl.generateRandomLandlord();
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    void testGetEmployeeList() {
        List<HouseEmployeeInfo> result = houseServiceImpl.getEmployeeList(new HashSet<Employee>(Arrays.asList(null)));
        Assertions.assertEquals(Arrays.<HouseEmployeeInfo>asList(
            new HouseEmployeeInfo(Integer.valueOf(0), "employeeName", "employeePhone", "employeeEmail", "employeeCar")),
            result);
    }

    @Test
    void testGetFacilityList() {
        when(iFacilityDao.getNumOfTypeByHouseId(anyInt(), anyString())).thenReturn(0);
        when(iFacilityDao.getAllFacilityReportByHouseId(any()))
            .thenReturn(Arrays.<FacilityReport>asList(new FacilityReport()));

        List<HouseFacilityInfo> result = houseServiceImpl.getFacilityList(new HashSet<Facility>(Arrays.asList(null)));
        Assertions.assertEquals(Arrays.<HouseFacilityInfo>asList(
            new HouseFacilityInfo(Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0),
                Integer.valueOf(0), Arrays.<HouseFacilityReportInfo>asList(null))), result);
    }

    @Test
    void testTransferFacilityList() {
        when(iFacilityDao.getNumOfTypeByHouseId(anyInt(), anyString())).thenReturn(0);
        when(iFacilityDao.getAllFacilityReportByHouseId(any()))
            .thenReturn(Arrays.<FacilityReport>asList(new FacilityReport()));

        HouseFacilityInfo result = houseServiceImpl.transferFacilityList(null);
        Assertions.assertEquals(
            new HouseFacilityInfo(Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0),
                Integer.valueOf(0), Arrays.<HouseFacilityReportInfo>asList(null)), result);
    }

    @Test
    void testTransferFacilityReport() {
        HouseFacilityReportInfo result = houseServiceImpl.transferFacilityReport(new FacilityReport());
        Assertions.assertEquals(
            new HouseFacilityReportInfo(Integer.valueOf(0), "houseFacilityReportTitle", "houseFacilityReportDate",
                "houseFacilityReportDescription", "houseFacilityReportStatus", Arrays.<HouseFacilityReportDetail>asList(
                new HouseFacilityReportDetail(Integer.valueOf(0), Integer.valueOf(0), "comments", "createDate"))),
            result);
    }

    @Test
    void testGetFacilityReportList() {
        List<HouseFacilityReportInfo> result = houseServiceImpl
            .getFacilityReportList(Arrays.<FacilityReport>asList(new FacilityReport()));
        Assertions.assertEquals(Arrays.<HouseFacilityReportInfo>asList(null), result);
    }

    @Test
    void testGetFacilityReportDetail() {
        HouseFacilityReportDetail result = houseServiceImpl.getFacilityReportDetail(null);
        Assertions.assertEquals(
            new HouseFacilityReportDetail(Integer.valueOf(0), Integer.valueOf(0), "comments", "createDate"), result);
    }

    @Test
    void testTransferFacilityReportDetail() {
        List<HouseFacilityReportDetail> result = houseServiceImpl
            .transferFacilityReportDetail(new HashSet<FacilityReportDetail>(Arrays.asList(null)));
        Assertions.assertEquals(Arrays.<HouseFacilityReportDetail>asList(
            new HouseFacilityReportDetail(Integer.valueOf(0), Integer.valueOf(0), "comments", "createDate")), result);
    }

    @Test
    void testGetBackFacilityReportDetail() {
        when(iEmployeeDao.getEmployeeById(anyInt())).thenReturn(new Employee());
        when(iFacilityReportDetailDao.getFacilityReportByReportDetailId(anyInt())).thenReturn(new FacilityReport());

        FacilityReportDetail result = houseServiceImpl.getBackFacilityReportDetail(
            new HouseFacilityReportDetail(Integer.valueOf(0), Integer.valueOf(0), "comments", "createDate"));
        Assertions.assertEquals(new FacilityReportDetail(), result);
    }

    @Test
    void testTransferBackFacilityReportDetail() {
        when(iEmployeeDao.getEmployeeById(anyInt())).thenReturn(new Employee());
        when(iFacilityReportDetailDao.getFacilityReportByReportDetailId(anyInt())).thenReturn(new FacilityReport());

        Set<FacilityReportDetail> result = houseServiceImpl.transferBackFacilityReportDetail(Arrays
            .<HouseFacilityReportDetail>asList(
                new HouseFacilityReportDetail(Integer.valueOf(0), Integer.valueOf(0), "comments", "createDate")));
        Assertions.assertEquals(new HashSet<FacilityReportDetail>(Arrays.asList(new FacilityReportDetail())), result);
    }

    @Test
    void testGetBackFacilityReport() {
        when(iEmployeeDao.getEmployeeById(anyInt())).thenReturn(new Employee());
        when(iFacilityReportDetailDao.getFacilityReportByReportDetailId(anyInt())).thenReturn(new FacilityReport());
        when(iFacilityReportDao.getEmployeeById(anyInt())).thenReturn(new Employee());

        FacilityReport result = houseServiceImpl.getBackFacilityReport(
            new HouseFacilityReportInfo(Integer.valueOf(0), "houseFacilityReportTitle", "houseFacilityReportDate",
                "houseFacilityReportDescription", "houseFacilityReportStatus", Arrays.<HouseFacilityReportDetail>asList(
                new HouseFacilityReportDetail(Integer.valueOf(0), Integer.valueOf(0), "comments", "createDate"))));
        Assertions.assertEquals(new FacilityReport(), result);
    }

    @Test
    void testTransferBackFacility() {
        when(iFacilityDao.getFacilityTypeById(anyInt())).thenReturn("getFacilityTypeByIdResponse");
        when(iFacilityDao.getFacilityDescriptionById(anyInt())).thenReturn("getFacilityDescriptionByIdResponse");
        when(iFacilityDao.getHouseById(anyInt())).thenReturn(new House("address", 0));

        Set<Facility> result = houseServiceImpl.transferBackFacility(Arrays.<HouseFacilityInfo>asList(
            new HouseFacilityInfo(Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0),
                Integer.valueOf(0), Arrays.<HouseFacilityReportInfo>asList(null))));
        Assertions.assertEquals(new HashSet<Facility>(Arrays.asList(null)), result);
    }

    @Test
    void testUpdateHouse() {
        when(iHouseDao.getContactIdByHouseId(anyInt())).thenReturn(0);
        when(iContactDao.getPersonIdByContactId(anyInt())).thenReturn(0);

        houseServiceImpl.updateHouse(
            new HousePageInfo(Integer.valueOf(0), "address", "landlord", "phone", Integer.valueOf(0), Arrays
                .<HouseEmployeeInfo>asList(
                    new HouseEmployeeInfo(Integer.valueOf(0), "employeeName", "employeePhone", "employeeEmail",
                        "employeeCar")), Arrays.<HouseFacilityInfo>asList(
                new HouseFacilityInfo(Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0),
                    Integer.valueOf(0), Arrays.<HouseFacilityReportInfo>asList(null)))));
    }

    @Test
    void testGetHouseByUserId() {
        when(iHouseDao.getHouseById(anyInt())).thenReturn(new House("address", 0));
        when(iEmployeeDao.getEmployeeByPerson(any())).thenReturn(new Employee());
        when(iEmployeeDao.getHouseIdByEmployee(any())).thenReturn(Integer.valueOf(0));
        when(iUserDao.getUserById(anyInt())).thenReturn(new User("username", "email", "password"));

        House result = houseServiceImpl.getHouseByUserId(Integer.valueOf(0));
        Assertions.assertEquals(new House("address", 0), result);
    }

    @Test
    void testGetAllTestHouses() {
        when(iHouseDao.getAllHouse()).thenReturn(Arrays.<House>asList(new House("address", 0)));

        List<House> result = houseServiceImpl.getAllTestHouses();
        Assertions.assertEquals(Arrays.<House>asList(new House("address", 0)), result);
    }

    @Test
    void testAddHouseByUserId() {
        when(iContactDao.getContactByPersonId(anyInt()))
            .thenReturn(Arrays.<Contact>asList(new Contact("relationship", "title", (byte) 0, (byte) 0, (byte) 0)));
        when(iUserDao.getPersonByUserId(anyInt()))
            .thenReturn(new Person(Integer.valueOf(0), null, null, null, null, null, null, null, null, null));

        houseServiceImpl.addHouseByUserId(
            new HousePageInfo(Integer.valueOf(0), "address", "landlord", "phone", Integer.valueOf(0), Arrays
                .<HouseEmployeeInfo>asList(
                    new HouseEmployeeInfo(Integer.valueOf(0), "employeeName", "employeePhone", "employeeEmail",
                        "employeeCar")), Arrays.<HouseFacilityInfo>asList(
                new HouseFacilityInfo(Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0),
                    Integer.valueOf(0), Arrays.<HouseFacilityReportInfo>asList(null)))), Integer.valueOf(0));
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
