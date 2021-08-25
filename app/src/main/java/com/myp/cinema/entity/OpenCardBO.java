package com.myp.cinema.entity;

/**
 * Created by Witness on 2019/1/28
 * Describe:
 */
public class OpenCardBO {
    /**
     * initMoney : 1000
     * levelId : 96
     * levelName : 德信JT会员卡
     * sendCardEquityFee : 0
     * sendCardFee : 1
     */

    private String initMoney;
    private String levelId;
    private String levelName;
    private String sendCardEquityFee;
    private String sendCardFee;
    private String openCardNotice;


    public String getInitMoney() {
        return initMoney;
    }

    public void setInitMoney(String initMoney) {
        this.initMoney = initMoney;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getSendCardEquityFee() {
        return sendCardEquityFee;
    }

    public void setSendCardEquityFee(String sendCardEquityFee) {
        this.sendCardEquityFee = sendCardEquityFee;
    }

    public String getSendCardFee() {
        return sendCardFee;
    }

    public void setSendCardFee(String sendCardFee) {
        this.sendCardFee = sendCardFee;
    }

    public String getOpenCardNotice() {
        return openCardNotice;
    }

    public void setOpenCardNotice(String openCardNotice) {
        this.openCardNotice = openCardNotice;
    }
}
