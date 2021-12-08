package com.nijunyang.tx.tcc.order.service;

import com.nijunyang.tx.common.entity.Order;
import com.nijunyang.tx.common.enums.OrderStatus;
import com.nijunyang.tx.common.mapper.OrderMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * Description:
 * Created by nijunyang on 2021/12/8 0:52
 */
@Service
public class OrderServiceImpl implements OrderService{

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private PaymentService paymentService;

    public String orderPay(Integer productId, Integer quantity, BigDecimal amount) {
        //生成订单
        Order order = buildOrder(productId, quantity, amount);
        orderMapper.insert(order);
        //订单支付的才进行库存扣减，账户扣减，支付时处理分布式事务
        paymentService.pay(order);
        return "success";
    }


    private Order buildOrder(Integer productId, Integer quantity, BigDecimal amount) {
        Order order = new Order();
        order.setNumber(UUID.randomUUID().toString().replaceAll("-",""));
        order.setProductId(productId);
        order.setOrderStatus(OrderStatus.NOT_PAY);
        order.setAmount(amount);
        order.setQuantity(quantity);
        order.setUserId("123");
        return order;
    }
}
