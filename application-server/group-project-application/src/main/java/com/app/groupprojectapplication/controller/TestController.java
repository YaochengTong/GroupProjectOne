package com.app.groupprojectapplication.controller;

import com.app.groupprojectapplication.dao.impl.PersonDaoImpl;
import com.app.groupprojectapplication.dao.impl.UserDaoImpl;
import com.app.groupprojectapplication.domain.Person;
import com.app.groupprojectapplication.email.EmailService;
import com.app.groupprojectapplication.file.AmazonS3FileService;
import com.app.groupprojectapplication.service.IHireService;
import com.app.groupprojectapplication.service.ITestService;
import com.app.groupprojectapplication.service.IVisaStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/test")
@CrossOrigin
public class TestController {

    @Autowired
    private ITestService iTestService;

    @Autowired
    private PersonDaoImpl personDao;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AmazonS3FileService amazonS3FileService;

    @Autowired
    private IHireService iHireService;

    @Autowired
    private UserDaoImpl userDao;

    @Autowired
    private IVisaStatusService iVisaStatusService;

    @GetMapping("/get")
    //To see the example output, visit http://localhost:8999/test/get?a=1
    public Map<String, Object> testMethod(@RequestParam Map<String, Object> paramMap){
        //do not use domain/POJO in the controller.
        //use a map to receive all parameters from the frontend.
        //pass the map to the service layer and create POJO based on the values in the map.
        //iTestService.do_something(paramMap);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("a", paramMap.get("a"));
        //always return a map to the frontend.
        return resultMap;
    }

    @GetMapping("/sendEmail")
    public Map<String, Object> testEmail(@RequestParam Map<String, Object> paramMap) throws ExecutionException, InterruptedException {
        //emailService.sendMail("tjfy1992@gmail.com", "Test email", "Test text");
        iHireService.generateAToken("zhongqiu_gao@126.com", 556);
        return new HashMap<>();
    }


    @PostMapping("/fileUpload")
    public Map<String, Object> testFileUpload(@RequestParam("file") MultipartFile file){
        Map<String, Object> resultMap = new HashMap<>();
        InputStream ips = null;
        File file1 = null;
        try {
            file1 = File.createTempFile("temp", null);
            file.transferTo(file1);
            ips = new FileInputStream(file1);
            //change "test/" to {userid}/, so that the user's files will be uploaded to
            //the folder under his userid.
            //the result here will be the link to the uploaded file
            String result = amazonS3FileService.upload(ips, "test/" + file.getOriginalFilename());
            System.out.println(result);
            resultMap.put("link", result);
            file1.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //call this method to print out all files on server
        amazonS3FileService.printOutName();
        return resultMap;
    }

    @PostMapping("/fileUploadWithForm")
    public Map<String, Object> testFileUploadWithForm(@RequestParam Map<String, Object> paramMap,
                                                      HttpServletRequest request){
        MultipartHttpServletRequest params = ((MultipartHttpServletRequest) request);
        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getFiles("file");
        Map<String, Object> resultMap = new HashMap<>();
        return resultMap;
    }

    @GetMapping("/print-all-files")
    public void print() {
        amazonS3FileService.printOutName();
    }

    // for config
    @GetMapping("/delete/{user_id}")
    public Map<String, Object> deleteAmazonS3Folder(@PathVariable Integer user_id) {
        amazonS3FileService.deleteFolder(String.valueOf(user_id));
        Map<String, Object> result = new HashMap<>();
        result.put("delete", "yes");
        return result;
    }

    @GetMapping("/delete/{user_id}/{filename}")
    public Map<String, Object> deleteAmazonS3Folder(@PathVariable Integer user_id, @PathVariable String filename) {
        amazonS3FileService.deleteOneFile(String.valueOf(user_id), filename);
        Map<String, Object> result = new HashMap<>();
        result.put("delete", "yes");
        return result;
    }
}