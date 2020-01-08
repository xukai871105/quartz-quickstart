package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author xukai
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {TestQuartzApplication.class})
public class HelloJobTest {

    private final static Logger log = LoggerFactory.getLogger(HelloJobTest.class);

    @Autowired
    SchedulerFactoryBean schedulerFactoryBean;

    @Test
    public void testStartThenDeleteHelloJob() throws SchedulerException, InterruptedException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        JobDetail helloJobDetail = JobBuilder.newJob(HelloJob.class)
                .withIdentity(HelloJob.helloJobKey)
                .build();

        Trigger helloJobTrigger = TriggerBuilder.newTrigger()
                .withIdentity(HelloJob.helloTriggerKey)
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ? "))
                .build();

        scheduler.scheduleJob(helloJobDetail, helloJobTrigger);

        Thread.sleep(60000);

        scheduler.deleteJob(HelloJob.helloJobKey);
    }

    @Test
    public void testDeleteHelloJob() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        if (scheduler.checkExists(HelloJob.helloJobKey)) {
            scheduler.deleteJob(HelloJob.helloJobKey);
        }
    }
}
