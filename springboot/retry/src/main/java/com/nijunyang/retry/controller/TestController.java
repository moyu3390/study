package com.nijunyang.retry.controller;

import com.nijunyang.retry.model.User;
import com.nijunyang.retry.service.TestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;

/**
 * Description:
 * Created by nijunyang on 2020/9/9 14:57
 */
@RestController
@RequestMapping
public class TestController {

    @Resource
    TestService testService;

    @GetMapping("/retry")
    public ResponseEntity<Void> test() throws NoSuchAlgorithmException {

        User user = new User();
        user.setAge(12);
        user.setName("zhangsan");
        testService.test(user);
        User user2 = new User();
        user2.setAge(12);
        user2.setName("zhangsan2");
        testService.test22(user2);
        return ResponseEntity.ok().build();
    }
}
