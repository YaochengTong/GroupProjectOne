package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.domain.Address;
import java.util.Arrays;
import java.util.List;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class AddressDaoImplTest {

    @Mock
    SessionFactory sessionFactory;
    @InjectMocks
    AddressDaoImpl addressDaoImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAddressByPersonId() {
        List<Address> result = addressDaoImpl.getAddressByPersonId(Integer.valueOf(0));
        Assertions.assertEquals(Arrays
                .<Address>asList(new Address("addressLine1", "addressLine2", "city", "zipCode", "stateName",
                    "stateAbbr")),
            result);
    }

    @Test
    void testInsertAddress() {
        addressDaoImpl
            .insertAddress(new Address("addressLine1", "addressLine2", "city", "zipCode", "stateName", "stateAbbr"));
    }

    @Test
    void testUpdateAddress() {
        boolean result = addressDaoImpl
            .updateAddress(new Address("addressLine1", "addressLine2", "city", "zipCode", "stateName", "stateAbbr"));
        Assertions.assertEquals(true, result);
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
