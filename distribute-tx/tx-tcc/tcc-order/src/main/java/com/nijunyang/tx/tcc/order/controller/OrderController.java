package com.nijunyang.tx.tcc.order.controller;

import com.nijunyang.tx.tcc.order.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * Description:
 * Created by nijunyang on 2021/12/8 0:51
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    //127.0.0.1:8081/order/orderPay?productId=1&quantity=1&amount=5
    @GetMapping(value = "/orderPay")
    public String orderPay(
            @RequestParam(value = "productId") Integer productId,
            @RequestParam(value = "quantity") Integer quantity,
            @RequestParam(value = "amount") BigDecimal amount) {
        return orderService.orderPay(productId, quantity, amount);
    }
}
