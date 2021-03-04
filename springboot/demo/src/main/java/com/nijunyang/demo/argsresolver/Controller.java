package com.nijunyang.demo.argsresolver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: create by nijunyang
 * @date:2019/10/30
 */
@RestController
public class Controller {

    @GetMapping("/test")
    public String test(User user){
        return user.toString();
    }
}
