package com.nijunyang.mysqlcommon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;


@Data
@TableName("t_order")
public class Order {

    @TableId(type= IdType.AUTO)
    private Integer id;
    
    private String userId;

    private Integer commodityId;
    
    private Integer quantity;
    
    private BigDecimal price;
    
    private Integer status;
}
