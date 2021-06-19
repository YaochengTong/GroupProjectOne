package com.app.groupprojectapplication.service;

import com.app.groupprojectapplication.domain.FacilityReport;
import java.util.List;

public interface IFacilityService {

    List<FacilityReport> getAllFacilityReportByHouseId(Integer houseId);

}
