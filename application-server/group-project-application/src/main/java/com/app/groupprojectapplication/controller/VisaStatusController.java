package com.app.groupprojectapplication.controller;

import com.app.groupprojectapplication.email.EmailService;
import com.app.groupprojectapplication.service.IVisaStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/visa-status-management")
@CrossOrigin
public class VisaStatusController {

    @Autowired
    private IVisaStatusService iVisaStatusService;

    @Autowired
    private EmailService emailService;

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

    @PostMapping("/update")
    public Map<String, Object> updateVisaStatusInfo(@RequestParam Map<String, Object> params) {
        Map<String, Object> resultMap = new HashMap<>();
        if (params != null) {
            System.out.println(params);
            resultMap.put("success", true);

            // to do
            // backend update database

        } else {
            System.err.println("Did not get data");
            resultMap.put("reason", "Got Null data");
        }
        return resultMap;
    }

    @PostMapping("/send-notification")
    public Map<String, Object> sendNotification(@RequestParam Map<String, Object> params) {
        Map<String, Object> resultMap = new HashMap<>();
        if (params != null) {
            System.out.println(params);
            String title = "Reminder: Report Updates on Visa Status";
            String content = "Please check your visa status. Based on our database, you may need to provide further document ( " + params.get("message") + " ) to maintain your legal work authorization in 90 days.\n" +
                    "If you are in a moderate situation, please ignore this email.";
            String email = iVisaStatusService.findEmailByUserId(Integer.parseInt((String)params.get("userId")));
            emailService.sendMail(email, title, content);
            resultMap.put("success", true);
            resultMap.put("email", email);
        } else {
            resultMap.put("success", false);
            resultMap.put("reason", "Got Null data");
        }
        return resultMap;
    }
}
