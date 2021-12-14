package com.nijunyang.tx.tcc.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ImportResource({"classpath:applicationContext.xml"})
@EnableTransactionManagement
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@MapperScan("com.nijunyang.tx.common.mapper")
@EnableFeignClients("com.nijunyang.tx.tcc.order.feign")
public class TccOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(TccOrderApplication.class, args);
    }

}
