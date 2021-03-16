package com.tj.asuna.manager;

import com.tj.asuna.model.FaceDetectResp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author nitianyi
 * @date 2021/3/15
 */
@SpringBootTest
class FacePlusManagerTest {

    @Autowired
    private FacePlusManager facePlusManager;

    @Test
    void detectFace() throws IOException {
        FaceDetectResp resp = facePlusManager.detectFace(new File("/Users/macuser/Desktop/微博脸数据整理/外滩脸/01 (1).jpg"));
        System.out.println(resp);
    }
}