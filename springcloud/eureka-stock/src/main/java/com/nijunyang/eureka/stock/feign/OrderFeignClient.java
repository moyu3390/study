package com.nijunyang.eureka.stock.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Description:
 * Created by nijunyang on 2020/11/17 9:18
 *
 * @author nijunyang
 */
@FeignClient(value = "order")
public interface OrderFeignClient {

    @RequestMapping(value = "order", method = RequestMethod.GET)
    String getOrder();
}
