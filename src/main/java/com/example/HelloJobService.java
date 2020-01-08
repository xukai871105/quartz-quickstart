package com.example;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

/**
 * @author xukai
 */
@Service
public class HelloJobService {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    /**
     * 启动任务
     * @throws SchedulerException
     */
    public void schedulerHelloJob() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobDetail helloJobDetail = JobBuilder.newJob(HelloJob.class)
                .withIdentity(HelloJob.helloJobKey)
                .build();

        Trigger helloJobTrigger = TriggerBuilder.newTrigger()
                .withIdentity(HelloJob.helloTriggerKey)
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ? "))
                .build();

        scheduler.scheduleJob(helloJobDetail, helloJobTrigger);
    }

    /**
     * 停止任务
     * @throws SchedulerException
     */
    public void deleteHelloJob() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        scheduler.deleteJob(HelloJob.helloJobKey);
    }
}
