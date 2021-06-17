package com.app.groupprojectapplication.service.impl;

import com.app.groupprojectapplication.dao.IHouseDao;
import com.app.groupprojectapplication.domain.FacilityReport;
import com.app.groupprojectapplication.domain.House;
import com.app.groupprojectapplication.domain.HouseElement.HousePageInfo;
import com.app.groupprojectapplication.domain.HouseElement.ReportDetail;
import com.app.groupprojectapplication.service.IHouseService;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HouseServiceImpl implements IHouseService {

    @Autowired
    IHouseDao iHouseDao;

    @Override
    public HousePageInfo getHouseById(Integer id) {
        return null;
    }

    @Override
    @Transactional
    public List<HousePageInfo> getAllHouse() {
        List<House> houseList = iHouseDao.getAllHouse();
        for (House h : houseList) {
            HousePageInfo hpi = new HousePageInfo();
            hpi.setHouseId(h.getId());
            hpi.setAddress(h.getAddress());
            hpi.setLandlord(generateRandomLandlord());
        }
        return houseList;
    }

    public String generateRandomLandlord() {
        const String allChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder(5);
        for(int i =0; i < 5;i++){
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            sb.append(allChar.charAt(index));
        }
        return sb.toString();
    }

    public ReportDetail getReportDetailFromReport(FacilityReport fr) {
        ReportDetail rd = new ReportDetail();
        return rd;
    }

}
