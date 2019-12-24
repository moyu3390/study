package com.nijunyang.aspect.controller;

import com.nijunyang.aspect.annotation.AspectDef;
import com.nijunyang.aspect.annotation.SensitiveInfo;
import com.nijunyang.aspect.annotation.SensitiveMethod;
import com.nijunyang.aspect.aspect.SensitiveAspect;
import com.nijunyang.aspect.model.User;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 
 * Created by nijunyang on 2019/12/10 14:56
 */
@RestController
public class TestController {

    @Autowired
    SensitiveAspect aspect;

    @GetMapping("/test/error")
    public String testError(){
        throw new RuntimeException();
    }

    @GetMapping(value = {"/test/before/{param}","/test/before"})
    public String testBefore(@PathVariable(required = false) String param){
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


    @GetMapping("/test/secret")

    public User testSecret(){
        User user = new User("zhangsan", "158xxxx8795","51323654498xxxxxxx");
        ((TestController) AopContext.currentProxy()).testSecret2(user);
        return testSecret2(user);
    }

    @SensitiveMethod(decrypt = false)
    public User testSecret2(@SensitiveInfo User user){
        User a = user;
        return a;
    }

}
