package com.nijunyang.clientconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ClientconsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientconsumerApplication.class, args);
	}

}
