package com.app.groupprojectapplication.service.impl;

import com.app.groupprojectapplication.dao.*;
import com.app.groupprojectapplication.domain.*;
import com.app.groupprojectapplication.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;
import java.sql.Date;

//don't forget to add @Service in the service implementation classes.
@Service
public class TestServiceImpl implements ITestService {

//    @Autowired
//    private ITestDao iTestDao;

    @Autowired
    private IUserDao iUserDao;

    @Autowired
    private IPersonDao iPersonDao;

    @Autowired
    private IAddressDao iAddressDao;

    @Autowired
    private IContactDao iContactDao;

    @Autowired
    private IHouseDao iHouseDao;

    @Autowired
    private IVisaStatusDao iVisaStatusDao;


    @Override
    //use transactional annotation here.
    @Transactional
    public void do_something(Map<String, Object> paramMap) {
        //get the values from the map, and create POJO here if needed.
//        iTestDao.test();
        iUserDao.getUserById(123);
        iPersonDao.getPersonById(1);

        // get person
        Person person = iPersonDao.getPersonById(100001);
        System.out.println(person.toString());

        // insert person
//        Person person1 = new Person(100002, "Ricard", "Huang", "", "fei.huang@gmail.com", "2349876890", "", "m", "1111111111", new Timestamp(102,11,12,0,0,0,0));
//        iPersonDao.insertPerson(person1);
        Person person1 = iPersonDao.getPersonById(100002);

        // get address by person id
        System.out.println(iAddressDao.getAddressByPersonId(100001).toString());

        // insert address by person
        Address address = new Address("803 Avalon Pines Dr", "", "Coram", "11697", "New York", "NY");
        Address address1 = new Address("407 Avalon Pines Dr", "", "Coram", "11697", "New York", "NY");
        address.setPerson(person1);
        address1.setPerson(person1);
//        iAddressDao.insertAddress(address);
        iAddressDao.insertAddress(address1);
        System.out.println(iAddressDao.getAddressByPersonId(100002));

        // insert contact by person
        Contact contact = new Contact("friend", "employee", (byte)1,(byte)1,(byte)1);
        contact.setPerson(person);
        iContactDao.insertContact(contact);

        // get contact by person id
        System.out.println(iContactDao.getContactByPersonId(100001).toString());

        // insert house by contact
        // Cannot add or update a child row: a foreign key constraint fails (`hr_db`.`house`, CONSTRAINT `house_ibfk_1` FOREIGN KEY (`contact_id`) REFERENCES `contact` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT)
//        House house = new House("105 Ave", 5);
//        house.setContact(contact);
//        iHouseDao.insertHouse(house);

        // get House by house id
//        System.out.println(iHouseDao.getHouseById(1));

        // insert facility ?
        // Facility facility = new Facility("bed", "queen size", 3);

        // issert visa status by user
        User user = iUserDao.getUserById(89);
        VisaStatus visaStatus = new VisaStatus("OPT", (byte)1, new Timestamp(System.currentTimeMillis()));
        visaStatus.setUser(user);
        iVisaStatusDao.insertVisa(visaStatus);

        // get visa by type
        System.out.println(iVisaStatusDao.getVisaByType("OPT"));










    }
}
