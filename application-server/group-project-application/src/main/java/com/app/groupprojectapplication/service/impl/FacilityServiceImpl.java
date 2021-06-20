package com.app.groupprojectapplication.service.impl;

import com.app.groupprojectapplication.dao.IEmployeeDao;
import com.app.groupprojectapplication.dao.IFacilityReportDao;
import com.app.groupprojectapplication.dao.IUserDao;
import com.app.groupprojectapplication.domain.FacilityReport;
import com.app.groupprojectapplication.domain.HouseElement.HouseFacilityReportInfo;
import com.app.groupprojectapplication.service.IFacilityService;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacilityServiceImpl implements IFacilityService {

    @Autowired
    private IUserDao iUserDao;
    @Autowired
    private IFacilityReportDao iFacilityReportDao;
    @Autowired
    private IEmployeeDao iEmployeeDao;

    @Override
    public List<FacilityReport> getAllFacilityReportByHouseId(Integer houseId) {
        return null;
    }

    @Override
    @Transactional
    public void addFacilityReportByUserId(HouseFacilityReportInfo fri, Integer userId) {
        int employeeId = iUserDao.getEmployeeIdByUserId(userId);

        FacilityReport fr = new FacilityReport();
        fr.setEmployee(iEmployeeDao.getEmployeeById(employeeId));
        fr.setTitle(fri.getHouseFacilityReportTitle());
        fr.setReportDate(new Timestamp(new Date().getTime()));
        fr.setDescription(fri.getHouseFacilityReportDescription());
        fr.setStatus(fri.getHouseFacilityReportStatus());
        iFacilityReportDao.insertFacilityReport(fr);
    }

}
