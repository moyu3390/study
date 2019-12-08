package com.nijunyang.designpatterns.state;

/**
 * @author: create by nijunyang
 * @date:2019/9/22
 */
public class PaidState extends State {
    private OrderContext orderContext;

    public PaidState(OrderContext orderContext) {
        this.orderContext = orderContext;
    }

    @Override
    void pay() {
        System.out.println("已支付");
    }

    @Override
    void checkPay() {
        System.out.println("确认支付成功...");
        orderContext.setState(orderContext.getDeliverGoodsState());
    }

    @Override
    void deliverGoods() {
        System.out.println("准备发货");
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
