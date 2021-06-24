package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.domain.RegistrationToken;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class RegistrationTokenDaoImplTest {

    @Mock
    RegistrationToken registrationToken;
    @Mock
    SessionFactory sessionFactory;
    @InjectMocks
    RegistrationTokenDaoImpl registrationTokenDaoImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetRegistrationTokenById() {
        RegistrationToken result = registrationTokenDaoImpl.getRegistrationTokenById(Integer.valueOf(0));
        Assertions.assertEquals(new RegistrationToken(), result);
    }

    @Test
    void testInsertRegistrationToke() {
        registrationTokenDaoImpl.insertRegistrationToke(new RegistrationToken());
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
