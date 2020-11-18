package com.nijunyang.eureka.extension.listener.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClient;
import com.netflix.loadbalancer.DynamicServerListLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import com.nijunyang.eureka.extension.util.AopTargetUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Description:
 * Created by nijunyang on 2020/11/18 14:38
 *
 * @author nijunyang
 */
@Slf4j
public class MessageHandler {

    private static final long DEFAULT_DELAY = 2000L;

    @Autowired
    EurekaClient eurekaClient;

    @Autowired
    @Lazy
    ILoadBalancer ribbonLoadBalancer;

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
//        if (now <= cancelTime + evictionIntervalTimerInMs) {
//            Thread.sleep(evictionIntervalTimerInMs - (now - cancelTime));
//        }
//        Thread.sleep(DEFAULT_DELAY);

        log.info("订阅消息： " + substring);
        refreshEurekaAndRibbonCache(0);

    }

    private void refreshEurekaAndRibbonCache(int cnt)
            throws Exception {
        if (cnt >= 3) {
            log.error("服务缓存主动更新失败...");
            return;
        }
        //1.反射调用，强制刷新本地缓存：DiscoveryClient.fetchRegistry()
        Object target = AopTargetUtils.getTarget(eurekaClient);
        if (target instanceof DiscoveryClient) {
            DiscoveryClient discoveryClient = (DiscoveryClient) target;
            Method fetchRegistryMethod = DiscoveryClient.class.getDeclaredMethod("fetchRegistry", boolean.class);
            fetchRegistryMethod.setAccessible(true);
            Object result = fetchRegistryMethod.invoke(discoveryClient, false);
            if (Boolean.TRUE.equals(result)) {
                // 2.更新ribbon本地缓存
                Object target1 = AopTargetUtils.getTarget(ribbonLoadBalancer);
                if (target1 instanceof DynamicServerListLoadBalancer) {
                    DynamicServerListLoadBalancer dynamicServerListLoadBalancer =
                            (DynamicServerListLoadBalancer) target1;
                    dynamicServerListLoadBalancer.updateListOfServers();
                }
            } else {
                refreshEurekaAndRibbonCache(cnt + 1);
            }
        } else {
            refreshEurekaAndRibbonCache(cnt + 1);
        }
    }

//    @Bean
//    @ConditionalOnMissingBean
//    public ILoadBalancer ribbonLoadBalancer(IClientConfig config,
//                                            ServerList<Server> serverList, ServerListFilter<Server> serverListFilter,
//                                            IRule rule, IPing ping, ServerListUpdater serverListUpdater) {
//        if (this.propertiesFactory.isSet(ILoadBalancer.class, name)) {
//            return this.propertiesFactory.get(ILoadBalancer.class, config, name);
//        }
//        return new ZoneAwareLoadBalancer<>(config, rule, ping, serverList,
//                serverListFilter, serverListUpdater);
//    }
}
