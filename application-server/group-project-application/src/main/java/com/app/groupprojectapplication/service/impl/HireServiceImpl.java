package com.app.groupprojectapplication.service.impl;

import com.app.groupprojectapplication.dao.*;
import com.app.groupprojectapplication.domain.*;
import com.app.groupprojectapplication.email.EmailService;
import com.app.groupprojectapplication.file.AmazonS3FileService;
import com.app.groupprojectapplication.service.IHireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.*;

@Service
public class HireServiceImpl implements IHireService {

    @Autowired
    private IUserDao iUserDao;

    @Autowired
    private IEmployeeDao iEmployeeDao;

    @Autowired
    private IPersonalDocumentDao iPersonalDocumentDao;

    @Autowired
    private IPersonDao iPersonDao;

    @Autowired
    private IContactDao iContactDao;

    @Autowired
    private IRegistrationTokenDao iRegistrationTokenDao;

    @Autowired
    private IApplicationWorkFlowDao iApplicationWorkFlowDao;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AmazonS3FileService amazonS3FileService;

    @Override
    public boolean generateAToken(String email, Integer userId) {
        //generate a UUID
        UUID token = UUID.randomUUID();

        //store the email and token into database
        Integer employeeId = iUserDao.getEmployeeIdByUserId(userId);
        if(employeeId == null)
            return false;
        Employee employee = iEmployeeDao.getEmployeeById(employeeId);
        if(employee == null)
            return false;
        RegistrationToken registrationToken = new RegistrationToken();
        registrationToken.setEmployee(employee);
        registrationToken.setToken(token.toString());
        registrationToken.setEmail(email);
        long timeStamp = System.currentTimeMillis();
        java.sql.Date expirationDate = new java.sql.Date(timeStamp + 3 * 60 * 60 * 1000);
        registrationToken.setValidUntil(new Timestamp(expirationDate.getTime()));
        iRegistrationTokenDao.insertRegistrationToke(registrationToken);

        //send the token through email
        String text = "http://localhost:4200/login/register?email=" + email + "&" +
            "token=" + token.toString();

        emailService.sendMail(email, "Registration Token", text);

        return true;
    }

    @Override
    public Map<String, Object> onboardSubmission(List<MultipartFile> files, Map<String, Object> paramMap) {
        Map<String, Object> resultMap = new HashMap<>();
        //Integer userId = Integer.parseInt(paramMap.get("user_id").toString());
        Integer userId = 574;
        User user = iUserDao.getUserById(userId);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        /**
         * Step 1: deal with the form
         */
        //Step 1.1: create a person for the new trainee
        Person person = new Person();
        person.setFirstName(paramMap.get("firstName").toString());
        person.setLastName(paramMap.get("lastName").toString());
        person.setMiddleName(paramMap.get("lastName").toString());
        person.setEmail(paramMap.get("email").toString());
        person.setPrimaryPhone(paramMap.get("cellPhone").toString());
        person.setAlternatePhone(paramMap.get("workPhone").toString());
        person.setGender(paramMap.get("gender").toString());
        person.setSsn(paramMap.get("ssn").toString());
        person.setDob(new Timestamp(Long.parseLong(paramMap.get("dateOfBirth").toString())));

        //Step 1.2 create a set of address for the trainee
        Address primary_address = new Address();
        primary_address.setAddressLine1(paramMap.get("address").toString());
        primary_address.setAddressLine2(paramMap.get("address2").toString());
        primary_address.setCity(paramMap.get("city").toString());
        primary_address.setStateAbbr(paramMap.get("state").toString());
        primary_address.setStateName(paramMap.get("stateFullName").toString());
        primary_address.setZipCode(paramMap.get("postalCode").toString());
        primary_address.setPerson(person);
        Set<Address> addressSet = new HashSet<>();
        addressSet.add(primary_address);

        person.setAddresses(addressSet);
        user.setPerson(person);

        //insert person
        Integer person_id = iPersonDao.insertPerson(person);
        person.setId(person_id);

        //update user
        iUserDao.updateUser(user);

        //Step 1.3 emergency contact
        Person emergencyContactPerson = new Person();
        emergencyContactPerson.setFirstName(paramMap.get("emergency1FirstName").toString());
        emergencyContactPerson.setLastName(paramMap.get("emergency1LastName").toString());
        emergencyContactPerson.setMiddleName(paramMap.get("emergency1MiddleName").toString());
        emergencyContactPerson.setEmail(paramMap.get("emergency1Email").toString());
        emergencyContactPerson.setPrimaryPhone(paramMap.get("emergency1Phone").toString());
        emergencyContactPerson.setDob(timestamp);
        //set address
        Address emergencyContactAddress = new Address();
        emergencyContactAddress.setAddressLine1(paramMap.get("emergency1Address").toString());
        emergencyContactAddress.setAddressLine2(paramMap.get("emergency1Address2").toString());
        emergencyContactAddress.setCity(paramMap.get("emergency1City").toString());
        emergencyContactAddress.setStateAbbr(paramMap.get("emergency1State").toString());
        emergencyContactAddress.setStateName(paramMap.get("emergency1StateFullName").toString());
        emergencyContactAddress.setZipCode(paramMap.get("emergency1PostalCode").toString());
        emergencyContactAddress.setPerson(person);
        Set<Address> addressSet2 = new HashSet<>();
        addressSet2.add(emergencyContactAddress);
        emergencyContactPerson.setAddresses(addressSet2);

        //insert emergency contact person
        Integer emergencyPerson1ID = iPersonDao.insertPerson(emergencyContactPerson);
        emergencyContactPerson.setId(emergencyPerson1ID);
        //insert into contact
        Contact contact = new Contact();
        contact.setPerson(person);
        contact.setRelated_person_id(emergencyPerson1ID);
        contact.setTitle("employee");
        contact.setIsEmergency((byte) 1);
        contact.setIsReferrence((byte) 0);
        contact.setIsLandlord((byte) 0);
        contact.setRelationship(paramMap.get("emergency1Relationship").toString());
        iContactDao.insertContact(contact);

        //emergency contact 2
        if(paramMap.get("emergency2FirstName") != null && !paramMap.get("emergency2FirstName").equals("")) {
            Person emergencyPerson2 = new Person();
            emergencyPerson2.setFirstName(paramMap.get("emergency2FirstName").toString());
            emergencyPerson2.setLastName(paramMap.get("emergency2LastName").toString());
            emergencyPerson2.setMiddleName(paramMap.get("emergency2MiddleName").toString());
            emergencyPerson2.setEmail(paramMap.get("emergency2Email").toString());
            emergencyPerson2.setPrimaryPhone(paramMap.get("emergency2Phone").toString());
            emergencyPerson2.setDob(timestamp);

            //set address
            Address referenceContactAddress = new Address();
            referenceContactAddress.setAddressLine1(paramMap.get("emergency2Address").toString());
            referenceContactAddress.setAddressLine2(paramMap.get("emergency2Address2").toString());
            referenceContactAddress.setCity(paramMap.get("emergency2City").toString());
            referenceContactAddress.setStateAbbr(paramMap.get("emergency2State").toString());
            referenceContactAddress.setStateName(paramMap.get("emergency2StateFullName").toString());
            referenceContactAddress.setZipCode(paramMap.get("emergency2PostalCode").toString());
            referenceContactAddress.setPerson(person);
            Set<Address> addressSet3 = new HashSet<>();
            addressSet3.add(referenceContactAddress);
            emergencyPerson2.setAddresses(addressSet3);

            //insert emergency contact 2
            Integer emergencyPerson2Id = iPersonDao.insertPerson(emergencyPerson2);
            //insert into contact
            Contact emergencyContact2 = new Contact();
            emergencyContact2.setPerson(person);
            emergencyContact2.setRelated_person_id(emergencyPerson2Id);
            emergencyContact2.setTitle("employee");
            emergencyContact2.setIsEmergency((byte) 1);
            emergencyContact2.setIsReferrence((byte) 0);
            emergencyContact2.setIsLandlord((byte) 0);
            emergencyContact2.setRelationship(paramMap.get("emergency2Relationship").toString());
            iContactDao.insertContact(emergencyContact2);
        }

        //Step 1.4 reference contact
        if(paramMap.get("referenceFirstName") != null && !paramMap.get("referenceFirstName").equals("")) {
            Person referencePerson = new Person();
            referencePerson.setFirstName(paramMap.get("referenceContactFirstName").toString());
            referencePerson.setLastName(paramMap.get("referenceContactLastName").toString());
            referencePerson.setMiddleName(paramMap.get("referenceContactMiddleName").toString());
            referencePerson.setEmail(paramMap.get("referenceContactEmail").toString());
            referencePerson.setPrimaryPhone(paramMap.get("referenceContactPhone").toString());
            referencePerson.setDob(timestamp);

            //set address
            Address referenceContactAddress = new Address();
            referenceContactAddress.setAddressLine1(paramMap.get("referenceContactAddress").toString());
            referenceContactAddress.setAddressLine2(paramMap.get("referenceContactAddress2").toString());
            referenceContactAddress.setCity(paramMap.get("referenceContactCity").toString());
            referenceContactAddress.setStateAbbr(paramMap.get("referenceContactState").toString());
            referenceContactAddress.setStateName(paramMap.get("referenceContactStateFullName").toString());
            referenceContactAddress.setZipCode(paramMap.get("referenceContactPostalCode").toString());
            referenceContactAddress.setPerson(person);
            Set<Address> addressSet3 = new HashSet<>();
            addressSet3.add(referenceContactAddress);
            referencePerson.setAddresses(addressSet3);

            //insert reference contact
            Integer referencePersonId = iPersonDao.insertPerson(referencePerson);
            //insert into contact
            Contact referenceContact = new Contact();
            referenceContact.setPerson(person);
            referenceContact.setRelated_person_id(referencePersonId);
            referenceContact.setTitle("employee");
            referenceContact.setIsEmergency((byte) 0);
            referenceContact.setIsReferrence((byte) 1);
            referenceContact.setIsLandlord((byte) 0);
            referenceContact.setRelationship(paramMap.get("referenceContactRelationship").toString());
            iContactDao.insertContact(referenceContact);
        }


        //step 1.5 insert into application workflow
        ApplicationWorkflow applicationWorkflow = new ApplicationWorkflow();
        applicationWorkflow.setUser(user);
        applicationWorkflow.setCreateDate(timestamp);
        applicationWorkflow.setModificationDate(timestamp);
        applicationWorkflow.setType("Onboarding");
        applicationWorkflow.setStatus("Pending");
        applicationWorkflow.setComments("");
        iApplicationWorkFlowDao.insertApplicationWorkFlow(applicationWorkflow);

        /**
         * Step 2: File uploads
         */

        //Step 2.1: deal with the file upload
        for (int i = 0; i < files.size(); i++) {
            InputStream ips = null;
            try {
                ips = files.get(i).getInputStream();
                String result = amazonS3FileService.upload(ips, userId + "/"
                        + files.get(i).getOriginalFilename());
                //Step 2.2: insert into personal document table
                PersonalDocument personalDocument = new PersonalDocument();
                personalDocument.setCreateDate(timestamp);
                personalDocument.setTitle(paramMap.get("title" + i).toString());
                personalDocument.setPath(result);
                personalDocument.setUser(user);
                iPersonalDocumentDao.insertPersonalDocument(personalDocument);
                System.out.println(result);
                ips.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return resultMap;
    }

}
