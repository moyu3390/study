package com.nijunyang.exception;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;

import java.util.Arrays;

@SpringBootApplication
public class ExceptionApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ExceptionApplication.class, args);
        MessageSource bean = context.getBean(MessageSource.class);
        String[] beanNamesForType = context.getBeanNamesForType(MessageSource.class);
        Arrays.stream(beanNamesForType).forEach(System.out::println);
    }

}
