package com.app.groupprojectapplication.controller;

import com.app.groupprojectapplication.domain.HouseElement.HouseFacilityReportInfo;
import com.app.groupprojectapplication.service.IFacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@CrossOrigin
@RestController
@RequestMapping("/facility-report")
public class FacilityController {

    @Autowired
    private IFacilityService iFacilityService;

    @PostMapping("/post-facility-report/{userId}")
    private String getFacilityReportsByEmployeeId(@PathVariable Integer userId,
        @RequestBody HouseFacilityReportInfo fri) throws ExecutionException, InterruptedException {
        System.out.println("received");
        iFacilityService.addFacilityReportByUserId(fri, userId);
        return "success";
    }

}
