package com.app.groupprojectauth.service;

import java.util.Map;

public interface IUserService {
    Map<String, Object> userLogin(Map<String, Object> param);
    Map<String, Object> userRegister(Map<String, Object> param);
}
