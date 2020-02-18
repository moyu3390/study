package com.nijunyang.eureka.order.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 * Created by nijunyang on 2020/2/15 13:40
 */
@RestController()
@RequestMapping("/order")
public class OrderController {

    @GetMapping("/create")
    public ResponseEntity<String> create() {
        return ResponseEntity.ok().body("create order");
    }
}
