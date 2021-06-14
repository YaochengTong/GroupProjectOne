package com.app.groupprojectapplication.service.impl;

import com.app.groupprojectapplication.dao.IContactDao;
import com.app.groupprojectapplication.dao.IEmployeeDao;
import com.app.groupprojectapplication.dao.IPersonDao;
import com.app.groupprojectapplication.domain.*;
import com.app.groupprojectapplication.domain.profile.*;
import com.app.groupprojectapplication.service.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ProfileServiceImpl implements IProfileService {

    private Profile profile;


    @Autowired
    IEmployeeDao iEmployeeDao;

    @Autowired
    IContactDao iContactDao;

    @Autowired
    IPersonDao iPersonDao;

    @Override
    @Transactional
    public List<Profile> getProfile() {
        List<Profile> profileList = new ArrayList<>();
        List<Employee> employeeList = iEmployeeDao.getEmployee();
        for(Employee employee : employeeList) {
            profileList.add(getProfileByEmployee(employee));
        }
        return profileList;
    }

    @Override
    @Transactional
    public Profile getProfileByEmployeeId(Integer employee_id) {
        Employee employee = iEmployeeDao.getEmployeeById(employee_id);
        return getProfileByEmployee(employee);
    }


    private Profile getProfileByEmployee(Employee employee) {
        profile = new Profile();
        Person person = employee.getPerson();
        profile.setEmployee_id(employee.getId());
        profile.setNameSection(setNameSection(employee, person));
        profile.setAddressSection(setAddressSection(person));
        profile.setContactInfoSection(setContactInfoSection(employee, person));
        profile.setEmploymentSection(setEmploymentSection(employee));
        profile.setEmergencyContactList(setEmergencyContactList(person));
//        profile.setSummary(setSummary(employee, person));
        return profile;
    }

//    private Summary setSummary(Employee employee, Person person) {
//        Summary summary = new Summary();
//        summary.setFullName(getFullName(person));
//        summary.setSSN(person.getSsn());
//        summary.setStartDate(employee.getStartDate());
//        summary.setVisaStatus(setw);
//        System.out.println(summary.toString());
//        return summary;
//    }


    private List<EmergencyContact> setEmergencyContactList(Person person) {
        List<EmergencyContact> emergencyContactList = new ArrayList<>();
        List<Contact> contactList = iContactDao.getEmergencyByPersonId(person.getId());
        for (Contact contact : contactList) {
            EmergencyContact emergencyContact = new EmergencyContact();
            Person emergencyPerson = iPersonDao.getPersonById(contact.getRelated_person_id());
            emergencyContact.setFullName(getFullName(emergencyPerson));
            emergencyContact.setPhone(emergencyPerson.getPrimaryPhone());
            emergencyContact.setAddress(setAddressSection(emergencyPerson));
            emergencyContactList.add(emergencyContact);
        }
        return emergencyContactList;
     }


    private EmploymentSection setEmploymentSection(Employee employee) {
        EmploymentSection employmentSection = new EmploymentSection();
        VisaStatus visaStatus = employee.getVisaStatus();
        employmentSection.setWorkAuthorization(visaStatus.getVisaType());
        employmentSection.setAuthorizationStartDate(employee.getVisaStartDate());
        employmentSection.setAuthorizationEndDate(employee.getVisaEndDate());
        employmentSection.setEmploymentStartDate(employee.getStartDate());
        employmentSection.setEmploymentEndDate(employee.getEndDate());
        employmentSection.setTitle(employee.getTitle());
        return employmentSection;
    }

    private ContactInfoSection setContactInfoSection(Employee employee, Person person) {
        ContactInfoSection contactInfoSection = new ContactInfoSection();
        contactInfoSection.setPersonalEmail(person.getEmail());
        // work email?
        contactInfoSection.setWorkEmail(person.getEmail());
        contactInfoSection.setCeilphone(person.getPrimaryPhone());
        // work phone? =? alternative phone
        contactInfoSection.setWorkPhone(person.getAlternatePhone());
        return contactInfoSection;
    }

    public NameSection setNameSection(Employee employee, Person person) {
        NameSection nameSection = new NameSection();
        nameSection.setFullName(getFullName(person));
        nameSection.setPreferredName(nameSection.getFullName());
        nameSection.setAvatar(employee.getAvartar());
        nameSection.setDOB(person.getDob());
        nameSection.setSSN(person.getSsn());
        return nameSection;
    }

    public AddressSection setAddressSection(Person person) {
        AddressSection addressSection = new AddressSection();
        List<Address> addressList = new ArrayList<>(person.getAddresses());
        List<Map<String, String>> addrMapList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            if (addressList.size() > i) {
                Map<String, String> addrMap = new HashMap<>();
                addrMap.put("Address Line 1", addressList.get(i).getAddressLine1());
                addrMap.put("Address Line 2", addressList.get(i).getAddressLine2());
                addrMap.put("City", addressList.get(i).getCity());
                addrMap.put("State", addressList.get(i).getStateName());
                addrMap.put("Zip", addressList.get(i).getZipCode());
                addrMapList.add(addrMap);
            }
        }

        if (addrMapList.size() >= 2) {
            addressSection.setPrimaryAddr(addrMapList.get(0));
            addressSection.setSecondaryAddr(addrMapList.get(1));
        } else {
            addressSection.setPrimaryAddr(null);
            addressSection.setSecondaryAddr(null);
        }


        return addressSection;
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
}
