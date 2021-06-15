package com.app.groupprojectapplication.service;

import com.app.groupprojectapplication.domain.homeElement.HomeElement;

public interface IHomeElementService {
    HomeElement getHomeElementByEmployeeId(Integer employeeId);
}
