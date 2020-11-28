package com.tj.asuna.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nitianyi
 * @since 2020/11/28
 */
@RestController
public class HealthyController {

    @GetMapping("/check")
    public String check() {
        return "success";
    }
}
