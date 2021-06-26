package com.app.groupprojectapplication.controller;


import com.app.groupprojectapplication.service.IHomeElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/employeeHome")
@CrossOrigin
public class EmployeeHomePageController {

    @Autowired
    private IHomeElementService iHomeElementService;

    @GetMapping("/{employeeId}")
    public Map<String, Object> getHomeElementById(@PathVariable Integer employeeId) throws ExecutionException, InterruptedException {
        Map<String, Object> homeEle = new HashMap<>();
        homeEle.put("Home element for: " + employeeId, iHomeElementService.getHomeElementByEmployeeId(employeeId));

        return homeEle;
    }
}
