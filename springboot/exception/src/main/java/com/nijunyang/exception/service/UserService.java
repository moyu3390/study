package com.nijunyang.exception.service;


import com.nijunyang.exception.model.User;
import org.springframework.stereotype.Service;

/**
 * Description:
 * Created by nijunyang on 2021/3/2 23:40
 */
@Service
public class UserService {

    public User setUser(User user) {
        User user1 = new User();
        user1.setSex(user.getSex());
        user1.setName(user.getName());
        user1.setAge(user.getAge() + "32");
        return user1;
    }
}
