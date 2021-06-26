package com.app.groupprojectapplication.service;

import com.app.groupprojectapplication.domain.FacilityReport;
import com.app.groupprojectapplication.domain.HouseElement.HouseFacilityReportInfo;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

public interface IFacilityService {

    List<FacilityReport> getAllFacilityReportByHouseId(Integer houseId);

    void addFacilityReportByUserId(HouseFacilityReportInfo fr, Integer userId) throws ExecutionException, InterruptedException;

}
