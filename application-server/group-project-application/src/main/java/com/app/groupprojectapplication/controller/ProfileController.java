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
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
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

    @Autowired
    private IUpdateEmergencyListService iUpdateEmergencyListService;

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

    @GetMapping("/allSummary")
    public Map<String, Object> getSummaryList() {
        Map<String, Object>  summaryList = new HashMap<>();
        summaryList.put("AllSummary", iProfileService.getSummary());
        return summaryList;
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
        String dob = params.get("dob").toString();

        iUpdateNameSectionService.updateSSN(ssn, user_id);
        iUpdateNameSectionService.updateAvatar(avatar, user_id);
        iUpdateNameSectionService.updateFullName(fullName, user_id);
        iUpdateNameSectionService.updateAge(age, user_id);
        iUpdateNameSectionService.updateDOB(dob, user_id);
        iUpdateNameSectionService.updatePreferredName(preferredName, user_id);
        resultMap.put("NameUpdateResult", "success");
        return resultMap;
    }

    @PostMapping("/{user_id}/updateAddressSection")
    public Map<String, Object> updateProfileAddressSection(@RequestParam Map<String, Object> params,
                                             @PathVariable Integer user_id) throws IOException {
        Map<String, Object> resultMap = new HashMap<>();

        String priAdd1 = params.get("priAdd1").toString();
        String priAdd2 = params.get("priAdd2").toString();
        String priCity = params.get("priCity").toString();
        String priState = params.get("priState").toString();
        String priZip = params.get("priZip").toString();
        String secAdd1 = params.get("secAdd1").toString();
        String secAdd2 = params.get("secAdd2").toString();
        String secCity= params.get("secCity").toString();
        String secState = params.get("secState").toString();
        String secZip = params.get("secZip").toString();

        iUpdateAddressSectionService.updateAdd(priAdd1, priAdd2, priCity, priState, priZip,
                secAdd1, secAdd2, secCity, secState, secZip, user_id);

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
        String EP1fullName = params.get("EP1fullName").toString();
        String EP1phone = params.get("EP1phone").toString();
        String EP1priAdd1 = params.get("EP1priAdd1").toString();
        String EP1priAdd2 = params.get("EP1priAdd2").toString();
        String EP1priCity = params.get("EP1priCity").toString();
        String EP1priState = params.get("EP1priState").toString();
        String EP1priZip = params.get("EP1priZip").toString();
        String EP1secAdd1 = params.get("EP1secAdd1").toString();
        String EP1secAdd2 = params.get("EP1secAdd2").toString();
        String EP1secCity = params.get("EP1secCity").toString();
        String EP1secState = params.get("EP1secState").toString();
        String EP1secZip = params.get("EP1secZip").toString();
        String EP2fullName = params.get("EP2fullName").toString();
        String EP2phone = params.get("EP2phone").toString();
        String EP2priAdd1 = params.get("EP2priAdd1").toString();
        String EP2priAdd2 = params.get("EP2priAdd2").toString();
        String EP2priCity = params.get("EP2priCity").toString();
        String EP2priState = params.get("EP2priState").toString();
        String EP2priZip = params.get("EP2priZip").toString();
        String EP2secAdd1 = params.get("EP2secAdd1").toString();
        String EP2secAdd2 = params.get("EP2secAdd2").toString();
        String EP2secCity = params.get("EP2secCity").toString();
        String EP2secState = params.get("EP2secState").toString();
        String EP2secZip = params.get("EP2secZip").toString();


        iUpdateEmergencyListService.updateEmergencyList(EP1fullName, EP1phone, EP1priAdd1,
                EP1priAdd2, EP1priCity, EP1priState,
                EP1priZip, EP1secAdd1, EP1secAdd2,
                EP1secCity, EP1secState, EP1secZip,
                EP2fullName, EP2phone, EP2priAdd1,
                EP2priAdd2, EP2priCity, EP2priState,
                EP2priZip, EP2secAdd1, EP2secAdd2,
                EP2secCity, EP2secState, EP2secZip,
                user_id);
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



