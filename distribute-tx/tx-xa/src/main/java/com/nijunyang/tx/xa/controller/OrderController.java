package com.nijunyang.tx.xa.controller;

import com.nijunyang.tx.common.entity.Order;
import com.nijunyang.tx.xa.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * Description:
 * Created by nijunyang on 2021/12/3 0:20
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @Resource
    private OrderService orderService;

    //127.0.0.1:8080/order?userId=1&commodityId=1&quantity=2&price=10
    @GetMapping
    public Object create(Order order) {
        orderService.create(order);
        return 1;
    }

}
