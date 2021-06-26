package com.app.groupprojectapplication.service;

import com.app.groupprojectapplication.domain.profile.Profile;
import com.app.groupprojectapplication.domain.profile.Summary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface IProfileService {
    List<Profile> getProfile();
    Profile getProfileByEmployeeId(Integer user_id) throws ExecutionException, InterruptedException;
    List<Summary> getSummary();

    Map<String, Object> uploadAvatar(List<MultipartFile> files, Map<String, Object> paramMap) throws ExecutionException, InterruptedException;
}
