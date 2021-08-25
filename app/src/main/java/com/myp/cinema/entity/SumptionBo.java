package com.myp.cinema.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/29.
 */
public class SumptionBo implements Serializable {
    private  Long id;
    private  String   payDate;

    private String orderType;

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public Integer getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(Integer ticketNum) {
        this.ticketNum = ticketNum;
    }

    public double getTicketRealPrice() {
        return ticketRealPrice;
    }

    public void setTicketRealPrice(double ticketRealPrice) {
        this.ticketRealPrice = ticketRealPrice;
    }

    private Integer ticketNum;
    private double ticketRealPrice;

}
