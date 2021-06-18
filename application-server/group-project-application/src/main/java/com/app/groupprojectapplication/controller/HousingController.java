package com.app.groupprojectapplication.controller;

import com.app.groupprojectapplication.dao.IHouseDao;
import com.app.groupprojectapplication.domain.HouseElement.HousePageInfo;
import com.app.groupprojectapplication.service.IHouseService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/housing")
public class HousingController {

    @Autowired
    private IHouseService iHouseService;
    @Autowired
    private IHouseDao iHouseDao;

    @GetMapping("/get-houses")
    public Map<String, Object> postAllHouse() {
        Map<String, Object> resultMap = new HashMap<>();
        List<HousePageInfo> houses = iHouseService.getAllHouse();
        resultMap.put("allHouse", houses);
        return resultMap;
    }

    @GetMapping("/get-house/{houseId}")
    public Map<String, Object> getHouseById(@PathVariable Integer houseId) {
        Map<String, Object> resultMap = new HashMap<>();
        HousePageInfo h = iHouseService.getHouseById(houseId);
        System.out.println(h.toString());
        resultMap.put("house", h);
        return resultMap;
    }

}
