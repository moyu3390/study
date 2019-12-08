package com.nijunyang.designpatterns.state;

/**
 * @author: create by nijunyang
 * @date:2019/9/22
 */
public class DeliverGoodsState extends State {
    private OrderContext orderContext;

    public DeliverGoodsState(OrderContext orderContext) {
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
        System.out.println("物流发货...");
        orderContext.setState(orderContext.getGoodsReceivedState());
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
