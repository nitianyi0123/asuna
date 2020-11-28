package com.tj.asuna.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author nitianyi
 * @since 2020/11/27
 */
public class AirQuality {

    /**
     * 数据发布的时间
     */
    @JsonProperty("time_point")
    private String timePoint;
    /**
     * 空气质量指数(AQI)，即air quality index，是定量描述空气质量状况的无纲量指数
     */
    @JsonProperty("aqi")
    private String aqi;
    /**
     * 城市名称
     */
    @JsonProperty("area")
    private String area;
    /**
     * 监测点名称
     */
    @JsonProperty("position_name")
    private String positionName;
    /**
     * 监测点编码
     */
    @JsonProperty("station_code")
    private String stationCode;
    /**
     * 首要污染物
     */
    @JsonProperty("primary_pollutant")
    private String primaryPollutant;
    /**
     * 空气质量指数类别，有“优、良、轻度污染、中度污染、重度污染、严重污染”6类
     */
    @JsonProperty("quality")
    private String quality;

    public String getTimePoint() {
        return timePoint;
    }

    public void setTimePoint(String timePoint) {
        this.timePoint = timePoint;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getPrimaryPollutant() {
        return primaryPollutant;
    }

    public void setPrimaryPollutant(String primaryPollutant) {
        this.primaryPollutant = primaryPollutant;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    @Override
    public String toString() {
        return "AirQuality{" +
                "timePoint='" + timePoint + '\'' +
                ", aqi=" + aqi +
                ", area='" + area + '\'' +
                ", positionName='" + positionName + '\'' +
                ", stationCode='" + stationCode + '\'' +
                ", primaryPollutant='" + primaryPollutant + '\'' +
                ", quality='" + quality + '\'' +
                '}';
    }
}
