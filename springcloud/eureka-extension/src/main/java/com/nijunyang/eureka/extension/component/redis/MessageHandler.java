package com.nijunyang.eureka.extension.component.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClient;
import com.netflix.loadbalancer.DynamicServerListLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import com.nijunyang.eureka.extension.constants.Constant;
import com.nijunyang.eureka.extension.util.AopTargetUtils;
import feign.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.ribbon.CachingSpringLoadBalancerFactory;
import org.springframework.cloud.openfeign.ribbon.FeignLoadBalancer;
import org.springframework.cloud.openfeign.ribbon.LoadBalancerFeignClient;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Created by nijunyang on 2020/11/18 14:38
 *
 * @author nijunyang
 */
@Slf4j
@RefreshScope
public class MessageHandler {

    private static final long DEFAULT_DELAY = 1000L;

    @Autowired(required = false)
    EurekaClient eurekaClient;

    @Autowired(required = false)
    Client feignClient;

    @Autowired
    ObjectMapper objectMapper;

    @Value("${eureka.server.evictionIntervalTimerInMs}")
    private long evictionIntervalTimerInMs;

    public void handleMessage(Object object)
            throws Exception {

        String s = object.toString();
        String substring = s.substring(s.indexOf(",") + 1, s.length() - 1);
        MessageHolder messageHolder = objectMapper.readValue(substring, MessageHolder.class) ;
        long cancelTime = messageHolder.getSendTime();
        long now = System.currentTimeMillis();
        if (now <= cancelTime + evictionIntervalTimerInMs) {
            Thread.sleep(evictionIntervalTimerInMs - (now - cancelTime));
        }
        Thread.sleep(DEFAULT_DELAY);

        log.info("订阅消息： " + substring);
        refreshEurekaAndRibbonCache(0);

    }

    private void refreshEurekaAndRibbonCache(int cnt) throws Exception {
        if (cnt >= Constant.RETRY_TIME) {
            log.error("服务缓存主动更新失败...");
            return;
        }
        //1.反射调用，强制刷新本地缓存：DiscoveryClient.fetchRegistry()
        Object eurekaClientNative = AopTargetUtils.getTarget(eurekaClient);
        if (eurekaClientNative instanceof DiscoveryClient) {
            DiscoveryClient discoveryClient = (DiscoveryClient) eurekaClientNative;
            Method fetchRegistryMethod = DiscoveryClient.class.getDeclaredMethod("fetchRegistry", boolean.class);
            fetchRegistryMethod.setAccessible(true);
            Object result = fetchRegistryMethod.invoke(discoveryClient, false);
            if (Boolean.TRUE.equals(result)) {
                // 2.更新ribbon本地缓存
                Object feignClientNative = AopTargetUtils.getTarget(feignClient);
                if (feignClientNative instanceof LoadBalancerFeignClient) {
                    LoadBalancerFeignClient loadBalancerFeignClient = (LoadBalancerFeignClient) feignClient;
                    //获取CachingSpringLoadBalancerFactory lbClientFactory;
                    Field lbClientFactoryField = LoadBalancerFeignClient.class.getDeclaredField("lbClientFactory");
                    lbClientFactoryField.setAccessible(true);
                    CachingSpringLoadBalancerFactory balancerFactory =
                            (CachingSpringLoadBalancerFactory) lbClientFactoryField.get(loadBalancerFeignClient);

                    //获取Map<String, FeignLoadBalancer> cache = new ConcurrentReferenceHashMap<>()缓存
                    Field cacheField = balancerFactory.getClass().getDeclaredField("cache");
                    cacheField.setAccessible(true);
                    Map<String, FeignLoadBalancer> feignLoadBalancerMap = (Map) cacheField.get(balancerFactory);
                    //刷新每个服务的负载均衡器
                    List<String> serverNameList = new ArrayList<>(feignLoadBalancerMap.size());
                    for (Map.Entry<String, FeignLoadBalancer> entry : feignLoadBalancerMap.entrySet()) {
                        ILoadBalancer loadBalancer = entry.getValue().getLoadBalancer();
                        if (loadBalancer instanceof DynamicServerListLoadBalancer) {
                            ((DynamicServerListLoadBalancer)loadBalancer).updateListOfServers();
                            serverNameList.add(entry.getKey());
                        }
                    }
                    log.info("{}服务本地服务列表缓存更新成功", serverNameList);
                }
            } else {
                Thread.sleep(1000);
                refreshEurekaAndRibbonCache(cnt + 1);
            }
        }
    }
}
