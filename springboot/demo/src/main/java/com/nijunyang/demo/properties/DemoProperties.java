package com.nijunyang.demo.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Description:
 * Created by nijunyang on 2020/7/21 16:55
 */
@ConfigurationProperties("demo")
@EnableConfigurationProperties(DemoProperties.class)
@Getter
@Setter
@Component
public class DemoProperties {
    private String info;
}
