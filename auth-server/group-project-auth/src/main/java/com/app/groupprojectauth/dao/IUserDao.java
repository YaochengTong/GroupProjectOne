package com.app.groupprojectauth.dao;

import com.app.groupprojectauth.domain.User;

import java.util.Map;

public interface IUserDao {
    User userLogin(Map<String, Object> param);
}
