package com.nijunyang.eureka.extension.util;

import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.support.AopUtils;

import java.lang.reflect.Field;

/**
 * Description:
 * Created by nijunyang on 2020/11/18 18:26
 *
 * @author nijunyang
 */
public class AopTargetUtils {

    /**
     * 获取原生对象
     *
     * @param proxy 代理对象
     * @return 原生对象
     * @throws Exception Exception
     */
    public static Object getTarget(Object proxy) throws Exception {
        if (proxy != null) {
            if (!AopUtils.isAopProxy(proxy)) {
                return proxy;
            }
            if (AopUtils.isJdkDynamicProxy(proxy)) {
                return getTarget(getJdkDynamicProxyTargetObject(proxy));
            } else {
                //cglib
                return getTarget(getCglibProxyTargetObject(proxy));
            }
        }
        return null;
    }


    /**
     * cglib
     * @param proxy 代理对象
     * @return 原生对象
     * @throws Exception Exception
     */
    private static Object getCglibProxyTargetObject(Object proxy) throws Exception {
        Field h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
        h.setAccessible(true);
        Object dynamicAdvisedInterceptor = h.get(proxy);
        Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
        advised.setAccessible(true);
        Object target = ((AdvisedSupport) advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();
        return target;
    }

    /**
     * JDK动态代理
     * @param proxy 代理对象
     * @return 原生对象
     * @throws Exception  Exception
     */
    private static Object getJdkDynamicProxyTargetObject(Object proxy) throws Exception {
        Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
        h.setAccessible(true);
        AopProxy aopProxy = (AopProxy) h.get(proxy);
        Field advised = aopProxy.getClass().getDeclaredField("advised");
        advised.setAccessible(true);
        Object target = ((AdvisedSupport) advised.get(aopProxy)).getTargetSource().getTarget();
        return target;
    }
}
