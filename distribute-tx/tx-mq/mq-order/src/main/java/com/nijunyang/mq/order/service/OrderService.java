package com.nijunyang.mq.order.service;

import com.nijunyang.tx.common.model.OrderTxMessage;

import java.math.BigDecimal;

/**
 * Description:
 * Created by nijunyang on 2021/12/15 0:53
 */
public interface OrderService {

    String sendOrderMsg(Integer productId, Integer quantity, BigDecimal amount);

    String create(OrderTxMessage order);
}
