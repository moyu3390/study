package com.nijunyang.spring.config;

import com.nijunyang.spring.Test;
import com.nijunyang.spring.model.Student;
import com.nijunyang.spring.model.Teacher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author: create by nijunyang
 * @date:2019/10/6
 */
@Configuration
@ComponentScan(basePackages = "com.nijunyang.spring.*")
public class MainConfig {

    @Bean
    public Test test(){
        return new Test();
    }

    @Bean
    public Teacher teacher(){
        return new  Teacher();
    }

    @Bean
    public Student student(){
        return new  Student();
    }
}
