package com.app.groupprojectapplication.controller;


import com.app.groupprojectapplication.service.IStatusTableElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/statusTable")
@CrossOrigin
public class StatusTableController {

    @Autowired
    private IStatusTableElementService iStatusTableElementService;

    @GetMapping("/all")
    public Map<String, Object> getStatusList() throws ExecutionException, InterruptedException {
        Map<String, Object> statusList = new HashMap<>();
        statusList.put("AllStatus", iStatusTableElementService.getStatus());
        return statusList;
    }
}
