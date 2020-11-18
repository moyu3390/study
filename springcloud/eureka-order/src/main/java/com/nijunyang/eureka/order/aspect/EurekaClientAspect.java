package com.nijunyang.eureka.order.aspect;

import com.netflix.eureka.cluster.protocol.ReplicationListResponse;
import com.netflix.eureka.resources.PeerReplicationResource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


/**
 * Description:
 * Created by nijunyang on 2020/11/18 10:04
 *
 * @author nijunyang
 */
@Aspect
@Component
@Slf4j
public class EurekaClientAspect {


    @Autowired
    ApplicationContext applicationContext;



    @Pointcut("execution(public * com.netflix.discovery.DiscoveryClient.shutdown(..))")
    public void pointCut() {

    }


    @After(value = "pointCut()")
    public void after() throws Throwable {
        log.info("销毁切面执行");
    }

    @Before(value = "pointCut()")
    public void before() throws Throwable {
        log.info("销毁切面执行 before");
    }

}
