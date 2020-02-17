package com.nijunyang.shiro.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nijunyang.shiro.dao.UserDao;
import com.nijunyang.shiro.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description:
 * Created by nijunyang on 2020/2/17 16:30
 */
@Service
public class UserService {

    @Autowired
    UserDao userDao;


    public User findByName(String name) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, name);
        return userDao.selectOne(queryWrapper);
    }


    public User findById(Integer id) {
        return userDao.selectById(id);
    }
}
