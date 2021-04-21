package com.nijunyang.eureka.stock.feign;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author nijunyang
 * @since 2021/4/21
 */
@Slf4j
@Component
public class MyFeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            String resp = Util.toString(response.body().asReader());
            if (StringUtils.isNotEmpty(resp)) {
                log.info("ErrorInfo:" + resp);
                JSONObject errorResult = JSONObject.parseObject(resp);
                Object data = errorResult.get("data");
                if (data instanceof JSONArray) {
                    return new MyException(
                            errorResult.getIntValue("code"),
                            errorResult.getString("msg"),
                            ((JSONArray)data).toArray());
                } else {
                    return new MyException(
                            errorResult.getIntValue("code"),
                            errorResult.getString("msg"),
                            data);
                }
            }
        } catch (IOException e) {
            return FeignException.errorStatus(methodKey, response);
        }
        return FeignException.errorStatus(methodKey, response);
    }
}
