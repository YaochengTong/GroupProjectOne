package com.app.groupprojectauth.controller;

import com.app.groupprojectauth.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestParam Map<String, Object> params){
        if(params.get("username") == null || params.get("password") == null){
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("loginSuccess", false);
        }
        return iUserService.userLogin(params);
    }
}
