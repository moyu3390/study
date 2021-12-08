package com.nijunyang.tx.tcc.order.feign;

import com.nijunyang.tx.common.dto.AccountDTO;
import org.dromara.hmily.annotation.Hmily;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Description:
 * Created by nijunyang on 2021/12/8 1:04
 */
@FeignClient(name = "account")
public interface AccountClient {

    /**
     * 支付
     * @param accountDTO
     */
    @Hmily
    @PostMapping("/account/payment")
    Boolean payment(@RequestBody AccountDTO accountDTO);
}
