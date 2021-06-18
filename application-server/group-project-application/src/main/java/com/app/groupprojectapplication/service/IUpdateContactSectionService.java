package com.app.groupprojectapplication.service;

public interface IUpdateContactSectionService {
    boolean updateContactInfo(String cellPhone, String personalEmail, String workEmail, String workPhone, Integer userId);
}
