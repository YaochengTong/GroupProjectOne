package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.dao.IHouseDao;
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
        return session.get(House.class, id);
    }

    @Override
    public void insertHouse(House house) {
        Session session = sessionFactory.getCurrentSession();
        session.save(house);
    }

    @Override
    public List<House> getAllHouse() {
        Session session = sessionFactory.getCurrentSession();
        List<House> houseList = session.createQuery("FROM House").getResultList();
        return houseList;
    }

}
