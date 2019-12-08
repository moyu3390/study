package com.nijunyang.clientconsumer.config;

import com.netflix.loadbalancer.IRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author: create by nijunyang
 * @date:2019/9/4
 */
@Configuration
public class Config {
    @Bean
    @LoadBalanced
    public RestTemplate getBean(){
        IRule iRule;
        return new RestTemplate();
    }
}
