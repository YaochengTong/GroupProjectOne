package com.app.groupprojectapplication.dao;

import com.app.groupprojectapplication.domain.House;


public interface IHouseDao {
    House getHouseById(Integer id);
    void insertHouse(House house);
}
