package com.example;


import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 一个最简单的Job
 * @author xukai
 */
public class HelloJob implements Job {
    private final static Logger log = LoggerFactory.getLogger(HelloJob.class);

    public static JobKey helloJobKey = new JobKey("helloJob", "defaultGroup");
    public static TriggerKey helloTriggerKey = new TriggerKey("helloKey", "defaultGroup");

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("HelloJob!");
    }
}
