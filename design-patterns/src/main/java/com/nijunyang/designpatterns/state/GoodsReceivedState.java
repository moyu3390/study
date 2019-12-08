package com.nijunyang.designpatterns.state;

/**
 * @author: create by nijunyang
 * @date:2019/9/22
 */
public class GoodsReceivedState extends State {
    private OrderContext orderContext;

    public GoodsReceivedState(OrderContext orderContext) {
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

    }

    @Override
    void receive() {
        System.out.println("签收货物...");
        orderContext.setState(orderContext.getSuccessState());
    }

    @Override
    void other() {

    }
}
