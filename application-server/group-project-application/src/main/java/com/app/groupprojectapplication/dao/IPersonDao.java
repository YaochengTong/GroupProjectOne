package com.app.groupprojectapplication.dao;

import com.app.groupprojectapplication.domain.Person;

public interface IPersonDao {
    Person getPersonById(Integer id);
    void insertPerson(Person person);
    void updatePerson(Person person);
}
