package com.nijunyang.order.service;

import com.nijunyang.mysqlcommon.entity.Order;
import com.nijunyang.mysqlcommon.mapper.OrderMapper;
import com.nijunyang.order.feign.AccountClient;
import com.nijunyang.order.feign.StockClient;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Description:
 * Created by nijunyang on 2021/10/26 0:04
 */
@Service
public class OrderService {

    private static final BigDecimal UNIT_PRICE = new BigDecimal("20");

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private StockClient stockClient;
    @Autowired
    private AccountClient accountClient;

    @GlobalTransactional
    public void create(String userId, Integer commodityId, Integer quantity) {
        Order order = new Order();
        order.setUserId(userId);
        order.setCommodityId(commodityId);
        order.setQuantity(quantity);
        order.setPrice(UNIT_PRICE.multiply(new BigDecimal(quantity)));
        order.setStatus(1);
        //创建订单
        orderMapper.insert(order);
        //扣减库存
        stockClient.deductStock(quantity, commodityId);
        //扣减余额
        accountClient.deductBalance(userId, order.getPrice());
    }
}
