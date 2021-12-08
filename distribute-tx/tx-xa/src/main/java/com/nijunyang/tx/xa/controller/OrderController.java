package com.nijunyang.tx.xa.controller;

import com.nijunyang.tx.common.entity.Order;
import com.nijunyang.tx.common.enums.OrderStatus;
import com.nijunyang.tx.xa.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * Description:
 * Created by nijunyang on 2021/12/3 0:20
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @Resource
    private OrderService orderService;

    //127.0.0.1:8080/order?userId=123&productId=1&quantity=2&amount=10
    @GetMapping
    public Object create(Order order) {
        order.setOrderStatus(OrderStatus.PAY_SUCCESS);
        order.setNumber(UUID.randomUUID().toString());
        orderService.create(order);
        return 1;
    }

}
