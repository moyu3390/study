package com.nijunyang.codegen.service;
import javax.servlet.http.HttpServletRequest;

public interface TestControllerService
{
     //逻辑均在子类中实现
    String test(HttpServletRequest request, String username);
}