package com.nijunyang.tx.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.nijunyang.tx.common.enums.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


@Data
@TableName("t_order")
public class Order {

    @TableId(type= IdType.AUTO)
    private Integer id;

    /**
     * 订单编号
     */
    private String number;

    /**
     * 购买人
     */
    private String userId;

    /**
     * 订单状态
     */
    private OrderStatus orderStatus;

    /**
     * 商品id
     */
    private Integer productId;

    /**
     * 订单金额
     */
    private BigDecimal amount;

    /**
     * 购买数量
     */
    private Integer quantity;


    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
