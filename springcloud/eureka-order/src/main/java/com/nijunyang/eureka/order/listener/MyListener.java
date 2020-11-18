package com.nijunyang.eureka.order.listener;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Applications;
import com.netflix.discovery.shared.transport.EurekaHttpClient;
import com.nijunyang.eureka.order.listener.redis.MessageHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

/**
 * Description:
 * Created by nijunyang on 2020/11/16 14:56
 *
 * @author nijunyang
 */
@Component
@Slf4j
public class MyListener implements ApplicationListener<ContextClosedEvent>, InitializingBean {

    public static final String TOPIC = "refresh";

    @Value("${spring.application.name}")
    String appName;

//    @Autowired
    private EurekaHttpClient eurekaHttpClient;

//    private AbstractJerseyEurekaHttpClient eurekaHttpClient;


//    private CloudEurekaClient cloudEurekaClient;

    @Autowired
    EurekaClient eurekaClient;

    @Autowired
    ApplicationInfoManager applicationInfoManager;

    String id;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisMessageListenerContainer container;

    @Autowired
    MessageListenerAdapter listenerAdapter;


    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
//        container.removeMessageListener(listenerAdapter, new PatternTopic(TOPIC));
//        container.removeMessageListener(listenerAdapter);
        MessageHolder messageHolder =
                new MessageHolder(appName, applicationInfoManager.getInfo().getId(), System.currentTimeMillis());
        redisTemplate.convertAndSend(TOPIC, messageHolder);
        log.info("容器销毁123");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println(123);
        Applications applications = eurekaClient.getApplications();
        System.out.println(applications);
        id = applicationInfoManager.getInfo().getId();


//        EurekaHttpClientFactory newRegistrationClientFactory = null;
//        EurekaHttpClient newRegistrationClient = null;
//        try {
//            newRegistrationClientFactory = EurekaHttpClients.registrationClientFactory(
//                    eurekaTransport.bootstrapResolver,
//                    eurekaTransport.transportClientFactory,
//                    transportConfig
//            );
//            newRegistrationClient = newRegistrationClientFactory.newClient();
//            newRegistrationClient.cancel(appName, id);
//        } catch (Exception e) {
//            logger.warn("Transport initialization failure", e);
//        }

//        eurekaHttpClient = applicationContext.getBean(AbstractJerseyEurekaHttpClient.class);
//        cloudEurekaClient = applicationContext.getBean(CloudEurekaClient.class);


    }

    public boolean fetchRegistry() {
//        cloudEurekaClient.refreshRegistry();
//        cloudEurekaClient.fetchRegistry();
        return false;
    }

}
