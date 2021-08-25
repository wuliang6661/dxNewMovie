package com.myp.cinema.entity;

import java.io.Serializable;

/**
 * Created by wuliang on 2017/6/20.
 * 
 * 会员卡bean
 */

public class CardBO implements Serializable{


    /**
     * availableJifen : 0
     * balance : 1000
     * cardLevel : 白金卡
     * cardNumber : 6851002
     * cardStatus : 1
     * cardType : 折扣卡
     * period : 2018-06-12 00:00:00
     * username : 德信
     */

    private String availableJifen;
    private String balance;
    private String cardLevel;
    private String cardNumber;
    private String cardStatus;
    private String cardType;
    private String period;
    private String username;
    private String id;

    public String getAvailableJifen() {
        return availableJifen;
    }

    public void setAvailableJifen(String availableJifen) {
        this.availableJifen = availableJifen;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getCardLevel() {
        return cardLevel;
    }

    public void setCardLevel(String cardLevel) {
        this.cardLevel = cardLevel;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
