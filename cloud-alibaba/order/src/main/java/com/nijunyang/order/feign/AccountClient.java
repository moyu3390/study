package com.nijunyang.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * Description:
 * Created by nijunyang on 2021/10/26 0:08
 */
@FeignClient(name = "account")
public interface AccountClient {

    @PostMapping("account/{userId}/deduct")
    String deductBalance(@PathVariable String userId, @RequestParam("amount") BigDecimal amount);
}
