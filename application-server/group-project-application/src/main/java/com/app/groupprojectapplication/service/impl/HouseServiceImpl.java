package com.app.groupprojectapplication.service.impl;

import com.app.groupprojectapplication.dao.IHouseDao;
import com.app.groupprojectapplication.domain.House;
import com.app.groupprojectapplication.service.IHouseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HouseServiceImpl implements IHouseService {

    @Autowired
    IHouseDao iHouseDao;

    @Override
    @Transactional
    public List<House> getAllHouse() {
        return iHouseDao.getAllHouse();
    }

}
