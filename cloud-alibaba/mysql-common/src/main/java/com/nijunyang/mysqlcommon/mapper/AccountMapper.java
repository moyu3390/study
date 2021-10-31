package com.nijunyang.mysqlcommon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nijunyang.mysqlcommon.entity.Account;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;


@Repository
public interface AccountMapper extends BaseMapper<Account> {

    /**
     * 扣减余额
     * @param userId 用户id
     * @param amount 要扣减的金额
     * @return
     */
    @Update("update account set available = available - #{amount}, total = total - #{amount} " +
            "where user_id = #{userId} and available >= #{amount}")
    int reduceBalance(@Param("userId") String userId, @Param("amount") BigDecimal amount);
    
}
