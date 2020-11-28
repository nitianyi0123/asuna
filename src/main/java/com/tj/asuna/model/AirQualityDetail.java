package com.tj.asuna.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author nitianyi
 * @since 2020/11/27
 */
public class AirQualityDetail extends AirQuality {

    /**
     * 二氧化硫1小时平均
     */
    @JsonProperty("so2")
    private String so2;
    /**
     * 二氧化硫24小时滑动平均
     */
    @JsonProperty("so2_24h")
    private String so224h;
    /**
     * 二氧化氮1小时平均
     */
    @JsonProperty("no2")
    private String no2;
    /**
     * 二氧化氮24小时滑动平均
     */
    @JsonProperty("no2_24h")
    private String no224h;
    /**
     * 颗粒物（粒径小于等于10μm）1小时平均
     */
    @JsonProperty("pm10")
    private String pm10;
    /**
     * 颗粒物（粒径小于等于10μm）24小时滑动平均
     */
    @JsonProperty("pm10_24h")
    private String pm1024h;
    /**
     * 一氧化碳1小时平均
     */
    @JsonProperty("co")
    private String co;
    /**
     * 一氧化碳24小时滑动平均
     */
    @JsonProperty("co_24h")
    private String co24h;
    /**
     * 臭氧1小时平均
     */
    @JsonProperty("o3")
    private String o3;
    /**
     * 臭氧日最大1小时平均
     */
    @JsonProperty("o3_24h")
    private String o324h;
    /**
     * 臭氧8小时滑动平均
     */
    @JsonProperty("o3_8h")
    private String o38h;
    /**
     * 臭氧日最大8小时滑动平均
     */
    @JsonProperty("o3_8h_24h")
    private String o38h24h;
    /**
     * 颗粒物（粒径小于等于2.5μm）1小时平均
     */
    @JsonProperty("pm2_5")
    private String pm25;
    /**
     * 颗粒物（粒径小于等于2.5μm）24小时滑动平均
     */
    @JsonProperty("pm2_5_24h")
    private String pm2524h;

    public String getSo2() {
        return so2;
    }

    public void setSo2(String so2) {
        this.so2 = so2;
    }

    public String getSo224h() {
        return so224h;
    }

    public void setSo224h(String so224h) {
        this.so224h = so224h;
    }

    public String getNo2() {
        return no2;
    }

    public void setNo2(String no2) {
        this.no2 = no2;
    }

    public String getNo224h() {
        return no224h;
    }

    public void setNo224h(String no224h) {
        this.no224h = no224h;
    }

    public String getPm10() {
        return pm10;
    }

    public void setPm10(String pm10) {
        this.pm10 = pm10;
    }

    public String getPm1024h() {
        return pm1024h;
    }

    public void setPm1024h(String pm1024h) {
        this.pm1024h = pm1024h;
    }

    public String getCo() {
        return co;
    }

    public void setCo(String co) {
        this.co = co;
    }

    public String getCo24h() {
        return co24h;
    }

    public void setCo24h(String co24h) {
        this.co24h = co24h;
    }

    public String getO3() {
        return o3;
    }

    public void setO3(String o3) {
        this.o3 = o3;
    }

    public String getO324h() {
        return o324h;
    }

    public void setO324h(String o324h) {
        this.o324h = o324h;
    }

    public String getO38h() {
        return o38h;
    }

    public void setO38h(String o38h) {
        this.o38h = o38h;
    }

    public String getO38h24h() {
        return o38h24h;
    }

    public void setO38h24h(String o38h24h) {
        this.o38h24h = o38h24h;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public String getPm2524h() {
        return pm2524h;
    }

    public void setPm2524h(String pm2524h) {
        this.pm2524h = pm2524h;
    }

    @Override
    public String toString() {
        return "AirQualityDetail{" +
                "so2='" + so2 + '\'' +
                ", so224h='" + so224h + '\'' +
                ", no2='" + no2 + '\'' +
                ", no224h='" + no224h + '\'' +
                ", pm10='" + pm10 + '\'' +
                ", pm1024h='" + pm1024h + '\'' +
                ", co='" + co + '\'' +
                ", co24h='" + co24h + '\'' +
                ", o3='" + o3 + '\'' +
                ", o324h='" + o324h + '\'' +
                ", o38h='" + o38h + '\'' +
                ", o38h24h='" + o38h24h + '\'' +
                ", pm25='" + pm25 + '\'' +
                ", pm2524h='" + pm2524h + '\'' +
                "} " + super.toString();
    }
}
