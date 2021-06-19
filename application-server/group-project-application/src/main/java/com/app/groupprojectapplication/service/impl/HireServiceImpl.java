package com.app.groupprojectapplication.service.impl;

import com.amazonaws.services.dynamodbv2.xspec.M;
import com.app.groupprojectapplication.dao.*;
import com.app.groupprojectapplication.domain.*;
import com.app.groupprojectapplication.email.EmailService;
import com.app.groupprojectapplication.file.AmazonS3FileService;
import com.app.groupprojectapplication.service.IHireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.*;

@Service
@Transactional
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
    private IVisaStatusDao iVisaStatusDao;

    @Autowired
    private IRegistrationTokenDao iRegistrationTokenDao;

    @Autowired
    private IApplicationWorkFlowDao iApplicationWorkFlowDao;

    @Autowired
    private IAddressDao iAddressDao;

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
        Person person = this.buildPerson(
                paramMap.get("firstName").toString(),
                paramMap.get("lastName").toString(),
                paramMap.get("middleName").toString(),
                paramMap.get("email").toString(),
                paramMap.get("cellPhone").toString(),
                timestamp,
                paramMap.get("address").toString(),
                paramMap.get("address2").toString(),
                paramMap.get("city").toString(),
                paramMap.get("state").toString(),
                paramMap.get("stateFullName").toString(),
                paramMap.get("postalCode").toString()
        );
        person.setAlternatePhone(paramMap.get("workPhone").toString());
        person.setGender(paramMap.get("gender").toString());
        person.setSsn(paramMap.get("ssn").toString());
        person.setDob(new Timestamp(Long.parseLong(paramMap.get("dateOfBirth").toString())));
        user.setPerson(person);

        //insert person
        Integer person_id = iPersonDao.insertPerson(person);
        person.setId(person_id);

        for(Address address : person.getAddresses()){
            address.setPerson(person);
            iAddressDao.insertAddress(address);
        }

        //update user
        iUserDao.updateUser(user);

        //Step 1.2 emergency contact
        Person emergencyContactPerson = this.buildPerson(
                paramMap.get("emergency1FirstName").toString(),
                paramMap.get("emergency1LastName").toString(),
                paramMap.get("emergency1MiddleName").toString(),
                paramMap.get("emergency1Email").toString(),
                paramMap.get("emergency1Phone").toString(),
                timestamp,
                paramMap.get("emergency1Address").toString(),
                paramMap.get("emergency1Address2").toString(),
                paramMap.get("emergency1City").toString(),
                paramMap.get("emergency1State").toString(),
                paramMap.get("emergency1StateFullName").toString(),
                paramMap.get("emergency1PostalCode").toString()
        );

        //insert emergency contact person
        Integer emergencyPerson1ID = iPersonDao.insertPerson(emergencyContactPerson);
        emergencyContactPerson.setId(emergencyPerson1ID);
        for(Address address : emergencyContactPerson.getAddresses()){
            address.setPerson(emergencyContactPerson);
            iAddressDao.insertAddress(address);
        }

        //insert into contact
        Contact contact = this.buildContact(
                person,
                emergencyPerson1ID,
                "employee",
                true,
                false,
                false,
                paramMap.get("emergency1Relationship").toString());
        iContactDao.insertContact(contact);


        //emergency contact 2
        if(paramMap.get("emergency2FirstName") != null && !paramMap.get("emergency2FirstName").equals("")) {
            Person emergencyPerson2 = this.buildPerson(
                    paramMap.get("emergency2FirstName").toString(),
                    paramMap.get("emergency2LastName").toString(),
                    paramMap.get("emergency2MiddleName").toString(),
                    paramMap.get("emergency2Email").toString(),
                    paramMap.get("emergency2Phone").toString(),
                    timestamp,
                    paramMap.get("emergency2Address").toString(),
                    paramMap.get("emergency2Address2").toString(),
                    paramMap.get("emergency2City").toString(),
                    paramMap.get("emergency2State").toString(),
                    paramMap.get("emergency2StateFullName").toString(),
                    paramMap.get("emergency2PostalCode").toString()
            );
            //insert emergency contact 2
            Integer emergencyPerson2Id = iPersonDao.insertPerson(emergencyPerson2);
            for(Address address : emergencyPerson2.getAddresses()){
                address.setPerson(emergencyPerson2);
                iAddressDao.insertAddress(address);
            }

            //insert into contact
            Contact emergencyContact2 = this.buildContact(person,
                    emergencyPerson2Id,
                    "employee",
                    true,
                    false,
                    false,
                    paramMap.get("emergency2Relationship").toString());
            iContactDao.insertContact(emergencyContact2);
        }

        //Step 1.3 reference contact
        if(paramMap.get("referenceContactFirstName") != null
                && !paramMap.get("referenceContactFirstName").equals("")) {
            Person referencePerson = this.buildPerson(
                    paramMap.get("referenceContactFirstName").toString(),
                    paramMap.get("referenceContactLastName").toString(),
                    paramMap.get("referenceContactMiddleName").toString(),
                    paramMap.get("referenceContactEmail").toString(),
                    paramMap.get("referenceContactPhone").toString(),
                    timestamp,
                    paramMap.get("referenceContactAddress").toString(),
                    paramMap.get("referenceContactAddress2").toString(),
                    paramMap.get("referenceContactCity").toString(),
                    paramMap.get("referenceContactState").toString(),
                    paramMap.get("referenceContactStateFullName").toString(),
                    paramMap.get("referenceContactPostalCode").toString()
            );
            //insert reference contact
            Integer referencePersonId = iPersonDao.insertPerson(referencePerson);
            for(Address address : referencePerson.getAddresses()){
                address.setPerson(referencePerson);
                iAddressDao.insertAddress(address);
            }

            //insert into contact
            Contact referenceContact = this.buildContact(
                    person,
                    referencePersonId,
                    "employee",
                    false,
                    true,
                    false,
                    paramMap.get("referenceContactRelationship").toString());
            iContactDao.insertContact(referenceContact);
        }

        //1.4 Insert into visa status and employee
        //Visa status
        VisaStatus visaStatus = new VisaStatus();
        visaStatus.setModificationDate(timestamp);
        Boolean isCitizen = Boolean.parseBoolean(paramMap.get("isCitizen").toString());
        if(isCitizen){
            visaStatus.setVisaType(paramMap.get("citizenType").toString());
            visaStatus.setIsActive((byte) 1);
        }
        else if(paramMap.get("authorizationType") != null && !paramMap.get("authorizationType").equals("")){
            visaStatus.setVisaType(paramMap.get("authorizationType").toString());
        }
        else visaStatus.setVisaType(paramMap.get("otherAuthorizationType").toString());
        //check the visa start date and end date to decide whether it is active
        Timestamp visaStart = new Timestamp(Long.parseLong(paramMap.get("authorizationStartDate").toString()));
        Timestamp visaEnd = new Timestamp(Long.parseLong(paramMap.get("authorizationEndDate").toString()));
        if(timestamp.after(visaStart) && timestamp.before(visaEnd)){
            visaStatus.setIsActive((byte) 1);
        }
        else visaStatus.setIsActive((byte) 0);
        visaStatus.setUser(user);

        //employee
        Employee employee = new Employee();
        employee.setPerson(person);
        employee.setTitle("javaSDE");
        employee.setManagerId(8842);
        employee.setStartDate(timestamp);
        employee.setAvartar("default_avatar");
        String car = paramMap.get("carMaker").toString() + " " + paramMap.get("carModel").toString() + " "
                + paramMap.get("carColor").toString();
        employee.setCar(car);
        employee.setVisaStatus(visaStatus);
        employee.setVisaStartDate(visaStart);
        employee.setVisaEndDate(visaEnd);
        employee.setDriverLicense(paramMap.get("driverLicense").toString());
        if(paramMap.get("driverLicenseExp").toString() != null &&
                !paramMap.get("driverLicenseExp").toString().equals("")){
            employee.setDriverLicenseExpirationDate(
                    new Timestamp(Long.parseLong(paramMap.get("driverLicenseExp").toString()))
            );
        }
        employee.setHouse(null);
        Set<Employee> employeeSet = new HashSet<>();
        employeeSet.add(employee);
        visaStatus.setEmployeeSet(employeeSet);

        Integer visaStatusId = iVisaStatusDao.insertVisa(visaStatus);
        visaStatus.setId(visaStatusId);

        Integer employeeId = iEmployeeDao.insertEmployee(employee);
        employee.setId(employeeId);

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

    private Person buildPerson(String firstName,
                 String lastName, String middleName, String email, String phone, Timestamp timestamp,
                 String address1, String address2, String city, String stateAbbr, String stateName,
                 String zipCode){
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setMiddleName(middleName);
        person.setEmail(email);
        person.setPrimaryPhone(phone);
        person.setDob(timestamp);

        Address address = new Address();
        address.setAddressLine1(address1);
        address.setAddressLine2(address2);
        address.setCity(city);
        address.setStateAbbr(stateAbbr);
        address.setStateName(stateName);
        address.setZipCode(zipCode);
        address.setPerson(person);
        Set<Address> addressSet = new HashSet<>();
        addressSet.add(address);
        person.setAddresses(addressSet);
        return person;
    }

    private Contact buildContact(Person person, Integer relatedPersonId, String title,
                 boolean isEmergency, boolean isReference, boolean isLandlord, String relationship){
        Contact contact = new Contact();
        contact.setPerson(person);
        contact.setRelated_person_id(relatedPersonId);
        contact.setTitle(title);
        contact.setIsEmergency(isEmergency? (byte) 1: (byte) 0);
        contact.setIsReferrence(isReference? (byte) 1: (byte) 0);
        contact.setIsLandlord(isLandlord? (byte) 1: (byte) 0);
        contact.setRelationship(relationship);
        return contact;
    }

    @Override
    public Map<String, Object> getOnboardApplications(Map<String, Object> paramMap) {
        Map<String, Object> resultMap = new HashMap<>();
        String type = "Onboarding";
        List<ApplicationWorkflow> resultList =
                iApplicationWorkFlowDao.getApplicationWorkFlowByApplicationType(type);

        List<Map<String, Object>> list = new ArrayList<>();
        for(ApplicationWorkflow awf : resultList){
            Map<String, Object> map = new HashMap<>();
            User user = awf.getUser();
            map.put("applicationId", awf.getId());
            map.put("userId", user.getId());
            map.put("email", user.getEmail());
            Person person = user.getPerson();
            map.put("firstName", person.getFirstName());
            map.put("lastName", person.getLastName());
            map.put("middleName", person.getMiddleName());
            map.put("SSN", person.getSsn());
            map.put("Gender", person.getGender());
            map.put("DateOfBirth", person.getDob());
            map.put("PrimaryPhone", person.getPrimaryPhone());
            map.put("WorkPhone", person.getAlternatePhone());
            //address
            List<Address> addressList = iAddressDao.getAddressByPersonId(person.getId());
            Optional<Address> person_address = addressList.stream().findFirst();
            person_address.ifPresent(value -> {
                map.put("Address", value.getAddressLine1());
                map.put("Address2", value.getAddressLine2());
                map.put("City", value.getCity());
                map.put("StateAbbr", value.getStateAbbr());
                map.put("StateFullName", value.getStateName());
                map.put("PostalCode", value.getZipCode());
            });

            //employee
            Employee employee = iEmployeeDao.getEmployeeById(
                    iUserDao.getEmployeeIdByUserId(user.getId()));
            map.put("DriverLicense", employee.getDriverLicense());
            map.put("DriverLicenseExpireDate", employee.getDriverLicenseExpirationDate());
            map.put("car", employee.getCar());
            map.put("visaStartDate", employee.getVisaStartDate());
            map.put("visaEndDate", employee.getVisaEndDate());

            //personal documents
            List<PersonalDocument> documents = iPersonalDocumentDao.
                    getPersonalDocumentsByUserId(user.getId());
            List<Map<String, Object>> documentMapList = new ArrayList<>();
            documents.forEach((item) -> {
                Map<String, Object> documentMap = new HashMap<>();
                documentMap.put("id", item.getId());
                documentMap.put("comment", item.getComment());
                documentMap.put("createDate", item.getCreateDate());
                documentMap.put("title", item.getTitle());
                documentMap.put("path", item.getPath().substring(0, item.getPath().indexOf('?')));
                documentMapList.add(documentMap);
            });
            map.put("documents", documentMapList);

            //visa status
            String visaType = iVisaStatusDao.getVisaTypeByEmployeeId(employee.getId());
            map.put("visaType", visaType);

            //contacts
            List<Contact> emergencyContactList = new ArrayList<>();
            for(Contact contact : person.getContacts()){
                if(contact.getIsEmergency() == (byte)1){
                    emergencyContactList.add(contact);
                }
                else if(contact.getIsReferrence() == (byte)1){
                    Map<String, Object> referenceMap = new HashMap<>();
                    Integer contactPersonId = contact.getRelated_person_id();
                    Person contactPerson = iPersonDao.getPersonById(contactPersonId);
                    referenceMap.put("ReferenceContactFirstName", contactPerson.getFirstName());
                    referenceMap.put("ReferenceContactLastName", contactPerson.getLastName());
                    referenceMap.put("ReferenceContactMiddleName", contactPerson.getMiddleName());
                    referenceMap.put("ReferenceContactPhone", contactPerson.getPrimaryPhone());
                    List<Address> referenceAddressList = iAddressDao.
                            getAddressByPersonId(contactPerson.getId());
                    Optional<Address> address = referenceAddressList.stream().findFirst();
                    address.ifPresent(value -> {
                        referenceMap.put("ReferenceContactAddress", value.getAddressLine1());
                        referenceMap.put("ReferenceContactAddress2", value.getAddressLine2());
                        referenceMap.put("ReferenceContactCity", value.getCity());
                        referenceMap.put("ReferenceContactStateAbbr", value.getStateAbbr());
                        referenceMap.put("ReferenceContactStateFullName", value.getStateName());
                        referenceMap.put("ReferenceContactStatePostalCode", value.getZipCode());
                    });
                    referenceMap.put("ReferenceContactEmail", contactPerson.getEmail());
                    referenceMap.put("ReferenceContactRelationShip", contact.getRelationship());
                    map.put("referenceContact", referenceMap);
                }
            }

            List<Map<String, Object>> emergencyContactMapList = new ArrayList<>();
            for(int i=0; i<emergencyContactList.size(); i++){
                //int finalI = i + 1;
                Map<String, Object> emergencyMap = new HashMap<>();

                Integer emergencyPersonId = emergencyContactList.get(i).getRelated_person_id();
                Person emergencyPerson = iPersonDao.getPersonById(emergencyPersonId);
                emergencyMap.put("EmergencyContactFirstName", emergencyPerson.getFirstName());
                emergencyMap.put("EmergencyContactLastName", emergencyPerson.getLastName());
                emergencyMap.put("EmergencyContactMiddleName", emergencyPerson.getMiddleName());
                emergencyMap.put("EmergencyContactPhone", emergencyPerson.getPrimaryPhone());
                List<Address> emergencyAddressList = iAddressDao.
                        getAddressByPersonId(emergencyPerson.getId());
                Optional<Address> address = emergencyAddressList.stream().findFirst();
                address.ifPresent(value -> {
                    emergencyMap.put("EmergencyContactAddress", value.getAddressLine1());
                    emergencyMap.put("EmergencyContactAddress2", value.getAddressLine2());
                    emergencyMap.put("EmergencyContactCity", value.getCity());
                    emergencyMap.put("EmergencyContactStateAbbr", value.getStateAbbr());
                    emergencyMap.put("EmergencyContactStateFullName", value.getStateName());
                    emergencyMap.put("EmergencyContactPostalCode", value.getZipCode());
                });
                emergencyMap.put("EmergencyContactEmail", emergencyPerson.getEmail());
                emergencyMap.put("EmergencyContactRelationShip",
                        emergencyContactList.get(i).getRelationship());
                emergencyContactMapList.add(emergencyMap);
            }
            map.put("emergencyContactList", emergencyContactMapList);
            list.add(map);
        }

        resultMap.put("OngoingApplications", list);
        return resultMap;
    }

}
