package com.nijunyang.mail.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Description:
 * Created by nijunyang on 2020/3/23 15:03
 */
@RestController
@RequestMapping("/session")
public class SessionController {

    @GetMapping("/{userName}")
    public boolean set(HttpSession httpSession, @PathVariable String userName) {
        httpSession.setAttribute("userId", userName);
        return true;
    }

    @GetMapping
    public Object get(HttpSession httpSession, String id) {
        return httpSession.getAttribute(id);
    }
}
