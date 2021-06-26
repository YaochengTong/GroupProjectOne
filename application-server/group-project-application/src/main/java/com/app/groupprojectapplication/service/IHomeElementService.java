package com.app.groupprojectapplication.service;

import com.app.groupprojectapplication.domain.homeElement.HomeElement;

import java.util.concurrent.ExecutionException;

public interface IHomeElementService {
    HomeElement getHomeElementByEmployeeId(Integer employeeId) throws ExecutionException, InterruptedException;
}
