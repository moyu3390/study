package com.nijunyang.redis.controller;

import com.nijunyang.redis.model.User;
import com.nijunyang.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 * Created by nijunyang on 2019/12/11 13:57
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    RedisService redisService;

    @Autowired
    HashOperations<String, String, Object> hashOperations;


    @GetMapping("/count")
    public ResponseEntity<Long> count(){
        Long count = redisService.getCount();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/addHash")
    public ResponseEntity<User> addHash(){
        User user = new User();
        user.setId(1);
        user.setName("张三");
        user.setAge(17);
        //第一个参数redisKey,第二个参数hashKey值(相当于Map的key),第三个参数data(相当于Map的value)
        //用对象的字段生成hashCode做key，如果直接用hashCode，可能会出现不然一样的数据不同的序列化方式还原的对象hash值不一样
        hashOperations.put("hash:user", user.createHashCode(), user);
        //第一个参数redisKey,第二个参数hashKey值(相当于Map的key),返回的value
        Object o = hashOperations.get("hash:user", user.createHashCode());
        redisService.expireKey("hash:user", 100, TimeUnit.SECONDS);
        System.out.println(o);
        return new ResponseEntity<>((User)o, HttpStatus.OK);
    }


}
