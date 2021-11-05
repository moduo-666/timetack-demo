package com.moduo.timetack.service;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * 定时任务类需要是job类的具体实现 QuartzJobBean是job的抽象类。
 * @author Wu Zicong
 * @create 2021-11-04 14:09
 */
public class DemoJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        System.out.println(new Date());
    }
}
