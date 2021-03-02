package com.tj.asuna.manager;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tj.asuna.config.RemoteCallProperties;
import com.tj.asuna.model.AirQualityDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author nitianyi
 * @since 2020/11/27
 */
@Component
public class Pm25Manager {
    private static final Logger logger = LoggerFactory.getLogger(Pm25Manager.class);

    private static final String COMMON_PATH = "/api/querys";

    private final RestTemplate restTemplate;
    private final RemoteCallProperties remoteCallProperties;
    private ObjectMapper objectMapper;

    @Autowired
    public Pm25Manager(RestTemplate restTemplate, RemoteCallProperties remoteCallProperties) {
        this.restTemplate = restTemplate;
        this.remoteCallProperties = remoteCallProperties;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * 获取一个城市所有监测点的AQI数据（含详情)
     *
     * @param city 城市名称
     * @return
     */
    public List<AirQualityDetail> queryAqiDetails(String city) {
        return queryAqiDetails(city, null, null);
    }

    /**
     * 获取一个城市所有监测点的AQI数据（含详情)
     *
     * @param city     城市名称
     * @param avg      是否返回一个城市所有监测点数据均值
     * @param stations 是否只返回一个城市均值
     * @return
     */
    public List<AirQualityDetail> queryAqiDetails(String city, Boolean avg, Boolean stations) {
        String url = remoteCallProperties.getPm25().getUrl() + COMMON_PATH + "/aqi_details.json";
        StringBuilder param = new StringBuilder();
        param.append("token=").append(remoteCallProperties.getPm25().getAppKey());
        param.append("&city=").append(city);
        if (avg != null) {
            param.append("&avg=").append(avg);
        }
        if (stations != null) {
            param.append("&stations=").append(stations ? "yes" : "no");
        }
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity(url + "?" + param.toString(), Object.class);
        logger.info("query aqi_detail response: {}", responseEntity.toString());
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return objectMapper.convertValue(responseEntity.getBody(),
                    new TypeReference<List<AirQualityDetail>>() {
                    });
        }
        throw new RuntimeException("query aqi_detail response not 200");
    }


}
