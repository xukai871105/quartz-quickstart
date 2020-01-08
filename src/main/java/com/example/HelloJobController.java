package com.example;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xukai
 */
@RestController
public class HelloJobController {

    private final static Logger log = LoggerFactory.getLogger(HelloJobController.class);

    @Autowired
    private HelloJobService helloJobService;

    @GetMapping("/start")
    public String startHelloJob() throws SchedulerException {
        helloJobService.schedulerHelloJob();
        log.info("start HelloJob");
        final String pattern = "yyyy-MM-dd HH:mm:ss";
        return String.format("Start helloJob Success %s", new SimpleDateFormat(pattern).format(new Date()));
    }

    @GetMapping("/delete")
    public String deleteHelloJob() throws SchedulerException {
        helloJobService.deleteHelloJob();

        final String pattern = "yyyy-MM-dd HH:mm:ss";
        return String.format("Delete helloJob Success %s", new SimpleDateFormat(pattern).format(new Date()));
    }
}
