package com.myp.cinema.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Witness on 2019/1/9
 * Describe: 卖品
 */
public class ProdectBO implements Serializable {


    /**
     * address : null
     * beforeActivityPrice : 61.0
     * beforeTicketPrice : 66.0
     * canRefund : null
     * coupon : null
     * createTime : 2019-01-21 17:53:09
     * deleteTime : null
     * deliveryType : null
     * details : [{"createTime":"2019-01-21 17:53:09","deleteTime":null,"id":null,"itemCombo":null,"merchandise":{"businessId":1,"cardDiscountType":2,"cinemaId":31,"counterPrice":34,"createTime":"2019-01-16 16:44:06","deleteTime":null,"dxId":1,"goodsNum":null,"id":1,"imageUrl":"https://hyg.happydoit.com/upload/201901/18KOfSXu.jpg","inventory":159,"itemClassId":"1","listOrder":0,"modifyTime":"2019-01-18 13:42:52","name":"常规套餐","number":null,"onlinePrice":22,"originalPrice":50,"overInventorySellFlag":0,"packageDetail":[],"type":"package","unit":"","updateTime":"2018-12-29 17:32:54","valid":true,"version":2},"modifyTime":null,"number":3,"price":22,"totalPrice":66,"type":1,"valid":true,"version":0}]
     * disPrice : 61.0
     * featureAppNo : null
     * id : null
     * isReady : 1
     * merTicket : {"createTime":null,"deleteTime":null,"dxAppUser":{"age":null,"birthday":null,"businessid":null,"city":null,"collectNum":null,"commentNum":null,"country":null,"createTime":"2019-01-20 14:35:23","deleteTime":null,"dxInsiderInfo":[],"gender":1,"header":"","id":3,"insider":null,"lastsign":null,"loginDate":"2019-01-21 17:42:43","mobile":"17376551017","modifyTime":"2019-01-21 17:42:43","nickName":"17376551017","password":"e10adc3949ba59abbe56e057f20f883e","points":null,"province":null,"qqUserId":null,"sign":null,"signdays":null,"uuid":"f0787d72-c865-438a-b890-4a69b92f9c9c","valid":true,"version":62,"wantseeNum":null,"watchedNum":null,"wbUserId":null,"wxOpenId":null,"wxUserId":null},"dxPlatTicket":{"achieveMoney":10,"amount":5,"applicationType":"","businessid":1,"changeFlag":null,"createTime":"2019-01-18 14:09:10","deleteTime":null,"endTime":"2019-03-09 00:00:00","fixedPayMoney":null,"giftType":null,"groupNumber":5,"id":2,"modifyTime":null,"name":"卖品代金券1","number":10,"platformType":1,"plusType":null,"reduceType":2,"remainNum":100,"sendNum":0,"startTime":"2018-12-31 00:00:00","status":1,"ticketDesc":"as\n阿斯达","ticketType":2,"totalNum":100,"valid":true,"version":0},"endTime":"2019-03-20 00:00:00","id":5,"modifyTime":null,"startTime":"2019-01-20 00:00:00","status":0,"valid":true,"version":0}
     * merTicketList : [{"createTime":null,"deleteTime":null,"dxAppUser":{"age":null,"birthday":null,"businessid":null,"city":null,"collectNum":null,"commentNum":null,"country":null,"createTime":"2019-01-20 14:35:23","deleteTime":null,"dxInsiderInfo":[],"gender":1,"header":"","id":3,"insider":null,"lastsign":null,"loginDate":"2019-01-21 17:42:43","mobile":"17376551017","modifyTime":"2019-01-21 17:42:43","nickName":"17376551017","password":"e10adc3949ba59abbe56e057f20f883e","points":null,"province":null,"qqUserId":null,"sign":null,"signdays":null,"uuid":"f0787d72-c865-438a-b890-4a69b92f9c9c","valid":true,"version":62,"wantseeNum":null,"watchedNum":null,"wbUserId":null,"wxOpenId":null,"wxUserId":null},"dxPlatTicket":{"achieveMoney":10,"amount":5,"applicationType":"","businessid":1,"changeFlag":null,"createTime":"2019-01-18 14:09:10","deleteTime":null,"endTime":"2019-03-09 00:00:00","fixedPayMoney":null,"giftType":null,"groupNumber":5,"id":2,"modifyTime":null,"name":"卖品代金券1","number":10,"platformType":1,"plusType":null,"reduceType":2,"remainNum":100,"sendNum":0,"startTime":"2018-12-31 00:00:00","status":1,"ticketDesc":"as\n阿斯达","ticketType":2,"totalNum":100,"valid":true,"version":0},"endTime":"2019-03-20 00:00:00","id":5,"modifyTime":null,"startTime":"2019-01-20 00:00:00","status":0,"valid":true,"version":0},{"createTime":null,"deleteTime":null,"dxAppUser":{"age":null,"birthday":null,"businessid":null,"city":null,"collectNum":null,"commentNum":null,"country":null,"createTime":"2019-01-20 14:35:23","deleteTime":null,"dxInsiderInfo":[],"gender":1,"header":"","id":3,"insider":null,"lastsign":null,"loginDate":"2019-01-21 17:42:43","mobile":"17376551017","modifyTime":"2019-01-21 17:42:43","nickName":"17376551017","password":"e10adc3949ba59abbe56e057f20f883e","points":null,"province":null,"qqUserId":null,"sign":null,"signdays":null,"uuid":"f0787d72-c865-438a-b890-4a69b92f9c9c","valid":true,"version":62,"wantseeNum":null,"watchedNum":null,"wbUserId":null,"wxOpenId":null,"wxUserId":null},"dxPlatTicket":{"achieveMoney":10,"amount":5,"applicationType":"","businessid":1,"changeFlag":null,"createTime":"2019-01-18 14:09:10","deleteTime":null,"endTime":"2019-03-09 00:00:00","fixedPayMoney":null,"giftType":null,"groupNumber":5,"id":2,"modifyTime":null,"name":"卖品代金券1","number":10,"platformType":1,"plusType":null,"reduceType":2,"remainNum":100,"sendNum":0,"startTime":"2018-12-31 00:00:00","status":1,"ticketDesc":"as\n阿斯达","ticketType":2,"totalNum":100,"valid":true,"version":0},"endTime":"2019-03-20 00:00:00","id":8,"modifyTime":null,"startTime":"2019-01-20 00:00:00","status":0,"valid":true,"version":0}]
     * merchandiseInfo : [{"id":1,"number":3}]
     * modifyTime : null
     * phone : null
     * qrCode : null
     * ticketFlag1 : null
     * ticketFlag2 : null
     * totalNumber : 3
     * totalPrice : 66.0
     * valid : true
     * version : 0
     */

    private Object address;
    private double beforeActivityPrice;
    private double beforeTicketPrice;
    private Object canRefund;
    private Object coupon;
    private String createTime;
    private Object deleteTime;
    private Object deliveryType;
    private double disPrice;
    private double vipDisPrice;
    private Object featureAppNo;
    private Object id;
    private int isReady;
    private MerTicketBean merTicket;
    private String merchandiseInfo;
    private Object modifyTime;
    private Object phone;
    private Object qrCode;
    private Object ticketFlag1;
    private Object ticketFlag2;
    private int totalNumber;
    private double totalPrice;
    private boolean valid;
    private int version;
    private List<DetailsBean> details;
    private List<MerTicketListBean> merTicketList;
    private String discountDetail;


    public String getDiscountDetail() {
        return discountDetail;
    }

    public void setDiscountDetail(String discountDetail) {
        this.discountDetail = discountDetail;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public double getBeforeActivityPrice() {
        return beforeActivityPrice;
    }

    public void setBeforeActivityPrice(double beforeActivityPrice) {
        this.beforeActivityPrice = beforeActivityPrice;
    }

    public double getBeforeTicketPrice() {
        return beforeTicketPrice;
    }

    public void setBeforeTicketPrice(double beforeTicketPrice) {
        this.beforeTicketPrice = beforeTicketPrice;
    }

    public Object getCanRefund() {
        return canRefund;
    }

    public void setCanRefund(Object canRefund) {
        this.canRefund = canRefund;
    }

    public Object getCoupon() {
        return coupon;
    }

    public void setCoupon(Object coupon) {
        this.coupon = coupon;
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

    public Object getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(Object deliveryType) {
        this.deliveryType = deliveryType;
    }

    public double getDisPrice() {
        return disPrice;
    }

    public void setDisPrice(double disPrice) {
        this.disPrice = disPrice;
    }

    public double getVipDisPrice() {
        return vipDisPrice;
    }

    public void setVipDisPrice(double vipDisPrice) {
        this.vipDisPrice = vipDisPrice;
    }

    public Object getFeatureAppNo() {
        return featureAppNo;
    }

    public void setFeatureAppNo(Object featureAppNo) {
        this.featureAppNo = featureAppNo;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public int getIsReady() {
        return isReady;
    }

    public void setIsReady(int isReady) {
        this.isReady = isReady;
    }

    public MerTicketBean getMerTicket() {
        return merTicket;
    }

    public void setMerTicket(MerTicketBean merTicket) {
        this.merTicket = merTicket;
    }

    public String getMerchandiseInfo() {
        return merchandiseInfo;
    }

    public void setMerchandiseInfo(String merchandiseInfo) {
        this.merchandiseInfo = merchandiseInfo;
    }

    public Object getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Object modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Object getPhone() {
        return phone;
    }

    public void setPhone(Object phone) {
        this.phone = phone;
    }

    public Object getQrCode() {
        return qrCode;
    }

    public void setQrCode(Object qrCode) {
        this.qrCode = qrCode;
    }

    public Object getTicketFlag1() {
        return ticketFlag1;
    }

    public void setTicketFlag1(Object ticketFlag1) {
        this.ticketFlag1 = ticketFlag1;
    }

    public Object getTicketFlag2() {
        return ticketFlag2;
    }

    public void setTicketFlag2(Object ticketFlag2) {
        this.ticketFlag2 = ticketFlag2;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
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

    public List<DetailsBean> getDetails() {
        return details;
    }

    public void setDetails(List<DetailsBean> details) {
        this.details = details;
    }

    public List<MerTicketListBean> getMerTicketList() {
        return merTicketList;
    }

    public void setMerTicketList(List<MerTicketListBean> merTicketList) {
        this.merTicketList = merTicketList;
    }

    public static class MerTicketBean implements Serializable{
        /**
         * createTime : null
         * deleteTime : null
         * dxAppUser : {"age":null,"birthday":null,"businessid":null,"city":null,"collectNum":null,"commentNum":null,"country":null,"createTime":"2019-01-20 14:35:23","deleteTime":null,"dxInsiderInfo":[],"gender":1,"header":"","id":3,"insider":null,"lastsign":null,"loginDate":"2019-01-21 17:42:43","mobile":"17376551017","modifyTime":"2019-01-21 17:42:43","nickName":"17376551017","password":"e10adc3949ba59abbe56e057f20f883e","points":null,"province":null,"qqUserId":null,"sign":null,"signdays":null,"uuid":"f0787d72-c865-438a-b890-4a69b92f9c9c","valid":true,"version":62,"wantseeNum":null,"watchedNum":null,"wbUserId":null,"wxOpenId":null,"wxUserId":null}
         * dxPlatTicket : {"achieveMoney":10,"amount":5,"applicationType":"","businessid":1,"changeFlag":null,"createTime":"2019-01-18 14:09:10","deleteTime":null,"endTime":"2019-03-09 00:00:00","fixedPayMoney":null,"giftType":null,"groupNumber":5,"id":2,"modifyTime":null,"name":"卖品代金券1","number":10,"platformType":1,"plusType":null,"reduceType":2,"remainNum":100,"sendNum":0,"startTime":"2018-12-31 00:00:00","status":1,"ticketDesc":"as\n阿斯达","ticketType":2,"totalNum":100,"valid":true,"version":0}
         * endTime : 2019-03-20 00:00:00
         * id : 5
         * modifyTime : null
         * startTime : 2019-01-20 00:00:00
         * status : 0
         * valid : true
         * version : 0
         */

        private Object createTime;
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

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
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

        public static class DxAppUserBean implements Serializable{
            /**
             * age : null
             * birthday : null
             * businessid : null
             * city : null
             * collectNum : null
             * commentNum : null
             * country : null
             * createTime : 2019-01-20 14:35:23
             * deleteTime : null
             * dxInsiderInfo : []
             * gender : 1
             * header :
             * id : 3
             * insider : null
             * lastsign : null
             * loginDate : 2019-01-21 17:42:43
             * mobile : 17376551017
             * modifyTime : 2019-01-21 17:42:43
             * nickName : 17376551017
             * password : e10adc3949ba59abbe56e057f20f883e
             * points : null
             * province : null
             * qqUserId : null
             * sign : null
             * signdays : null
             * uuid : f0787d72-c865-438a-b890-4a69b92f9c9c
             * valid : true
             * version : 62
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
            private Object country;
            private String createTime;
            private Object deleteTime;
            private int gender;
            private String header;
            private int id;
            private Object insider;
            private Object lastsign;
            private String loginDate;
            private String mobile;
            private String modifyTime;
            private String nickName;
            private String password;
            private Object points;
            private Object province;
            private Object qqUserId;
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
            private List<?> dxInsiderInfo;

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

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
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

            public List<?> getDxInsiderInfo() {
                return dxInsiderInfo;
            }

            public void setDxInsiderInfo(List<?> dxInsiderInfo) {
                this.dxInsiderInfo = dxInsiderInfo;
            }
        }

        public static class DxPlatTicketBean implements Serializable{
            /**
             * achieveMoney : 10.0
             * amount : 5.0
             * applicationType :
             * businessid : 1
             * changeFlag : null
             * createTime : 2019-01-18 14:09:10
             * deleteTime : null
             * endTime : 2019-03-09 00:00:00
             * fixedPayMoney : null
             * giftType : null
             * groupNumber : 5
             * id : 2
             * modifyTime : null
             * name : 卖品代金券1
             * number : 10
             * platformType : 1
             * plusType : null
             * reduceType : 2
             * remainNum : 100
             * sendNum : 0
             * startTime : 2018-12-31 00:00:00
             * status : 1
             * ticketDesc : as
             阿斯达
             * ticketType : 2
             * totalNum : 100
             * valid : true
             * version : 0
             */

            private double achieveMoney;
            private double amount;
            private String applicationType;
            private int businessid;
            private Object changeFlag;
            private String createTime;
            private Object deleteTime;
            private String endTime;
            private Object fixedPayMoney;
            private Object giftType;
            private int groupNumber;
            private int id;
            private Object modifyTime;
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

            public double getAchieveMoney() {
                return achieveMoney;
            }

            public void setAchieveMoney(double achieveMoney) {
                this.achieveMoney = achieveMoney;
            }

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
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

            public Object getChangeFlag() {
                return changeFlag;
            }

            public void setChangeFlag(Object changeFlag) {
                this.changeFlag = changeFlag;
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

    public static class DetailsBean implements Serializable{
        /**
         * createTime : 2019-01-21 17:53:09
         * deleteTime : null
         * id : null
         * itemCombo : null
         * merchandise : {"businessId":1,"cardDiscountType":2,"cinemaId":31,"counterPrice":34,"createTime":"2019-01-16 16:44:06","deleteTime":null,"dxId":1,"goodsNum":null,"id":1,"imageUrl":"https://hyg.happydoit.com/upload/201901/18KOfSXu.jpg","inventory":159,"itemClassId":"1","listOrder":0,"modifyTime":"2019-01-18 13:42:52","name":"常规套餐","number":null,"onlinePrice":22,"originalPrice":50,"overInventorySellFlag":0,"packageDetail":[],"type":"package","unit":"","updateTime":"2018-12-29 17:32:54","valid":true,"version":2}
         * modifyTime : null
         * number : 3
         * price : 22.0
         * totalPrice : 66.0
         * type : 1
         * valid : true
         * version : 0
         */

        private String createTime;
        private Object deleteTime;
        private Object id;
        private Object itemCombo;
        private MerchandiseBean merchandise;
        private Object modifyTime;
        private int number;
        private double price;
        private double totalPrice;
        private int type;
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

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
            this.id = id;
        }

        public Object getItemCombo() {
            return itemCombo;
        }

        public void setItemCombo(Object itemCombo) {
            this.itemCombo = itemCombo;
        }

        public MerchandiseBean getMerchandise() {
            return merchandise;
        }

        public void setMerchandise(MerchandiseBean merchandise) {
            this.merchandise = merchandise;
        }

        public Object getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(Object modifyTime) {
            this.modifyTime = modifyTime;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public double getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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

        public static class MerchandiseBean implements Serializable{
            /**
             * businessId : 1
             * cardDiscountType : 2
             * cinemaId : 31
             * counterPrice : 34.0
             * createTime : 2019-01-16 16:44:06
             * deleteTime : null
             * dxId : 1
             * goodsNum : null
             * id : 1
             * imageUrl : https://hyg.happydoit.com/upload/201901/18KOfSXu.jpg
             * inventory : 159
             * itemClassId : 1
             * listOrder : 0
             * modifyTime : 2019-01-18 13:42:52
             * name : 常规套餐
             * number : null
             * onlinePrice : 22.0
             * originalPrice : 50.0
             * overInventorySellFlag : 0
             * packageDetail : []
             * type : package
             * unit :
             * updateTime : 2018-12-29 17:32:54
             * valid : true
             * version : 2
             */

            private int businessId;
            private int cardDiscountType;
            private int cinemaId;
            private double counterPrice;
            private String createTime;
            private Object deleteTime;
            private int dxId;
            private Object goodsNum;
            private int id;
            private String imageUrl;
            private int inventory;
            private String itemClassId;
            private int listOrder;
            private String modifyTime;
            private String name;
            private Object number;
            private double onlinePrice;
            private double originalPrice;
            private int overInventorySellFlag;
            private String type;
            private String unit;
            private String updateTime;
            private boolean valid;
            private int version;
            private List<?> packageDetail;

            public int getBusinessId() {
                return businessId;
            }

            public void setBusinessId(int businessId) {
                this.businessId = businessId;
            }

            public int getCardDiscountType() {
                return cardDiscountType;
            }

            public void setCardDiscountType(int cardDiscountType) {
                this.cardDiscountType = cardDiscountType;
            }

            public int getCinemaId() {
                return cinemaId;
            }

            public void setCinemaId(int cinemaId) {
                this.cinemaId = cinemaId;
            }

            public double getCounterPrice() {
                return counterPrice;
            }

            public void setCounterPrice(double counterPrice) {
                this.counterPrice = counterPrice;
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

            public int getDxId() {
                return dxId;
            }

            public void setDxId(int dxId) {
                this.dxId = dxId;
            }

            public Object getGoodsNum() {
                return goodsNum;
            }

            public void setGoodsNum(Object goodsNum) {
                this.goodsNum = goodsNum;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public int getInventory() {
                return inventory;
            }

            public void setInventory(int inventory) {
                this.inventory = inventory;
            }

            public String getItemClassId() {
                return itemClassId;
            }

            public void setItemClassId(String itemClassId) {
                this.itemClassId = itemClassId;
            }

            public int getListOrder() {
                return listOrder;
            }

            public void setListOrder(int listOrder) {
                this.listOrder = listOrder;
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

            public Object getNumber() {
                return number;
            }

            public void setNumber(Object number) {
                this.number = number;
            }

            public double getOnlinePrice() {
                return onlinePrice;
            }

            public void setOnlinePrice(double onlinePrice) {
                this.onlinePrice = onlinePrice;
            }

            public double getOriginalPrice() {
                return originalPrice;
            }

            public void setOriginalPrice(double originalPrice) {
                this.originalPrice = originalPrice;
            }

            public int getOverInventorySellFlag() {
                return overInventorySellFlag;
            }

            public void setOverInventorySellFlag(int overInventorySellFlag) {
                this.overInventorySellFlag = overInventorySellFlag;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
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

            public List<?> getPackageDetail() {
                return packageDetail;
            }

            public void setPackageDetail(List<?> packageDetail) {
                this.packageDetail = packageDetail;
            }
        }
    }

    public static class MerTicketListBean implements Serializable{
        /**
         * createTime : null
         * deleteTime : null
         * dxAppUser : {"age":null,"birthday":null,"businessid":null,"city":null,"collectNum":null,"commentNum":null,"country":null,"createTime":"2019-01-20 14:35:23","deleteTime":null,"dxInsiderInfo":[],"gender":1,"header":"","id":3,"insider":null,"lastsign":null,"loginDate":"2019-01-21 17:42:43","mobile":"17376551017","modifyTime":"2019-01-21 17:42:43","nickName":"17376551017","password":"e10adc3949ba59abbe56e057f20f883e","points":null,"province":null,"qqUserId":null,"sign":null,"signdays":null,"uuid":"f0787d72-c865-438a-b890-4a69b92f9c9c","valid":true,"version":62,"wantseeNum":null,"watchedNum":null,"wbUserId":null,"wxOpenId":null,"wxUserId":null}
         * dxPlatTicket : {"achieveMoney":10,"amount":5,"applicationType":"","businessid":1,"changeFlag":null,"createTime":"2019-01-18 14:09:10","deleteTime":null,"endTime":"2019-03-09 00:00:00","fixedPayMoney":null,"giftType":null,"groupNumber":5,"id":2,"modifyTime":null,"name":"卖品代金券1","number":10,"platformType":1,"plusType":null,"reduceType":2,"remainNum":100,"sendNum":0,"startTime":"2018-12-31 00:00:00","status":1,"ticketDesc":"as\n阿斯达","ticketType":2,"totalNum":100,"valid":true,"version":0}
         * endTime : 2019-03-20 00:00:00
         * id : 5
         * modifyTime : null
         * startTime : 2019-01-20 00:00:00
         * status : 0
         * valid : true
         * version : 0
         */

        private Object createTime;
        private Object deleteTime;
        private DxAppUserBeanX dxAppUser;
        private DxPlatTicketBeanX dxPlatTicket;
        private String endTime;
        private int id;
        private Object modifyTime;
        private String startTime;
        private int status;
        private boolean valid;
        private int version;

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public Object getDeleteTime() {
            return deleteTime;
        }

        public void setDeleteTime(Object deleteTime) {
            this.deleteTime = deleteTime;
        }

        public DxAppUserBeanX getDxAppUser() {
            return dxAppUser;
        }

        public void setDxAppUser(DxAppUserBeanX dxAppUser) {
            this.dxAppUser = dxAppUser;
        }

        public DxPlatTicketBeanX getDxPlatTicket() {
            return dxPlatTicket;
        }

        public void setDxPlatTicket(DxPlatTicketBeanX dxPlatTicket) {
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

        public static class DxAppUserBeanX implements Serializable{
            /**
             * age : null
             * birthday : null
             * businessid : null
             * city : null
             * collectNum : null
             * commentNum : null
             * country : null
             * createTime : 2019-01-20 14:35:23
             * deleteTime : null
             * dxInsiderInfo : []
             * gender : 1
             * header :
             * id : 3
             * insider : null
             * lastsign : null
             * loginDate : 2019-01-21 17:42:43
             * mobile : 17376551017
             * modifyTime : 2019-01-21 17:42:43
             * nickName : 17376551017
             * password : e10adc3949ba59abbe56e057f20f883e
             * points : null
             * province : null
             * qqUserId : null
             * sign : null
             * signdays : null
             * uuid : f0787d72-c865-438a-b890-4a69b92f9c9c
             * valid : true
             * version : 62
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
            private Object country;
            private String createTime;
            private Object deleteTime;
            private int gender;
            private String header;
            private int id;
            private Object insider;
            private Object lastsign;
            private String loginDate;
            private String mobile;
            private String modifyTime;
            private String nickName;
            private String password;
            private Object points;
            private Object province;
            private Object qqUserId;
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
            private List<?> dxInsiderInfo;

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

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
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

            public List<?> getDxInsiderInfo() {
                return dxInsiderInfo;
            }

            public void setDxInsiderInfo(List<?> dxInsiderInfo) {
                this.dxInsiderInfo = dxInsiderInfo;
            }
        }

        public static class DxPlatTicketBeanX implements Serializable{
            /**
             * achieveMoney : 10.0
             * amount : 5.0
             * applicationType :
             * businessid : 1
             * changeFlag : null
             * createTime : 2019-01-18 14:09:10
             * deleteTime : null
             * endTime : 2019-03-09 00:00:00
             * fixedPayMoney : null
             * giftType : null
             * groupNumber : 5
             * id : 2
             * modifyTime : null
             * name : 卖品代金券1
             * number : 10
             * platformType : 1
             * plusType : null
             * reduceType : 2
             * remainNum : 100
             * sendNum : 0
             * startTime : 2018-12-31 00:00:00
             * status : 1
             * ticketDesc : as
             阿斯达
             * ticketType : 2
             * totalNum : 100
             * valid : true
             * version : 0
             */

            private double achieveMoney;
            private double amount;
            private String applicationType;
            private int businessid;
            private Object changeFlag;
            private String createTime;
            private Object deleteTime;
            private String endTime;
            private Object fixedPayMoney;
            private Object giftType;
            private int groupNumber;
            private int id;
            private Object modifyTime;
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

            public double getAchieveMoney() {
                return achieveMoney;
            }

            public void setAchieveMoney(double achieveMoney) {
                this.achieveMoney = achieveMoney;
            }

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
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

            public Object getChangeFlag() {
                return changeFlag;
            }

            public void setChangeFlag(Object changeFlag) {
                this.changeFlag = changeFlag;
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
}
