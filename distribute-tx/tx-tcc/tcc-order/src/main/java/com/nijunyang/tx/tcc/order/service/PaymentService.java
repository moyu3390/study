package com.nijunyang.tx.tcc.order.service;

import com.nijunyang.tx.common.entity.Order;

/**
 * Description:
 * Created by nijunyang on 2021/12/8 23:53
 */
public interface PaymentService {

    void pay(Order order);

    void confirmOrderStatus(Order order);

    void cancelOrderStatus(Order order);
}
