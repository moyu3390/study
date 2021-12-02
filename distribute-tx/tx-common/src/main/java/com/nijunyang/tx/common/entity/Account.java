package com.nijunyang.tx.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class Account {

    @TableId(type= IdType.AUTO)
    private Integer id;
    
    private String userId;
    
    private BigDecimal available;

    private BigDecimal freeze;

    private BigDecimal total;

}
