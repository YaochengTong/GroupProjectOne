package com.app.groupprojectapplication.service;

import com.app.groupprojectapplication.domain.visaStatusManagement.VisaStatusInfo;

import java.util.List;
import java.util.Map;

public interface IVisaStatusService {
    List<VisaStatusInfo> getVisaInfo();
    VisaStatusInfo getVisaInfoByUserId(Integer userId, Integer index);
    String findEmailByUserId(Integer userId);
    boolean updateInfo(Map<String, Object> result);
}
