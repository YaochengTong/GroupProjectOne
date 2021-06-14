package com.app.groupprojectapplication.service;

import com.app.groupprojectapplication.domain.profile.Profile;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

public interface IProfileService {
    List<Profile> getProfile();
    Profile getProfileByEmployeeId(Integer employee_id);

}
