package com.app.groupprojectapplication.service;

import java.sql.Timestamp;
import java.util.concurrent.ExecutionException;

public interface IUpdateNameSectionService {
    boolean updateFullName(String fullName, Integer userId) throws ExecutionException, InterruptedException;
    boolean updateAge(Integer age, Integer userId) throws ExecutionException, InterruptedException;
    boolean updateSSN(Integer ssn, Integer userId) throws ExecutionException, InterruptedException;
    boolean updateDOB(String DOB, Integer userId) throws ExecutionException, InterruptedException;
    boolean updateAvatar(String avatar, Integer userId) throws ExecutionException, InterruptedException;
    boolean updatePreferredName(String preferredName, Integer userId);
}
