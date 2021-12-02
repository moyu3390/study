package com.nijunyang.tx.xa.mapper2;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nijunyang.tx.common.entity.Storage;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * Description:
 * Created by nijunyang on 2021/12/3 0:10
 */
@Repository
public interface StorageMapper extends BaseMapper<Storage> {

    @Update("UPDATE storage SET quantity = quantity - #{quantity} WHERE commodity_id = #{commodityId} and quantity >= #{quantity}")
    void reduce(Integer commodityId, Integer quantity);
}
