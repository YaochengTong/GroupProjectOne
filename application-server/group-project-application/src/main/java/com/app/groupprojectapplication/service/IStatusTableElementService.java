package com.app.groupprojectapplication.service;

import com.app.groupprojectapplication.domain.statusTableElement.StatusTableElement;
import com.app.groupprojectapplication.domain.statusTableElement.StatusTableElement;

import java.util.List;

public interface IStatusTableElementService {
    List<StatusTableElement> getStatus();
    StatusTableElement getStatusByEmployeeId(Integer userId);
}
