package com.nijunyang.consumerfeign.controller;

import com.nijunyang.consumerfeign.api.ServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: create by nijunyang
 * @date:2019/9/15
 */
@RestController
public class FeignController {

    @Autowired
    private ServiceApi serviceApi;

    @RequestMapping(value = "/feign/port", method = RequestMethod.GET)
    public int getPort(){
        return serviceApi.getPort();
    }

}
