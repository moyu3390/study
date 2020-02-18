package com.nijunyang.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;

/**
 * Description:
 * Created by nijunyang on 2020/2/18 21:02
 */
public class MyFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
//        pre：在请求被路由之前调用
//        route：在路由请求时候被调用
//        post：在route和error过滤器之后被调用
//        error：处理请求时发生错误时被调用
    }

    @Override
    public int filterOrder() {
        return 0;
        //一个请求多个filter 先后执行顺序
    }

    @Override
    public boolean shouldFilter() {
        return false;
        //是否执行该过滤器
    }

    @Override
    public Object run() throws ZuulException {
        //过滤逻辑
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String username = request.getParameter("username");// 获取请求的参数

        if (null != username) {// 如果请求的参数不为空，
            ctx.setSendZuulResponse(true);// 对该请求进行路由
            ctx.setResponseStatusCode(200);
            ctx.set("isSuccess", true);// 设值，让下一个Filter看到上一个Filter的状态
        } else{
            ctx.setSendZuulResponse(false);// 过滤该请求，不对其进行路由
            ctx.setResponseStatusCode(401);// 返回错误码
            ctx.setResponseBody("{\"result\":\"username is not correct!\"}");// 返回错误内容
            ctx.set("isSuccess", false);
        }
        return null;
    }
}
