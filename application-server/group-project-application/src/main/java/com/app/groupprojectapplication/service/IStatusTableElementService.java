package com.app.groupprojectapplication.service;

import com.app.groupprojectapplication.domain.statusTableElement.StatusTableElement;
import com.app.groupprojectapplication.domain.statusTableElement.StatusTableElement;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface IStatusTableElementService {
    List<StatusTableElement> getStatus() throws ExecutionException, InterruptedException;
    StatusTableElement getStatusByUserId(Integer userId) throws ExecutionException, InterruptedException;
}
