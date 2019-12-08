package com.nijunyang.designpatterns.proxy.cglibproxy;

/**
 * @author: create by nijunyang
 * @date:2019/10/5
 */
public class Test {
    public static void main(String[] args)
    {
        TeacherLi teacherLi = new TeacherLi();
        ProxyFactory proxyFactory = new ProxyFactory(teacherLi);
        TeacherLi proxy = (TeacherLi) proxyFactory.getProxyInstance();
        proxy.teach();
    }
}
