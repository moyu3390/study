package com.nijunyang.aspect.annotation;

import java.lang.annotation.*;

/**
 * Description: 
 * Created by nijunyang on 2019/12/23 16:03
 */
@Documented
@Target({ ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SensitiveMethod {
    /**
     * 加密入参
     */
    boolean encrypt() default true;

    /**
     * 解密返回
     */
    boolean decrypt() default true;
}
