package com.nijunyang.order.config;

import feign.Contract;
import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

/**
 * Description:
 * Created by nijunyang on 2020/12/15 15:18
 *
 * @author nijunyang
 */
public class FeignConfig {

    @Bean
    public Logger.Level level() {
        //feign日志级别
        return Logger.Level.FULL;
//        return Logger.Level.HEADERS;
//        return Logger.Level.BASIC;
//        return Logger.Level.NONE; //默认
    }

    //不配使用springMVC注解，配置了使用自己的注解
//    @Bean
//    public Contract feignContract() {
//        return new Contract.Default();
//    }


    /**
     * 自定义拦截器，比如透传消息头信息等
     * @return
     */
    @Bean
    public RequestInterceptor requestInterceptor() {
        return new MyRequestInterceptor();
    }
}
