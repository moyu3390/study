package com.nijunyang.shiro.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

/**
 * 菜单
 * @author nijunyang
 * @since 2021/3/5
 */
@Data
public class SystemMenu extends BaseEntity{

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 父id
     */
    private Long parentId;
    /**
     * 以逗号隔开的所有父id
     */
    private String parentIds;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 同级菜单排序
     */
    private Integer sort;
    /**
     * 连接地址
     */
    private String href;
    /**
     * 图标
     */
    private String icon;
    /**
     * 是否是菜单
     */
    private Boolean menu;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 权限
     */
    private String permission;

}
