package com.nijunyang.eureka.stock.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ZoneAwareLoadBalancer;
import com.nijunyang.eureka.stock.feign.OrderFeignClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Description:
 * Created by nijunyang on 2020/2/15 13:36
 */
@RestController
@RequestMapping("/stock")
public class StockController implements InitializingBean {


    @Autowired
    OrderFeignClient orderFeignClient;

    @Autowired
    EurekaClient eurekaClient;

//    @Autowired
//    ILoadBalancer loadBalancer;

    @Autowired
    ApplicationContext applicationContext;

    @Override
    public void afterPropertiesSet() throws Exception {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        String infoName = null;
        for (int i = 0; i < beanDefinitionNames.length; i++) {
            if (beanDefinitionNames[i].toLowerCase().contains("loadbalancer")) {
                infoName = beanDefinitionNames[i];
            }
        }
//        String[] beanNamesForType = applicationContext.getBeanNamesForType(ILoadBalancer.class);
//        ILoadBalancer bean = (ILoadBalancer) applicationContext.getBean("ribbonLoadBalancer");
//        ILoadBalancer bean2 = applicationContext.getBean(ZoneAwareLoadBalancer.class);
//        System.out.println();
    }

    @GetMapping("/deduct")
    public ResponseEntity<String> deductStock() {
        return ResponseEntity.ok().body("stock deduct success");
    }

    @GetMapping("/order")
    public ResponseEntity<String> getOrder() {
//        Applications applications = eurekaClient.getApplications();
//        List<Application> registeredApplications = applications.getRegisteredApplications();
        return ResponseEntity.ok().body(orderFeignClient.getOrder());
    }

    @GetMapping("/apps")
    public ResponseEntity<String> getApps() {
        Applications applications = eurekaClient.getApplications();
        List<Application> registeredApplications = applications.getRegisteredApplications();
        Optional<Application> any =
                registeredApplications.stream().filter(application -> "order".equalsIgnoreCase(application.getName())).findAny();
        return ResponseEntity.ok().body(any.toString());
    }

//    @GetMapping("/server")
//    public ResponseEntity<String> server() {
//        List<Server> allServers = loadBalancer.getAllServers();
//        final StringBuilder info = new StringBuilder();
//        allServers.forEach(server -> info.append(server.getHost()));
//        return ResponseEntity.ok().body(info.toString());
//    }


}
