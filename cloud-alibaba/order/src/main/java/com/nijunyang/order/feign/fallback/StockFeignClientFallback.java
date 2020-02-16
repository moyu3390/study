package com.nijunyang.order.feign.fallback;

import com.nijunyang.order.feign.StockFeignClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Description:
 * Created by nijunyang on 2020/2/15 13:59
 */
@Component
@Slf4j
public class StockFeignClientFallback implements FallbackFactory<StockFeignClient> {

    @Override
    public StockFeignClient create(Throwable throwable) {
        return () -> {
            log.error("扣减积分失败");
            return null;
        };
    }
}
