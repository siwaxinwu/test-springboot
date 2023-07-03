package com.roy.controller;


import com.roy.bean.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: dingyawu
 * @Description: TODO
 * @Date: Created in 11:03 2022/11/20
 */
@RestController
@Slf4j
public class TestController implements InitializingBean {


    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    @GetMapping("/healthCheck")
    public String healthCheck()    {
        return "ok";
    }


    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Scheduled(cron = "0/1 * * * * ?")
    private void runTask() {
        threadPoolExecutor.execute(new Task());
        System.out.println(new Date() + ": thread pool queue size ： " + threadPoolExecutor.getQueue().size());
    }
}