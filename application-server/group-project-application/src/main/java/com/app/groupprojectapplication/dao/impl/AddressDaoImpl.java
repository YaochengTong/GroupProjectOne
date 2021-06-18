package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.dao.IAddressDao;
import com.app.groupprojectapplication.domain.Address;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository()
@Transactional
public class AddressDaoImpl implements IAddressDao {

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public List<Address> getAddressByPersonId(Integer person_id) {
        Session session = sessionFactory.getCurrentSession();
        List<Address> addressSet = session.createQuery(" FROM Address a WHERE a.person.id = " + person_id).getResultList();
        return addressSet;
    }

    @Override
    public void insertAddress(Address address) {
        Session session = sessionFactory.getCurrentSession();
        session.save(address);
    }

    @Override
    public boolean updateAddress(Address address) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(address);
        return true;
    }
}