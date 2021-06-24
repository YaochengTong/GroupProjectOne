package com.app.groupprojectapplication.service.impl;

import com.app.groupprojectapplication.email.EmailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class NotificationImplTest {

    @Mock
    EmailService emailService;
    @InjectMocks
    NotificationImpl notificationImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSendNotification() {
        boolean result = notificationImpl.sendNotification("email", "nextStep", Integer.valueOf(0));
        Assertions.assertEquals(true, result);
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
