package com.app.groupprojectapplication.dao;

import com.app.groupprojectapplication.domain.Facility;
import com.app.groupprojectapplication.domain.FacilityReport;

import java.util.List;

public interface IFacilityReport {
    List<FacilityReport> getFacilityReportsByEmployeeId(Integer employee_id);
    List<FacilityReport> getFacilityReportsByStatus(String status);
    void insertFacilityReport(FacilityReport facilityReport);
}
