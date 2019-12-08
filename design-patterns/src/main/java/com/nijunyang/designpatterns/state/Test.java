package com.nijunyang.designpatterns.state;

/**
 * @author: create by nijunyang
 * @date:2019/9/22
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {

        for(int i = 0; i < 5; i++) {
            OrderContext orderContext = new OrderContext("user" + i);
            orderContext.toPay();
            orderContext.checkPay();
            orderContext.deliverGoods();
            orderContext.toBeReceive();
            orderContext.receive();
            orderContext.end();
            System.out.println("---------");
            Thread.sleep(1000);
        }

    }

}
