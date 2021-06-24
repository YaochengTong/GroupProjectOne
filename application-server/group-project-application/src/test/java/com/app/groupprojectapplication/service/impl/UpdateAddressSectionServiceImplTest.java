package com.app.groupprojectapplication.service.impl;

import com.app.groupprojectapplication.dao.IAddressDao;
import com.app.groupprojectapplication.dao.IEmployeeDao;
import com.app.groupprojectapplication.dao.IPersonDao;
import com.app.groupprojectapplication.dao.IUserDao;
import com.app.groupprojectapplication.domain.Address;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class UpdateAddressSectionServiceImplTest {

    @Mock
    IEmployeeDao iEmployeeDao;
    @Mock
    IPersonDao iPersonDao;
    @Mock
    IAddressDao iAddressDao;
    @Mock
    IUserDao iUserDao;
    @InjectMocks
    UpdateAddressSectionServiceImpl updateAddressSectionServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testUpdateAdd() {
        when(iAddressDao.getAddressByPersonId(anyInt())).thenReturn(Arrays
            .<Address>asList(new Address("addressLine1", "addressLine2", "city", "zipCode", "stateName", "stateAbbr")));
        when(iAddressDao.updateAddress(any())).thenReturn(true);
        when(iUserDao.getPersonIdByUserId(anyInt())).thenReturn(Integer.valueOf(0));

        boolean result = updateAddressSectionServiceImpl
            .updateAdd("priAdd1", "priAdd2", "priCity", "priState", "priZip", "secAdd1", "secAdd2", "secCity",
                "secState", "secZip", Integer.valueOf(0));
        Assertions.assertEquals(true, result);
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
