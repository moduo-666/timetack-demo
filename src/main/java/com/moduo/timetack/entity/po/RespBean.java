package com.moduo.timetack.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Wu Zicong
 * @create 2021-11-05 9:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {
    private Integer code;
    private String msg;
    private Object obj;
    public static RespBean success(Integer code, String msg , Object obj){
        return new RespBean(code,msg,obj);
    }
    public static RespBean info(Integer code, String msg , Object obj){
        return new RespBean(code,msg,obj);
    }
    public static RespBean info(Integer code){
        return new RespBean(code,null,null);
    }
    public static RespBean info(Integer code, String msg){
        return new RespBean(code,msg,null);
    }

}
