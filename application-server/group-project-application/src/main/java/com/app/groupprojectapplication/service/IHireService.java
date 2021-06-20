package com.app.groupprojectapplication.service;

import com.app.groupprojectapplication.domain.Employee;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IHireService {
    boolean generateAToken(String email, Integer userId);
    Map<String, Object> onboardSubmission(List<MultipartFile> files, Map<String, Object> paramMap);
    Map<String, Object> getOnboardApplications(Map<String, Object> paramMap);
    Map<String, Object> auditApplications(Map<String, Object> paramMap);
    Map<String, Object> onboardReSubmission(List<MultipartFile> files, Map<String, Object> paramMap);
}
