package com.nijunyang.stock.service;

import com.nijunyang.mysqlcommon.mapper.StorageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description:
 * Created by nijunyang on 2021/10/26 0:10
 */
@Service
public class StockService {

    @Autowired
    StorageMapper storageMapper;


    public void deductStock(Integer quantity, Integer commodityId) {
        int result = storageMapper.reduceStorage(commodityId, quantity);
        if (result <= 0) {
            throw new RuntimeException("扣减库存失败");
        }
    }
}
