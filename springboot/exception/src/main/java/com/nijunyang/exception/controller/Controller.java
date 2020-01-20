package com.nijunyang.exception.controller;

import com.nijunyang.exception.exception.ErrorCodeException;
import com.nijunyang.exception.model.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 
 * Created by nijunyang on 2019/12/20 9:58
 */
@RestController
@RequestMapping("/error")
public class Controller {

    @GetMapping("/file")
    public ResponseEntity test() {
        //模拟文件不存在
        String path = "a/b/c/d.txt";
        throw new ErrorCodeException(ErrorCode.FILE_DOES_NOT_EXIST_51001, path);
    }
}
