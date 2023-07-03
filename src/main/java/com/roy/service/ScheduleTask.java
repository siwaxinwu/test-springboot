package com.roy.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.util.Date;

/**
 * @Author: dingyawu
 * @Description: TODO
 * @Date: Created in 17:33 2023/2/21
 */
//@Component
public class ScheduleTask implements SchedulingConfigurer {


    private String cron = "0/10 * * * * ?";

    public void setCron(String cron) {
        this.cron = cron;
    }

    private static final Logger logger = LoggerFactory.getLogger(ScheduleTask.class);


    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(new Runnable() {
            @Override
            public void run() {
                logger.info("current time is:{}", new Date());
            }
        }, new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                CronTrigger cronTrigger = new CronTrigger(cron);
                Date date = cronTrigger.nextExecutionTime(triggerContext);
                return date;
            }
        });
    }
}
