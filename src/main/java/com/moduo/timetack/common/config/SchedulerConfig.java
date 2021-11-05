package com.moduo.timetack.common.config;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author Wu Zicong
 * @create 2021-11-05 10:27
 */
//@Configuration
//public class SchedulerConfig {
//    @Bean("scheduler1")
//    public Scheduler scheduler() throws SchedulerException {
//        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
//        return schedulerFactory.getScheduler();
//    }
//}
