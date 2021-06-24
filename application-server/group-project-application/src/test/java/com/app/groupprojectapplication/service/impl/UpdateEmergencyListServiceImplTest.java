package com.app.groupprojectapplication.service.impl;

import com.app.groupprojectapplication.dao.IAddressDao;
import com.app.groupprojectapplication.dao.IPersonDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class UpdateEmergencyListServiceImplTest {

    @Mock
    IAddressDao iAddressDao;
    @Mock
    IPersonDao iPersonDao;
    @InjectMocks
    UpdateEmergencyListServiceImpl updateEmergencyListServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testUpdateEmergencyList() {
        when(iAddressDao.updateAddress(any())).thenReturn(true);
        when(iPersonDao.insertPerson(any())).thenReturn(Integer.valueOf(0));

        boolean result = updateEmergencyListServiceImpl
            .updateEmergencyList("EP1fullName", "EP1phone", "EP1priAdd1", "EP1priAdd2", "EP1priCity", "EP1priState",
                "EP1priZip", "EP1secAdd1", "EP1secAdd2", "EP1secCity", "EP1secState", "EP1secZip", "EP2fullName",
                "EP2phone", "EP2priAdd1", "EP2priAdd2", "EP2priCity", "EP2priState", "EP2priZip", "EP2secAdd1",
                "EP2secAdd2", "EP2secCity", "EP2secState", "EP2secZip", Integer.valueOf(0));
        Assertions.assertEquals(true, result);
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
