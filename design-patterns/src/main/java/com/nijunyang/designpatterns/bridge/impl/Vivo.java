package com.nijunyang.designpatterns.bridge.impl;

import com.nijunyang.designpatterns.bridge.api.Brand;

/**
 * @author: create by nijunyang
 * @date:2019/9/22
 */
public class Vivo implements Brand {
    @Override
    public void call() {
        System.out.println("Vivo手机打电话");
    }

    @Override
    public void close() {
        System.out.println("Vivo手机关机");
    }
}
