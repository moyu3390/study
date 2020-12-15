package com.nijunyang.order.feign;

import com.nijunyang.order.config.FeignConfig;
import com.nijunyang.order.feign.fallback.StockFeignClientFallback;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Description:
 * Created by nijunyang on 2020/2/15 13:47
 */
@FeignClient(value = "stock", fallbackFactory = StockFeignClientFallback.class, configuration = FeignConfig.class)
public interface StockFeignClient {

    @PostMapping("stock/deduct")
    String deductStock();



    //feign 自己的注解
//    @RequestLine("POST /stock/deduct")
//    String deductStock0(@Param("xxx") String xxx);
}
