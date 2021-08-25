package com.myp.cinema.entity;

/**
 * Created by Witness on 2/3/21
 * Describe:
 */
public class CouponDataBean {

    private int id;
    private String number;

    public CouponDataBean(int id, String number) {
        this.id = id;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
