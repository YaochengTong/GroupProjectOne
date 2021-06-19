package com.app.groupprojectapplication.service;

import java.sql.Timestamp;

public interface IUpdateNameSectionService {
    boolean updateFullName(String fullName, Integer userId);
    boolean updateAge(Integer age, Integer userId);
    boolean updateSSN(Integer ssn, Integer userId);
    boolean updateDOB(String DOB, Integer userId);
    boolean updateAvatar(String avatar, Integer userId);
    boolean updatePreferredName(String preferredName, Integer userId);
}
