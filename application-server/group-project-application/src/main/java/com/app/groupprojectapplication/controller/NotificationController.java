package com.app.groupprojectapplication.controller;

import com.app.groupprojectapplication.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    private INotificationService iNotificationService;

    @PostMapping("/nextStep")
    public Map<String, Object> sendNotification(@RequestParam Map<String, Object> params) {
        Map<String, Object> resultMap = new HashMap<>();
        String email = params.get("email").toString();
        Integer userId = Integer.parseInt(params.get("userId").toString());
        String nextStep = params.get("nextStep").toString();
        iNotificationService.sendNotification(email, nextStep, userId);
        resultMap.put("result", "Success");

        return resultMap;
    }
}
