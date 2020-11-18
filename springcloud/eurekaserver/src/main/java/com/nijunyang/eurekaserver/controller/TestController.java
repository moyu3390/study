package com.nijunyang.eurekaserver.controller;

import com.netflix.eureka.EurekaBootStrap;
import com.netflix.eureka.registry.PeerAwareInstanceRegistry;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Description:
 * Created by nijunyang on 2020/11/18 9:35
 *
 * @author nijunyang
 */
public class TestController {

    @Autowired
    EurekaBootStrap eurekaBootStrap;

    /**
     * 实际org.springframework.cloud.netflix.eureka.server.InstanceRegistry
     */
    @Autowired
    PeerAwareInstanceRegistry peerAwareInstanceRegistry;

}
