package com.app.groupprojectapplication.service;

import com.app.groupprojectapplication.domain.Employee;

import java.util.List;

public interface IHireService {
    boolean generateAToken(String email, Integer userId);
}
