package com.nijunyang.tx.common.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Description:
 * Created by nijunyang on 2021/12/15 1:12
 */
@Getter
@Setter
public class TxLog {

    private String txNo;
    private Date createTime;
}
