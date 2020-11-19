package com.nijunyang.eureka.order.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nijunyang.eureka.extension.constants.Constant;
import com.nijunyang.eureka.extension.component.redis.MessageHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 * Created by nijunyang on 2020/2/15 13:40
 */
@RestController()
@RequestMapping("/order")
public class OrderController {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("/create")
    public ResponseEntity<String> create() {
        return ResponseEntity.ok().body("create order");
    }

    @GetMapping
    public ResponseEntity<String> get() {
        return ResponseEntity.ok().body("order detail");
    }


    @GetMapping("pub")
    public ResponseEntity<String> get0() {
        long l = System.currentTimeMillis();
        MessageHolder messageHolder = new MessageHolder(null, null, l);
        redisTemplate.convertAndSend(Constant.REDIS_TOPIC, messageHolder);
        return ResponseEntity.ok().body("发布时间：" + l);
    }

}
