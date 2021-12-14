package com.nijunyang.mq.order.listener;

import com.nijunyang.tx.common.model.OrderTxMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;



/**
 * Description:
 * Created by nijunyang on 2021/12/15 1:19
 */
@Slf4j
@Component
@RocketMQTransactionListener
public class OrderTxMessageListener implements RocketMQLocalTransactionListener {

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        return null;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        return null;
    }

    private OrderTxMessage getTxMessage(Message<OrderTxMessage> msg){
       return msg.getPayload();
    }
}
