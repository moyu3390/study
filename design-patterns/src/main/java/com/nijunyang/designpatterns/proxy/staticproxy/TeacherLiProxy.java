package com.nijunyang.designpatterns.proxy.staticproxy;

/**
 * @author: create by nijunyang
 * @date:2019/10/5
 */
public class TeacherLiProxy implements Teacher{
    //聚合代理的目标对象
    private Teacher target;

    public TeacherLiProxy(Teacher target) {
        this.target = target;
    }

    @Override
    public void teach() {
        System.out.println("代课开始..");
        target.teach();
        System.out.println("代课结束..");
    }
}
