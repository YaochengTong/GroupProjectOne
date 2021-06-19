package com.app.groupprojectapplication.service.impl;

import com.app.groupprojectapplication.dao.IAddressDao;
import com.app.groupprojectapplication.dao.IEmployeeDao;
import com.app.groupprojectapplication.dao.IPersonDao;
import com.app.groupprojectapplication.dao.IUserDao;
import com.app.groupprojectapplication.domain.Address;
import com.app.groupprojectapplication.domain.Person;
import com.app.groupprojectapplication.service.IUpdateAddressSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UpdateAddressSectionServiceImpl implements IUpdateAddressSectionService {

    @Autowired
    IEmployeeDao iEmployeeDao;

    @Autowired
    IPersonDao iPersonDao;

    @Autowired
    IAddressDao iAddressDao;

    @Autowired
    IUserDao iUserDao;


    @Override
    public boolean updateAdd(String priAdd1, String priAdd2, String priCity,
                             String priState, String priZip, String secAdd1,
                             String secAdd2, String secCity, String secState,
                             String secZip, Integer userId) {
        Integer personId = iUserDao.getPersonIdByUserId(userId);
        List<Address> addressList = iAddressDao.getAddressByPersonId(personId);


        Address address1 = addressList.get(0);
        address1.setAddressLine1(priAdd1);
        address1.setAddressLine2(priAdd2);
        address1.setCity(priCity);
        address1.setStateName(priState);
        address1.setZipCode(priZip);
        iAddressDao.updateAddress(address1);

        Address address2 = null;
        if (addressList.size() != 1) {
             address2 = addressList.get(1);
        }
        address2.setAddressLine1(secAdd1);
        address2.setAddressLine2(secAdd2);
        address2.setCity(secCity);
        address2.setStateName(secState);
        address2.setZipCode(secZip);
        iAddressDao.updateAddress(address2);

        return true;
    }
}
