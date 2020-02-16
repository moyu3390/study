package com.nijunyang.order.feign;

import com.nijunyang.order.feign.fallback.StockFeignClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Description:
 * Created by nijunyang on 2020/2/15 13:47
 */
@FeignClient(value = "stock", fallbackFactory = StockFeignClientFallback.class)
public interface StockFeignClient {

    @PostMapping("stock/deduct")
    String deductStock();
}
