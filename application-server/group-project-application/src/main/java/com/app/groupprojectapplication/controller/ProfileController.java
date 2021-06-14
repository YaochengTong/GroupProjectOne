package com.app.groupprojectapplication.controller;

import com.app.groupprojectapplication.service.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/profile")
@CrossOrigin
public class ProfileController {

    @Autowired
    private IProfileService iProfileService;

    @GetMapping("/{employee_id}")
    public Map<String, Object> getProfile(@PathVariable Integer employee_id) {
        Map<String, Object> profile = new HashMap<>();
        profile.put("profile" + employee_id, iProfileService.getProfileByEmployeeId(employee_id));
        return profile;
    }

    @GetMapping("/admin")
    public Map<String, Object> getProfileList() {
        Map<String, Object>  profileList = new HashMap<>();
        profileList.put("AllProfile", iProfileService.getProfile());
        return profileList;
    }
}
