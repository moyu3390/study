package com.nijunyang.clientt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: create by nijunyang
 * @date:2019/9/3
 */
@RestController
@RequestMapping("/test")
public class ClientController {

    @Value("${server.port}")
    private int port;

    @GetMapping()
    public String test() {

        return "这是client2";
    }
}
