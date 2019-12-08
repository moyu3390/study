package com.nijunyang.designpatterns.state;

/**
 * @author: create by nijunyang
 * @date:2019/9/22
 */
public class ToBePayState extends State {
    private OrderContext orderContext;

    public ToBePayState(OrderContext orderContext) {
        this.orderContext = orderContext;
    }

    @Override
    void pay() {
        System.out.println("用户已支付...");
        orderContext.setState(orderContext.getPaidState());
    }

    @Override
    void checkPay() {

    }

    @Override
    void deliverGoods() {
        System.out.println("未付款，不能发货");
    }

    @Override
    void toBeReceive() {

    }

    @Override
    void receive() {

    }

    @Override
    void other() {

    }
}
