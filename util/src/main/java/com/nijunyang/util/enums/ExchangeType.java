package com.nijunyang.util.enums;

/**
 * Description: 
 * Created by nijunyang on 2020/1/8 11:29
 */
public enum ExchangeType {

    POINT(1,"积分"),
    MONEY(2,"现金"),
    ;

    private final int value;
    private final String name;

    ExchangeType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static ExchangeType getByValue(int value) {
        for (ExchangeType exchangeType : values()) {
            if (exchangeType.value == value) {
                return exchangeType;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
