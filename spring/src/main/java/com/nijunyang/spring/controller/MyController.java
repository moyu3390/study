package com.nijunyang.spring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 * Created by nijunyang on 2020/2/22 16:31
 */
@RestController
@RequestMapping("/my")
public class MyController {

    @GetMapping
    public ResponseEntity<String> methodA(){
        try{
            System.out.println("methodA");
            return ResponseEntity.ok("methodA");
        }
        finally {
            System.out.println("finally");
        }
    }

    public ResponseEntity<String> methodB(){
        throw new RuntimeException();
    }
}
