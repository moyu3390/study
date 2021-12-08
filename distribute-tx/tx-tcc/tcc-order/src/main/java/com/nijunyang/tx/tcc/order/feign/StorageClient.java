package com.nijunyang.tx.tcc.order.feign;

import com.nijunyang.tx.common.dto.StorageDTO;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * Description:
 * Created by nijunyang on 2021/12/8 1:05
 */
@FeignClient(name = "stock")
public interface StorageClient {
    /**
     * 扣库存
     * @param storageDTO
     */
    @HmilyTCC
    @PostMapping("/stock/decrease")
    Boolean decrease(@RequestBody StorageDTO storageDTO);
}
