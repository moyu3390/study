package com.nijunyang.designpatterns.state;

/**
 * @author: create by nijunyang
 * @date:2019/9/22
 */
public class OrderContext {
    private State state;
    private ToBePayState toBePayState = new ToBePayState(this);
    private PaidState paidState = new PaidState(this);
    private DeliverGoodsState deliverGoodsState = new DeliverGoodsState(this);
    private GoodsToBeReceivedState goodsToBeReceivedState = new GoodsToBeReceivedState(this);
    private GoodsReceivedState goodsReceivedState = new GoodsReceivedState(this);
    private SuccessState successState = new SuccessState(this);



    //初始化订单的时候状态设置待付款
    public OrderContext(String userName) {
        System.out.println("用户 " + userName +" 下单");
        this.state = toBePayState;
    }

    public void toPay(){
        toBePayState.pay();
    }

    public void checkPay(){
        paidState.checkPay();
    }

    public void deliverGoods(){
        deliverGoodsState.deliverGoods();
    }

    public void toBeReceive(){
        goodsToBeReceivedState.toBeReceive();
    }

    public void receive(){
        goodsReceivedState.receive();
    }

    public void end(){
        successState.other();
    }


    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public ToBePayState getToBePayState() {
        return toBePayState;
    }

    public void setToBePayState(ToBePayState toBePayState) {
        this.toBePayState = toBePayState;
    }

    public PaidState getPaidState() {
        return paidState;
    }

    public void setPaidState(PaidState paidState) {
        this.paidState = paidState;
    }

    public DeliverGoodsState getDeliverGoodsState() {
        return deliverGoodsState;
    }

    public void setDeliverGoodsState(DeliverGoodsState deliverGoodsState) {
        this.deliverGoodsState = deliverGoodsState;
    }

    public SuccessState getSuccessState() {
        return successState;
    }

    public void setSuccessState(SuccessState successState) {
        this.successState = successState;
    }

    public GoodsToBeReceivedState getGoodsToBeReceivedState() {
        return goodsToBeReceivedState;
    }

    public void setGoodsToBeReceivedState(GoodsToBeReceivedState goodsToBeReceivedState) {
        this.goodsToBeReceivedState = goodsToBeReceivedState;
    }

    public GoodsReceivedState getGoodsReceivedState() {
        return goodsReceivedState;
    }

    public void setGoodsReceivedState(GoodsReceivedState goodsReceivedState) {
        this.goodsReceivedState = goodsReceivedState;
    }
}
