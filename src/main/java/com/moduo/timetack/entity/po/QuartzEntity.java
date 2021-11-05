package com.moduo.timetack.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.quartz.Job;

/**
 * @author Wu Zicong
 * @create 2021-11-04 16:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuartzEntity {

    /**
     * 定时任务名称
     */
    private String quartzName;
    /**
     * 定时任务类名
     */
    private String quartzClass;
    /**
     * 定时任务表达式
     */
    private String cronExp;

}
