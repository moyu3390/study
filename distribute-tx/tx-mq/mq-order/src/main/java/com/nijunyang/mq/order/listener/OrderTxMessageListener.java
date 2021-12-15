package com.nijunyang.mq.order.listener;

import com.nijunyang.mq.order.service.OrderService;
import com.nijunyang.tx.common.entity.TxLog;
import com.nijunyang.tx.common.mapper.OrderMapper;
import com.nijunyang.tx.common.mapper.TxLogMapper;
import com.nijunyang.tx.common.model.OrderTxMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * Description:
 * Created by nijunyang on 2021/12/15 1:19
 */
@Slf4j
@Component
@RocketMQTransactionListener
public class OrderTxMessageListener implements RocketMQLocalTransactionListener {

    @Resource
    private OrderService orderService;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private TxLogMapper txLogMapper;

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {

        try{
            log.info("订单微服务执行本地事务");
            OrderTxMessage txMessage = getTxMessage(msg);
            //执行本地事务
            orderService.create(txMessage);
            //提交事务
            log.info("订单微服务提交事务");
            return RocketMQLocalTransactionState.COMMIT;
        }catch (Exception e){
            //异常回滚事务
            log.error("订单微服务回滚事务", e);
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        log.info("订单微服务查询本地事务");
        OrderTxMessage txMessage = getTxMessage(msg);
        TxLog txLog = txLogMapper.selectById(txMessage.getTxNo());
        if(txLog != null){
            return RocketMQLocalTransactionState.COMMIT;
        }
        return RocketMQLocalTransactionState.UNKNOWN;
    }

    private OrderTxMessage getTxMessage(Message<OrderTxMessage> msg){
       return msg.getPayload();
    }
}
