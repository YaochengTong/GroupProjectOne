package com.app.groupprojectapplication.dao;

import com.app.groupprojectapplication.domain.PersonalDocument;

import java.util.List;

public interface IPersonalDocumentDao {
    PersonalDocument getPersonalDocumentById(Integer id);
    List<PersonalDocument> getPersonalDocumentsByEmployeeId(Integer employee_id);
    List<PersonalDocument> getPersonalDocumentsByUserId(Integer id);
    void insertPersonalDocument(PersonalDocument personalDocument);
}
