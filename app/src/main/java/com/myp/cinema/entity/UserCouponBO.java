package com.myp.cinema.entity;

import java.io.Serializable;

/**
 * Created by Witness on 2019/1/7
 * Describe: 优惠券
 */
public class UserCouponBO implements Serializable {
    /**
     * createTime : 2019-01-03 10:56:34
     * deleteTime : null
     * dxAppUser : {"age":null,"birthday":null,"businessid":1,"city":"Hangzhou","collectNum":null,"commentNum":null,"country":null,"createTime":"2018-12-23 17:34:02","deleteTime":null,"dxInsiderInfo":null,"gender":1,"header":"https://wx.qlogo.cn/mmopen/vi_32/NW8prWtW0DjS165cHeHC4p0z4goupgMlMDg7V9vHXhgWNl5XAIWcvg99zFZo9EfnBW0ia99Rdf2jKD4BCl0JoNQ/132","id":1,"insider":null,"integrals":0,"lastsign":null,"loginDate":"2019-01-07 10:01:21","mobile":"13718939456","modifyTime":"2019-01-07 10:01:21","nickName":"楚河","password":null,"points":0,"province":"Zhejiang","qqUserId":null,"roll":2,"sign":null,"signdays":null,"uuid":"17e37218-a5e9-4ea9-8b8d-bba9e06a3f9d","valid":true,"version":56,"wantseeNum":null,"watchedNum":null,"wbUserId":null,"wxOpenId":"os7U34_NUeQ0dcAdkO_OcxCZHa48","wxUserId":null}
     * dxPlatTicket : {"achieveMoney":10,"amount":5,"applicationType":"","businessid":1,"createTime":"2019-01-02 14:12:18","deleteTime":null,"endTime":"2019-02-22 00:00:00","giftType":null,"groupNumber":6,"id":2,"modifyTime":"2019-01-04 17:08:12","name":"测试1","number":15,"platformType":1,"plusType":null,"reduceType":2,"remainNum":820,"sendNum":180,"startTime":"2019-01-03 00:00:00","status":1,"ticketDesc":"escasda","ticketType":1,"totalNum":1000,"valid":true,"version":15}
     * id : 2
     * modifyTime : null
     * status : 0
     * valid : true
     * version : 0
     */

    private String createTime;
    private String deleteTime;
    private DxAppUserBean dxAppUser;
    private DxPlatTicketBean dxPlatTicket;
    private int id;
    private String modifyTime;
    private int status;
    private boolean valid;
    private int version;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(String deleteTime) {
        this.deleteTime = deleteTime;
    }

    public DxAppUserBean getDxAppUser() {
        return dxAppUser;
    }

    public void setDxAppUser(DxAppUserBean dxAppUser) {
        this.dxAppUser = dxAppUser;
    }

    public DxPlatTicketBean getDxPlatTicket() {
        return dxPlatTicket;
    }

    public void setDxPlatTicket(DxPlatTicketBean dxPlatTicket) {
        this.dxPlatTicket = dxPlatTicket;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public static class DxAppUserBean implements Serializable{
        /**
         * age : null
         * birthday : null
         * businessid : 1
         * city : Hangzhou
         * collectNum : null
         * commentNum : null
         * country : null
         * createTime : 2018-12-23 17:34:02
         * deleteTime : null
         * dxInsiderInfo : null
         * gender : 1
         * header : https://wx.qlogo.cn/mmopen/vi_32/NW8prWtW0DjS165cHeHC4p0z4goupgMlMDg7V9vHXhgWNl5XAIWcvg99zFZo9EfnBW0ia99Rdf2jKD4BCl0JoNQ/132
         * id : 1
         * insider : null
         * integrals : 0
         * lastsign : null
         * loginDate : 2019-01-07 10:01:21
         * mobile : 13718939456
         * modifyTime : 2019-01-07 10:01:21
         * nickName : 楚河
         * password : null
         * points : 0
         * province : Zhejiang
         * qqUserId : null
         * roll : 2
         * sign : null
         * signdays : null
         * uuid : 17e37218-a5e9-4ea9-8b8d-bba9e06a3f9d
         * valid : true
         * version : 56
         * wantseeNum : null
         * watchedNum : null
         * wbUserId : null
         * wxOpenId : os7U34_NUeQ0dcAdkO_OcxCZHa48
         * wxUserId : null
         */

        private Object age;
        private Object birthday;
        private int businessid;
        private String city;
        private Object collectNum;
        private Object commentNum;
        private Object country;
        private String createTime;
        private Object deleteTime;
        private Object dxInsiderInfo;
        private int gender;
        private String header;
        private int id;
        private Object insider;
        private int integrals;
        private Object lastsign;
        private String loginDate;
        private String mobile;
        private String modifyTime;
        private String nickName;
        private Object password;
        private int points;
        private String province;
        private Object qqUserId;
        private int roll;
        private Object sign;
        private Object signdays;
        private String uuid;
        private boolean valid;
        private int version;
        private Object wantseeNum;
        private Object watchedNum;
        private Object wbUserId;
        private String wxOpenId;
        private Object wxUserId;

        public Object getAge() {
            return age;
        }

        public void setAge(Object age) {
            this.age = age;
        }

        public Object getBirthday() {
            return birthday;
        }

        public void setBirthday(Object birthday) {
            this.birthday = birthday;
        }

        public int getBusinessid() {
            return businessid;
        }

        public void setBusinessid(int businessid) {
            this.businessid = businessid;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public Object getCollectNum() {
            return collectNum;
        }

        public void setCollectNum(Object collectNum) {
            this.collectNum = collectNum;
        }

        public Object getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(Object commentNum) {
            this.commentNum = commentNum;
        }

        public Object getCountry() {
            return country;
        }

        public void setCountry(Object country) {
            this.country = country;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getDeleteTime() {
            return deleteTime;
        }

        public void setDeleteTime(Object deleteTime) {
            this.deleteTime = deleteTime;
        }

        public Object getDxInsiderInfo() {
            return dxInsiderInfo;
        }

        public void setDxInsiderInfo(Object dxInsiderInfo) {
            this.dxInsiderInfo = dxInsiderInfo;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getHeader() {
            return header;
        }

        public void setHeader(String header) {
            this.header = header;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getInsider() {
            return insider;
        }

        public void setInsider(Object insider) {
            this.insider = insider;
        }

        public int getIntegrals() {
            return integrals;
        }

        public void setIntegrals(int integrals) {
            this.integrals = integrals;
        }

        public Object getLastsign() {
            return lastsign;
        }

        public void setLastsign(Object lastsign) {
            this.lastsign = lastsign;
        }

        public String getLoginDate() {
            return loginDate;
        }

        public void setLoginDate(String loginDate) {
            this.loginDate = loginDate;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(String modifyTime) {
            this.modifyTime = modifyTime;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public Object getPassword() {
            return password;
        }

        public void setPassword(Object password) {
            this.password = password;
        }

        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public Object getQqUserId() {
            return qqUserId;
        }

        public void setQqUserId(Object qqUserId) {
            this.qqUserId = qqUserId;
        }

        public int getRoll() {
            return roll;
        }

        public void setRoll(int roll) {
            this.roll = roll;
        }

        public Object getSign() {
            return sign;
        }

        public void setSign(Object sign) {
            this.sign = sign;
        }

        public Object getSigndays() {
            return signdays;
        }

        public void setSigndays(Object signdays) {
            this.signdays = signdays;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public boolean isValid() {
            return valid;
        }

        public void setValid(boolean valid) {
            this.valid = valid;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public Object getWantseeNum() {
            return wantseeNum;
        }

        public void setWantseeNum(Object wantseeNum) {
            this.wantseeNum = wantseeNum;
        }

        public Object getWatchedNum() {
            return watchedNum;
        }

        public void setWatchedNum(Object watchedNum) {
            this.watchedNum = watchedNum;
        }

        public Object getWbUserId() {
            return wbUserId;
        }

        public void setWbUserId(Object wbUserId) {
            this.wbUserId = wbUserId;
        }

        public String getWxOpenId() {
            return wxOpenId;
        }

        public void setWxOpenId(String wxOpenId) {
            this.wxOpenId = wxOpenId;
        }

        public Object getWxUserId() {
            return wxUserId;
        }

        public void setWxUserId(Object wxUserId) {
            this.wxUserId = wxUserId;
        }
    }

    public static class DxPlatTicketBean implements Serializable{
        /**
         * achieveMoney : 10
         * amount : 5
         * applicationType :
         * businessid : 1
         * createTime : 2019-01-02 14:12:18
         * deleteTime : null
         * endTime : 2019-02-22 00:00:00
         * giftType : null
         * groupNumber : 6
         * id : 2
         * modifyTime : 2019-01-04 17:08:12
         * name : 测试1
         * number : 15
         * platformType : 1
         * plusType : null
         * reduceType : 2
         * remainNum : 820
         * sendNum : 180
         * startTime : 2019-01-03 00:00:00
         * status : 1
         * ticketDesc : escasda
         * ticketType : 1
         * totalNum : 1000
         * valid : true
         * version : 15
         */

        private int achieveMoney;
        private int amount;
        private String applicationType;
        private int businessid;
        private String createTime;
        private String deleteTime;
        private String endTime;
        private Object giftType;
        private int groupNumber;
        private int id;
        private String modifyTime;
        private String name;
        private int number;
        private int platformType;
        private Object plusType;
        private int reduceType;
        private int remainNum;
        private int sendNum;
        private String startTime;
        private int status;
        private String ticketDesc;
        private int ticketType;
        private int totalNum;
        private boolean valid;
        private int version;
        private double fixedPayMoney;

        public double getFixedPayMoney() {
            return fixedPayMoney;
        }

        public void setFixedPayMoney(double fixedPayMoney) {
            this.fixedPayMoney = fixedPayMoney;
        }

        public int getAchieveMoney() {
            return achieveMoney;
        }

        public void setAchieveMoney(int achieveMoney) {
            this.achieveMoney = achieveMoney;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getApplicationType() {
            return applicationType;
        }

        public void setApplicationType(String applicationType) {
            this.applicationType = applicationType;
        }

        public int getBusinessid() {
            return businessid;
        }

        public void setBusinessid(int businessid) {
            this.businessid = businessid;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDeleteTime() {
            return deleteTime;
        }

        public void setDeleteTime(String deleteTime) {
            this.deleteTime = deleteTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public Object getGiftType() {
            return giftType;
        }

        public void setGiftType(Object giftType) {
            this.giftType = giftType;
        }

        public int getGroupNumber() {
            return groupNumber;
        }

        public void setGroupNumber(int groupNumber) {
            this.groupNumber = groupNumber;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(String modifyTime) {
            this.modifyTime = modifyTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getPlatformType() {
            return platformType;
        }

        public void setPlatformType(int platformType) {
            this.platformType = platformType;
        }

        public Object getPlusType() {
            return plusType;
        }

        public void setPlusType(Object plusType) {
            this.plusType = plusType;
        }

        public int getReduceType() {
            return reduceType;
        }

        public void setReduceType(int reduceType) {
            this.reduceType = reduceType;
        }

        public int getRemainNum() {
            return remainNum;
        }

        public void setRemainNum(int remainNum) {
            this.remainNum = remainNum;
        }

        public int getSendNum() {
            return sendNum;
        }

        public void setSendNum(int sendNum) {
            this.sendNum = sendNum;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTicketDesc() {
            return ticketDesc;
        }

        public void setTicketDesc(String ticketDesc) {
            this.ticketDesc = ticketDesc;
        }

        public int getTicketType() {
            return ticketType;
        }

        public void setTicketType(int ticketType) {
            this.ticketType = ticketType;
        }

        public int getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(int totalNum) {
            this.totalNum = totalNum;
        }

        public boolean isValid() {
            return valid;
        }

        public void setValid(boolean valid) {
            this.valid = valid;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }
    }
}
