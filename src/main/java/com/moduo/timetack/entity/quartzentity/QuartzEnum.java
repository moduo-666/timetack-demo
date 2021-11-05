package com.moduo.timetack.entity.quartzentity;

import com.moduo.timetack.exception.UnknownQuartzException;

/**
 * @author Wu Zicong
 * @create 2021-11-04 17:57
 */
public enum QuartzEnum {
    /*demo定时任务，每5秒执行一次*/
    DEMO_JOB("demoJob","com.moduo.timetack.service.DemoJob","定时任务demo","0/5 * * * * ?");

    QuartzEnum(String name, String clazz,String chName,String cronExp){
        this.name = name;
        this.clazz = clazz;
        this.chName = chName;
        this.cronExp = cronExp;
    }
    private String name;
    private String clazz;
    private String chName;
    private String cronExp;
    public String getCronExp() {
        return cronExp;
    }

    public void setCronExp(String cronExp) {
        this.cronExp = cronExp;
    }


    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
}
