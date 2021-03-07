package com.nijunyang.shiro.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 角色和菜单
 * @author nijunyang
 * @since 2021/3/5
 */
@Data
public class SystemMenuRole {
    @TableId(type= IdType.AUTO)
    private Integer id;
    private Integer roleId;
    private Integer menuId;
}
