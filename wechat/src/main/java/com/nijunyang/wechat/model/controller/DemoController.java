package com.nijunyang.wechat.model.controller;

import com.nijunyang.wechat.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * Description:
 * Created by nijunyang on 2020/4/26 17:00
 */
@RestController
public class DemoController {

    @GetMapping("test-demo")
    public ResponseEntity<User> demoTest() {
        int age = new Random().nextInt(20);
        User user = new User();
        user.setName("demoUser" + age);
        user.setAge(age);
        return ResponseEntity.ok(user);
    }
}
