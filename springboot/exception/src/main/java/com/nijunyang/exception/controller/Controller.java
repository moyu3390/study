package com.nijunyang.exception.controller;

import com.nijunyang.exception.exception.ErrorCodeException;
import com.nijunyang.exception.model.ErrorCode;
import com.nijunyang.exception.model.User;
import com.nijunyang.exception.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 
 * Created by nijunyang on 2019/12/20 9:58
 */
@RestController
@RequestMapping("/error")
public class Controller {

    @Autowired
    UserService userService;

    @GetMapping("/file")
    public ResponseEntity test() {
        //模拟文件不存在
        String path = "a/b/c/d.txt";
        throw new ErrorCodeException(ErrorCode.FILE_DOES_NOT_EXIST_51001, path);
    }


    @GetMapping("/user")
    public User getUser() {
        User user = new User();
        user.setAge("12");
        user.setName("awsda");
        user.setSex("男");
        return userService.setUser(user);
    }


}
