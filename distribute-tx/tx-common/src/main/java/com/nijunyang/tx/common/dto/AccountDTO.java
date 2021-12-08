package com.nijunyang.tx.common.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Description:
 * Created by nijunyang on 2021/12/8 1:11
 */
@Data
public class AccountDTO {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 扣款金额
     */
    private BigDecimal amount;
}
