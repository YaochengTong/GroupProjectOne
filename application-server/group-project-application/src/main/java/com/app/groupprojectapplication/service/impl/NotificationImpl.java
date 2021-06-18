package com.app.groupprojectapplication.service.impl;

import com.app.groupprojectapplication.email.EmailService;
import com.app.groupprojectapplication.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationImpl implements INotificationService {


    @Autowired
    private EmailService emailService;


    @Override
    public boolean sendNotification(String email, String nextStep, Integer userId) {
        String text = "Prvious Documents are approved, please check the next Step and " +
                "upload necessary document: " + nextStep;
        emailService.sendMail(email, "Notification for next Step", text);

        return true;
    }
}
