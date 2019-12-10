package com.nijunyang.aspect.aspect;

import com.nijunyang.aspect.annotation.AspectDef;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description: 
 * Created by nijunyang on 2019/12/9 17:46
 */
@Component
@Aspect
public class AspectTest {

    /**
     * 切点表达式中，..两个点表明多个，*代表一个，
     * 下面表达式代表切入com.nijunyang.aspect.controller包下的所有类的所有方法，方法参数不限，返回类型不限。
     * 其中访问修饰符可以不写，不能用*代替，
     * 第一个*代表返回类型不限，
     * 第二个*表示所有类，
     * 第三个*表示所有方法，
     * ..两个点表示方法里的参数不限。
     * 然后用@Pointcut切点注解，想在一个空方法上面，一会儿在Advice通知中，直接调用这个空方法就行了，也可以把切点表达式卸载Advice通知中的，单独定义出来主要是为了好管理。
     */
    @Pointcut("execution(public * com.nijunyang.aspect.controller.*.*(..))")
    public void pointCut() {

    }

    /**
    @Before  在切点方法之前执行
    @After  在切点方法之后执行
    @AfterReturning 切点方法返回后执行
    @AfterThrowing 切点方法抛异常执行
    @Around 属于环绕增强，能控制切点执行前，执行后，，用这个注解后，程序抛异常，会影响@AfterThrowing这个注解
     */

    @AfterThrowing(pointcut = "pointCut()", throwing="exception")
    public void doAfterThrowingAdvice(JoinPoint joinPoint,Throwable exception){
        System.out.println(exception.getMessage());
    }

    @Before(value = "execution(* com.nijunyang.aspect.controller.*.testBefore(..))")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        System.out.println(request.getContextPath());
    }

    @After(value = "execution(* com.nijunyang.aspect.controller.*.testAfter(..))")
    public void doAfter(JoinPoint joinPoint){
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        System.out.println(request.getContextPath());
    }

    @AfterReturning(pointcut = "execution(* com.nijunyang.aspect.controller.*.testAfterReturning(..))", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result){
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        System.out.println(request.getContextPath());
    }

    @Before(value = "@annotation(aspectDef)")
    public void doBefore1(JoinPoint joinPoint, AspectDef aspectDef){
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        System.out.println(request.getContextPath());
    }

    @Before(value = "@annotation(com.nijunyang.aspect.annotation.AspectDef)")
    public void doBefore2(JoinPoint joinPoint){
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        System.out.println(request.getContextPath());
    }




}
