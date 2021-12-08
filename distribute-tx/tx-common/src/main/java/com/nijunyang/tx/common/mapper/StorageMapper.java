package com.nijunyang.tx.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nijunyang.tx.common.dto.StorageDTO;
import com.nijunyang.tx.common.entity.Storage;
import org.apache.ibatis.annotations.Update;


public interface StorageMapper extends BaseMapper<Storage> {


    @Update("update storage set total_inventory = total_inventory - #{quantity}," +
            " lock_inventory= lock_inventory + #{quantity} " +
            " where product_id =#{productId} and total_inventory > 0  ")
    int decrease(StorageDTO storageDTO);

    @Update("update storage set " +
            " lock_inventory = lock_inventory - #{quantity} " +
            " where product_id =#{productId} and lock_inventory > 0 ")
    int confirm(StorageDTO storageDTO);

    @Update("update storage set total_inventory = total_inventory + #{quantity}," +
            " lock_inventory= lock_inventory - #{quantity} " +
            " where product_id =#{productId}  and lock_inventory > 0 ")
    int cancel(StorageDTO storageDTO);

}
