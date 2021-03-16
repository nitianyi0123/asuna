package com.tj.asuna.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

/**
 * @author nitianyi
 * @date 2021/3/15
 */
public class FaceDetectResp {

    @JsonProperty("request_id")
    private String requestId;
    @JsonProperty("faces")
    private List<Face> faces;
    @JsonProperty("image_id")
    private String imageId;
    @JsonProperty("time_used")
    private Integer timeUsed;
    @JsonProperty("error_message")
    private String errorMessage;
    @JsonProperty("face_num")
    private Integer faceNum;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public List<Face> getFaces() {
        return faces;
    }

    public void setFaces(List<Face> faces) {
        this.faces = faces;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public Integer getTimeUsed() {
        return timeUsed;
    }

    public void setTimeUsed(Integer timeUsed) {
        this.timeUsed = timeUsed;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getFaceNum() {
        return faceNum;
    }

    public void setFaceNum(Integer faceNum) {
        this.faceNum = faceNum;
    }

    public static class Face {

        @JsonProperty("face_token")
        private String faceToken;
        @JsonProperty("attributes")
        private Attributes attributes;

        public String getFaceToken() {
            return faceToken;
        }

        public void setFaceToken(String faceToken) {
            this.faceToken = faceToken;
        }

        public Attributes getAttributes() {
            return attributes;
        }

        public void setAttributes(Attributes attributes) {
            this.attributes = attributes;
        }
    }

    public static class Attributes {

        @JsonProperty("gender")
        private Map<String, String> gender;
        @JsonProperty("age")
        private Map<String, Integer> age;
        @JsonProperty("smile")
        private Map<String, Object> smile;
        @JsonProperty("emotion")
        private Map<String, Double> emotion;

        public Map<String, String> getGender() {
            return gender;
        }

        public void setGender(Map<String, String> gender) {
            this.gender = gender;
        }

        public Map<String, Integer> getAge() {
            return age;
        }

        public void setAge(Map<String, Integer> age) {
            this.age = age;
        }

        public Map<String, Object> getSmile() {
            return smile;
        }

        public void setSmile(Map<String, Object> smile) {
            this.smile = smile;
        }

        public Map<String, Double> getEmotion() {
            return emotion;
        }

        public void setEmotion(Map<String, Double> emotion) {
            this.emotion = emotion;
        }
    }
}
