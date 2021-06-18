package com.app.groupprojectapplication.service;

public interface IUpdateEmploymentSectionService {
    boolean updateEmployment(String title, String workAuthorization, String authorizationEndDate,
                             String authorizationStartDate, String employmentEndDate, String employmentStartDate,
                             Integer userId);

}
