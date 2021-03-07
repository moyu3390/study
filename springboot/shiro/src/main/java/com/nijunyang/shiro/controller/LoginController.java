package com.nijunyang.shiro.controller;

import com.nijunyang.shiro.request.LoginInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author nijunyang
 * @since 2021/3/5
 */
@RestController
@RequestMapping
public class LoginController {


    @PostMapping("login")
    @PutMapping
    public ResponseEntity<?> doLogin(@RequestBody LoginInfo user, HttpServletRequest request) {


        return ResponseEntity.ok("");
    }
}
