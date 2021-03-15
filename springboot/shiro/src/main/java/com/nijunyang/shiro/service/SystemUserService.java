package com.nijunyang.shiro.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nijunyang.shiro.dao.SystemUserMapper;
import com.nijunyang.shiro.entity.SystemUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author nijunyang
 * @since 2021/3/10
 */
@Service
public class SystemUserService {

    @Resource
    SystemUserMapper systemUserMapper;

    public SystemUser selectByLoginName(String loginName) {
        LambdaQueryWrapper<SystemUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SystemUser::getLoginName, loginName);
        SystemUser systemUser = systemUserMapper.selectOne(queryWrapper);
        return systemUser;
    }
}
