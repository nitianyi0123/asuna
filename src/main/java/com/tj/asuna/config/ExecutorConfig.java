package com.tj.asuna.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @author nitianyi
 * @date 2021/3/2
 */
@Configuration
public class ExecutorConfig {

    @Bean
    public ExecutorService executorService() {
        return new ThreadPoolExecutor(2, 8,
                0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
