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

    @Update("UPDATE storage SET total_inventory = total_inventory - #{quantity} WHERE product_id = #{productId} and total_inventory >= #{quantity}")
    void reduce(Integer productId, Integer quantity);
}
