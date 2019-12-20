package com.nijunyang.exception.handler;

import com.nijunyang.exception.exception.RequestException;
import com.nijunyang.exception.model.RestErrorResponse;
import com.nijunyang.exception.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

/**
 * Description: 控制层统一异常处理
 * Created by nijunyang on 2019/12/20 9:33
 */
@ControllerAdvice
public class RestExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    //locale可以处理国际化资源文件，不同的语言
    @ExceptionHandler(value = RequestException.class)
    public final ResponseEntity handleBadRequestException(RequestException requestException, Locale locale) {
        RestErrorResponse restErrorResponse = new RestErrorResponse(Status.FAILED, requestException.getCode(),
                requestException.getMessage(), requestException.getArgs());
        return new ResponseEntity<>(restErrorResponse, HttpStatus.OK);
    }
}
