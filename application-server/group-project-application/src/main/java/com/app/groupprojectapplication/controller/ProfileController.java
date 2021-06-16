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

    @GetMapping("/{user_id}")
    public Map<String, Object> getProfile(@PathVariable Integer user_id) {
        Map<String, Object> profile = new HashMap<>();
        profile.put("profile", iProfileService.getProfileByEmployeeId(user_id));
        return profile;
    }

    @GetMapping("/all")
    public Map<String, Object> getProfileList() {
        Map<String, Object>  profileList = new HashMap<>();
        profileList.put("AllProfile", iProfileService.getProfile());
        return profileList;
    }

    @PostMapping("/{user_id}/update")
    public Map<String, Object> updateProfile(@RequestParam Map<String, Object> params) {
        System.out.println(params);
        Map<String, Object> resultMap = new HashMap<>();
        // to do: get data from frontend
        // to do: update database
        // to do: return success or failed reason
        return resultMap;
    }
}
