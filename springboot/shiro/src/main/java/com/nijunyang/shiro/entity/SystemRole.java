package com.nijunyang.shiro.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 角色
 * @author nijunyang
 * @since 2021/3/5
 */
@Data
public class SystemRole extends BaseEntity{

    /**
     * 序列号id
     */
    @TableId(type= IdType.AUTO)
    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色编码
     */
    private String code;
}
