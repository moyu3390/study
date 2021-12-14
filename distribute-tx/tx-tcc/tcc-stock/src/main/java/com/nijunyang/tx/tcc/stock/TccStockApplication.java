package com.nijunyang.tx.tcc.stock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ImportResource({"classpath:applicationContext.xml"})
@EnableTransactionManagement
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@MapperScan("com.nijunyang.tx.common.mapper")
public class TccStockApplication {

    public static void main(String[] args) {
        SpringApplication.run(TccStockApplication.class, args);
    }

}
