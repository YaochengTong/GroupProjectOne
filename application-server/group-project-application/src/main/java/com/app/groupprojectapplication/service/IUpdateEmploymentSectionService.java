package com.app.groupprojectapplication.service;

import java.util.concurrent.ExecutionException;

public interface IUpdateEmploymentSectionService {
    boolean updateEmployment(String title, String workAuthorization, String authorizationEndDate,
                             String authorizationStartDate, String employmentEndDate, String employmentStartDate,
                             Integer userId) throws ExecutionException, InterruptedException;

}
