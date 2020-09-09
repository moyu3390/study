package com.nijunyang.retry.service;

import com.nijunyang.retry.model.User;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


/**
 * Description:
 * Created by nijunyang on 2020/9/9 14:58
 */
@Service
public class TestService {


    @Retryable(maxAttempts = 5, backoff = @Backoff(value = 1000, multiplier = 2))
    @Async
    public void test(User user) throws NoSuchAlgorithmException {
        System.out.println(user);
        user.setAge(SecureRandom.getInstance("SHA1PRNG").nextInt());
        user.setName("zhangsan");
        throw new RuntimeException();
    }

    @Retryable(maxAttempts = 5, backoff = @Backoff(value = 1000, multiplier = 2))
    @Async
    public void test22(User user) throws NoSuchAlgorithmException {
        System.out.println(user);
        user.setAge(SecureRandom.getInstance("SHA1PRNG").nextInt());
        user.setName("zhangsan2");
        throw new IllegalArgumentException();
    }


    @Recover()
    public void testRecover(RuntimeException e, User user) {
        System.out.println("异常：" + e);
        System.out.println(user);
    }

    @Recover()
    public void testRecover(IllegalArgumentException e, User user) {
        System.out.println("异常：" + e);
        System.out.println(user);
    }
}
