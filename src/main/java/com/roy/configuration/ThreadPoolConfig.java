package com.roy.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @Author: dingyawu
 * @Description: TODO
 * @Date: Created in 14:07 2023/1/7
 */

@Configuration
public class ThreadPoolConfig {
    @Bean("threadPoolExecutor")
    public static ThreadPoolExecutor threadPoolExecutor() {
        return new ThreadPoolExecutor(1, 1,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());
    }
}
