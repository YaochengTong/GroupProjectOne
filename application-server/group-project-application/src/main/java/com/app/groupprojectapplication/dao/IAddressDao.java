package com.app.groupprojectapplication.dao;

import com.app.groupprojectapplication.domain.Address;

import java.util.List;

public interface IAddressDao {
    List<Address> getAddressByPersonId(Integer person_id);
    void insertAddress(Address address);
}
