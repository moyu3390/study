package com.nijunyang.aspect.controller;

import com.nijunyang.aspect.annotation.AspectDef;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 
 * Created by nijunyang on 2019/12/10 14:56
 */
@RestController
public class TestController {

    @GetMapping("/test/error")
    public String testError(){
        throw new RuntimeException();
    }

    @GetMapping("/test/before")
    public String testBefore(){
        return "before";
    }

    @GetMapping("/test/after")
    public String testAfter(){
        return "after";
    }

    @GetMapping("/test/afterReturning")
    public String testAfterReturning(){
        return "afterReturning";
    }

    @AspectDef
    @GetMapping("/test/annotation")
    public String testAnnotation(){
        return "annotation";
    }

}
