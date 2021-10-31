package com.nijunyang.account.service;

import com.nijunyang.mysqlcommon.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Description:
 * Created by nijunyang on 2021/10/26 0:20
 */
@Service
public class AccountService {

    @Autowired
    AccountMapper accountMapper;

    public void deductBalance(String userId, BigDecimal amount) {
        int result = accountMapper.reduceBalance(userId, amount);
        if (result <= 0) {
            throw new RuntimeException("余额扣减失败");
        }
    }
}
