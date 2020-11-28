package com.tj.asuna.dao.model;

/**
 * @author nitianyi
 * @since 2020/11/28
 */
public class AirQualityDO {
    private Long id;

    private String timePoint;

    private String area;

    private String positionName;

    private String stationCode;

    private String primaryPollutant;

    private String quality;

    private String aqi;

    private String so2;

    private String so224h;

    private String no2;

    private String no224h;

    private String pm10;

    private String pm1024h;

    private String co;

    private String co24h;

    private String o3;

    private String o324h;

    private String o38h;

    private String o38h24h;

    private String pm25;

    private String pm2524h;

    private Long ctime;

    private Long mtime;

    private Integer version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTimePoint() {
        return timePoint;
    }

    public void setTimePoint(String timePoint) {
        this.timePoint = timePoint;
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

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

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

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    public Long getMtime() {
        return mtime;
    }

    public void setMtime(Long mtime) {
        this.mtime = mtime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}