package com.nijunyang.consumerfeign.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: create by nijunyang
 * @date:2019/9/15
 * 声明式接口调用
 */
@FeignClient("eurekaclient") //调用某个服务，指向服务名，yml配置文件配置的服务提供者的服务名
public interface ServiceApi {

    @RequestMapping("nijunyang/port") //服务提供者的rest接口
    int getPort();
}
