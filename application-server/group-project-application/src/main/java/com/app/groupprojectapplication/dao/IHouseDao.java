package com.app.groupprojectapplication.dao;

import com.app.groupprojectapplication.domain.Employee;
import com.app.groupprojectapplication.domain.House;
import java.util.List;

public interface IHouseDao {

    House getHouseById(Integer id);

    void insertHouse(House house);

    List<House> getAllHouse();

    int getContactIdByHouseId(Integer house_id);

    void updateHouseAddressByHouseId(String address, Integer id);

    void updateHouseNumberOfPersonByHouseId(Integer n, Integer id);

}
