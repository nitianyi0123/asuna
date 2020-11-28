package com.tj.asuna.manager;

import com.tj.asuna.model.AirQualityDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author nitianyi
 * @since 2020/11/28
 */
@SpringBootTest
class Pm25ManagerTest {

    @Autowired
    private Pm25Manager pm25Manager;

    @Test
    void queryAqiDetails() {
        List<AirQualityDetail> resp = pm25Manager.queryAqiDetails("nanjing", null, null);
        System.out.println(resp);
    }
}