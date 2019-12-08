package com.nijunyang.designpatterns.bridge.impl;

import com.nijunyang.designpatterns.bridge.api.Brand;

/**
 * @author: create by nijunyang
 * @date:2019/9/22
 */
public class HuaWei implements Brand {
    @Override
    public void call() {
        System.out.println("华为手机打电话");
    }

    @Override
    public void close() {
        System.out.println("华为手机关机");
    }
}
