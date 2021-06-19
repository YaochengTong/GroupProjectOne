package com.app.groupprojectapplication.service.impl;

import com.app.groupprojectapplication.domain.FacilityReport;
import com.app.groupprojectapplication.service.IFacilityService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FacilityServiceImpl implements IFacilityService {

    @Override
    public List<FacilityReport> getAllFacilityReportByHouseId(Integer houseId) {
        return null;
    }

}
