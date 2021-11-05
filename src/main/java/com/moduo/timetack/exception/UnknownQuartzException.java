package com.moduo.timetack.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Wu Zicong
 * @create 2021-11-05 9:07
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UnknownQuartzException extends RuntimeException {
    private String quartzName;
}
