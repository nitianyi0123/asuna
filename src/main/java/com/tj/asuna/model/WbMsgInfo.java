package com.tj.asuna.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nitianyi
 * @date 2021/3/2
 */
public class WbMsgInfo {

    private String nickname;
    private String article;
    private List<String> picUrls;
    private String postTime;
    private String location;
    private String gender;
    private String birthday;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public List<String> getPicUrls() {
        if (picUrls == null) {
            picUrls = new ArrayList<>();
        }
        return picUrls;
    }

    public void setPicUrls(List<String> picUrls) {
        this.picUrls = picUrls;
    }

    public void addPicUrl(String picUrl) {
        if (this.picUrls == null) {
            this.picUrls = new ArrayList<>();
        }
        this.picUrls.add(picUrl);
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
