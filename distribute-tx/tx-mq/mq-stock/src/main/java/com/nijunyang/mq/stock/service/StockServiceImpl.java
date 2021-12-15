package com.nijunyang.mq.stock.service;

import com.nijunyang.tx.common.dto.StorageDTO;
import com.nijunyang.tx.common.entity.Order;
import com.nijunyang.tx.common.entity.TxLog;
import com.nijunyang.tx.common.mapper.StorageMapper;
import com.nijunyang.tx.common.mapper.TxLogMapper;
import com.nijunyang.tx.common.model.OrderTxMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Description:
 * Created by nijunyang on 2021/12/15 23:42
 */
@Slf4j
@Service
public class StockServiceImpl implements StockService {

    @Resource
    private StorageMapper storageMapper;
    @Resource
    private TxLogMapper txLogMapper;

    @Override
    public void decreaseStock(OrderTxMessage orderTxMessage) {
        TxLog txLog = txLogMapper.selectById(orderTxMessage.getTxNo());
        if (txLog != null) {
            log.info("库存已扣减");
        } else {
            storageMapper.decrease(buildStorage(orderTxMessage));
            txLog = new TxLog();
            txLog.setTxNo(orderTxMessage.getTxNo());
            txLogMapper.insert(txLog);
        }
    }

    private StorageDTO buildStorage(OrderTxMessage order) {
        StorageDTO storageDTO = new StorageDTO();
        storageDTO.setProductId(order.getProductId());
        storageDTO.setQuantity(order.getQuantity());
        return storageDTO;
    }
}
