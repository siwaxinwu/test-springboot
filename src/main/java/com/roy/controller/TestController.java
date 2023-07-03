package com.roy.controller;


import com.roy.service.ScheduleTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author: dingyawu
 * @Description: TODO
 * @Date: Created in 11:03 2022/11/20
 */
@RestController
@Slf4j
public class TestController implements InitializingBean {

    @GetMapping("/test/{uid}")
    public String test(@PathVariable String uid)    {
        return uid + "-ok";
    }

    //@Autowired
    private ScheduleTask scheduleTask;


    @GetMapping("/update")
    public String test1(@RequestParam String time)  {
        scheduleTask.setCron(time);
        return "ok";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            ScheduledExecutorService  executorService = Executors.newScheduledThreadPool(1);
            executorService.scheduleAtFixedRate(this::runTask, 1,  1, TimeUnit.MINUTES);
        }catch (Exception e){
            log.error("execute init error", e);
        }
    }

    private void runTask() {
        log.info("execute at time:{}", new Date());
    }
}