package com.app.groupprojectapplication.controller;

import com.app.groupprojectapplication.service.IHireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
}
