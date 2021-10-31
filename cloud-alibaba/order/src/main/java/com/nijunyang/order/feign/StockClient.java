package com.nijunyang.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Description:
 * Created by nijunyang on 2021/10/26 0:06
 */
@FeignClient(name = "stock")
public interface StockClient {

    @PostMapping("stock/deduct")
    String deductStock(@RequestParam("quantity") Integer quantity, @RequestParam("commodityId") Integer commodityId);
}
