package com.nijunyang.shiro.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 用户和角色
 * @author nijunyang
 * @since 2021/3/5
 */
@Data
public class SystemUserRole {

    @TableId(type= IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer roleId;

}
