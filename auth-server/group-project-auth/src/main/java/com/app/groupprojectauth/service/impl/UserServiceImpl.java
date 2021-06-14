package com.app.groupprojectauth.service.impl;

import com.app.groupprojectauth.dao.IUserDao;
import com.app.groupprojectauth.domain.User;
import com.app.groupprojectauth.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao iUserDao;

    @Override
    public Map<String, Object> userLogin(Map<String, Object> param) {
        Map<String, Object> resultMap = new HashMap<>();
        User user = iUserDao.userLogin(param);
        if(user == null){
            resultMap.put("loginSuccess", false);
        }
        else{
            resultMap.put("loginSuccess", true);
            resultMap.put("user", user);
        }
        return resultMap;
    }
}
