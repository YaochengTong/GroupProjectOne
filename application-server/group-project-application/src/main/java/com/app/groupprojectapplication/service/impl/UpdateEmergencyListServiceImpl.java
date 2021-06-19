package com.app.groupprojectapplication.service.impl;

import com.app.groupprojectapplication.dao.IAddressDao;
import com.app.groupprojectapplication.dao.IPersonDao;
import com.app.groupprojectapplication.domain.Address;
import com.app.groupprojectapplication.domain.Person;
import com.app.groupprojectapplication.service.IUpdateAddressSectionService;
import com.app.groupprojectapplication.service.IUpdateEmergencyListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;


@Service
@Transactional
public class UpdateEmergencyListServiceImpl implements IUpdateEmergencyListService {

    @Autowired
    IAddressDao iAddressDao;


    @Autowired
    IPersonDao iPersonDao;

    @Override
    public boolean updateEmergencyList(String EP1fullName, String EP1phone, String EP1priAdd1,
                                       String EP1priAdd2, String EP1priCity, String EP1priState,
                                       String EP1priZip, String EP1secAdd1, String EP1secAdd2,
                                       String EP1secCity, String EP1secState, String EP1secZip,
                                       String EP2fullName, String EP2phone, String EP2priAdd1,
                                       String EP2priAdd2, String EP2priCity, String EP2priState,
                                       String EP2priZip, String EP2secAdd1, String EP2secAdd2,
                                       String EP2secCity, String EP2secState, String EP2secZip,
                                       Integer userId) {


        Person person1 = new Person();
        String firstName1 = EP1fullName.split(" ")[0];
        String lastName1 = EP1fullName.split(" ")[1];
        person1.setFirstName(firstName1);
        person1.setLastName(lastName1);
        person1.setPrimaryPhone(EP1phone);
        person1.setEmail("NO Info for Emergency contact");
        String defaultDOB = "1990-01-01 00:00:00";
        person1.setDob(Timestamp.valueOf(defaultDOB));
        Integer person_id = iPersonDao.insertPerson(person1);
        person1.setId(person_id);
        iPersonDao.updatePerson(person1);

        Address EP1address1 = new Address();
        EP1address1.setAddressLine1(EP1priAdd1);
        EP1address1.setAddressLine2(EP1priAdd2);
        EP1address1.setCity(EP1priCity);
        EP1address1.setStateName(EP1priState);
        EP1address1.setZipCode(EP1priZip);
        EP1address1.setPerson(person1);
        EP1address1.setStateAbbr(EP1priState.substring(0, 2).toUpperCase());
        iAddressDao.updateAddress(EP1address1);
        Address EP1address2 = new Address();
        EP1address2.setAddressLine1(EP1secAdd1);
        EP1address2.setAddressLine2(EP1secAdd2);
        EP1address2.setCity(EP1secCity);
        EP1address2.setStateName(EP1secState);
        EP1address2.setZipCode(EP1secZip);
        EP1address2.setPerson(person1);
        EP1address2.setStateAbbr(EP2priState.substring(0, 2).toUpperCase());
        iAddressDao.updateAddress(EP1address2);


        return true;
    }
}
