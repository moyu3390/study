package com.nijunyang.eureka.extension.component;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.discovery.EurekaClient;
import com.nijunyang.eureka.extension.constants.Constant;
import com.nijunyang.eureka.extension.component.redis.MessageHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * Description:
 * Created by nijunyang on 2020/11/16 14:56
 *
 * @author nijunyang
 */
@Slf4j
@RefreshScope
public class ExtensionComponent implements DisposableBean {

    @Value("${spring.application.name}")
    String appName;

    @Autowired
    ApplicationInfoManager applicationInfoManager;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisMessageListenerContainer container;

    @Autowired
    MessageListenerAdapter listenerAdapter;

    @Override
    public void destroy() {
        container.removeMessageListener(listenerAdapter, new PatternTopic(Constant.REDIS_TOPIC));
        MessageHolder messageHolder =
                new MessageHolder(appName, applicationInfoManager.getInfo().getId(), System.currentTimeMillis());
        redisTemplate.convertAndSend(Constant.REDIS_TOPIC, messageHolder);
        log.info("{}服务下线...", appName);
    }

}
