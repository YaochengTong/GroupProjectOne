package com.app.groupprojectapplication.controller;

import com.app.groupprojectapplication.service.IProfileService;
import com.app.groupprojectapplication.service.IUpdateNameSectionService;
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

    @Autowired
    private IUpdateNameSectionService iUpdateNameSectionService;

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

    @PostMapping("/{user_id}/updateNameSection")
    public Map<String, Object> updateProfile(@RequestParam Map<String, Object> params,
                                             @PathVariable Integer user_id) {
        System.out.println(params);
        Map<String, Object> resultMap = new HashMap<>();
        Integer age = Integer.parseInt(params.get("age").toString());
        String avatar = params.get("avatar").toString();
        String fullName = params.get("fullName").toString();
        String preferredName = params.get("preferredName").toString();
        Integer ssn = Integer.parseInt(params.get("ssn").toString());

        System.out.println("userId: " + user_id);
        System.out.println(age);
        System.out.println(avatar);

        iUpdateNameSectionService.updateSSN(ssn, user_id);

//
//        age: 20
//        avatar: "path_to_avartar"
//        dob: "2001-01-01T20:45:45.000+00:00"
//        fullName: "Admin Admin"
//        preferredName: "Admin Admin"
//        ssn: "111111222"
        resultMap.put("result", "success");
        return resultMap;
    }
}
