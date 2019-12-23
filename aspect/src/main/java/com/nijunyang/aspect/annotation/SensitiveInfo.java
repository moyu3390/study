package com.nijunyang.aspect.annotation;

import java.lang.annotation.*;

/**
 * Description: 
 * Created by nijunyang on 2019/12/23 16:00
 */
@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface SensitiveInfo {

}
