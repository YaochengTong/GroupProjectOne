package com.app.groupprojectapplication.controller;

import com.app.groupprojectapplication.mongodbdomain.MApplicationWorkFlow;
import com.app.groupprojectapplication.mongodbrepo.ApplicationWorkFlowRepo;
import com.app.groupprojectapplication.service.IMongoHireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@CrossOrigin
@RestController
@RequestMapping("/mhire")
public class MongoDBHireController {

    @Autowired
    IMongoHireService mongoHireService;

    @PostMapping("/submitOnboard")
    public Map<String, Object> submitOnboardApplication(@RequestParam Map<String, Object> paramMap,
                                                        HttpServletRequest request) throws ExecutionException, InterruptedException {
        Map<String, Object> resultMap = new HashMap<>();
        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getFiles("file");
        mongoHireService.onboardSubmission(files, paramMap);
        return resultMap;
    }
}
