package com.app.groupprojectapplication.dao;

import com.app.groupprojectapplication.domain.Employee;
import com.app.groupprojectapplication.domain.Facility;
import com.app.groupprojectapplication.domain.FacilityReport;
import java.util.List;

public interface IFacilityDao {

    void insertFacility(Facility facility);

    List<Facility> getFacilityByHouseId(Integer house_id);

    int getNumOfTypeByHouseId(Integer house_id, String facilityType);

    List<FacilityReport> getAllFacilityReportByHouseId(Employee e);

}
