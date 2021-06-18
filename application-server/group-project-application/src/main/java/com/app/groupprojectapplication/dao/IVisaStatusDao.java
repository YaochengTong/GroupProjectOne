package com.app.groupprojectapplication.dao;

import com.app.groupprojectapplication.domain.VisaStatus;

import java.util.List;

public interface IVisaStatusDao {
    Integer insertVisa(VisaStatus visaStatus);
    List<VisaStatus> getVisaByType(String visaType);
    Integer getVisaAuthorizationLeftDay(Integer visaStatusId);
    String getVisaTypeByEmployeeId(Integer employeeId);

}
