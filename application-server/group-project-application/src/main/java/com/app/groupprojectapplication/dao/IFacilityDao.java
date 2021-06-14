package com.app.groupprojectapplication.dao;

import com.app.groupprojectapplication.domain.Facility;

import java.util.List;

public interface IFacilityDao {
    void insertFacility(Facility facility);
    List<Facility> getFacilityByHouseId(Integer house_id);
}