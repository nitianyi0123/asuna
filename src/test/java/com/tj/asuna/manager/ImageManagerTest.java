package com.tj.asuna.manager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author nitianyi
 * @date 2021/3/2
 */
@SpringBootTest
class ImageManagerTest {

    @Autowired
    private ImageManager imageManager;

    @Test
    void downloadAndSave() {
        imageManager.downloadAndSave("https://atanx.alicdn.com/t/img/TB1tWvVJFXXXXc_aXXXXXXXXXXX-40-26.png", "asd");
    }
}