package com.tj.asuna;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author nitianyi
 * @since 2020/11/27
 */
@EnableScheduling
@SpringBootApplication
public class AsunaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsunaApplication.class, args);
    }

}
