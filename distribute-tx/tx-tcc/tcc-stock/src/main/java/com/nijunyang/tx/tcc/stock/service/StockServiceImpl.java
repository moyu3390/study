package com.nijunyang.tx.tcc.stock.service;

import com.nijunyang.tx.common.dto.StorageDTO;
import com.nijunyang.tx.common.mapper.StorageMapper;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Description:
 * Created by nijunyang on 2021/12/8 21:47
 */
@Service("stockService")
public class StockServiceImpl implements StockService {

    @Resource
    private StorageMapper storageMapper;

    @HmilyTCC(confirmMethod = "confirmMethod", cancelMethod = "cancelMethod")
    public Boolean decrease(StorageDTO storageDTO) {
        return storageMapper.decrease(storageDTO) > 0;
    }

    public Boolean confirmMethod(StorageDTO storageDTO) {
        return storageMapper.confirm(storageDTO) > 0;
    }

    public Boolean cancelMethod(StorageDTO storageDTO) {
        return storageMapper.cancel(storageDTO) > 0;
    }
}
