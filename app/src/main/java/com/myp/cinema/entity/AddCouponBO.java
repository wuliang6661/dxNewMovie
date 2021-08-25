package com.myp.cinema.entity;

/**
 * Created by Witness on 2019/1/14
 * Describe:
 */
public class AddCouponBO {
    /**
     * createTime : 2019-01-14 19:46:35
     * deleteTime : null
     * dxAppUser : {"age":null,"birthday":null,"businessid":null,"city":null,"collectNum":null,"commentNum":null,"counterfoil":0,"country":null,"createTime":"2019-01-14 19:44:07","deleteTime":null,"dxInsiderInfo":null,"gender":0,"header":"","id":15,"insider":null,"integrals":0,"lastsign":null,"loginDate":null,"mobile":"17376551017","modifyTime":"2019-01-14 19:44:41","nickName":"17376551017","password":null,"points":null,"province":null,"qqUserId":null,"roll":1,"sign":null,"signdays":null,"uuid":"4014e6a1-2c8a-4d61-af0a-0b1bad80a262","valid":true,"version":1,"wantseeNum":null,"watchedNum":null,"wbUserId":null,"wxOpenId":null,"wxUserId":null}
     * dxPlatTicket : {"achieveMoney":null,"amount":null,"applicationType":null,"businessid":1,"createTime":"2019-01-14 19:46:35","deleteTime":null,"endTime":"2019-12-31 00:00:00","fixedPayMoney":null,"giftType":null,"groupNumber":null,"id":17,"modifyTime":null,"name":"优惠10元","number":null,"platformType":2,"plusType":0,"reduceType":2,"remainNum":null,"sendNum":0,"startTime":"2019-01-01 00:00:00","status":1,"ticketDesc":null,"ticketNumber":"034956177636","ticketType":1,"totalNum":null,"valid":true,"version":0}
     * endTime : 2019-12-31 00:00:00
     * id : 19
     * modifyTime : null
     * startTime : 2019-01-01 00:00:00
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
         * birthday : null
         * businessid : null
         * city : null
         * collectNum : null
         * commentNum : null
         * counterfoil : 0
         * country : null
         * createTime : 2019-01-14 19:44:07
         * deleteTime : null
         * dxInsiderInfo : null
         * gender : 0
         * header :
         * id : 15
         * insider : null
         * integrals : 0
         * lastsign : null
         * loginDate : null
         * mobile : 17376551017
         * modifyTime : 2019-01-14 19:44:41
         * nickName : 17376551017
         * password : null
         * points : null
         * province : null
         * qqUserId : null
         * roll : 1
         * sign : null
         * signdays : null
         * uuid : 4014e6a1-2c8a-4d61-af0a-0b1bad80a262
         * valid : true
         * version : 1
         * wantseeNum : null
         * watchedNum : null
         * wbUserId : null
         * wxOpenId : null
         * wxUserId : null
         */

        private Object age;
        private Object birthday;
        private Object businessid;
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
        private Object lastsign;
        private Object loginDate;
        private String mobile;
        private String modifyTime;
        private String nickName;
        private Object password;
        private Object points;
        private Object province;
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
        private Object wxOpenId;
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

        public Object getBusinessid() {
            return businessid;
        }

        public void setBusinessid(Object businessid) {
            this.businessid = businessid;
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

        public Object getLastsign() {
            return lastsign;
        }

        public void setLastsign(Object lastsign) {
            this.lastsign = lastsign;
        }

        public Object getLoginDate() {
            return loginDate;
        }

        public void setLoginDate(Object loginDate) {
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

        public Object getPoints() {
            return points;
        }

        public void setPoints(Object points) {
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

        public Object getWxOpenId() {
            return wxOpenId;
        }

        public void setWxOpenId(Object wxOpenId) {
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
         * achieveMoney : null
         * amount : null
         * applicationType : null
         * businessid : 1
         * createTime : 2019-01-14 19:46:35
         * deleteTime : null
         * endTime : 2019-12-31 00:00:00
         * fixedPayMoney : null
         * giftType : null
         * groupNumber : null
         * id : 17
         * modifyTime : null
         * name : 优惠10元
         * number : null
         * platformType : 2
         * plusType : 0
         * reduceType : 2
         * remainNum : null
         * sendNum : 0
         * startTime : 2019-01-01 00:00:00
         * status : 1
         * ticketDesc : null
         * ticketNumber : 034956177636
         * ticketType : 1
         * totalNum : null
         * valid : true
         * version : 0
         */

        private Object achieveMoney;
        private Object amount;
        private Object applicationType;
        private int businessid;
        private String createTime;
        private Object deleteTime;
        private String endTime;
        private Object fixedPayMoney;
        private Object giftType;
        private Object groupNumber;
        private int id;
        private Object modifyTime;
        private String name;
        private Object number;
        private int platformType;
        private int plusType;
        private int reduceType;
        private Object remainNum;
        private int sendNum;
        private String startTime;
        private int status;
        private Object ticketDesc;
        private String ticketNumber;
        private int ticketType;
        private Object totalNum;
        private boolean valid;
        private int version;

        public Object getAchieveMoney() {
            return achieveMoney;
        }

        public void setAchieveMoney(Object achieveMoney) {
            this.achieveMoney = achieveMoney;
        }

        public Object getAmount() {
            return amount;
        }

        public void setAmount(Object amount) {
            this.amount = amount;
        }

        public Object getApplicationType() {
            return applicationType;
        }

        public void setApplicationType(Object applicationType) {
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

        public Object getDeleteTime() {
            return deleteTime;
        }

        public void setDeleteTime(Object deleteTime) {
            this.deleteTime = deleteTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public Object getFixedPayMoney() {
            return fixedPayMoney;
        }

        public void setFixedPayMoney(Object fixedPayMoney) {
            this.fixedPayMoney = fixedPayMoney;
        }

        public Object getGiftType() {
            return giftType;
        }

        public void setGiftType(Object giftType) {
            this.giftType = giftType;
        }

        public Object getGroupNumber() {
            return groupNumber;
        }

        public void setGroupNumber(Object groupNumber) {
            this.groupNumber = groupNumber;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getNumber() {
            return number;
        }

        public void setNumber(Object number) {
            this.number = number;
        }

        public int getPlatformType() {
            return platformType;
        }

        public void setPlatformType(int platformType) {
            this.platformType = platformType;
        }

        public int getPlusType() {
            return plusType;
        }

        public void setPlusType(int plusType) {
            this.plusType = plusType;
        }

        public int getReduceType() {
            return reduceType;
        }

        public void setReduceType(int reduceType) {
            this.reduceType = reduceType;
        }

        public Object getRemainNum() {
            return remainNum;
        }

        public void setRemainNum(Object remainNum) {
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

        public Object getTicketDesc() {
            return ticketDesc;
        }

        public void setTicketDesc(Object ticketDesc) {
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

        public Object getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(Object totalNum) {
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
