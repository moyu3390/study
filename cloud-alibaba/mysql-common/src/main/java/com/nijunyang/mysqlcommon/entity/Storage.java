package com.nijunyang.mysqlcommon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Storage {
    @TableId(type= IdType.AUTO)
    private Integer id;
    
    private Integer commodityId;
    
    private Integer quantity;
    
}
