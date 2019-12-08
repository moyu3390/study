package com.nijunyang.designpatterns.state;

/**
 * @author: create by nijunyang
 * @date:2019/9/22
 */
public class SuccessState extends State {
    private OrderContext orderContext;

    public SuccessState(OrderContext orderContext) {
        this.orderContext = orderContext;
    }

    @Override
    void pay() {
        System.out.println("已支付");
    }

    @Override
    void checkPay() {

    }

    @Override
    void deliverGoods() {
        System.out.println("已收货");
    }

    @Override
    void toBeReceive() {

    }

    @Override
    void receive() {

    }

    @Override
    void other() {
        System.out.println("用户积分增加...");
    }
}
