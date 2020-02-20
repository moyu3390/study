package com.nijunyang.spring.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author: create by nijunyang
 * @date:2019/10/6
 */
//@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    //注册了bean定义之后调用，可以修改bean定义，比如把student设置成懒加载
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        for(String name : beanFactory.getBeanDefinitionNames()) {
            if("student".equals(name)) {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(name);
                beanDefinition.setLazyInit(true);
            }

        }
    }
}
