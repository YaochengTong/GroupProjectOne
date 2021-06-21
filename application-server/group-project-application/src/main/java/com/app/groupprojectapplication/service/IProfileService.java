package com.app.groupprojectapplication.service;

import com.app.groupprojectapplication.domain.profile.Profile;
import com.app.groupprojectapplication.domain.profile.Summary;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

public interface IProfileService {
    List<Profile> getProfile();
    Profile getProfileByEmployeeId(Integer user_id);
    List<Summary> getSummary();
}
