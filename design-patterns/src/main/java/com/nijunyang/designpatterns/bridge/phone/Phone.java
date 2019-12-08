package com.nijunyang.designpatterns.bridge.phone;

import com.nijunyang.designpatterns.bridge.api.Brand;

/**
 * @author: create by nijunyang
 * @date:2019/9/22
 */
public abstract class Phone {
    private Brand brand;

    public Phone(Brand brand) {
        this.brand = brand;
    }


    public void call() {
        brand.call();
    }


    public void close() {
        brand.close();
    }
}
