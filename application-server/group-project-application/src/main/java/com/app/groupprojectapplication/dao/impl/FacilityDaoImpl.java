package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.dao.IFacilityDao;
import com.app.groupprojectapplication.domain.Facility;
import com.app.groupprojectapplication.domain.PersonalDocument;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class FacilityDaoImpl implements IFacilityDao {

    Facility facility;

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public Facility getFacilityById(Integer id) {
        Session session = sessionFactory.openSession();
        facility = session.get(Facility.class, id);
        session.close();
        return facility;
    }

    @Override
    public List<Facility> getFacilitiesByHouseId(Integer id) {
        Session session = sessionFactory.openSession();
        List<Facility> facilityList = session.createQuery("From Facility f WHERE f.house.id = " + id).getResultList();
        session.close();
        return facilityList;
    }

    @Override
    public void insertFacility(Facility facility) {
        Session session = sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        session.save(facility);
        ts.commit();
        session.close();
    }
}
