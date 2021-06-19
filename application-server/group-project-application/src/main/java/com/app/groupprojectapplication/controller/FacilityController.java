package com.app.groupprojectapplication.controller;

import com.app.groupprojectapplication.service.IFacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/housing/detail")
public class FacilityController {

    @Autowired
    private IFacilityService ifacilityService;



}
