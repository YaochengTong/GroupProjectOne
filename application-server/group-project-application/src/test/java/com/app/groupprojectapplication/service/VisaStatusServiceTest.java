package com.app.groupprojectapplication.service;

import com.app.groupprojectapplication.dao.*;
import com.app.groupprojectapplication.domain.visaStatusManagement.DocumentInfo;
import com.app.groupprojectapplication.domain.visaStatusManagement.VisaStatusInfo;
import com.app.groupprojectapplication.file.AmazonS3FileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

public class VisaStatusServiceTest {

    @Mock
    IUserDao iUserDao;

    @Mock
    IEmployeeDao iEmployeeDao;

    @Mock
    IVisaStatusDao iVisaStatusDao;

    @Mock
    IPersonDao iPersonDao;

    @Mock
    AmazonS3FileService amazonS3FileService;

    @Mock
    IApplicationWorkFlowDao iApplicationWorkFlowDao;

    @Mock
    IPersonalDocumentDao iPersonalDocumentDao;

    @InjectMocks
    IVisaStatusService iVisaStatusService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetVisaInfoList() {
        List<VisaStatusInfo> expected = (Arrays.asList(
                new VisaStatusInfo("Bailey Bai", "F1(OPT/CPT)", "2021-01-05", "2021-09-10", 98, Arrays.asList(new DocumentInfo("OPT EAD.pdf", "2020-01-15"), new DocumentInfo("OPT EAD", "2020-02-16")), "Fill I-985", 1, 558, "Please fill I-983", "OPT EAD"),
                new VisaStatusInfo("Bailey Bai2", "F1(OPT/CPT)", "2021-01-05", "2021-09-10", 98, Arrays.asList(new DocumentInfo("OPT EAD.pdf", "2020-01-15"), new DocumentInfo("OPT EAD", "2020-02-16")), "Fill I-985", 1, 558, "Please fill I-983", "OPT EAD")
        ));
//
//        Mockito.when();
    }
}
