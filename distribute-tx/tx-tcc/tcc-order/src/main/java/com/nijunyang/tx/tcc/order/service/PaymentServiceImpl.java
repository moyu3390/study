package com.nijunyang.tx.tcc.order.service;

import com.nijunyang.tx.common.dto.AccountDTO;
import com.nijunyang.tx.common.dto.StorageDTO;
import com.nijunyang.tx.common.entity.Order;
import com.nijunyang.tx.common.enums.OrderStatus;
import com.nijunyang.tx.common.mapper.OrderMapper;
import com.nijunyang.tx.tcc.order.feign.AccountClient;
import com.nijunyang.tx.tcc.order.feign.StorageClient;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Description:
 * Created by nijunyang on 2021/12/8 1:01
 */
@Service("paymentService")
public class PaymentServiceImpl implements PaymentService{

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private AccountClient accountClient;
    @Resource
    private StorageClient storageClient;


    /**
     * 订单支付
     * @param order
     */
    @HmilyTCC(confirmMethod = "confirmOrderStatus", cancelMethod = "cancelOrderStatus")
    public void pay(Order order) {
        //修改订单状态为支付中
        order.setOrderStatus(OrderStatus.PAYING);
        orderMapper.updateById(order);
        accountClient.payment(buildAccount(order));
        storageClient.decrease(buildStorage(order));
    }

    public void confirmOrderStatus(Order order) {
        order.setOrderStatus(OrderStatus.PAY_SUCCESS);
        orderMapper.updateById(order);
    }

    public void cancelOrderStatus(Order order) {
        order.setOrderStatus(OrderStatus.PAY_FAIL);
        orderMapper.updateById(order);
    }





    private StorageDTO buildStorage(Order order) {
        StorageDTO storageDTO = new StorageDTO();
        storageDTO.setProductId(order.getProductId());
        storageDTO.setQuantity(order.getQuantity());
        return storageDTO;
    }

    private AccountDTO buildAccount(Order order) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAmount(order.getAmount());
        accountDTO.setUserId(order.getUserId());
        return accountDTO;
    }
}
