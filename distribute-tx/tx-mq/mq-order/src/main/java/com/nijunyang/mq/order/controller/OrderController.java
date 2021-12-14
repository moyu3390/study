package com.nijunyang.mq.order.controller;

import com.nijunyang.mq.order.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * Description:
 * Created by nijunyang on 2021/12/15 0:50
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    //127.0.0.1:8081/order/productId=1&quantity=1&amount=5
    @GetMapping
    public String transfer(@RequestParam(value = "productId") Integer productId,
                           @RequestParam(value = "quantity") Integer quantity,
                           @RequestParam(value = "amount") BigDecimal amount){
        orderService.sendOrderMsg(productId, quantity, amount);
        return "下单成功";
    }
}
