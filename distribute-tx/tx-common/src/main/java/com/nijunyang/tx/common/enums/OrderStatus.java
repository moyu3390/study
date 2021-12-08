package com.nijunyang.tx.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description:
 * Created by nijunyang on 2021/12/7 23:04
 */
@Getter
@AllArgsConstructor
public enum OrderStatus {

    /**
     * 未支付
     */
    NOT_PAY(1, "未支付"),

    /**
     * 支付中
     */
    PAYING(2, "支付中"),

    /**
     * 支付失败
     */
    PAY_FAIL(3, "支付失败"),

    /**
     * 支付成功
     */
    PAY_SUCCESS(4, "支付成功");

    private final int code;

    private final String desc;
}
