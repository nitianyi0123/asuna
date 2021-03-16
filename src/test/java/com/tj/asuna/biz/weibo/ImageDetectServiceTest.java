package com.tj.asuna.biz.weibo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author nitianyi
 * @date 2021/3/15
 */
@SpringBootTest
class ImageDetectServiceTest {

    @Autowired
    private ImageDetectService imageDetectService;

    @Test
    void detect() {
        imageDetectService.detect(
                "/Users/macuser/Desktop/微博脸数据整理/实拍人脸/20210314/杨浦/头像",
                "/Users/macuser/Desktop/微博脸数据整理/实拍人脸-20210314-杨浦.xlsx");
    }

    @Test
    void exportExcel() {
    }
}