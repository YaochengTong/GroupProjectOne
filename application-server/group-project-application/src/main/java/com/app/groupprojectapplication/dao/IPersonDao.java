package com.app.groupprojectapplication.dao;

public interface IPersonDao {
    Person getPersonById(Integer id);
    void insertPerson(Person person);
    void updatePerson(Person person);
}
