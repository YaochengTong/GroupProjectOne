package com.app.groupprojectapplication.service.impl;

import com.app.groupprojectapplication.dao.IContactDao;
import com.app.groupprojectapplication.dao.IEmployeeDao;
import com.app.groupprojectapplication.dao.IPersonDao;
import com.app.groupprojectapplication.dao.IUserDao;
import com.app.groupprojectapplication.domain.*;
import com.app.groupprojectapplication.domain.profile.*;
import com.app.groupprojectapplication.file.AmazonS3FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class ProfileAsyncService {
    @Autowired
    AmazonS3FileService amazonS3FileService;

    @Autowired
    IEmployeeDao iEmployeeDao;

    @Autowired
    IContactDao iContactDao;

    @Autowired
    IPersonDao iPersonDao;

    @Autowired
    IUserDao iUserDao;

    @Async("taskExecutor")
    @Transactional
    public CompletableFuture<List<DocumentSection>> setDocumentSectionList(Integer userId) {
        List<String> names = amazonS3FileService.printFilesInOneFolder(String.valueOf(userId));
        List<DocumentSection> documentSectionList = new ArrayList<>();
        for (String name : names) {
            DocumentSection documentSection = new DocumentSection();
            documentSection.setName(name);
            documentSection.setPath("https://gp1storage.s3.us-east-2.amazonaws.com/" + userId + "/" + name.split("_")[0]+ ".txt");
            documentSectionList.add(documentSection);
        }
        return CompletableFuture.completedFuture(documentSectionList);


    }

    @Async("taskExecutor")
    @Transactional
    public Integer getUserId(Employee employee) {
        return iEmployeeDao.getUserIdByEmployeeId(employee.getId());
    }

    @Async("taskExecutor")
    @Transactional
    public CompletableFuture<EmergencyContactList> setEmergencyContactList(Person person) {
        EmergencyContactList emergencyContactList = new EmergencyContactList();
        List<Contact> contactList = iContactDao.getEmergencyByPersonId(person.getId());

        if (contactList.size() == 2) {
            EmergencyContact emergencyContact = new EmergencyContact();
            Person emergencyPerson = iPersonDao.getPersonById(contactList.get(1).getRelated_person_id());
            emergencyContact.setFullName(getFullName(emergencyPerson));
            emergencyContact.setPhone(emergencyPerson.getPrimaryPhone());
            emergencyContact.setAddress(setAddressSection(emergencyPerson).join());
            emergencyContactList.setEmergencyPerson1(emergencyContact);
        }

        EmergencyContact emergencyContact = new EmergencyContact();
        Person emergencyPerson = iPersonDao.getPersonById(contactList.get(0).getRelated_person_id());
        emergencyContact.setFullName(getFullName(emergencyPerson));
        emergencyContact.setPhone(emergencyPerson.getPrimaryPhone());
        emergencyContact.setAddress(setAddressSection(emergencyPerson).join());
        emergencyContactList.setEmergencyPerson1(emergencyContact);

        return CompletableFuture.completedFuture(emergencyContactList);
    }

    @Async("taskExecutor")
    @Transactional
    public CompletableFuture<EmploymentSection> setEmploymentSection(Employee employee) {
        EmploymentSection employmentSection = new EmploymentSection();
        employmentSection.setWorkAuthorization(getVisaStatus(employee).join().getVisaType());
        employmentSection.setAuthorizationStartDate(employee.getVisaStartDate());
        employmentSection.setAuthorizationEndDate(employee.getVisaEndDate());
        employmentSection.setEmploymentStartDate(employee.getStartDate());
        employmentSection.setEmploymentEndDate(employee.getEndDate());
        employmentSection.setTitle(employee.getTitle());
        return CompletableFuture.completedFuture(employmentSection);
    }

    @Transactional
    @Async("taskExecutor")
    public CompletableFuture<VisaStatus> getVisaStatus(Employee employee) {
        VisaStatus visaStatus = employee.getVisaStatus();
        return  CompletableFuture.completedFuture(visaStatus);
    }

    @Async("taskExecutor")
    @Transactional
    public CompletableFuture<ContactInfoSection> setContactInfoSection(Employee employee, Person person) {
        ContactInfoSection contactInfoSection = new ContactInfoSection();
        contactInfoSection.setPersonalEmail(person.getEmail());
        // work email?
        contactInfoSection.setWorkEmail(person.getEmail());
        contactInfoSection.setCeilphone(person.getPrimaryPhone());
        // work phone? =? alternative phone
        contactInfoSection.setWorkPhone(person.getAlternatePhone());
        return CompletableFuture.completedFuture(contactInfoSection);
    }

    @Async("taskExecutor")
    @Transactional
    public CompletableFuture<NameSection> setNameSection(Employee employee, Person person) {
        NameSection nameSection = new NameSection();
        nameSection.setFullName(getFullName(person));
        nameSection.setPreferredName(nameSection.getFullName());
        nameSection.setAvatar(employee.getAvartar());
        nameSection.setDOB(person.getDob());
        nameSection.setSSN(person.getSsn());
        nameSection.setAge(iPersonDao.getAge(person.getId()));
        return CompletableFuture.completedFuture(nameSection);
    }

    @Async("taskExecutor")
    @Transactional
    public CompletableFuture<AddressSection> setAddressSection(Person person) {
        AddressSection addressSection = new AddressSection();
        List<Address> addressList = person.getAddresses() == null ? new ArrayList<>() : new ArrayList<>(person.getAddresses());
        List<Map<String, String>> addrMapList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            if (addressList.size() > i) {
                Map<String, String> addrMap = new HashMap<>();
                addrMap.put("AddressLine1", addressList.get(i).getAddressLine1());
                addrMap.put("AddressLine2", addressList.get(i).getAddressLine2());
                addrMap.put("City", addressList.get(i).getCity());
                addrMap.put("State", addressList.get(i).getStateName());
                addrMap.put("Zip", addressList.get(i).getZipCode());
                addrMapList.add(addrMap);
            }
        }

        if (addrMapList.size() >= 2) {
            addressSection.setPrimaryAddr(addrMapList.get(0));
            addressSection.setSecondaryAddr(addrMapList.get(1));
        } else if (addressList.size() ==1) {
            addressSection.setPrimaryAddr(addrMapList.get(0));
            addressSection.setSecondaryAddr(null);
        } else if (addressList == null) {
            addressSection.setPrimaryAddr(null);
            addressSection.setSecondaryAddr(null);
        }


        return CompletableFuture.completedFuture(addressSection);


    }

    public String getFullName(Person person) {
        String fullName;
        if (person.getMiddleName() != null && person.getMiddleName().length() > 0){
            fullName = person.getFirstName() + " " + person.getMiddleName() + " " + person.getLastName();
        } else {
            fullName = person.getFirstName() + " " + person.getLastName();
        }
        return  fullName;
    }

    @Async("taskExecutor")
    @Transactional
    public Summary getSummaryByEmployee(Employee employee, Integer index) {
        Summary summary = new Summary();
        Person person = employee.getPerson();
        summary.setIndex(index);
        summary.setEmployeeId(employee.getId());
        summary.setSSN(person.getSsn());
        summary.setFullName(getFullName(person));
        summary.setVisaType(employee.getVisaStatus().getVisaType());
        summary.setStartDate(employee.getStartDate());
        return summary;
    }
}
