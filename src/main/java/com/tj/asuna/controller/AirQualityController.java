package com.tj.asuna.controller;

import com.tj.asuna.biz.aq.AirQualityDataService;
import com.tj.asuna.manager.Pm25Manager;
import com.tj.asuna.model.AirQualityDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @author nitianyi
 * @since 2020/11/28
 */
@RestController
@RequestMapping(path = "/air_quality")
public class AirQualityController {

    private static final Logger logger = LoggerFactory.getLogger(AirQualityController.class);

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

    @GetMapping(path = "/aqi_detail/{city}")
    public List<AirQualityDetail> queryAqiDetails(@PathVariable("city") String city) {
        return pm25Manager.queryAqiDetails(city);
    }

    @GetMapping(path = "/download/{city}")
    public String download(@PathVariable("city") String city,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        response.setContentType("application/octet-stream");
        try (OutputStream os = response.getOutputStream()) {
            airQualityDataService.exportExcel(city, os);
            os.flush();
            return "success";
        } catch (IOException ioe) {
            logger.error("download occur error", ioe);
        }
        return "fail";
    }
}
