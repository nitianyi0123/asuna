package com.tj.asuna.manager;

import com.tj.asuna.config.RemoteCallProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author nitianyi
 * @since 2020/11/27
 */
@Service
public class Pm25Manager {

    private RestTemplate restTemplate;
    private RemoteCallProperties remoteCallProperties;

    @Autowired
    public Pm25Manager(RestTemplate restTemplate, RemoteCallProperties remoteCallProperties) {
        this.restTemplate = restTemplate;
        this.remoteCallProperties = remoteCallProperties;
    }


}
