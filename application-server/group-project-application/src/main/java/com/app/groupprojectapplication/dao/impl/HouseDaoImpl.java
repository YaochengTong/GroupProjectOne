package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.dao.IHouseDao;
import com.app.groupprojectapplication.domain.Employee;
import com.app.groupprojectapplication.domain.House;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HouseDaoImpl implements IHouseDao {

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public House getHouseById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        House h = session.get(House.class, id);
        return h;
    }

    @Override
    public void insertHouse(House house) {
        Session session = sessionFactory.getCurrentSession();
        session.save(house);
    }

    @Override
    public List<House> getAllHouse() {
        Session session = sessionFactory.getCurrentSession();
        List<House> resultList = session.createQuery("FROM House").getResultList();
        return resultList;
    }

    @Override
    public int getContactIdByHouseId(Integer house_id) {
        Session session = sessionFactory.getCurrentSession();
        List<Integer> resultList = session.createQuery("SELECT contact.id FROM House").getResultList();
        return resultList.get(0);
    }

    @Override
    public void updateHouseAddressByHouseId(String address, Integer id) {
        Session session = sessionFactory.getCurrentSession();
        House h = session.get(House.class, id);
        h.setAddress(address);
        session.saveOrUpdate(h);
    }

    @Override
    public void updateHouseNumberOfPersonByHouseId(Integer n, Integer id) {
        Session session = sessionFactory.getCurrentSession();
        House h = session.get(House.class, id);
        h.setNumberOfPerson(n);
        session.saveOrUpdate(h);
    }

}
