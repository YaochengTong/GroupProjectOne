package com.app.groupprojectapplication.service.impl;

import com.app.groupprojectapplication.dao.IPersonDao;
import com.app.groupprojectapplication.dao.ITestDao;
import com.app.groupprojectapplication.dao.IUserDao;
import com.app.groupprojectapplication.service.IPersonService;
import com.app.groupprojectapplication.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.Map;

//don't forget to add @Service in the service implementation classes.
@Service
public class TestServiceImpl implements ITestService {

    @Autowired
    private ITestDao iTestDao;

    @Autowired
    private IUserDao iUserDao;

    @Autowired
    private IPersonDao iPersonDao;


    @Override
    //use transactional annotation here.
    @Transactional
    public void do_something(Map<String, Object> paramMap) {
        //get the values from the map, and create POJO here if needed.
        iTestDao.test();
        iUserDao.getUserById(123);
        iPersonDao.getPersonById(1);
    }
}
