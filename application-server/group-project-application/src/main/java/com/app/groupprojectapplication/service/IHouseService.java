package com.app.groupprojectapplication.service;

import com.app.groupprojectapplication.domain.House;
import com.app.groupprojectapplication.domain.HouseElement.HousePageInfo;
import java.util.List;

public interface IHouseService {

    HousePageInfo getHouseById(Integer id);
    List<HousePageInfo> getAllHouse();
    void updateHouse(HousePageInfo housePageInfo);
    House getHouseByUserId(Integer userId);
    List<House> getAllTestHouses();

    void addHouseByUserId(HousePageInfo housePageInfo, Integer userId);

}
