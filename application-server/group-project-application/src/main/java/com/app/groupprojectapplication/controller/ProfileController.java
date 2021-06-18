package com.app.groupprojectapplication.controller;

import com.app.groupprojectapplication.service.*;
//import org.graalvm.compiler.debug.TimeSource;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @Autowired
    private IUpdateEmploymentSectionService iUpdateEmploymentSectionService;

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
                                             @PathVariable Integer user_id) throws IOException {
        Map<String, Object> resultMap = new HashMap<>();


//        String priAdd = params.get("primaryAddr").toString();
//        System.out.println(priAdd);

//        Map<String,Object> result = new ObjectMapper().readValue(params.get("primaryAddr"), HashMap.class);



//        Map<String, Object> priAddress = new HashMap<>(params.get("priAddress"));
//        String priAdd1 = params.get("primaryAddr").get("AddressLine1").toString();
//        String priAdd2 = params.get("primaryAddr").get("AddressLine2").toString();
//        String priCity = params.get("primaryAddr").get("City").toString();
//        String priState = params.get("primaryAddr").get("State").toString();
//        String priZip = params.get("primaryAddr").get("Zip").toString();
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
        String title = params.get("title").toString();
        String workAuthorization = params.get("workAuthorization").toString();

        String authorizationEndDate = params.get("authorizationEndDate").toString();
        String authorizationStartDate = params.get("authorizationStartDate").toString();
        String employmentEndDate = params.get("employmentEndDate").toString();
        String employmentStartDate = params.get("employmentStartDate").toString();

        iUpdateEmploymentSectionService.updateEmployment(title, workAuthorization, authorizationStartDate, authorizationEndDate,
                employmentStartDate, employmentEndDate, user_id);
        resultMap.put("EmploymentResult", "success");
        return resultMap;
    }
}



