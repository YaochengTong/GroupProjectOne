package com.app.groupprojectapplication.service.impl;

import com.app.groupprojectapplication.dao.IEmployeeDao;
import com.app.groupprojectapplication.dao.IPersonDao;
import com.app.groupprojectapplication.dao.IUserDao;
import com.app.groupprojectapplication.domain.Person;
import com.app.groupprojectapplication.service.IUpdateContactSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UpdateContactSectionImpl implements IUpdateContactSectionService {

    @Autowired
    IEmployeeDao iEmployeeDao;

    @Autowired
    IPersonDao iPersonDao;

    @Autowired
    IUserDao iUserDao;


    @Override
    public boolean updateContactInfo(String cellPhone, String personalEmail, String workEmail, String workPhone, Integer userId) {
        Person person = iUserDao.getPersonByUserId(userId);
        person.setEmail(personalEmail);
        person.setPrimaryPhone(cellPhone);
        person.setAlternatePhone(workPhone);
        System.out.println("Updated Work Email is :" + workEmail);
        iPersonDao.updatePerson(person);
        return true;
    }
}
