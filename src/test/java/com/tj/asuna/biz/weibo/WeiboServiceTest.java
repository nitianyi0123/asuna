package com.tj.asuna.biz.weibo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author nitianyi
 * @date 2021/3/2
 */
@SpringBootTest
class WeiboServiceTest {

    @Autowired
    private WeiboService weiboService;

    @Test
    void searchUntilNow() {
        weiboService.searchUntilNow("外摊打卡");
    }

    @Test
    void yearRoll() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2019);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DATE, 1);
        Calendar now = Calendar.getInstance();
        while (cal.compareTo(now) <= 0) {
            Date d = cal.getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String df = simpleDateFormat.format(d);
            System.out.println(df);
            cal.add(Calendar.DATE, 1);
        }
    }
}