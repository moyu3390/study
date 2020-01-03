package com.nijunyang.codegen.service;
import javax.servlet.http.HttpServletRequest;
import com.nijunyang.codegen.model.User;

public interface UserControllerService
{
     //逻辑均在子类中实现
    User getUserByName(HttpServletRequest request, String username);
}