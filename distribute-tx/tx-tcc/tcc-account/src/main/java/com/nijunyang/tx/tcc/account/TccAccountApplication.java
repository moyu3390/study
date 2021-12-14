package com.nijunyang.tx.tcc.account;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ImportResource({"classpath:applicationContext.xml"})
@EnableTransactionManagement
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@MapperScan("com.nijunyang.tx.common.mapper")
public class TccAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(TccAccountApplication.class, args);
    }

}
