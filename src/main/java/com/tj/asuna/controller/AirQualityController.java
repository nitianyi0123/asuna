package com.tj.asuna.controller;

import com.tj.asuna.biz.aq.AirQualityDataService;
import com.tj.asuna.manager.Pm25Manager;
import com.tj.asuna.model.AirQualityDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

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

    @GetMapping(path = "/aqi_detail")
    public List<AirQualityDetail> queryAqiDetails(@RequestParam("city") String city) {
        return pm25Manager.queryAqiDetails(city);
    }

    @GetMapping(path = "/download")
    public String download(@RequestParam("city") String city,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        String fileName = city + "_" + System.currentTimeMillis() / 1000 + ".xlsx";
        response.setContentType("application/octet-stream");
        try {
            response.setHeader(CONTENT_DISPOSITION, "attachment;filename=" +
                    URLEncoder.encode(fileName, StandardCharsets.UTF_8.displayName()));
        } catch (UnsupportedEncodingException uee) {
            logger.error("URLEncoder encode occur error, filename: {}", fileName, uee);
        }
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
