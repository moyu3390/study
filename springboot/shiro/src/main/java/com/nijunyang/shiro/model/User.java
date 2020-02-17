package com.nijunyang.shiro.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Description:
 * Created by nijunyang on 2020/2/17 16:17
 */
@Getter
@Setter
public class User {
    private Integer id;
    private String userName;
    private String password;
    private String perms;
}
