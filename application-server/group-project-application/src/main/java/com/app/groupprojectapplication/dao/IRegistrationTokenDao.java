package com.app.groupprojectapplication.dao;

import com.app.groupprojectapplication.domain.RegistrationToken;

public interface IRegistrationTokenDao {
    RegistrationToken getRegistrationTokenById(Integer id);
    void insertRegistrationToke(RegistrationToken registrationToken);
}
