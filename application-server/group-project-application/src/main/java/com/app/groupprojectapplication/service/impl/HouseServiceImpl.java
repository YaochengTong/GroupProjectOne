package com.app.groupprojectapplication.service.impl;

import com.app.groupprojectapplication.dao.IHouseDao;
import com.app.groupprojectapplication.domain.House;
import com.app.groupprojectapplication.service.IHouseService;
import java.util.List;

public class HouseServiceImpl implements IHouseService {

    IHouseDao iHouseDao;

    @Override
    public List<House> getAllHouse() {
        return iHouseDao.getAllHouse();
    }

}
