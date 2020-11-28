package com.tj.asuna.schedule;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author nitianyi
 * @since 2020/11/28
 */
@SpringBootTest
class AirQualityScheduleTest {

    private AirQualitySchedule airQualitySchedule;

    @Autowired
    public void setAirQualitySchedule(AirQualitySchedule airQualitySchedule) {
        this.airQualitySchedule = airQualitySchedule;
    }

    @Test
    void execute() {
        airQualitySchedule.execute();
    }
}