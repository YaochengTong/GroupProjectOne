package com.app.groupprojectapplication.service.impl;

import com.app.groupprojectapplication.dao.IAddressDao;
import com.app.groupprojectapplication.dao.IContactDao;
import com.app.groupprojectapplication.dao.IHouseDao;
import com.app.groupprojectapplication.dao.IPersonDao;
import com.app.groupprojectapplication.dao.IUserDao;
import com.app.groupprojectapplication.dao.IVisaStatusDao;
import com.app.groupprojectapplication.domain.Address;
import com.app.groupprojectapplication.domain.Contact;
import com.app.groupprojectapplication.domain.Person;
import com.app.groupprojectapplication.domain.User;
import com.app.groupprojectapplication.domain.VisaStatus;
import java.util.Arrays;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class TestServiceImplTest {

    @Mock
    IUserDao iUserDao;
    @Mock
    IPersonDao iPersonDao;
    @Mock
    IAddressDao iAddressDao;
    @Mock
    IContactDao iContactDao;
    @Mock
    IHouseDao iHouseDao;
    @Mock
    IVisaStatusDao iVisaStatusDao;
    @InjectMocks
    TestServiceImpl testServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testDo_something() {
        when(iUserDao.getUserById(anyInt())).thenReturn(new User("username", "email", "password"));
        when(iPersonDao.getPersonById(anyInt())).thenReturn(
            new Person(Integer.valueOf(0), "firstName", "lastName", "middleName", "email", "primaryPhone",
                "alternatePhone", "gender", "ssn", null));
        when(iAddressDao.getAddressByPersonId(anyInt())).thenReturn(Arrays
            .<Address>asList(new Address("addressLine1", "addressLine2", "city", "zipCode", "stateName", "stateAbbr")));
        when(iContactDao.getContactByPersonId(anyInt()))
            .thenReturn(Arrays.<Contact>asList(new Contact("relationship", "title", (byte) 0, (byte) 0, (byte) 0)));
        when(iVisaStatusDao.insertVisa(any())).thenReturn(Integer.valueOf(0));
        when(iVisaStatusDao.getVisaByType(anyString()))
            .thenReturn(Arrays.<VisaStatus>asList(new VisaStatus("visaType", (byte) 0, null)));

        testServiceImpl.do_something(new HashMap<String, Object>() {{put("String", "paramMap");}});
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
