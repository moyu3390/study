package com.nijunyang.mybatis.mapper;

import com.nijunyang.mybatis.model.User;

/**
 * @author: create by nijunyang
 * @date:2019/7/3
 */
public interface UserMapper {
    int insertUser(User user);
    User selectByCard(String card);
}
