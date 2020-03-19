package com.nijunyang.redis.lock;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * Created by nijunyang on 2020/3/17 23:53
 */
@RestController
public class LockController {

    @Autowired
    ValueOperations<String, Object> valueOperations;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    Redisson redisson;

    String lockKey = "lockKey";

    String quantityKey = "quantity";

    @GetMapping("/deduct-stock")
    public String deductStock() {
        String uuid = UUID.randomUUID().toString();
        try {
            //设置值，并且设置超时时间
            boolean getLock = valueOperations.setIfAbsent(lockKey, uuid, 10, TimeUnit.SECONDS);
//            boolean getLock = valueOperations.setIfAbsent(lock, 1);
            if (!getLock) {
                return "没有获取到锁";
            }
            //使用当做数据库，只是模拟扣减库存场景，因此不使用原子操作
            Integer quantity = (Integer) valueOperations.get(quantityKey);
            if (quantity > 0) {
                --quantity;
                valueOperations.set(quantityKey, quantity);
                System.out.println("扣减库存成功,剩余库存： " + quantity);
            } else {
                System.out.println("扣减库存成功,剩余库存： " + quantity);
            }
            return "true";
        } finally {
            //删除之前判断是否是自己加的锁
            if (uuid.equals(valueOperations.get(lockKey))) {
                redisTemplate.delete(lockKey);
            }
        }
    }


    @GetMapping("/deduct-stock2")
    public String deductStock2() {
        RLock redissonLock = redisson.getLock(lockKey);
        try {
            redissonLock.lock();
            //使用当做数据库，只是模拟扣减库存场景，因此不使用原子操作
            Integer quantity = (Integer) valueOperations.get(quantityKey);
            if (quantity > 0) {
                --quantity;
                valueOperations.set(quantityKey, quantity);
                System.out.println("扣减库存成功,剩余库存： " + quantity);
                return "true";
            } else {
                System.out.println("扣减库存成功,剩余库存： " + quantity);
                return "false";
            }
        } finally {
            redissonLock.unlock();
        }
    }

}
