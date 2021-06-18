package com.app.groupprojectapplication.controller;

import com.app.groupprojectapplication.service.IVisaStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/visaStatusManagement")
@CrossOrigin
public class VisaStatusController {

    @Autowired
    private IVisaStatusService iVisaStatusService;

    @GetMapping("/{user_id}")
    public Map<String, Object> getVisaStatusInfo(@PathVariable Integer user_id) {
        Map<String, Object> visaStatusInfo = new HashMap<>();
        visaStatusInfo.put("visaStatusInfo", iVisaStatusService.getVisaInfoByUserId(user_id, 0));
        return visaStatusInfo;
    }

    @GetMapping("/all")
    public Map<String, Object> getVisaStatusInfoList() {
        Map<String, Object> visaStatusInfoList = new HashMap<>();
        visaStatusInfoList.put("visaStatusInfoList", iVisaStatusService.getVisaInfo());
        return visaStatusInfoList;
    }
}
