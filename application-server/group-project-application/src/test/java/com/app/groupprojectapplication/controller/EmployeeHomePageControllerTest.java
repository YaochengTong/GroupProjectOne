package com.app.groupprojectapplication.controller;

import com.app.groupprojectapplication.domain.homeElement.HomeElement;
import com.app.groupprojectapplication.service.impl.HomeElementImpl;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest(controllers = EmployeeHomePageController.class)
@RunWith(MockitoJUnitRunner.class)
class EmployeeHomePageControllerTest {

    private static final Gson gson = new Gson();

    @MockBean
    private HomeElementImpl homeService;

    @InjectMocks
    EmployeeHomePageController employeeHomePageController;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testHomeElement() throws Exception {

        HomeElement homeElement = new HomeElement();
        Mockito.when(homeService.getHomeElementByEmployeeId(1)).thenReturn(homeElement);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/employeeHome/1"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(
                        gson.toJson(homeElement))
                );
    }


}