package com.nijunyang.springboot.argsresolver;

import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author: create by nijunyang
 * @date:2019/10/30
 */
@Component
//一定要将自己的扩展类加到容器中
public class DefWebMvcConfigurer implements WebMvcConfigurer {

    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new DefArgumentResolver());
    }
}
