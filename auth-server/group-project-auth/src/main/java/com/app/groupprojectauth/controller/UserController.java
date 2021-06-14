package com.app.groupprojectauth.controller;

import com.app.groupprojectauth.domain.User;
import com.app.groupprojectauth.security.JwtUtil;
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

    private static final String jwtTokenCookieName = "JWT-TOKEN";
    private static final String signingKey = "signingKey";

    @PostMapping("/login")
    public Map<String, Object> login(@RequestParam Map<String, Object> params){
        if(params.get("username") == null || params.get("password") == null){
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("success", false);
            resultMap.put("reason", "Invalid username or password for login");
            return resultMap;
        }
        Map<String, Object> map =  iUserService.userLogin(params);
        User user = (User) map.get("user");
        String token = JwtUtil.generateToken(signingKey, user.getUsername());
        map.put(jwtTokenCookieName, token);
        return map;
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestParam Map<String, Object> params){
        if(params.get("username") == null || params.get("password") == null){
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("success", false);
            resultMap.put("reason", "Invalid username or password for register");
            return resultMap;
        }
        return iUserService.userRegister(params);
    }
}
