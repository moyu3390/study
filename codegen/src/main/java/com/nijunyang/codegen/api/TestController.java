package com.nijunyang.codegen.api;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.nijunyang.codegen.service.TestControllerService;

@RestController
public class TestController
{
     //接口注入，逻辑均在实现类中实现
     @Autowired
    TestControllerService service;
    @RequestMapping(value = "/test/{username}",method = RequestMethod.GET)
    public String test(HttpServletRequest request, @PathVariable String username) {
        //TODO
        return service.test(request, username);
    }
}