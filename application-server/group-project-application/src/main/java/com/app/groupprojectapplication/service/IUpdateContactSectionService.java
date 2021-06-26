package com.app.groupprojectapplication.service;

import java.util.concurrent.ExecutionException;

public interface IUpdateContactSectionService {
    boolean updateContactInfo(String cellPhone, String personalEmail, String workEmail, String workPhone, Integer userId) throws ExecutionException, InterruptedException;
}
