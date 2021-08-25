package com.myp.cinema.entity;

import java.io.Serializable;

/**
 * Created by Witness on 2019/1/20
 * Describe:
 */
public class ConfirmPayBO implements Serializable {
    /**
     * orderNum : 6e5a2bb2e61f4720190120175247
     * orderExpireSecond : 1547978687
     */

    private String orderNum;
    private long orderExpireSecond;

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
