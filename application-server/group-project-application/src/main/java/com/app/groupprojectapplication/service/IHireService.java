package com.app.groupprojectapplication.service;

import com.app.groupprojectapplication.domain.Employee;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface IHireService {
    boolean generateAToken(String email, Integer userId) throws ExecutionException, InterruptedException;
    Map<String, Object> onboardSubmission(List<MultipartFile> files, Map<String, Object> paramMap) throws ExecutionException, InterruptedException;
    Map<String, Object> getOnboardApplications(Map<String, Object> paramMap) throws ExecutionException, InterruptedException;
    Map<String, Object> auditApplications(Map<String, Object> paramMap) throws ExecutionException, InterruptedException;
    Map<String, Object> onboardReSubmission(List<MultipartFile> files, Map<String, Object> paramMap);
}
