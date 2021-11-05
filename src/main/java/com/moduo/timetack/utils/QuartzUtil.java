package com.moduo.timetack.utils;

import com.moduo.timetack.entity.po.QuartzEntity;
import com.moduo.timetack.entity.quartzentity.QuartzEnum;
import com.moduo.timetack.exception.UnknownQuartzException;
import org.omg.CORBA.portable.UnknownException;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.*;

/**
 *  定时任务工具类
 * @author Wu Zicong
 * @create 2021-11-04 16:04
 */
public class QuartzUtil {
    /**
     * 创建定时任务(创建后默认启动)
     * @param scheduler  调度器
     * @param quartzEntities  定时任务实体集合
     */
    public static void createSchedulerJob(Scheduler scheduler, QuartzEntity...quartzEntities) throws ClassNotFoundException, SchedulerException, InterruptedException {
        for(QuartzEntity quartzEntity : quartzEntities) {
            // 构建定时任务信息
            Class<?> classz = Class.forName(quartzEntity.getQuartzClass());
            String quartzName = quartzEntity.getQuartzName();
            JobDetail jobDetail = JobBuilder.newJob((Class<? extends Job>) classz).withIdentity(quartzName).build();
            //设置定时任务执行间隔时间（这里还可以设置执行方式）
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(quartzEntity.getCronExp());
            //构建触发器
            Trigger trigger = TriggerBuilder.newTrigger().withSchedule(scheduleBuilder).build();
            //绑定jobDetail和trigger，将它注册进Scheduler当中，返回的是最近一次任务执行的开始时间。
            //当执行scheduleJob方法时，定时任务默认启动
            Date date = scheduler.scheduleJob(jobDetail, trigger);
        }
    }
    public static void updateSchedulerJob(Scheduler scheduler, QuartzEntity...quartzEntities) throws SchedulerException {
        for(QuartzEntity quartzEntity : quartzEntities){
            if(hasTask(scheduler,quartzEntity)){
                //获取对应任务的触发器
                TriggerKey triggerKey = TriggerKey.triggerKey(quartzEntity.getQuartzName());
                //设置定时任务执行方式，withMisfireHandlingInstructionDoNothing()指定错过的执行不会触发
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(quartzEntity.getCronExp()).withMisfireHandlingInstructionDoNothing();
                //重新构建任务的触发器trigger
                CronTrigger trigger = (CronTrigger)scheduler.getTrigger(triggerKey);
                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
                //重置定时任务
                scheduler.rescheduleJob(triggerKey,trigger);
            }else{
                throw new UnknownQuartzException(quartzEntity.getQuartzName());
            }
        }
    }
    /**
     * 获取正在执行的定时任务列表
     * @return 定时任务列表
     */
    public static Map<String,String> getSchedulerJobList(Scheduler scheduler) throws SchedulerException {
        //        JobKey jobKey = new JobKey("demoJob1");
        //获取当前已启动的任务名称
        Set<JobKey> jobKeys = scheduler.getJobKeys(GroupMatcher.anyGroup());
        Map<String, String> map = new HashMap<>();
        for(JobKey key : jobKeys){
            map.put(key.getName(),getQuartzCHName(key.getName()));
        }
        return map;
    }
    private static String getQuartzCHName(String name){
        QuartzEnum quartzEnum = QuartzEnum.valueOf(name);
        return quartzEnum.getChName();
    }

    /**
     * 立即执行一次定时任务
     * @param scheduler
     * @param quartzEntities
     * @throws SchedulerException
     */
    public static void runOnce(Scheduler scheduler,QuartzEntity...quartzEntities) throws SchedulerException {
        for(QuartzEntity quartzEntity: quartzEntities){
            if(hasTask(scheduler,quartzEntity)){
                JobKey key = JobKey.jobKey(quartzEntity.getQuartzName());
                scheduler.triggerJob(key);
            }else{
                throw new UnknownQuartzException(quartzEntity.getQuartzName());
            }
        }
    }
    /**
     * 暂停定时任务
     * @param scheduler
     * @param quartzEntities
     * @throws SchedulerException
     */
    public static void standBySchedulerJobList(Scheduler scheduler, QuartzEntity...quartzEntities) throws SchedulerException {
        //定时任务暂停
        for(QuartzEntity quartzEntity: quartzEntities){
            if(hasTask(scheduler,quartzEntity)){
                JobKey key = JobKey.jobKey(quartzEntity.getQuartzName());
                scheduler.pauseJob(key);
            }else{
                throw new UnknownQuartzException(quartzEntity.getQuartzName());
            }
        }
    }

    /**
     * 恢复暂停的定时任务
     * @param scheduler
     * @param quartzEntities
     */
    public static void startSchedulerJob(Scheduler scheduler, QuartzEntity...quartzEntities) throws SchedulerException {
        for(QuartzEntity quartzEntity : quartzEntities){
            if(hasTask(scheduler,quartzEntity)){
                JobKey key = JobKey.jobKey(quartzEntity.getQuartzName());
                scheduler.resumeJob(key);
            }else{
                throw new UnknownQuartzException(quartzEntity.getQuartzName());
            }
        }
    }

    /**
     * 结束定时任务（无法重新开始）
     * @param scheduler
     * @param quartzEntities
     */
    public static void shutdownSchedulerJob(Scheduler scheduler, QuartzEntity...quartzEntities) throws SchedulerException {
        for(QuartzEntity quartzEntity : quartzEntities){
            if(hasTask(scheduler,quartzEntity)){
                scheduler.shutdown();
            }else {
                throw new UnknownQuartzException(quartzEntity.getQuartzName());
            }
        }
    }

    /**
     * 检查定时任务是否已存在
     * @param scheduler
     * @param quartzEntity
     * @return
     */
    public static Boolean hasTask(Scheduler scheduler, QuartzEntity quartzEntity) throws SchedulerException {
        JobKey key = JobKey.jobKey(quartzEntity.getQuartzName());
        return scheduler.checkExists(key);
    }

}
