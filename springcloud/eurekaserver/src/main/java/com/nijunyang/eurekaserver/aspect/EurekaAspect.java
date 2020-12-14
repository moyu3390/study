package com.nijunyang.eurekaserver.aspect;

import com.netflix.eureka.cluster.protocol.ReplicationListResponse;
import com.netflix.eureka.registry.PeerAwareInstanceRegistry;
import com.netflix.eureka.resources.PeerReplicationResource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.server.EurekaServerConfigBean;
import org.springframework.context.ApplicationContext;

import javax.ws.rs.core.Response;

/**
 * Description:
 * Created by nijunyang on 2020/11/18 10:04
 *
 * @author nijunyang
 */
@Aspect
//@Component
public class EurekaAspect implements InitializingBean {

//    @Autowired
    PeerReplicationResource peerReplicationResource;
    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    EurekaServerConfigBean eurekaServerConfigBean;

    @Autowired
    PeerAwareInstanceRegistry peerAwareInstanceRegistry;

    public void test() {
//        LoadingCache<Key, Object> readWriteCacheMap;
//        readWriteCacheMap.invalidate();

//        ResponseCacheImpl.invalidate(null,null,null);

//        InstanceRegistry.internalCancel(null,null,false);

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (int i = 0; i < beanDefinitionNames.length; i++) {
            if (beanDefinitionNames[i].contains("com.netflix.eureka.resources")) {
                System.out.println(beanDefinitionNames);
            }
        }
//        PeerReplicationResource bean = applicationContext.getBean(PeerReplicationResource.class);
        int a = 0;
    }


    @Pointcut("execution(public * org.springframework.cloud.netflix.eureka.server.InstanceRegistry.cancel(..))")
    public void pointCut() {

    }

    @Pointcut("execution(public * com.netflix.eureka.resources.InstanceResource.cancelLease(..))")
    public void pointCutCancelLease() {

    }

    @Pointcut("execution(public * com.netflix.eureka.resources.PeerReplicationResource.batchReplication(..))")
    public void pointCutBatchReplication() {

    }


//    ReplicationList replicationList
    @AfterReturning(pointcut = "pointCutBatchReplication()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) throws Throwable {
        if (result instanceof Response) {
            Response response = (Response) result;
            if (Response.Status.OK.getStatusCode() == response.getStatus()) {
                ReplicationListResponse replicationListResponse = (ReplicationListResponse) response.getEntity();


            }


            if (Response.Status.OK.getStatusCode() == response.getStatus()) {
                Thread.sleep(eurekaServerConfigBean.getEvictionIntervalTimerInMs());
                publishEvent();
            }
            if (Response.Status.NOT_FOUND.getStatusCode() == response.getStatus()) {
                publishEvent();
            }

        }
    }


    private void publishEvent() {
        System.out.println(123);
    }
}
