package com.app.groupprojectapplication.service;
import org.springframework.web.multipart.MultipartFile;


public interface INotificationService {
    boolean sendNotification(String email, String nextStep, Integer userId);
}
