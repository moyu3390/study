package com.nijunyang.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 
 * Created by nijunyang on 2019/12/16 17:04
 */
@RestController
@RequestMapping("/bank")
public class Controller {
    /**
     * 模拟存款总额为 10
     */
    private static Integer money = 10;

    /**
     * 获取当前存款
     * @return
     */
    @GetMapping("/money")
    public int getMoney(){
        return money;
    }

    /**
     * 模拟消费一元
     * @return
     */
    @PostMapping("/money")
    public int putMoney(){
        money -= 1;
        return money;
    }

}
