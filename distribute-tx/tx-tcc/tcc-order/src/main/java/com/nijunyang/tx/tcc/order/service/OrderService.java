package com.nijunyang.tx.tcc.order.service;

import java.math.BigDecimal;

/**
 * Description:
 * Created by nijunyang on 2021/12/8 23:55
 */
public interface OrderService {

    String orderPay(Integer productId, Integer quantity, BigDecimal amount);

}
