package com.nijunyang.springcache.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

/**
 * Description:
 * Created by nijunyang on 2020/1/14 23:27
 */
@Configuration
public class RedisConfiguration extends CachingConfigurerSupport {

    //使用redis作为缓存，不配置RedisCacheManager 则默认使用自带的jdk缓存
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheManager redisCacheManager = RedisCacheManager.create(connectionFactory);
        return  redisCacheManager;
    }
}
