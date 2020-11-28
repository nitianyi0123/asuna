package com.tj.asuna.schedule;

import com.tj.asuna.biz.aq.AirQualityDataService;
import com.tj.asuna.manager.Pm25Manager;
import com.tj.asuna.model.AirQualityDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author nitianyi
 * @since 2020/11/28
 */
@Component
public class AirQualitySchedule {

    private static final String[] CITIES = new String[]{"shanghai"};

    private Pm25Manager pm25Manager;
    private AirQualityDataService airQualityDataService;

    @Autowired
    public void setPm25Manager(Pm25Manager pm25Manager) {
        this.pm25Manager = pm25Manager;
    }

    @Autowired
    public void setAirQualityDataService(AirQualityDataService airQualityDataService) {
        this.airQualityDataService = airQualityDataService;
    }

    @Scheduled(cron = "0 5 * * * ?", zone = "Asia/Shanghai")
    public void execute() {
        for (String city : CITIES) {
            List<AirQualityDetail> airQualityDetailList = pm25Manager.queryAqiDetails(city);
            airQualityDataService.save(airQualityDetailList);
        }
    }
}
