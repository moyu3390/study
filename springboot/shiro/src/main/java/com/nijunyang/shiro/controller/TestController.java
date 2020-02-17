package com.nijunyang.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 * Created by nijunyang on 2020/2/17 15:53
 */
@Controller
@RequestMapping()
public class TestController {
    @GetMapping("/test")
    public String test(Model model,String name){
        //把数据存入model
        model.addAttribute("name", name);
        //返回test.html
        return "test";
    }
}
