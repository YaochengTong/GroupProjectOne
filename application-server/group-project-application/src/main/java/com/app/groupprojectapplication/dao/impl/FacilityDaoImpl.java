package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.dao.IFacilityDao;
import com.app.groupprojectapplication.domain.Facility;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FacilityDaoImpl implements IFacilityDao {

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public void insertFacility(Facility facility) {
        Session session = sessionFactory.getCurrentSession();
        session.save(facility);
    }

    @Override
    public List<Facility> getFacilityByHouseId(Integer house_id) {
        Session session = sessionFactory.getCurrentSession();
        List<Facility> facilityList = session.createQuery("FROM Facility f WHERE f.house.id = " + house_id).getResultList();
        return facilityList;
    }
}
