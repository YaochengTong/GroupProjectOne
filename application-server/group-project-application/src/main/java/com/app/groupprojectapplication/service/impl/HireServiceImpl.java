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
        Integer userId = Integer.parseInt(paramMap.get("user_id").toString());
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
        iPersonDao.insertPerson(person);

        //update user
        iUserDao.updateUser(user);

        //Step 1.3 emergency contact
        Person emergencyContactPerson = new Person();
        emergencyContactPerson.setFirstName(paramMap.get("emergencyFirstName").toString());
        emergencyContactPerson.setLastName(paramMap.get("emergencyLastName").toString());
        emergencyContactPerson.setMiddleName(paramMap.get("emergencyMiddleName").toString());
        emergencyContactPerson.setEmail(paramMap.get("emergencyEmail").toString());
        emergencyContactPerson.setPrimaryPhone(paramMap.get("emergencyPhone").toString());
        emergencyContactPerson.setDob(new Timestamp(1));
        emergencyContactPerson.setGender("m");
        //todo set address
        //emergencyContactPerson.set

        //Step 1.4 reference contact
        if(paramMap.get("referenceFirstName") != null) {
            Person referencePerson = new Person();
            referencePerson.setFirstName(paramMap.get("emergencyFirstName").toString());
            referencePerson.setLastName(paramMap.get("emergencyLastName").toString());
            referencePerson.setMiddleName(paramMap.get("emergencyMiddleName").toString());
            referencePerson.setEmail(paramMap.get("emergencyEmail").toString());
            referencePerson.setPrimaryPhone(paramMap.get("emergencyPhone").toString());
            referencePerson.setDob(new Timestamp(1));
            referencePerson.setGender("m");
            //todo set address
            //emergencyContactPerson.set
        }

        //step 1.5 insert into application workflow
        ApplicationWorkflow applicationWorkflow = new ApplicationWorkflow();
        applicationWorkflow.setUser(user);
        applicationWorkflow.setCreateDate(timestamp);
        applicationWorkflow.setModificationDate(timestamp);
        applicationWorkflow.setType("Onboard");
        applicationWorkflow.setStatus("Pending");
        applicationWorkflow.setUser(user);
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
                personalDocument.setTitle(files.get(i).getOriginalFilename());
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
