package com.nijunyang.algorithm.util;

/**
 * Description: 引用包装，用于去一个方法里面除开返回值之外，将其他额外需要的数据带出来
 * Created by nijunyang on 2020/4/20 10:26
 */
public class RefObject<E> {

    public RefObject() {
    }

    public RefObject(E value) {
        this.value = value;
    }


    private E value;

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }
}
