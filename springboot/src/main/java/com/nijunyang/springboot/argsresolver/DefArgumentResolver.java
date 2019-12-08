package com.nijunyang.springboot.argsresolver;

import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: create by nijunyang
 * @date:2019/10/30
 */
public class DefArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        //方法参数是User 则使用此解析器
        return User.class.isAssignableFrom(methodParameter.getParameterType());
    }

    @Nullable
    @Override
    public Object resolveArgument(
            MethodParameter methodParameter,
            @Nullable ModelAndViewContainer modelAndViewContainer,
            NativeWebRequest nativeWebRequest,
            @Nullable WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest servletRequest = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        String info = (String) nativeWebRequest.getAttribute("params", NativeWebRequest.SCOPE_REQUEST);
        //获取消息头认证信息
        String token = servletRequest.getHeader("Authorization");
        //自己塞数据进去，也可以从配置文件获取数据
        return new User("zhangsan",18);


    }
}
