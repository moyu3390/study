package com.nijunyang.mq.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.nijunyang.tx.common.mapper")
public class MqOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MqOrderApplication.class, args);
    }

}
