package com.nijunyang.aspect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy=true)
public class AspectApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(AspectApplication.class, args);
//        System.out.println(run.getBean(SensitiveAspect.class));
    }

}
