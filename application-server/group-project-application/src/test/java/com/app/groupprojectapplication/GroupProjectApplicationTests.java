package com.app.groupprojectapplication;

import com.app.groupprojectapplication.service.IHouseService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GroupProjectApplicationTests {

    private IHouseService ih;

    @Test
    void contextLoads() {

        System.out.println(ih.getAllHouse());
    }

}
