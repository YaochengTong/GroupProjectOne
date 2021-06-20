package com.app.groupprojectapplication.dao;

import com.app.groupprojectapplication.domain.Employee;
import com.app.groupprojectapplication.domain.FacilityReport;
import java.util.List;

public interface IFacilityReportDao {

    List<FacilityReport> getFacilityReportsByEmployeeId(Integer employee_id);

    List<FacilityReport> getFacilityReportsByStatus(String status);

    void insertFacilityReport(FacilityReport facilityReport);

    Employee getEmployeeById(Integer facilityReportId);

}
