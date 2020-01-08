package com.example;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

/**
 * HelloJob随应用一起启动
 * @author xukai
 */
// @Component
public class QuartzApplicationRunner implements ApplicationRunner {
    private final static Logger log = LoggerFactory.getLogger(QuartzApplicationRunner.class);

    @Autowired
    SchedulerFactoryBean schedulerFactoryBean;

    @Autowired
    HelloJobService helloJobService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        JobKey helloJobKey = new JobKey("helloJob", "defaultGroup");

        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        if (!scheduler.checkExists(helloJobKey)) {
            log.info("start helloJob");
            helloJobService.schedulerHelloJob();
        } else {
            log.info("helloJob is already running");
        }
    }
}
