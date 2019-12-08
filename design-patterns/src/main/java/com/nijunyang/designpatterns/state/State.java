package com.nijunyang.designpatterns.state;

/**
 * @author: create by nijunyang
 * @date:2019/9/22
 */
public abstract class State {
    abstract void pay();
    abstract void checkPay();
    abstract void deliverGoods();
    abstract void toBeReceive();
    abstract void receive();
    abstract void other();
}
