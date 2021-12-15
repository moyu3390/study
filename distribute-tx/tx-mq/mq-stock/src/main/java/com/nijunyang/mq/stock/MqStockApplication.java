package com.nijunyang.mq.stock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.nijunyang.tx.common.mapper")
public class MqStockApplication {

    public static void main(String[] args) {
        SpringApplication.run(MqStockApplication.class, args);
    }

}
