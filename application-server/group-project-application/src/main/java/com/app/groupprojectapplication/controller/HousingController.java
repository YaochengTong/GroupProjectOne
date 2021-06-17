package com.app.groupprojectapplication.controller;

import com.app.groupprojectapplication.domain.House;
import com.app.groupprojectapplication.service.IHouseService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/housing")
@CrossOrigin
public class HousingController {

    @Autowired
    private IHouseService iHouseService;

    @GetMapping("/all")
    public Map<String, Object> postAllHouse() {
        Map<String, Object> resultMap = new HashMap<>();
        List<House> houses = iHouseService.getAllHouse();
        resultMap.put("AllHouse", houses);
        // TODO: unable to get house list from POJO
        System.out.println(houses);
        return resultMap;

    }

}
