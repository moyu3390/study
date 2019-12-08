package com.nijunyang.clientconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author: create by nijunyang
 * @date:2019/9/4
 */
@RestController
public class Controller {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/test")
    public int test(){
        return restTemplate.getForEntity("http://eurekaclient/nijunyang/port", Integer.class).getBody();
    }
}
