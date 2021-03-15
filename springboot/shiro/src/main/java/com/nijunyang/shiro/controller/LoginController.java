package com.nijunyang.shiro.controller;

import com.nijunyang.shiro.entity.SystemUser;
import com.nijunyang.shiro.request.LoginInfo;
import com.nijunyang.shiro.service.AuthCacheService;
import com.nijunyang.shiro.service.SystemUserService;
import org.apache.shiro.subject.Subject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author nijunyang
 * @since 2021/3/5
 */
@RestController
@RequestMapping
public class LoginController {

    @Resource
    private SystemUserService systemUserService;
    @Resource
    private AuthCacheService authCacheService;


    @PostMapping("login")
    @PutMapping
    public ResponseEntity<?> doLogin(@RequestBody LoginInfo user, HttpServletRequest request) {

        SystemUser systemUser = systemUserService.selectByLoginName(user.getLoginName());

        Subject cacheSubject = authCacheService.getCacheLogin(systemUser.getId());

        if (cacheSubject != null) {

        }



        return ResponseEntity.ok("");
    }
}
