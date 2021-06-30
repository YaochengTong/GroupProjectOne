package com.app.groupprojectapplication.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface IMongoHireService {
    Map<String, Object> onboardSubmission(List<MultipartFile> files, Map<String, Object> paramMap) throws ExecutionException, InterruptedException;

}
