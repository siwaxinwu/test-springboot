package com.roy.annotation;

import java.lang.annotation.*;

/**
 * @description：
 * @author： dingyawu
 * @date： created in 16:21 2021-01-07
 * @history:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ReqLog {
}
