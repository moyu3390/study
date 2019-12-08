package com.nijunyang.zookeeper;

/**
 * @author: create by nijunyang
 * @date:2019/9/29
 */
public interface TestInterface {

    Integer a = new Integer(0);


    static int fanc(){
        return 1;
    }

    default int fanc1(){
        return 1;
    }
}
