package com.app.groupprojectapplication.service.impl;

import com.app.groupprojectapplication.dao.IAddressDao;
import com.app.groupprojectapplication.dao.IEmployeeDao;
import com.app.groupprojectapplication.dao.IPersonDao;
import com.app.groupprojectapplication.domain.Address;
import com.app.groupprojectapplication.service.IUpdateAddressSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateAddressSectionServiceImpl implements IUpdateAddressSectionService {

    @Autowired
    IEmployeeDao iEmployeeDao;

    @Autowired
    IPersonDao iPersonDao;

    @Autowired
    IAddressDao iAddressDao;


    @Override
    public boolean updatePriAdd(String priAdd1, String priAdd2, String priCity,
                                String priState, String priZip, Integer userId) {

        Address address = null;
        address.setAddressLine1(priAdd1);
        address.setAddressLine2(priAdd2);
        address.setCity(priCity);
        address.setStateName(priState);
        address.setZipCode(priZip);
        iAddressDao.updateAddress(address);
        System.out.println("Update Primary Address");
        return true;
    }

    @Override
    public boolean updateSecAdd(String secAdd1, String secAdd2, String secCity,
                                 String secState, String secZip, Integer userId) {
        Address address = null;
        address.setAddressLine1(secAdd1);
        address.setAddressLine2(secAdd2);
        address.setCity(secCity);
        address.setStateName(secState);
        address.setZipCode(secZip);
        iAddressDao.updateAddress(address);

        System.out.println("Update Second Address");
        return true;
    }
}
