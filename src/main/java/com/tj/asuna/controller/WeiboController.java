package com.tj.asuna.controller;

import com.tj.asuna.biz.weibo.WeiboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nitianyi
 * @date 2021/3/2
 */
@RestController
@RequestMapping("/weibo")
public class WeiboController {

    @Autowired
    private WeiboService weiboService;

    @PostMapping(path = "/trigger")
    public void trigger() {
        weiboService.searchUntilNow("外滩打卡");
        weiboService.searchUntilNow("徐汇滨江");
        weiboService.searchUntilNow("杨浦滨江");
    }
}
