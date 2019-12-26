package com.nijunyang.clientt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ClienttApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClienttApplication.class, args);
    }

}
