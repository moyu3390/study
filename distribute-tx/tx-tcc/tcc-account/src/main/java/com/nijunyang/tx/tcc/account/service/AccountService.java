package com.nijunyang.tx.tcc.account.service;

import com.nijunyang.tx.common.dto.AccountDTO;

/**
 * Description:
 * Created by nijunyang on 2021/12/8 23:56
 */
public interface AccountService {

    Boolean payment(AccountDTO accountDO);

    boolean confirm(final AccountDTO accountDTO);

    boolean cancel(final AccountDTO accountDTO);
}
