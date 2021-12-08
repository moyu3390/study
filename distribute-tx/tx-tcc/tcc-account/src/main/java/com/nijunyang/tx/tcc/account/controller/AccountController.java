package com.nijunyang.tx.tcc.account.controller;

import com.nijunyang.tx.common.dto.AccountDTO;
import com.nijunyang.tx.tcc.account.service.AccountService;
import com.nijunyang.tx.tcc.account.service.AccountServiceImpl;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Description:
 * Created by nijunyang on 2021/12/8 1:42
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Resource
    private AccountService accountService;
    @Resource
    @Lazy
    private ConfigurableApplicationContext cfgContext;

    @GetMapping("/test")
    public Object payment() {
        AccountServiceImpl bean = cfgContext.getBean(AccountServiceImpl.class);
        return bean;
    }

    @PostMapping("/payment")
    public Boolean payment(@RequestBody AccountDTO accountDO) {
        return accountService.payment(accountDO);
    }
}
