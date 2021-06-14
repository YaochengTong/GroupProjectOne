package com.app.groupprojectapplication.dao;

import com.app.groupprojectapplication.domain.Facility;

import java.util.List;

public interface IFacilityDao {
    Facility getFacilityById(Integer id);
    List<Facility> getFacilitiesByHouseId(Integer id);
    void insertFacility(Facility facility);
}
