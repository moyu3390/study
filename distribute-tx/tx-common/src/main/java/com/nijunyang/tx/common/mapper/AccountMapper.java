package com.nijunyang.tx.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nijunyang.tx.common.dto.AccountDTO;
import com.nijunyang.tx.common.entity.Account;
import org.apache.ibatis.annotations.Update;


public interface AccountMapper extends BaseMapper<Account> {


    @Update("update account set available_amount = available_amount - #{amount}," +
            " freeze_amount= freeze_amount + #{amount} " +
            " where user_id =#{userId}  and  available_amount >= #{amount}  ")
    int preFreeze(AccountDTO accountDTO);

    @Update("update account set " +
            " freeze_amount= freeze_amount - #{amount}" +
            " where user_id =#{userId}  and freeze_amount >= #{amount} ")
    int confirm(AccountDTO accountDTO);

    @Update("update account set available_amount = available_amount + #{amount}," +
            " freeze_amount= freeze_amount -  #{amount} " +
            " where user_id =#{userId}  and freeze_amount >= #{amount}")
    int cancel(AccountDTO accountDTO);
}
