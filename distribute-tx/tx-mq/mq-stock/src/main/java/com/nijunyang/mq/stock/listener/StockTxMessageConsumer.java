package com.nijunyang.mq.stock.listener;

import com.nijunyang.mq.stock.service.StockService;
import com.nijunyang.tx.common.model.OrderTxMessage;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Description:
 * Created by nijunyang on 2021/12/15 23:40
 */
@Component
@RocketMQMessageListener(consumerGroup = "tx_stock_group", topic = "topic_order_tx_msg")
public class StockTxMessageConsumer implements RocketMQListener<OrderTxMessage> {

    @Resource
    private StockService stockService;

    @Override
    public void onMessage(OrderTxMessage message) {
        //扣减库存
        stockService.decreaseStock(message);
    }



}
