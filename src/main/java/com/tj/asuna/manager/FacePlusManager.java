package com.tj.asuna.manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tj.asuna.model.FaceDetectResp;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * @author nitianyi
 * @date 2021/3/15
 */
@Component
public class FacePlusManager {

    @Value("${megvii.face-plus.host}")
    private String host;
    @Value("${megvii.face-plus.api-key}")
    private String apiKey;
    @Value("${megvii.face-plus.api-secret}")
    private String apiSecret;

    @Autowired
    private CloseableHttpClient httpClient;

    @Autowired
    private ObjectMapper objectMapper;

    public FaceDetectResp detectFace(File image) throws IOException {
        HttpPost request = new HttpPost(host + "/facepp/v3/detect");
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addTextBody("api_key", apiKey);
        builder.addTextBody("api_secret", apiSecret);
        builder.addBinaryBody("image_file", image);
        builder.addTextBody("return_attributes", "gender,age,smiling,emotion");
        request.setEntity(builder.build());
        HttpResponse response = httpClient.execute(request);
        return objectMapper.readValue(
                EntityUtils.toByteArray(response.getEntity()),
                FaceDetectResp.class);
    }
}
