package com.nijunyang.designpatterns.bridge.phone;

import com.nijunyang.designpatterns.bridge.api.Brand;

/**
 * @author: create by nijunyang
 * @date:2019/9/22
 */
public class WaterDropScreePhone extends Phone {

    public WaterDropScreePhone(Brand brand) {
        super(brand);
    }

    @Override
    public void call() {
        System.out.print("这是水滴屏");
        super.call();
    }

    @Override
    public void close() {
        System.out.print("这是水滴屏");
        super.close();
    }
}
