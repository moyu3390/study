package com.nijunyang.order;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Description:
 * Created by nijunyang on 2020/12/15 22:42
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }
}
