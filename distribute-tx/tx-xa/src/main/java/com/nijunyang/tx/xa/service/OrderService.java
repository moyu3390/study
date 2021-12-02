package com.nijunyang.tx.xa.service;

import com.nijunyang.tx.common.entity.Order;
import com.nijunyang.tx.xa.mapper1.OrderMapper;
import com.nijunyang.tx.xa.mapper2.StorageMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Description:
 * Created by nijunyang on 2021/12/3 0:21
 */
@Service
public class OrderService {

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private StorageMapper storageMapper;

    @Transactional(rollbackFor = Exception.class)
    public void create(Order order) {
        orderMapper.insert(order);
        storageMapper.reduce(order.getCommodityId(), order.getQuantity());
//        int a = 1/0;
    }
}
