package com.app.groupprojectapplication.controller;

import com.app.groupprojectapplication.service.IProfileService;
import com.app.groupprojectapplication.service.IUpdateAddressSectionService;
import com.app.groupprojectapplication.service.IUpdateContactSectionService;
import com.app.groupprojectapplication.service.IUpdateNameSectionService;
//import org.graalvm.compiler.debug.TimeSource;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
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

    @Autowired
    private IUpdateAddressSectionService iUpdateAddressSectionService;

    @Autowired
    private IUpdateContactSectionService iUpdateContactSectionService;

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
    public Map<String, Object> updateProfileNameSection(@RequestParam Map<String, Object> params,
                                             @PathVariable Integer user_id) {
        Map<String, Object> resultMap = new HashMap<>();
        Integer age = Integer.parseInt(params.get("age").toString());
        String avatar = params.get("avatar").toString();
        String fullName = params.get("fullName").toString();
        String preferredName = params.get("preferredName").toString();
        Integer ssn = Integer.parseInt(params.get("ssn").toString());

        // DOB = params

        iUpdateNameSectionService.updateSSN(ssn, user_id);
        iUpdateNameSectionService.updateAvatar(avatar, user_id);
        iUpdateNameSectionService.updateFullName(fullName, user_id);
        /**
         * To do
         */
//        iUpdateNameSectionService.updateAge(age, user_id);
//        iUpdateNameSectionService.updateDOB(, user_id);
//        iUpdateNameSectionService.updatePreferredName(preferredName, user_id);
        resultMap.put("NameUpdateResult", "success");
        return resultMap;
    }

    @PostMapping("/{user_id}/updateAddressSection")
    public Map<String, Object> updateProfileAddressSection(@RequestParam Map<String, Object> params,
                                             @PathVariable Integer user_id) {
//        Map<String,Object> paramsResult =
//                new ObjectMapper().readValue(params, HashMap.class);
        Map<String, Object> resultMap = new HashMap<>();
//        String priAdd1 = params.get("primaryAddr").get("AddressLine1").toString();
//        String priAdd2 = params.get("primaryAddr").get("AddressLine2").toString();
//        String priCity = params.get("primaryAddr").get("City").toString();
//        String priState = params.get("primaryAddr").get("State").toString();
//        String priZip = params.get("primaryAddr").get("Zip").toString();
        String priAdd = params.get("primaryAddr").toString();
        System.out.println(priAdd);

//        System.out.println(priAdd1);
//        System.out.println(priAdd2);
//        System.out.println(priZip);
//        String secAdd1 = params.get("secondaryAddr").get("AddressLine1").toString();
//        String secAdd2 = params.get("secondaryAddr").get("AddressLine2").toString();
//        String secCity = params.get("secondaryAddr").get("City").toString();
//        String secState = params.get("secondaryAddr").get("State").toString();
//        String secZip = params.get("secondaryAddr").get("Zip").toString();
//        iUpdateAddressSectionService.updatePriAdd(priAdd1, priAdd2, priCity, priState, priZip, user_id);
//        iUpdateAddressSectionService.updateSecAdd(secAdd1, secAdd2, secCity, secState, secZip, user_id);

        resultMap.put("AddressUpdateResult", "success");
        return resultMap;
    }

    @PostMapping("/{user_id}/updateContactSection")
    public Map<String, Object> updateProfileContactSection(@RequestParam Map<String, Object> params,
                                                        @PathVariable Integer user_id) {
        Map<String, Object> resultMap = new HashMap<>();
        String ceilphone = params.get("ceilphone").toString();
        String personalEmail = params.get("personalEmail").toString();
        String workEmail = params.get("workEmail").toString();
        String workPhone = params.get("workPhone").toString();
        System.out.println(ceilphone);
        iUpdateContactSectionService.updateContactInfo(ceilphone, personalEmail, workEmail, workPhone, user_id);
        resultMap.put("ContactResult", "success");
        return resultMap;
    }


    @PostMapping("/{user_id}/updateEmergencySection")
    public Map<String, Object> updateProfileEmergencySection(@RequestParam Map<String, Object> params,
                                                        @PathVariable Integer user_id) {
        Map<String, Object> resultMap = new HashMap<>();

//        String priAdd = params.get("primaryAddr").toString();
//        System.out.println(priAdd);
        resultMap.put("EmergencyResult", "success");
        return resultMap;
    }

    @PostMapping("/{user_id}/updateEmploymentSection")
    public Map<String, Object> updateProfileEmploymentSection(@RequestParam Map<String, Object> params,
                                                        @PathVariable Integer user_id) {
        Map<String, Object> resultMap = new HashMap<>();

//        authorizationEndDate: "2021-12-23T20:50:31.000+00:00"
//        authorizationStartDate: "2021-06-14T19:50:29.000+00:00"
//        employmentEndDate: "2021-12-01T20:49:36.000+00:00"
//        employmentStartDate: "2020-02-14T20:49:27.000+00:00"
//        title: "manager"
//        workAuthorization:
//        String priAdd = params.get("primaryAddr").toString();
//        System.out.println(priAdd);
        resultMap.put("EmploymentResult", "success");
        return resultMap;
    }
}
