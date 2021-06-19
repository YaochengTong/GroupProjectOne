package com.app.groupprojectapplication.service.impl;

import com.app.groupprojectapplication.dao.IContactDao;
import com.app.groupprojectapplication.dao.IEmployeeDao;
import com.app.groupprojectapplication.dao.IFacilityDao;
import com.app.groupprojectapplication.dao.IFacilityReportDetailDao;
import com.app.groupprojectapplication.dao.IHouseDao;
import com.app.groupprojectapplication.dao.IPersonDao;
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
import com.app.groupprojectapplication.service.IHouseService;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HouseServiceImpl implements IHouseService {

    @Autowired
    IHouseDao iHouseDao;
    @Autowired
    IFacilityDao iFacilityDao;
    @Autowired
    IContactDao iContactDao;
    @Autowired
    IPersonDao iPersonDao;
    @Autowired
    IEmployeeDao iEmployeeDao;
    @Autowired
    IFacilityReportDetailDao iFacilityReportDetailDao;

    @Override
    @Transactional
    public HousePageInfo getHouseById(Integer id) {
        House h = iHouseDao.getHouseById(id);

        int contactId = iHouseDao.getContactIdByHouseId(id);
        int contactPersonId = iContactDao.getPersonIdByContactId(contactId);
        String contactPhone = iPersonDao.getPhoneByPersonId(contactPersonId);

        HousePageInfo hpi = new HousePageInfo();
        hpi.setHouseId(h.getId());
        hpi.setAddress(h.getAddress());
        hpi.setLandlord(generateRandomLandlord());
        hpi.setPhone(contactPhone);
        hpi.setNumberOfPerson(h.getNumberOfPerson());
        Set<Employee> eList = h.getEmployeeSet();
        Set<Facility> fList = h.getFacilitySet();
        List<HouseEmployeeInfo> houseEmployeeInfoList = getEmployeeList(eList);
        List<HouseFacilityInfo> houseFacilityInfoList = getFacilityList(fList);
        hpi.setHouseEmployeeInfoList(houseEmployeeInfoList);
        hpi.setHouseFacilityInfoList(houseFacilityInfoList);
        return hpi;
    }

    @Override
    @Transactional
    public List<HousePageInfo> getAllHouse() {
        List<House> houseList = iHouseDao.getAllHouse();
        List<HousePageInfo> resultList = new ArrayList<>();
        for (House h : houseList) {
            HousePageInfo hpi = getHouseById(h.getId());
            resultList.add(hpi);
        }
        return resultList;
    }

    public String generateRandomLandlord() {
        String allChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(5);
        for (int i = 0; i < 5; i++) {
            int index = (int) (rnd.nextFloat() * allChar.length());
            sb.append(allChar.charAt(index));
        }
        return sb.toString();
    }

    public List<HouseEmployeeInfo> getEmployeeList(Set<Employee> eList) {
        List<HouseEmployeeInfo> houseEmployeeInfoList = new ArrayList<>();
        for (Employee e : eList) {
            HouseEmployeeInfo he = new HouseEmployeeInfo();
            he.setEmployeeId(e.getId());
            Person p = e.getPerson();
            he.setEmployeeName(p.getFirstName() + " " + p.getLastName());
            he.setEmployeePhone(p.getPrimaryPhone());
            he.setEmployeeEmail(p.getEmail());
            he.setEmployeeCar(e.getCar());
            houseEmployeeInfoList.add(he);
        }
        return houseEmployeeInfoList;
    }

    public List<HouseFacilityInfo> getFacilityList(Set<Facility> fList) {
        List<HouseFacilityInfo> resultList = new ArrayList<>();
        for (Facility f : fList) {
            resultList.add(transferFacilityList(f));
        }
        return resultList;
    }

    @Transactional
    public HouseFacilityInfo transferFacilityList(Facility f) {
        HouseFacilityInfo hi = new HouseFacilityInfo();
        House h = f.getHouse();
        hi.setFacilityId(f.getId());
        hi.setNumberOfBeds(iFacilityDao.getNumOfTypeByHouseId(h.getId(), "bed"));
        hi.setNumberOfMattresses(iFacilityDao.getNumOfTypeByHouseId(h.getId(), "mattress"));
        hi.setNumberOfTables(iFacilityDao.getNumOfTypeByHouseId(h.getId(), "table"));
        hi.setNumberOfChairs(iFacilityDao.getNumOfTypeByHouseId(h.getId(), "chair"));

        List<HouseFacilityReportInfo> houseFacilityReportInfoList = new ArrayList<>();

        List<Employee> employeeList = new ArrayList<>(h.getEmployeeSet());

        for (Employee e : employeeList) {
            List<FacilityReport> frList = iFacilityDao.getAllFacilityReportByHouseId(e);
            List<HouseFacilityReportInfo> facilityReportList = getFacilityReportList(frList);
            houseFacilityReportInfoList.addAll(facilityReportList);
        }
        hi.setHouseFacilityReportInfo(houseFacilityReportInfoList);
        return hi;
    }

    public HouseFacilityReportInfo transferFacilityReport(FacilityReport fr) {
        HouseFacilityReportInfo hri = new HouseFacilityReportInfo();
        hri.setHouseFacilityReportId(fr.getId());
        hri.setHouseFacilityReportTitle(fr.getTitle());
        hri.setHouseFacilityReportDate(fr.getReportDate().toString());
        hri.setHouseFacilityReportDescription(fr.getDescription());
        hri.setHouseFacilityReportStatus(fr.getStatus());
        List<HouseFacilityReportDetail> HouseFacilityReportDetailList = transferFacilityReportDetail(
            fr.getFacilityReportDetailSet());
        hri.setHouseFacilityReportDetails(HouseFacilityReportDetailList);
        return hri;
    }

    public List<HouseFacilityReportInfo> getFacilityReportList(List<FacilityReport> frList) {
        List<HouseFacilityReportInfo> resultList = new ArrayList<>();
        for (FacilityReport fr : frList) {
            resultList.add(transferFacilityReport(fr));
        }
        return resultList;
    }

    public HouseFacilityReportDetail getFacilityReportDetail(FacilityReportDetail frd) {
        HouseFacilityReportDetail hrd = new HouseFacilityReportDetail();
        hrd.setReportDetailId(frd.getId());
        hrd.setEmployeeId(frd.getEmployee().getId());
        hrd.setComments(frd.getComments());
        hrd.setCreateDate(frd.getCreateDate().toString());
        return hrd;
    }

    public List<HouseFacilityReportDetail> transferFacilityReportDetail(Set<FacilityReportDetail> frdSet) {
        List<HouseFacilityReportDetail> resultList = new ArrayList<>();
        for (FacilityReportDetail frd : frdSet) {
            resultList.add(getFacilityReportDetail(frd));
        }
        return resultList;
    }

    public FacilityReportDetail getBackFacilityReportDetail(HouseFacilityReportDetail hrd) {
        FacilityReportDetail res = new FacilityReportDetail();
        int reportDetailId = hrd.getReportDetailId();
        res.setId(reportDetailId);
        res.setEmployee(iEmployeeDao.getEmployeeById(hrd.getEmployeeId()));
        res.setComments(hrd.getComments());
        res.setCreateDate(Timestamp.valueOf(hrd.getCreateDate()));
        res.setFacilityReport(iFacilityReportDetailDao.getFacilityReportByReportDetailId(reportDetailId));
        res.setLastModificationDate(new Timestamp(new Date().getTime()));
        return res;
    }
    //
    //public House updateHouseUsingPageInfo(HousePageInfo hpi) {
    //    House h = new House();
    //    int houseId = hpi.getHouseId();
    //    h.setId(houseId);
    //    h.setAddress(hpi.getAddress());
    //    h.setNumberOfPerson(hpi.getNumberOfPerson());
    //    int contactId = iHouseDao.getContactIdByHouseId(houseId);
    //    Contact contact = iContactDao.getContactById(contactId);
    //    h.setContact(contact);
    //    Set<Employee> eSet = new HashSet<>();
    //    Set<Facility> fSet = new HashSet<>();
    //    h.setEmployeeSet();
    //    h.setFacilitySet();
    //}
    //
    //public Set<FacilityReportDetail> transferBackFacilityReportDetail(List<HouseFacilityReportDetail> list) {
    //
    //}
    //
    //public FacilityReport getBackFacilityReport()
}


