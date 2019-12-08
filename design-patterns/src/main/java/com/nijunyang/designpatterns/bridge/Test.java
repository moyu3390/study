package com.nijunyang.designpatterns.bridge;

import com.nijunyang.designpatterns.bridge.impl.HuaWei;
import com.nijunyang.designpatterns.bridge.phone.Phone;
import com.nijunyang.designpatterns.bridge.phone.WaterDropScreePhone;

/**
 * @author: create by nijunyang
 * @date:2019/9/22
 */
public class Test {

    public static void main(String[] args)
    {
        Phone phone = new WaterDropScreePhone(new HuaWei());
        phone.call();
    }
}
