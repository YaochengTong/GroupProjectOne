package com.app.groupprojectapplication.controller;

import com.app.groupprojectapplication.domain.House;
import com.app.groupprojectapplication.service.IHouseService;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/housing")
@CrossOrigin
public class HousingController {

    @Autowired
    private IHouseService iHouseService;

    @PostMapping("/get-houses")
    public String postAllHouse(@RequestParam Map<String, Object> paramMap) {
        System.out.println(paramMap);
        Map<String, Object> resultMap = new HashMap<>();
        List<House> houses = iHouseService.getAllHouse();
        Gson gs = new Gson();
        return gs.toJson(houses);
    }

}
