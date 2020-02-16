package com.nijunyang.stock.controller;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    NacosDiscoveryProperties nacosDiscoveryProperties;

    @PostMapping("/deduct")
    public ResponseEntity<String> deductStock() {
        return new ResponseEntity<>("stock success", HttpStatus.OK);
    }
}
