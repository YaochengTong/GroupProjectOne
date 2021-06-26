package com.app.groupprojectapplication.service.impl;

import com.app.groupprojectapplication.dao.IContactDao;
import com.app.groupprojectapplication.dao.IEmployeeDao;
import com.app.groupprojectapplication.dao.IPersonDao;
import com.app.groupprojectapplication.dao.IUserDao;
import com.app.groupprojectapplication.domain.*;
import com.app.groupprojectapplication.domain.profile.*;
import com.app.groupprojectapplication.file.AmazonS3FileService;
import com.app.groupprojectapplication.service.IProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sun.java2d.cmm.ProfileDeferralInfo;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountedCompleter;
import java.util.concurrent.Executor;

@Service
@Slf4j
@Transactional
public class ProfileServiceImpl implements IProfileService {

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

    @Autowired
    ProfileAsyncService profileAsyncService;

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
    public Profile getProfileByEmployeeId(Integer user_id) {
        Integer employee_id = iUserDao.getEmployeeIdByUserId(user_id);
        Employee employee = iEmployeeDao.getEmployeeById(employee_id);
        return getProfileByEmployee(employee);
    }

    @Transactional
    @Async("taskExecutor")
    public Profile getProfileByEmployee(Employee employee) {
        Person person = employee.getPerson();

        CompletableFuture<NameSection> nameSectionCompletableFuture = profileAsyncService.setNameSection(employee,person);
        CompletableFuture<List<DocumentSection>> documentSectionCompletableFuture = profileAsyncService.setDocumentSectionList(profileAsyncService.getUserId(employee));
        CompletableFuture<AddressSection> addressSectionCompletableFuture = profileAsyncService.setAddressSection(person);
        CompletableFuture<ContactInfoSection> contactInfoSectionCompletableFuture = profileAsyncService.setContactInfoSection(employee,person);
        CompletableFuture<EmploymentSection> employmentSectionCompletableFuture = profileAsyncService.setEmploymentSection(employee);
        CompletableFuture<EmergencyContactList> emergencyListCompletableFuture = profileAsyncService.setEmergencyContactList(person);


        Profile profile = Profile.builder()
                .nameSection(nameSectionCompletableFuture.join())
                .addressSection(addressSectionCompletableFuture.join())
                .contactInfoSection(contactInfoSectionCompletableFuture.join())
                .employmentSection(employmentSectionCompletableFuture.join())
                .emergencyContactList(emergencyListCompletableFuture.join())
                .documentSectionList(documentSectionCompletableFuture.join())
                .build();

        return profile;
    }






    @Override
    public List<Summary> getSummary() {
        List<Summary> summaryList = new ArrayList<>();
        Integer index = 1;
        List<Employee> employeeList = iEmployeeDao.getEmployee();
        for(Employee employee : employeeList) {
            summaryList.add(profileAsyncService.getSummaryByEmployee(employee, index));
            index++;
        }
        return summaryList;
    }

    @Override
    public Map<String, Object> uploadAvatar(List<MultipartFile> files, Map<String, Object> paramMap) {
        Map<String, Object> resultMap = new HashMap<>();
        Integer userId = Integer.parseInt((String) paramMap.get("userId"));
        Integer employeeId = iUserDao.getEmployeeIdByUserId(userId);
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




}
