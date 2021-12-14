package com.nijunyang.tx.tcc.account.config;

import com.nijunyang.tx.tcc.account.service.AccountServiceImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * @author: create by nijunyang
 * @date:2019/10/6
 */
//@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("bean定义的数据量:"+registry.getBeanDefinitionCount());
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(AccountServiceImpl.class);
        //注册一个名为student的bean定义进去
        registry.registerBeanDefinition("accountServiceImpl", rootBeanDefinition);
        System.out.println("bean定义的数据量:"+registry.getBeanDefinitionCount());
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
