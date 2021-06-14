package com.app.groupprojectapplication.controller;

import com.app.groupprojectapplication.dao.impl.FacilityDaoImpl;
import com.app.groupprojectapplication.dao.impl.FacilityReportDaoImpl;
import com.app.groupprojectapplication.dao.impl.PersonDaoImpl;
import com.app.groupprojectapplication.dao.impl.UserDaoImpl;
import com.app.groupprojectapplication.domain.FacilityReport;
import com.app.groupprojectapplication.domain.Person;
import com.app.groupprojectapplication.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
@CrossOrigin
public class TestController {

    @Autowired
    private ITestService iTestService;

    @Autowired
    private PersonDaoImpl personDao;

    @Autowired
    private UserDaoImpl userDao;

    @Autowired
    private FacilityReportDaoImpl ifacilityReportDao;

    @GetMapping("/get")
    //To see the example output, visit http://localhost:8999/test/get?a=1
    public Map<String, Object> testMethod(@RequestParam Map<String, Object> paramMap){
        //do not use domain/POJO in the controller.
        //use a map to receive all parameters from the frontend.
        //pass the map to the service layer and create POJO based on the values in the map.
        iTestService.do_something(paramMap);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("a", paramMap.get("a"));
        //always return a map to the frontend.
        return resultMap;
    }


    @GetMapping("/getDao")
    public void test() {
        Person p = personDao.getPersonById(100001);

        System.out.println(p.getEmail());
    }


    @GetMapping("/deleteUser")
    public void userDeleteTest() {
        userDao.deleteUserById(555);
        System.out.println("----Deleted----");
    }


    @GetMapping("/updateUser")
    public void userUpdate() {
        userDao.updateUser(123);
        System.out.println("-----Update----");
    }
}
