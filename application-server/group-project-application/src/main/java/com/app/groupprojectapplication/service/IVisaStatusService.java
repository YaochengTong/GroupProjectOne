package com.app.groupprojectapplication.service;

import com.app.groupprojectapplication.domain.visaStatusManagement.VisaStatusInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface IVisaStatusService {
    List<VisaStatusInfo> getVisaInfoList();
    VisaStatusInfo getVisaInfo(Integer userId);
    String findEmailByUserId(Integer userId) throws ExecutionException, InterruptedException;
    boolean updateInfo(Map<String, Object> result) throws ExecutionException, InterruptedException;
    Map<String, Object> uploadAndUpdate(List<MultipartFile> files, Map<String, Object> paramMap, Integer userId) throws ExecutionException, InterruptedException;
}
