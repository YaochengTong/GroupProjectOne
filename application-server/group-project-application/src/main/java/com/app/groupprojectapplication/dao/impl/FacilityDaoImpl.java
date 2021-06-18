package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.dao.IFacilityDao;
import com.app.groupprojectapplication.domain.Employee;
import com.app.groupprojectapplication.domain.Facility;
import com.app.groupprojectapplication.domain.FacilityReport;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        List<Facility> facilityList = session.createQuery("FROM Facility f WHERE f.house.id = " + house_id)
            .getResultList();
        return facilityList;
    }

    @Override
    public int getNumOfTypeByHouseId(Integer house_id, Integer type_number) {
        Session session = sessionFactory.getCurrentSession();
        List<Integer> result = session.createQuery(
            "SELECT quantity FROM Facility f WHERE f.house.id = " + house_id + "AND " + "type = " + type_number)
            .getResultList();
        return result.get(0);
    }

    @Override
    public List<FacilityReport> getAllFacilityReportByHouseId(Employee e) {
        Session session = sessionFactory.getCurrentSession();
        List<FacilityReport> resultList = session.createQuery("FROM FacilityReport WHERE employee = " + e)
            .getResultList();
        return resultList;
    }

}
