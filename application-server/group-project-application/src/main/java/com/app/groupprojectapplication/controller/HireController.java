package com.app.groupprojectapplication.controller;

import com.app.groupprojectapplication.file.AmazonS3FileService;
import com.app.groupprojectapplication.service.IHireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
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
}
