package com.nijunyang.order.controller;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.nijunyang.order.feign.StockClient;
import com.nijunyang.order.feign.StockFeignClient;
import com.nijunyang.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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
@RefreshScope
public class OrderController {

    @Autowired
    NacosDiscoveryProperties nacosDiscoveryProperties;

    @Autowired
    private StockFeignClient stockFeignClient;

    @Autowired
    private OrderService orderService;

    @Value("${info.test:}")
    private String info;

    @Value("${info.database:}")
    private String database;

    @Value("${info.redis:}")
    private String redis;

    @GetMapping("/create")
    public ResponseEntity<String> create(String userId, Integer commodityId, Integer quantity) {
        orderService.create(userId, commodityId, quantity);
        return new ResponseEntity<>("order success + ", HttpStatus.OK);
    }

//    @GetMapping("/create")
//    public ResponseEntity<String> create() {
//        String result = stockFeignClient.deductStock();
//        return new ResponseEntity<String>("order success + " + result, HttpStatus.OK);
//    }

    @GetMapping("/properties")
    public ResponseEntity<String> properties() {

        return ResponseEntity.ok(info + database + redis);
    }
}
