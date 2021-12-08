package com.nijunyang.tx.tcc.stock.service;

import com.nijunyang.tx.common.dto.StorageDTO;

/**
 * Description:
 * Created by nijunyang on 2021/12/8 23:57
 */
public interface StockService {

    Boolean decrease(StorageDTO storageDTO);

    Boolean confirmMethod(StorageDTO storageDTO);

    Boolean cancelMethod(StorageDTO storageDTO);
}
