package com.nijunyang.algorithm.util;

/**
 * Description: 引用对象
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
