package com.app.groupprojectapplication.service;

import com.app.groupprojectapplication.domain.visaStatusManagement.VisaStatusInfo;

import java.util.List;
import java.util.Map;

public interface IVisaStatusService {
    List<VisaStatusInfo> getVisaInfoList();
    VisaStatusInfo getVisaInfo(Integer userId);
    String findEmailByUserId(Integer userId);
    String updateInfo(Map<String, Object> result);

}
