package com.app.groupprojectapplication.controller;

import com.app.groupprojectapplication.exception.ApplicationNotFoundException;
import com.app.groupprojectapplication.service.IHireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/hire")
public class HireController {

    @Autowired
    private IHireService iHireService;

    @PostMapping("/generateToken")
    public Map<String, Object> generateToken(@RequestParam Map<String, Object> params){
        Map<String, Object> resultMap = new HashMap<>();
        String email = params.get("email").toString();
        Integer userId = Integer.parseInt(params.get("user_id").toString());
        iHireService.generateAToken(email, userId);
        resultMap.put("result", "success");
        return resultMap;
    }

    @PostMapping("/submitOnboard")
    public Map<String, Object> submitOnboardApplication(@RequestParam Map<String, Object> paramMap,
                                                      HttpServletRequest request){
        Map<String, Object> resultMap = new HashMap<>();
        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getFiles("file");
        iHireService.onboardSubmission(files, paramMap);
        return resultMap;
    }

    @GetMapping("/getOnboardApplications")
    public Map<String, Object> getOnboardApplication(@RequestParam Map<String, Object> paramMap) throws ApplicationNotFoundException {
        Map<String, Object> resultMap = iHireService.getOnboardApplications(paramMap);
        List<Map<String, Object>> list = (List<Map<String, Object>>) resultMap.get("OngoingApplications");
        if(list.size() == 0){
            throw new ApplicationNotFoundException("Application not found");
        }
        return resultMap;
    }

    @PostMapping("/auditOnboard")
    public Map<String, Object> auditOnboardApplication(@RequestParam Map<String, Object> paramMap){
        Map<String, Object> resultMap = new HashMap<>();
        iHireService.auditApplications(paramMap);
        resultMap.put("result", "success");
        return resultMap;
    }

    @PostMapping("/resubmitOnboard")
    public Map<String, Object> resubmitOnboardApplication(@RequestParam Map<String, Object> paramMap,
                                                        HttpServletRequest request){
        Map<String, Object> resultMap = new HashMap<>();
        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getFiles("file");
        iHireService.onboardSubmission(files, paramMap);
        return resultMap;
    }
}
