package com.tj.asuna.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author nitianyi
 * @since 2020/11/27
 */
@Configuration
@ConfigurationProperties(prefix = "remote-call")
public class RemoteCallProperties {

    private Pm25 pm25;

    public Pm25 getPm25() {
        return pm25;
    }

    public void setPm25(Pm25 pm25) {
        this.pm25 = pm25;
    }

    public static class Pm25 {
        private String url;
        private String appKey;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAppKey() {
            return appKey;
        }

        public void setAppKey(String appKey) {
            this.appKey = appKey;
        }
    }



}
