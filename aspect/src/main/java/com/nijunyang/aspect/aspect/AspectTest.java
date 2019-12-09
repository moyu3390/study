package com.nijunyang.aspect.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Description: 
 * Created by nijunyang on 2019/12/9 17:46
 */
@Component
@Aspect
public class AspectTest {


    @Pointcut("")
    public void pointcut() {

    }

}
