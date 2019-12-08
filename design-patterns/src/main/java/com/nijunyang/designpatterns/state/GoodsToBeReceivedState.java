package com.nijunyang.designpatterns.state;

/**
 * @author: create by nijunyang
 * @date:2019/9/22
 */
public class GoodsToBeReceivedState extends State {
    private OrderContext orderContext;

    public GoodsToBeReceivedState(OrderContext orderContext) {
        this.orderContext = orderContext;
    }

    @Override
    void pay() {

    }

    @Override
    void checkPay() {

    }

    @Override
    void deliverGoods() {

    }

    @Override
    void toBeReceive() {
        System.out.println("运输中...");
        orderContext.setState(orderContext.getGoodsReceivedState());
    }

    @Override
    void receive() {

    }

    @Override
    void other() {

    }
}
