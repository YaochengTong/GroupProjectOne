package com.app.groupprojectapplication.service.impl;

import com.app.groupprojectapplication.dao.IContactDao;
import com.app.groupprojectapplication.dao.IEmployeeDao;
import com.app.groupprojectapplication.dao.IPersonDao;
import com.app.groupprojectapplication.dao.IUserDao;
import com.app.groupprojectapplication.domain.*;
import com.app.groupprojectapplication.domain.profile.*;
import com.app.groupprojectapplication.file.AmazonS3FileService;
import com.app.groupprojectapplication.service.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
@Transactional
public class ProfileServiceImpl implements IProfileService {

    private Profile profile;


    @Autowired
    IEmployeeDao iEmployeeDao;

    @Autowired
    IContactDao iContactDao;

    @Autowired
    IPersonDao iPersonDao;

    @Autowired
    IUserDao iUserDao;

    @Autowired
    AmazonS3FileService amazonS3FileService;

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
    public Profile getProfileByEmployeeId(Integer user_id) throws ExecutionException, InterruptedException {
        Integer employee_id = iUserDao.getEmployeeIdByUserId(user_id).get();
        Employee employee = iEmployeeDao.getEmployeeById(employee_id).get();
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
        profile.setDocumentSectionList(setDocumentSectionList(iEmployeeDao.getUserIdByEmployeeId(employee.getId())));
        return profile;
    }

    private List<DocumentSection> setDocumentSectionList(Integer userId) {
        List<String> names = amazonS3FileService.printFilesInOneFolder(String.valueOf(userId));
        List<DocumentSection> documentSectionList = new ArrayList<>();
        for (String name : names) {
            DocumentSection documentSection = new DocumentSection();
            documentSection.setName(name);
            documentSection.setPath("https://gp1storage.s3.us-east-2.amazonaws.com/" + userId + "/" + name.split("_")[0]+ ".txt");
            documentSectionList.add(documentSection);
        }
        return documentSectionList;


    }

    private EmergencyContactList setEmergencyContactList(Person person) {
        EmergencyContactList emergencyContactList = new EmergencyContactList();
        List<Contact> contactList = iContactDao.getEmergencyByPersonId(person.getId());

        if (contactList.size() == 2) {
            EmergencyContact emergencyContact = new EmergencyContact();
            Person emergencyPerson = iPersonDao.getPersonById(contactList.get(1).getRelated_person_id());
            emergencyContact.setFullName(getFullName(emergencyPerson));
            emergencyContact.setPhone(emergencyPerson.getPrimaryPhone());
            emergencyContact.setAddress(setAddressSection(emergencyPerson));
            emergencyContactList.setEmergencyPerson1(emergencyContact);
        }

        EmergencyContact emergencyContact = new EmergencyContact();
        Person emergencyPerson = iPersonDao.getPersonById(contactList.get(0).getRelated_person_id());
        emergencyContact.setFullName(getFullName(emergencyPerson));
        emergencyContact.setPhone(emergencyPerson.getPrimaryPhone());
        emergencyContact.setAddress(setAddressSection(emergencyPerson));
        emergencyContactList.setEmergencyPerson1(emergencyContact);

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
        nameSection.setAge(iPersonDao.getAge(person.getId()));
        return nameSection;
    }

    public AddressSection setAddressSection(Person person) {
        AddressSection addressSection = new AddressSection();
        List<Address> addressList = new ArrayList<>(person.getAddresses());
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


    @Override
    public List<Summary> getSummary() {
        List<Summary> summaryList = new ArrayList<>();
        Integer index = 1;
        List<Employee> employeeList = iEmployeeDao.getEmployee();
        for(Employee employee : employeeList) {
            summaryList.add(getSummaryByEmployee(employee, index));
            index++;
        }
        return summaryList;
    }

    @Override
    public Map<String, Object> uploadAvatar(List<MultipartFile> files, Map<String, Object> paramMap) throws ExecutionException, InterruptedException {
        Map<String, Object> resultMap = new HashMap<>();
        Integer userId = Integer.parseInt((String) paramMap.get("userId"));
        Integer employeeId = iUserDao.getEmployeeIdByUserId(userId).get();
        try {
            System.out.println("???");
            InputStream ips = files.get(0).getInputStream();
            String result = amazonS3FileService.upload(ips,  "avatar/" + userId +"/" + files.get(0).getOriginalFilename());
            resultMap.put("success", true);
            resultMap.put("path", result);
            iEmployeeDao.updateAvatarPath(employeeId, result);
            ips.close();
        } catch (IOException e) {
            resultMap.put("reason", e.toString());
            resultMap.put("success", false);
            e.printStackTrace();
        }
        return resultMap;
    }


    private Summary getSummaryByEmployee(Employee employee, Integer index) {
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
