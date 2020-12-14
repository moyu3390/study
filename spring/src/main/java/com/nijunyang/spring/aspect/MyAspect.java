package com.nijunyang.spring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Description:
 * Created by nijunyang on 2020/2/27 22:41
 */
//@Component
//@Aspect
public class MyAspect {

    @Pointcut("execution(public * com.nijunyang.spring.controller.*.*())")
    public void pointCut() {

    }

    @Before(value = "pointCut()")
    public void before1(JoinPoint joinPoint) {
        System.out.println("before 方法执行");
    }

    @After(value = "pointCut()")
    public void after1(JoinPoint joinPoint) {
        System.out.println("after 方法执行");
    }

    @AfterReturning(pointcut = "pointCut()", returning = "result")
    public void afterReturn1(JoinPoint joinPoint, Object result) {
        System.out.println("afterReturn 方法执行");
    }

    @AfterThrowing(pointcut = "pointCut()", throwing = "throwable")
    public void afterThrow1(JoinPoint joinPoint, Throwable throwable) {
        System.out.println("afterThrow 方法执行");
    }

    @Around(value = "pointCut()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around前面");
        joinPoint.proceed();
        System.out.println("around后面");
    }
}
