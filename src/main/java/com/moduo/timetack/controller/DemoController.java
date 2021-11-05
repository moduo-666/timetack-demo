package com.moduo.timetack.controller;

import com.moduo.timetack.entity.po.QuartzEntity;
import com.moduo.timetack.entity.po.RespBean;
import com.moduo.timetack.entity.quartzentity.QuartzEnum;
import com.moduo.timetack.exception.UnknownQuartzException;
import com.moduo.timetack.utils.QuartzUtil;
import lombok.AllArgsConstructor;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Wu Zicong
 * @create 2021-11-05 9:52
 */
@RestController
public class DemoController {
    @Autowired
//    @Qualifier("scheduler1")
    private Scheduler scheduler;
    @RequestMapping("/createSchedulerJob")
    public RespBean createSchedulerJob(String name){
        for(QuartzEnum quartzEnum:QuartzEnum.values()){
            if(quartzEnum.getName().equals(name)){
                QuartzEntity quartzEntity = new QuartzEntity(quartzEnum.getName(), quartzEnum.getClazz(), quartzEnum.getCronExp());
                try {
                    System.out.println(scheduler.getClass().getName());
                    QuartzUtil.createSchedulerJob(scheduler,quartzEntity);
                } catch (Exception e) {
                    throw new RuntimeException();
                }
                return RespBean.info(200,"创建成功");
            }
        }
        return RespBean.info(400,"创建失败，没有此定时任务");
    }

}
