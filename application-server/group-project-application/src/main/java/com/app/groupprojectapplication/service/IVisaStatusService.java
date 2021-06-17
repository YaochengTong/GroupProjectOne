package com.app.groupprojectapplication.service;

import com.app.groupprojectapplication.domain.visaStatusManagement.VisaStatusInfo;

import java.util.List;

public interface IVisaStatusService {
    List<VisaStatusInfo> getVisaInfo();
    VisaStatusInfo getVisaInfoByUserId(Integer userId);
}
