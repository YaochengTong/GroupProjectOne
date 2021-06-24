package com.app.groupprojectapplication.controller;

import com.app.groupprojectapplication.domain.visaStatusManagement.DocumentInfo;
import com.app.groupprojectapplication.domain.visaStatusManagement.VisaStatusInfo;
import com.app.groupprojectapplication.email.EmailService;
import com.app.groupprojectapplication.service.IVisaStatusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@WebMvcTest(controllers = VisaStatusController.class)
@ExtendWith(MockitoExtension.class)
public class VisaStatusControllerTest {

    @MockBean
    private IVisaStatusService iVisaStatusService;

    @MockBean
    private EmailService emailService;

    @InjectMocks
    VisaStatusController visaStatusController;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetVisaStatusInfo() throws Exception {
        VisaStatusInfo visaStatusInfo = new VisaStatusInfo("Bailey Bai", "F1(OPT/CPT)", "2021-01-05", "2021-09-10", 98, Arrays.asList(new DocumentInfo("OPT Receipt.pdf", "2020-01-15"), new DocumentInfo("OPT EAD.pdf", "2020-02-16")), "Fill I-985", 1, 558, "Please fill I-983", "OPT EAD");
        Mockito.when(iVisaStatusService.getVisaInfo(Mockito.anyInt())).thenReturn(visaStatusInfo);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/visa-status-management/558"))
            .andExpect(MockMvcResultMatchers.content().json("{'visaStatusInfo':{" +
                    "'fullName':'Bailey Bai'," +
                    "'workAuthorization': 'F1(OPT/CPT)'," +
                    "'authorizationStartDate': '2021-01-05'," +
                    "'authorizationEndDate': '2021-09-10'," +
                    "'authorizationDayLeft': 98," +
                    "'documentReceived': [{" +
                    "                   'name':'OPT EAD.pdf'," +
                    "                    'date': '2020-02-16'" +
                    "                },{" +
                    "                    'name': 'OPT Receipt.pdf'," +
                    "                    'date': '2020-01-15'" +
                    "                }]," +
                    "'nextStep': 'Fill I-985'," +
                    "'idx': 1," +
                    "'userId': 558," +
                    "'message': 'Please fill I-983'," +
                    "'currStep': 'OPT EAD'}}"));

    }

    @Test
    public void testGetVisaInfoList() throws Exception {
        List<VisaStatusInfo> list = Arrays.asList(new VisaStatusInfo("Bailey Bai", "F1(OPT/CPT)", "2021-01-05", "2021-09-10", 98, Arrays.asList(new DocumentInfo("OPT Receipt.pdf", "2020-01-15"), new DocumentInfo("OPT EAD.pdf", "2020-02-16")), "Fill I-985", 1, 558, "Please fill I-983", "OPT EAD"));
        Mockito.when(iVisaStatusService.getVisaInfoList()).thenReturn(list);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/visa-status-management/all"))
                .andExpect(MockMvcResultMatchers.content().json("{" +
                        "'visaStatusInfoList': [" +
                        "{"+
                        "'fullName':'Bailey Bai'," +
                        "'workAuthorization': 'F1(OPT/CPT)'," +
                        "authorizationStartDate: '2021-01-05'," +
                        "'authorizationEndDate': '2021-09-10'," +
                        "'authorizationDayLeft': 98," +
                        "'documentReceived': [{" +
                        "                   'name':'OPT EAD.pdf'," +
                        "                    'date': '2020-02-16'" +
                        "                },{" +
                        "                    'name': 'OPT Receipt.pdf'," +
                        "                    'date': '2020-01-15'" +
                        "                }]," +
                        "'nextStep': 'Fill I-985'," +
                        "'idx': 1," +
                        "'userId': 558," +
                        "'message': 'Please fill I-983'," +
                        "'currStep': 'OPT EAD'}]}"));
    }

    @Test
    public void testUpdateVisaStatusInfo() throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("fullName", "Bailey Bail2");
        map.put("userId", 558);
        map.put("authorizationEndDate", "2022-05-19");
        map.put("authorizationStartDate", "2020-06-07");
        map.put("workAuthorization", "F1(OPT/CPT)");
        Mockito.when(iVisaStatusService.updateInfo(map)).thenReturn(true);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/visa-status-management/update"))
                .andExpect(MockMvcResultMatchers.content().json("{'success':true}"));

    }

    @Test
    public void testSendNotification() throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", "558");

        Mockito.when(iVisaStatusService.findEmailByUserId(Mockito.anyInt())).thenReturn("xinye.bai4@gmail.com");

        this.mockMvc.perform(MockMvcRequestBuilders.post("/visa-status-management/send-notification"))
                .andExpect(MockMvcResultMatchers.content().json("{'success':true, 'email':'xinye.bai4@gmail.com'}"));
    }
}
