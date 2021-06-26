package com.app.groupprojectapplication.dao;

import com.app.groupprojectapplication.domain.VisaStatus;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IVisaStatusDao {
    Integer insertVisa(VisaStatus visaStatus);
    VisaStatus getVisaById(Integer id);
    List<VisaStatus> getVisaByType(String visaType);
    CompletableFuture<Integer> getVisaAuthorizationLeftDay(Integer visaStatusId);
    CompletableFuture<String> getVisaTypeByEmployeeId(Integer employeeId);
    void updateVisaStatus(VisaStatus visaStatus);
}
