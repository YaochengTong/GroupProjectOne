package com.app.groupprojectapplication.dao;

import com.app.groupprojectapplication.domain.VisaStatus;

import java.util.List;

public interface IVisaStatusDao {
    Integer insertVisa(VisaStatus visaStatus);
    VisaStatus getVisaById(Integer id);
    List<VisaStatus> getVisaByType(String visaType);
    Integer getVisaAuthorizationLeftDay(Integer visaStatusId);
    String getVisaTypeByEmployeeId(Integer employeeId);
    void updateVisaStatus(VisaStatus visaStatus);
}
