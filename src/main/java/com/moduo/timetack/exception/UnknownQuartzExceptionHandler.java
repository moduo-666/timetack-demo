package com.moduo.timetack.exception;

import com.moduo.timetack.entity.po.RespBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.xml.transform.Result;

/**
 * @author Wu Zicong
 * @create 2021-11-05 9:08
 */
@RestControllerAdvice
public class UnknownQuartzExceptionHandler {
    @ExceptionHandler
    public RespBean handler(Exception e){
        if(e instanceof UnknownQuartzException){
            UnknownQuartzException unknownQuartzException = (UnknownQuartzException) e;
            return RespBean.info(500,
                                 "未知定时任务异常",
                                 "未知定时任务:"+ unknownQuartzException.getQuartzName());
        }
        return RespBean.info(500,"服务器异常",e);
    }
}
