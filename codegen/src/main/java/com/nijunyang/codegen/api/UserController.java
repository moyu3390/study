package com.nijunyang.codegen.api;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.nijunyang.codegen.model.User;
import com.nijunyang.codegen.service.UserControllerService;

@RestController
public class UserController
{
     //接口注入，逻辑均在实现类中实现
     @Autowired
    UserControllerService service;
    @RequestMapping(value = "/user/{username}",method = RequestMethod.GET)
    public User getUserByName(HttpServletRequest request, @PathVariable String username) {
        //TODO
        return service.getUserByName(request, username);
    }
}