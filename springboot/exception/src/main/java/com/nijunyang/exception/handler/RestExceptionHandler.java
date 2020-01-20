package com.nijunyang.exception.handler;

import com.nijunyang.exception.exception.ErrorCodeException;
import com.nijunyang.exception.model.RestErrorResponse;
import com.nijunyang.exception.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

/**
 * Description: 控制层统一异常处理
 * Created by nijunyang on 2019/12/20 9:33
 */
@ControllerAdvice
public class RestExceptionHandler {

    @Resource
    private MessageSource messageSource;

    //locale可以处理国际化资源文件，不同的语言
    @ExceptionHandler(value = ErrorCodeException.class)
    public final ResponseEntity<RestErrorResponse> handleBadRequestException(ErrorCodeException errorCodeException, Locale locale) {
        File file = new File("");
        try {
            String canonicalPath = file.getCanonicalPath();
            System.out.println(canonicalPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String message = messageSource.getMessage(String.valueOf(errorCodeException.getCode()), errorCodeException.getArgs(), locale);
        RestErrorResponse restErrorResponse = new RestErrorResponse(Status.FAILED, errorCodeException.getCode(), message);
        return new ResponseEntity<>(restErrorResponse, HttpStatus.OK);
    }
}
