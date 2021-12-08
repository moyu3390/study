package com.nijunyang.tx.tcc.account.service;

import com.nijunyang.tx.common.dto.AccountDTO;
import com.nijunyang.tx.common.mapper.AccountMapper;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Description:
 * Created by nijunyang on 2021/12/8 1:43
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public Boolean payment(AccountDTO accountDO) {
        accountMapper.preFreeze(accountDO);
        return Boolean.TRUE;
    }

    public boolean confirm(final AccountDTO accountDTO) {
        return accountMapper.confirm(accountDTO) > 0;
    }

    public boolean cancel(final AccountDTO accountDTO) {
        return accountMapper.cancel(accountDTO) > 0;
    }
}
