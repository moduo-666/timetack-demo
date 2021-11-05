package com.moduo.timetack.autostartup;

import com.moduo.timetack.service.DemoJob;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * ApplicationRunner实现springboot应用启动后做一些初始化操作
 * 把定时任务的启动写在ApplicationRunner的实现类重写的run方法中，实现项目启动后定时任务也启动
 * @author Wu Zicong
 * @create 2021-11-04 13:52
 */
@Component
public class MigrateApplicationRunner implements ApplicationRunner {
    @Autowired
    private Scheduler scheduler;
    /** 默认每5秒执行一次 */
    private static final String DEFAULT_CRON_EXP = "0/5 * * * * ?";
    @Override
    public void run(ApplicationArguments args) throws Exception {
//        QuartzUtil.createSchedulerJob(scheduler, new QuartzEntity());
        // 构建定时任务信息
        Class<?> aClass = Class.forName(DemoJob.class.getName());
        JobDetail jobDetail = JobBuilder.newJob((Class<? extends Job>) aClass).withIdentity("demoJob1").build();
        //设置定时任务执行时间（这里还可以设置执行方式）
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(DEFAULT_CRON_EXP);
        //构建触发器
        Trigger trigger = TriggerBuilder.newTrigger().withSchedule(scheduleBuilder).build();
        //绑定jobDetail和trigger，将它注册进Scheduler当中，返回的是最近一次任务执行的开始时间。
        //当执行scheduleJob方法时，定时任务默认启动
        Date date = scheduler.scheduleJob(jobDetail, trigger);
    }
}
