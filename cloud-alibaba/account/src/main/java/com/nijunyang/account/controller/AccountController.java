package com.nijunyang.account.controller;

import com.nijunyang.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * Description:
 * Created by nijunyang on 2021/10/26 0:17
 */
@RequestMapping("account")
@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping("/{userId}/deduct")
    public ResponseEntity<?> deductBalance(@PathVariable String userId, @RequestParam BigDecimal amount) {
        accountService.deductBalance(userId, amount);
        return ResponseEntity.ok().build();
    }
}
