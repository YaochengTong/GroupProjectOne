package com.app.groupprojectapplication.service;

import com.app.groupprojectapplication.domain.Employee;
import com.app.groupprojectapplication.domain.Person;
import com.app.groupprojectapplication.domain.profile.*;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface IProfileService {
    List<Profile> getProfile();
    Profile getProfileByEmployeeId(Integer user_id);
    List<Summary> getSummary();
//    CompletableFuture<List<DocumentSection>> setDocumentSectionList(Integer userId);
//    CompletableFuture<EmergencyContactList> setEmergencyContactList(Person person);
////    CompletableFuture<EmploymentSection> setEmploymentSection(Employee employee);
//    ContactInfoSection setContactInfoSection(Employee employee, Person person);
//    NameSection setNameSection(Employee employee, Person person);
//    AddressSection setAddressSection(Person person);

    Map<String, Object> uploadAvatar(List<MultipartFile> files, Map<String, Object> paramMap);
}
