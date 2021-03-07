package com.nijunyang.shiro.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 用户
 * @author nijunyang
 * @since 2021/3/5
 */
@Data
public class SystemUser extends BaseEntity{

    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 登录名
     */
    private String loginName;
    /**
     * 登录密码
     */
    private String password;


}
