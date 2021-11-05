package com.moduo.timetack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 笔记
 *  Quartz的三大核心概念：
 *      1. 调度器  scheduler
 *          a. 创建调度器的第一种方式
 *           SchedulerFactory schedulerFactory = new StdSchedulerFactory();
             Scheduler scheduler = schedulerFactory.getScheduler();
            b. 创建调度器的第二种方式
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
 *      2. 任务    job
 *      3. 触发器  trigger
 */
@SpringBootApplication
public class TimetackApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimetackApplication.class, args);
    }

}
