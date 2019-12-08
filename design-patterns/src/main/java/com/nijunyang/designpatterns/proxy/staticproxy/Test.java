package com.nijunyang.designpatterns.proxy.staticproxy;

/**
 * @author: create by nijunyang
 * @date:2019/10/5
 */
public class Test {
    public static void main(String[] args)
    {
        TeacherLi teacherLi = new TeacherLi();
        TeacherLiProxy teacherLiProxy = new TeacherLiProxy(teacherLi);
        teacherLiProxy.teach();
    }
}
