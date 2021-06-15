package com.app.groupprojectapplication.dao;

import com.app.groupprojectapplication.domain.VisaStatus;

import java.util.List;

public interface IVisaStatusDao {
    void insertVisa(VisaStatus visaStatus);
    List<VisaStatus> getVisaByType(String visaType);


}
