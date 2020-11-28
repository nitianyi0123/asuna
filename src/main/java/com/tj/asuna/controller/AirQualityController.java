package com.tj.asuna.controller;

import com.tj.asuna.manager.Pm25Manager;
import com.tj.asuna.model.AirQualityDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author nitianyi
 * @since 2020/11/28
 */
@RestController
@RequestMapping(path = "/air_quality")
public class AirQualityController {

    private Pm25Manager pm25Manager;

    @Autowired
    public void setPm25Manager(Pm25Manager pm25Manager) {
        this.pm25Manager = pm25Manager;
    }

    @GetMapping(path = "/aqi_detail/{city}")
    public List<AirQualityDetail> queryAqiDetails(@PathVariable("city") String city) {
        return pm25Manager.queryAqiDetails(city);
    }
}
