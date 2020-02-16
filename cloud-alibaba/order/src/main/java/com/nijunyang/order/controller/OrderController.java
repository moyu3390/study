package com.nijunyang.order.controller;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.nijunyang.order.feign.StockFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    NacosDiscoveryProperties nacosDiscoveryProperties;

    @Autowired
    private StockFeignClient stockFeignClient;

    @GetMapping("/create")
    public ResponseEntity<String> create() {
        String result = stockFeignClient.deductStock();
        return new ResponseEntity<String>("order success + " + result, HttpStatus.OK);
    }
}
