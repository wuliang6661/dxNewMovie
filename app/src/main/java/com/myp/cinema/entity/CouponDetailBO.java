package com.myp.cinema.entity;

/**
 * Created by Witness on 2019/1/28
 * Describe:
 */
public class CouponDetailBO {
    /**
     * createTime : 2019-01-25 09:46:37
     * deleteTime : null
     * dxAppUser : {"age":null,"alertPhoto":null,"birthday":null,"businessid":1,"cinemaCode":"33018361","city":null,"collectNum":null,"commentNum":null,"counterfoil":0,"country":null,"createTime":"2019-01-25 09:46:37","deleteTime":null,"dxInsiderInfo":null,"gender":0,"header":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKCqdGX2QictBoIibCKKv2ZjVCdl3TwEdIltibEzRGlPoQVr2icdOJ3lfdqibNTBrKV0XxWGWmH7cqp1jg/132","id":407,"insider":null,"integrals":0,"lastsign":"2019-01-25 11:05:43","loginDate":"2019-01-28 17:14:30","mobile":"17376551017","modifyTime":"2019-01-28 17:14:30","nickName":"17376551017","password":null,"points":2,"province":null,"qqUserId":null,"roll":1,"sign":null,"signdays":1,"uuid":"71a27072-27b3-46dc-86cb-89267c56157d","valid":true,"version":30,"wantseeNum":null,"watchedNum":null,"wbUserId":null,"wxOpenId":"os7U3435M8DVP3635Ob66dJZwiGg","wxUserId":null}
     * dxPlatTicket : {"achieveMoney":50,"amount":10,"applicationType":"","businessid":1,"createTime":"2019-01-24 22:47:26","deleteTime":null,"endTime":"2019-05-31 00:00:00","exceptDate":"2019-02-04,2019-02-05,2019-02-06,2019-02-07,2019-02-08,2019-02-09,2019-02-10,2019-02-14","fixedPayMoney":null,"giftType":null,"groupNumber":0,"id":37,"modifyTime":"2019-01-28 17:15:38","name":"10元卖品抵价券","number":0,"platformType":1,"plusType":null,"reduceType":2,"remainNum":102051,"sendNum":17949,"startTime":"2019-01-24 00:00:00","status":1,"ticketDesc":"凭此券购买卖品满50元即可抵用10元，不得叠加使用，情人节和春节谢绝使用。","ticketNumber":null,"ticketType":2,"totalNum":120000,"valid":true,"version":17949}
     * endTime : 2019-03-26 00:00:00
     * id : 2947
     * modifyTime : null
     * startTime : 2019-01-25 00:00:00
     * status : 0
     * valid : true
     * version : 0
     */

    private String createTime;
    private Object deleteTime;
    private DxAppUserBean dxAppUser;
    private DxPlatTicketBean dxPlatTicket;
    private String endTime;
    private int id;
    private Object modifyTime;
    private String startTime;
    private int status;
    private boolean valid;
    private int version;

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

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Object modifyTime) {
        this.modifyTime = modifyTime;
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

    public static class DxAppUserBean {
        /**
         * age : null
         * alertPhoto : null
         * birthday : null
         * businessid : 1
         * cinemaCode : 33018361
         * city : null
         * collectNum : null
         * commentNum : null
         * counterfoil : 0
         * country : null
         * createTime : 2019-01-25 09:46:37
         * deleteTime : null
         * dxInsiderInfo : null
         * gender : 0
         * header : https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKCqdGX2QictBoIibCKKv2ZjVCdl3TwEdIltibEzRGlPoQVr2icdOJ3lfdqibNTBrKV0XxWGWmH7cqp1jg/132
         * id : 407
         * insider : null
         * integrals : 0
         * lastsign : 2019-01-25 11:05:43
         * loginDate : 2019-01-28 17:14:30
         * mobile : 17376551017
         * modifyTime : 2019-01-28 17:14:30
         * nickName : 17376551017
         * password : null
         * points : 2
         * province : null
         * qqUserId : null
         * roll : 1
         * sign : null
         * signdays : 1
         * uuid : 71a27072-27b3-46dc-86cb-89267c56157d
         * valid : true
         * version : 30
         * wantseeNum : null
         * watchedNum : null
         * wbUserId : null
         * wxOpenId : os7U3435M8DVP3635Ob66dJZwiGg
         * wxUserId : null
         */

        private Object age;
        private Object alertPhoto;
        private Object birthday;
        private int businessid;
        private String cinemaCode;
        private Object city;
        private Object collectNum;
        private Object commentNum;
        private int counterfoil;
        private Object country;
        private String createTime;
        private Object deleteTime;
        private Object dxInsiderInfo;
        private int gender;
        private String header;
        private int id;
        private Object insider;
        private int integrals;
        private String lastsign;
        private String loginDate;
        private String mobile;
        private String modifyTime;
        private String nickName;
        private Object password;
        private int points;
        private Object province;
        private Object qqUserId;
        private int roll;
        private Object sign;
        private int signdays;
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

        public Object getAlertPhoto() {
            return alertPhoto;
        }

        public void setAlertPhoto(Object alertPhoto) {
            this.alertPhoto = alertPhoto;
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

        public String getCinemaCode() {
            return cinemaCode;
        }

        public void setCinemaCode(String cinemaCode) {
            this.cinemaCode = cinemaCode;
        }

        public Object getCity() {
            return city;
        }

        public void setCity(Object city) {
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

        public int getCounterfoil() {
            return counterfoil;
        }

        public void setCounterfoil(int counterfoil) {
            this.counterfoil = counterfoil;
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

        public String getLastsign() {
            return lastsign;
        }

        public void setLastsign(String lastsign) {
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

        public Object getProvince() {
            return province;
        }

        public void setProvince(Object province) {
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

        public int getSigndays() {
            return signdays;
        }

        public void setSigndays(int signdays) {
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

    public static class DxPlatTicketBean {
        /**
         * achieveMoney : 50
         * amount : 10
         * applicationType :
         * businessid : 1
         * createTime : 2019-01-24 22:47:26
         * deleteTime : null
         * endTime : 2019-05-31 00:00:00
         * exceptDate : 2019-02-04,2019-02-05,2019-02-06,2019-02-07,2019-02-08,2019-02-09,2019-02-10,2019-02-14
         * fixedPayMoney : null
         * giftType : null
         * groupNumber : 0
         * id : 37
         * modifyTime : 2019-01-28 17:15:38
         * name : 10元卖品抵价券
         * number : 0
         * platformType : 1
         * plusType : null
         * reduceType : 2
         * remainNum : 102051
         * sendNum : 17949
         * startTime : 2019-01-24 00:00:00
         * status : 1
         * ticketDesc : 凭此券购买卖品满50元即可抵用10元，不得叠加使用，情人节和春节谢绝使用。
         * ticketNumber : null
         * ticketType : 2
         * totalNum : 120000
         * valid : true
         * version : 17949
         */

        private int achieveMoney;
        private int amount;
        private String applicationType;
        private int businessid;
        private String createTime;
        private String deleteTime;
        private String endTime;
        private String exceptDate;
        private String fixedPayMoney;
        private String giftType;
        private int groupNumber;
        private int id;
        private String modifyTime;
        private String name;
        private int number;
        private int platformType;
        private String plusType;
        private int reduceType;
        private int remainNum;
        private int sendNum;
        private String startTime;
        private int status;
        private String ticketDesc;
        private String ticketNumber;
        private int ticketType;
        private int totalNum;
        private boolean valid;
        private int version;

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

        public String getExceptDate() {
            return exceptDate;
        }

        public void setExceptDate(String exceptDate) {
            this.exceptDate = exceptDate;
        }

        public String getFixedPayMoney() {
            return fixedPayMoney;
        }

        public void setFixedPayMoney(String fixedPayMoney) {
            this.fixedPayMoney = fixedPayMoney;
        }

        public String getGiftType() {
            return giftType;
        }

        public void setGiftType(String giftType) {
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

        public String getPlusType() {
            return plusType;
        }

        public void setPlusType(String plusType) {
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

        public String getTicketNumber() {
            return ticketNumber;
        }

        public void setTicketNumber(String ticketNumber) {
            this.ticketNumber = ticketNumber;
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
