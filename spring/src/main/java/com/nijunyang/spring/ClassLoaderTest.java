package com.nijunyang.spring;


/**
 * @author: create by nijunyang
 * @date:2019/6/16
 */
public class ClassLoaderTest {
    static {
        System.out.println("静态块执行");
    }
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(3000);
        System.out.println(System.currentTimeMillis());
        new A();
        System.out.println("A对象创建完毕");
        new B();
    }
}
