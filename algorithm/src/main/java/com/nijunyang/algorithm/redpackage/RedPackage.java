package com.nijunyang.algorithm.redpackage;

import java.math.BigDecimal;

/**
 * Description:
 * Created by nijunyang on 2019/12/12 21:58
 */
public class RedPackage {
    private int remainCount;
    private BigDecimal remainMoney;

    public int getRemainCount() {
        return remainCount;
    }

    public void setRemainCount(int remainCount) {
        this.remainCount = remainCount;
    }

    public BigDecimal getRemainMoney() {
        return remainMoney;
    }

    public void setRemainMoney(BigDecimal remainMoney) {
        this.remainMoney = remainMoney;
    }
}
