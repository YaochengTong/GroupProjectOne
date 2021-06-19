package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.dao.IFacilityDao;
import com.app.groupprojectapplication.domain.Employee;
import com.app.groupprojectapplication.domain.Facility;
import com.app.groupprojectapplication.domain.FacilityReport;
import com.app.groupprojectapplication.domain.House;
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
    public int getNumOfTypeByHouseId(Integer house_id, String f_type) {
        Session session = sessionFactory.getCurrentSession();
        List<Integer> result = session.createNativeQuery(
            "SELECT quantity FROM facility where house_id =" + house_id + " AND type = '" + f_type + "'")
            .getResultList();
        return result.get(0);
    }

    @Override
    public List<FacilityReport> getAllFacilityReportByHouseId(Employee e) {
        Session session = sessionFactory.getCurrentSession();
        List<FacilityReport> resultList = session.createQuery("FROM FacilityReport WHERE employee.id = " + e.getId())
            .getResultList();
        return resultList;
    }

    @Override
    public String getFacilityTypeById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        List<String> resultList = session.createQuery("SELECT type FROM Facility WHERE id =" + id).getResultList();
        return resultList.get(0);
    }

    @Override
    public String getFacilityDescriptionById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        List<String> resultList = session.createQuery("SELECT description FROM Facility WHERE id =" + id)
            .getResultList();
        return resultList.get(0);
    }

    @Override
    public House getHouseById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        List<House> resultList = session.createQuery("SELECT House FROM Facility WHERE id =" + id).getResultList();
        return resultList.get(0);
    }

}
