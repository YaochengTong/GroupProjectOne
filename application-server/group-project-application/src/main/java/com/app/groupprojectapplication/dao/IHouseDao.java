package com.app.groupprojectapplication.dao;

import com.app.groupprojectapplication.domain.House;
import java.util.List;

public interface IHouseDao {

    House getHouseById(Integer id);

    void insertHouse(House house);

    List<House> getAllHouse();

    int getContactIdByHouseId(Integer house_id);


}
