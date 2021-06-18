package com.app.groupprojectapplication.dao;

import com.app.groupprojectapplication.domain.Person;

public interface IPersonDao {

    Person getPersonById(Integer id);

    Integer insertPerson(Person person);

    void updatePerson(Person person);

    Integer getAge(Integer id);

    String getPhoneByPersonId(Integer id);

}
