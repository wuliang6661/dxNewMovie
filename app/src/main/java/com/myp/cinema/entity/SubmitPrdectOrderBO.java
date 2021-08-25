package com.myp.cinema.entity;

import java.io.Serializable;

/**
 * Created by Witness on 2019/1/10
 * Describe:
 */
public class SubmitPrdectOrderBO implements Serializable {

    /**
     * payPrice : 44.0
     * orderNum : ad41147e251b4320190121194831
     * orderExpireSecond : 1548072031
     */

    private double payPrice;
    private String orderNum;
    private long orderExpireSecond;

    public double getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(double payPrice) {
        this.payPrice = payPrice;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public long getOrderExpireSecond() {
        return orderExpireSecond;
    }

    public void setOrderExpireSecond(long orderExpireSecond) {
        this.orderExpireSecond = orderExpireSecond;
    }
}
