package com.nijunyang.mysqlcommon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nijunyang.mysqlcommon.entity.Storage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageMapper extends BaseMapper<Storage> {

    /**
     * 扣减库存
     * @param commodityId 商品ID
     * @param quantity  要扣减的库存
     * @return
     */
    @Update("UPDATE storage SET quantity = quantity - #{quantity} WHERE commodity_id = #{commodityId} and quantity >= #{quantity}")
    int reduceStorage(@Param("commodityId") Integer commodityId, @Param("quantity") Integer quantity);
    
}
