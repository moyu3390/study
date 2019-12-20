package com.nijunyang.exception.controller;

import com.nijunyang.exception.exception.RequestException;
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

    @GetMapping("/runtime")
    public ResponseEntity testRuntime() {
        throw new NullPointerException();
    }

    @GetMapping("/customize")
    public ResponseEntity test() throws RequestException {
        throw new RequestException("这是自定义异常", 51110, "其他信息1", "其他信息2");
    }
}
