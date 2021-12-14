package com.nijunyang.mq.order.service;

import com.nijunyang.tx.common.entity.Order;
import com.nijunyang.tx.common.entity.TxLog;
import com.nijunyang.tx.common.enums.OrderStatus;
import com.nijunyang.tx.common.mapper.OrderMapper;
import com.nijunyang.tx.common.mapper.TxLogMapper;
import com.nijunyang.tx.common.model.OrderTxMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * Description:
 * Created by nijunyang on 2021/12/15 0:54
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService{

    @Resource
    private RocketMQTemplate rocketMQTemplate;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private TxLogMapper txLogMapper;

    @Override
    public String sendOrderMsg(Integer productId, Integer quantity, BigDecimal amount) {
        String txNo = UUID.randomUUID().toString();
        OrderTxMessage txMessage = new OrderTxMessage();
        txMessage.setTxNo(txNo);
        txMessage.setProductId(productId);
        txMessage.setQuantity(quantity);
        txMessage.setAmount(amount);
        Message<OrderTxMessage> message = MessageBuilder.withPayload(txMessage).build();
        rocketMQTemplate.sendMessageInTransaction( "topic_order_tx_msg", message, null);
        return "success";
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String create(OrderTxMessage orderMsg) {
        TxLog txLog = txLogMapper.selectById(orderMsg.getTxNo());
        if (txLog != null) {
            log.info("订单已创建");

        } else {
            Order order = buildOrder(orderMsg.getProductId(), orderMsg.getQuantity(), orderMsg.getAmount());
            orderMapper.insert(order);
            txLog = new TxLog();
            txLog.setTxNo(orderMsg.getTxNo());
            txLogMapper.insert(txLog);
        }
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
