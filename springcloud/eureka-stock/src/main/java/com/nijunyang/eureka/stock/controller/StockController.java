package com.nijunyang.eureka.stock.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 * Created by nijunyang on 2020/2/15 13:36
 */
@RestController
@RequestMapping("/stock")
public class StockController {

    @GetMapping("/deduct")
    public ResponseEntity<String> deductStock() {
        return ResponseEntity.ok().body("stock deduct success");
    }
}
