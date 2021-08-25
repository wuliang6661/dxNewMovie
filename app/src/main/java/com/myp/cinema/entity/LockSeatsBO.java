package com.myp.cinema.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Witness on 2019/1/7
 * Describe:锁座以及确认订单
 */
public class LockSeatsBO implements Serializable {

    private Object activityCode;
    private String cinemaId;
    private String id;
    private Object shareUrl;
    private String playName;
    private Object successReportTime;
    private String ticketNum;
    private Object hallName;
    private MerOrderBean merOrder;
    private Object seatStatus;
    private String beforeTicketPrice;
    private String seatId;
    private Object ticketFlag1;
    private Object orderResponse;
    private Object coupon;
    private Object refundCode;
    private String activityPriceNum;
    private Object playId;
    private Object cinemaName;
    private String orderNum;
    private Object refundHandFee;
    private Object deleteTime;
    private Object ticketFlag2;
    private String poundage;
    private Object cinema;
    private Object lowestPrice;
    private Object payOrderNum;
    private Object refundResponse;
    private DxMovieBean dxMovie;
    private Object modifyTime;
    private int refundStatus;
    private String ticketOriginPrice;
    private Object hallId;
    private String totalDisprice;
    private int payStatus;
    private String partnerPrice;
    private Object refundCount;
    private String seats;
    private Object payDate;
    private Object payType;
    private Object ticketRealPrice;
    private Object cineMovieNum;
    private Object payResponse;
    private String payPrice;
    private String vipPayPrice;
    private String vipTicketPayPrice;
    private String vipBeforeTicketPrice;
    private Object refundId;
    private Object firstReportTime;
    private Object card;
    private Object orderName;
    private Object payNotifyResponse;
    private Object buyResponse;
    private Object cinemaNumber;
    private Object qrCode;
    private int reportStatus;
    private Object playUpdateTime;
    private String marketPrice;
    private Object subsidyType;
    private int version;
    private Object canRefund;
    private Object dxAppUser;
    private boolean valid;
    private Object refundTime;
    private long orderExpireSecond;
    private Object refundFee;
    private Object seatLockFlag;
    private String createTime;
    private Object serviceCharge;
    private String orderPhone;
    private SeatTicketBean seatTicket;
    private double subsidyCount;
    private List<ComboListBean> comboList;
    private List<SeatTicketListBean> seatTicketList;

    public Object getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(Object activityCode) {
        this.activityCode = activityCode;
    }

    public String getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(String cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(Object shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getPlayName() {
        return playName;
    }

    public void setPlayName(String playName) {
        this.playName = playName;
    }

    public Object getSuccessReportTime() {
        return successReportTime;
    }

    public void setSuccessReportTime(Object successReportTime) {
        this.successReportTime = successReportTime;
    }

    public String getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(String ticketNum) {
        this.ticketNum = ticketNum;
    }

    public Object getHallName() {
        return hallName;
    }

    public void setHallName(Object hallName) {
        this.hallName = hallName;
    }

    public MerOrderBean getMerOrder() {
        return merOrder;
    }

    public void setMerOrder(MerOrderBean merOrder) {
        this.merOrder = merOrder;
    }

    public Object getSeatStatus() {
        return seatStatus;
    }

    public void setSeatStatus(Object seatStatus) {
        this.seatStatus = seatStatus;
    }

    public String getBeforeTicketPrice() {
        return beforeTicketPrice;
    }

    public void setBeforeTicketPrice(String beforeTicketPrice) {
        this.beforeTicketPrice = beforeTicketPrice;
    }

    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }

    public Object getTicketFlag1() {
        return ticketFlag1;
    }

    public void setTicketFlag1(Object ticketFlag1) {
        this.ticketFlag1 = ticketFlag1;
    }

    public Object getOrderResponse() {
        return orderResponse;
    }

    public void setOrderResponse(Object orderResponse) {
        this.orderResponse = orderResponse;
    }

    public Object getCoupon() {
        return coupon;
    }

    public void setCoupon(Object coupon) {
        this.coupon = coupon;
    }

    public Object getRefundCode() {
        return refundCode;
    }

    public void setRefundCode(Object refundCode) {
        this.refundCode = refundCode;
    }

    public String getActivityPriceNum() {
        return activityPriceNum;
    }

    public void setActivityPriceNum(String activityPriceNum) {
        this.activityPriceNum = activityPriceNum;
    }

    public Object getPlayId() {
        return playId;
    }

    public void setPlayId(Object playId) {
        this.playId = playId;
    }

    public Object getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(Object cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Object getRefundHandFee() {
        return refundHandFee;
    }

    public void setRefundHandFee(Object refundHandFee) {
        this.refundHandFee = refundHandFee;
    }

    public Object getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Object deleteTime) {
        this.deleteTime = deleteTime;
    }

    public Object getTicketFlag2() {
        return ticketFlag2;
    }

    public void setTicketFlag2(Object ticketFlag2) {
        this.ticketFlag2 = ticketFlag2;
    }

    public String getPoundage() {
        return poundage;
    }

    public void setPoundage(String poundage) {
        this.poundage = poundage;
    }

    public Object getCinema() {
        return cinema;
    }

    public void setCinema(Object cinema) {
        this.cinema = cinema;
    }

    public Object getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(Object lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public Object getPayOrderNum() {
        return payOrderNum;
    }

    public void setPayOrderNum(Object payOrderNum) {
        this.payOrderNum = payOrderNum;
    }

    public Object getRefundResponse() {
        return refundResponse;
    }

    public void setRefundResponse(Object refundResponse) {
        this.refundResponse = refundResponse;
    }

    public DxMovieBean getDxMovie() {
        return dxMovie;
    }

    public void setDxMovie(DxMovieBean dxMovie) {
        this.dxMovie = dxMovie;
    }

    public Object getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Object modifyTime) {
        this.modifyTime = modifyTime;
    }

    public int getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(int refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getTicketOriginPrice() {
        return ticketOriginPrice;
    }

    public void setTicketOriginPrice(String ticketOriginPrice) {
        this.ticketOriginPrice = ticketOriginPrice;
    }

    public Object getHallId() {
        return hallId;
    }

    public void setHallId(Object hallId) {
        this.hallId = hallId;
    }

    public String getTotalDisprice() {
        return totalDisprice;
    }

    public void setTotalDisprice(String totalDisprice) {
        this.totalDisprice = totalDisprice;
    }

    public int getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(int payStatus) {
        this.payStatus = payStatus;
    }

    public String getPartnerPrice() {
        return partnerPrice;
    }

    public void setPartnerPrice(String partnerPrice) {
        this.partnerPrice = partnerPrice;
    }

    public Object getRefundCount() {
        return refundCount;
    }

    public void setRefundCount(Object refundCount) {
        this.refundCount = refundCount;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public Object getPayDate() {
        return payDate;
    }

    public void setPayDate(Object payDate) {
        this.payDate = payDate;
    }

    public Object getPayType() {
        return payType;
    }

    public void setPayType(Object payType) {
        this.payType = payType;
    }

    public Object getTicketRealPrice() {
        return ticketRealPrice;
    }

    public void setTicketRealPrice(Object ticketRealPrice) {
        this.ticketRealPrice = ticketRealPrice;
    }

    public Object getCineMovieNum() {
        return cineMovieNum;
    }

    public void setCineMovieNum(Object cineMovieNum) {
        this.cineMovieNum = cineMovieNum;
    }

    public Object getPayResponse() {
        return payResponse;
    }

    public void setPayResponse(Object payResponse) {
        this.payResponse = payResponse;
    }

    public String getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(String payPrice) {
        this.payPrice = payPrice;
    }

    public void setVipPayPrice(String vipPayPrice) {
        this.vipPayPrice = vipPayPrice;
    }

    public String getVipPayPrice() {
        return vipPayPrice;
    }

    public String getVipTicketPayPrice() {
        return vipTicketPayPrice;
    }

    public void setVipTicketPayPrice(String vipTicketPayPrice) {
        this.vipTicketPayPrice = vipTicketPayPrice;
    }

    public String getVipBeforeTicketPrice() {
        return vipBeforeTicketPrice;
    }

    public void setVipBeforeTicketPrice(String vipBeforeTicketPrice) {
        this.vipBeforeTicketPrice = vipBeforeTicketPrice;
    }

    public Object getRefundId() {
        return refundId;
    }

    public void setRefundId(Object refundId) {
        this.refundId = refundId;
    }

    public Object getFirstReportTime() {
        return firstReportTime;
    }

    public void setFirstReportTime(Object firstReportTime) {
        this.firstReportTime = firstReportTime;
    }

    public Object getCard() {
        return card;
    }

    public void setCard(Object card) {
        this.card = card;
    }

    public Object getOrderName() {
        return orderName;
    }

    public void setOrderName(Object orderName) {
        this.orderName = orderName;
    }

    public Object getPayNotifyResponse() {
        return payNotifyResponse;
    }

    public void setPayNotifyResponse(Object payNotifyResponse) {
        this.payNotifyResponse = payNotifyResponse;
    }

    public Object getBuyResponse() {
        return buyResponse;
    }

    public void setBuyResponse(Object buyResponse) {
        this.buyResponse = buyResponse;
    }

    public Object getCinemaNumber() {
        return cinemaNumber;
    }

    public void setCinemaNumber(Object cinemaNumber) {
        this.cinemaNumber = cinemaNumber;
    }

    public Object getQrCode() {
        return qrCode;
    }

    public void setQrCode(Object qrCode) {
        this.qrCode = qrCode;
    }

    public int getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(int reportStatus) {
        this.reportStatus = reportStatus;
    }

    public Object getPlayUpdateTime() {
        return playUpdateTime;
    }

    public void setPlayUpdateTime(Object playUpdateTime) {
        this.playUpdateTime = playUpdateTime;
    }

    public String getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Object getSubsidyType() {
        return subsidyType;
    }

    public void setSubsidyType(Object subsidyType) {
        this.subsidyType = subsidyType;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Object getCanRefund() {
        return canRefund;
    }

    public void setCanRefund(Object canRefund) {
        this.canRefund = canRefund;
    }

    public Object getDxAppUser() {
        return dxAppUser;
    }

    public void setDxAppUser(Object dxAppUser) {
        this.dxAppUser = dxAppUser;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public Object getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Object refundTime) {
        this.refundTime = refundTime;
    }

    public long getOrderExpireSecond() {
        return orderExpireSecond;
    }

    public void setOrderExpireSecond(long orderExpireSecond) {
        this.orderExpireSecond = orderExpireSecond;
    }

    public Object getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(Object refundFee) {
        this.refundFee = refundFee;
    }

    public Object getSeatLockFlag() {
        return seatLockFlag;
    }

    public void setSeatLockFlag(Object seatLockFlag) {
        this.seatLockFlag = seatLockFlag;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Object getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(Object serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public String getOrderPhone() {
        return orderPhone;
    }

    public void setOrderPhone(String orderPhone) {
        this.orderPhone = orderPhone;
    }

    public SeatTicketBean getSeatTicket() {
        return seatTicket;
    }

    public void setSeatTicket(SeatTicketBean seatTicket) {
        this.seatTicket = seatTicket;
    }

    public double getSubsidyCount() {
        return subsidyCount;
    }

    public void setSubsidyCount(double subsidyCount) {
        this.subsidyCount = subsidyCount;
    }

    public List<ComboListBean> getComboList() {
        return comboList;
    }

    public void setComboList(List<ComboListBean> comboList) {
        this.comboList = comboList;
    }

    public List<SeatTicketListBean> getSeatTicketList() {
        return seatTicketList;
    }

    public void setSeatTicketList(List<SeatTicketListBean> seatTicketList) {
        this.seatTicketList = seatTicketList;
    }
    public static class DxMovieBean implements Serializable {
        /**
         * alias : null
         * cast : null
         * chatRoomImage :
         * cineMovieId : null
         * cineMovieNum : 051102672018
         * comment : null
         * commentRecord : null
         * content : null
         * country : 美国
         * createTime : 2019-01-02 11:00:01
         * deleteTime : null
         * director : null
         * dxActors : []
         * dxDirectors : []
         * dxVideos : []
         * foreignName : Creed: Revenge
         * id : 16
         * infoUrl :
         * introduction : null
         * level : null
         * mediaLib :
         * modifyTime : 2019-01-10 14:33:01
         * movieDimensional : 2D
         * movieFormat : 2D
         * movieLanguage : 英语
         * movieName : 奎迪：英雄再起（数字）
         * movieSize : 2D
         * movieSubtitle :
         * movieType : 剧情, 运动
         * photos : https://shapp.oss-cn-hangzhou.aliyuncs.com/image/upload/201901/03FKEdhD.jpg,https://shapp.oss-cn-hangzhou.aliyuncs.com/image/upload/201901/03pciyAn.jpg
         * picture : https://shapp.oss-cn-hangzhou.aliyuncs.com/image/upload/201901/03sBoKZV.jpg
         * playTime : 130
         * point : 7.5
         * preVideo :
         * product : 美国米高梅影片公司
         * publisher : 暂空
         * redirectType : null
         * redirectUrl : null
         * score : null
         * screenPlanList : null
         * sell : null
         * shareurl :
         * startPlay : 2019-01-04 00:00:00
         * stopshow : 0
         * summary :
         * title :
         * type : null
         * uniqueName : 奎迪：英雄再起（数字）
         * valid : true
         * version : 463
         */

        private Object alias;
        private Object cast;
        private String chatRoomImage;
        private Object cineMovieId;
        private String cineMovieNum;
        private Object comment;
        private Object commentRecord;
        private Object content;
        private String country;
        private String createTime;
        private Object deleteTime;
        private Object director;
        private String foreignName;
        private int id;
        private String infoUrl;
        private Object introduction;
        private Object level;
        private String mediaLib;
        private String modifyTime;
        private String movieDimensional;
        private String movieFormat;
        private String movieLanguage;
        private String movieName;
        private String movieSize;
        private String movieSubtitle;
        private String movieType;
        private String photos;
        private String picture;
        private int playTime;
        private double point;
        private String preVideo;
        private String product;
        private String publisher;
        private Object redirectType;
        private Object redirectUrl;
        private Object score;
        private Object screenPlanList;
        private Object sell;
        private String shareurl;
        private String startPlay;
        private int stopshow;
        private String summary;
        private String title;
        private Object type;
        private String uniqueName;
        private boolean valid;
        private int version;
        private List<?> dxActors;
        private List<?> dxDirectors;
        private List<?> dxVideos;

        public Object getAlias() {
            return alias;
        }

        public void setAlias(Object alias) {
            this.alias = alias;
        }

        public Object getCast() {
            return cast;
        }

        public void setCast(Object cast) {
            this.cast = cast;
        }

        public String getChatRoomImage() {
            return chatRoomImage;
        }

        public void setChatRoomImage(String chatRoomImage) {
            this.chatRoomImage = chatRoomImage;
        }

        public Object getCineMovieId() {
            return cineMovieId;
        }

        public void setCineMovieId(Object cineMovieId) {
            this.cineMovieId = cineMovieId;
        }

        public String getCineMovieNum() {
            return cineMovieNum;
        }

        public void setCineMovieNum(String cineMovieNum) {
            this.cineMovieNum = cineMovieNum;
        }

        public Object getComment() {
            return comment;
        }

        public void setComment(Object comment) {
            this.comment = comment;
        }

        public Object getCommentRecord() {
            return commentRecord;
        }

        public void setCommentRecord(Object commentRecord) {
            this.commentRecord = commentRecord;
        }

        public Object getContent() {
            return content;
        }

        public void setContent(Object content) {
            this.content = content;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
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

        public Object getDirector() {
            return director;
        }

        public void setDirector(Object director) {
            this.director = director;
        }

        public String getForeignName() {
            return foreignName;
        }

        public void setForeignName(String foreignName) {
            this.foreignName = foreignName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getInfoUrl() {
            return infoUrl;
        }

        public void setInfoUrl(String infoUrl) {
            this.infoUrl = infoUrl;
        }

        public Object getIntroduction() {
            return introduction;
        }

        public void setIntroduction(Object introduction) {
            this.introduction = introduction;
        }

        public Object getLevel() {
            return level;
        }

        public void setLevel(Object level) {
            this.level = level;
        }

        public String getMediaLib() {
            return mediaLib;
        }

        public void setMediaLib(String mediaLib) {
            this.mediaLib = mediaLib;
        }

        public String getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(String modifyTime) {
            this.modifyTime = modifyTime;
        }

        public String getMovieDimensional() {
            return movieDimensional;
        }

        public void setMovieDimensional(String movieDimensional) {
            this.movieDimensional = movieDimensional;
        }

        public String getMovieFormat() {
            return movieFormat;
        }

        public void setMovieFormat(String movieFormat) {
            this.movieFormat = movieFormat;
        }

        public String getMovieLanguage() {
            return movieLanguage;
        }

        public void setMovieLanguage(String movieLanguage) {
            this.movieLanguage = movieLanguage;
        }

        public String getMovieName() {
            return movieName;
        }

        public void setMovieName(String movieName) {
            this.movieName = movieName;
        }

        public String getMovieSize() {
            return movieSize;
        }

        public void setMovieSize(String movieSize) {
            this.movieSize = movieSize;
        }

        public String getMovieSubtitle() {
            return movieSubtitle;
        }

        public void setMovieSubtitle(String movieSubtitle) {
            this.movieSubtitle = movieSubtitle;
        }

        public String getMovieType() {
            return movieType;
        }

        public void setMovieType(String movieType) {
            this.movieType = movieType;
        }

        public String getPhotos() {
            return photos;
        }

        public void setPhotos(String photos) {
            this.photos = photos;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public int getPlayTime() {
            return playTime;
        }

        public void setPlayTime(int playTime) {
            this.playTime = playTime;
        }

        public double getPoint() {
            return point;
        }

        public void setPoint(double point) {
            this.point = point;
        }

        public String getPreVideo() {
            return preVideo;
        }

        public void setPreVideo(String preVideo) {
            this.preVideo = preVideo;
        }

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public String getPublisher() {
            return publisher;
        }

        public void setPublisher(String publisher) {
            this.publisher = publisher;
        }

        public Object getRedirectType() {
            return redirectType;
        }

        public void setRedirectType(Object redirectType) {
            this.redirectType = redirectType;
        }

        public Object getRedirectUrl() {
            return redirectUrl;
        }

        public void setRedirectUrl(Object redirectUrl) {
            this.redirectUrl = redirectUrl;
        }

        public Object getScore() {
            return score;
        }

        public void setScore(Object score) {
            this.score = score;
        }

        public Object getScreenPlanList() {
            return screenPlanList;
        }

        public void setScreenPlanList(Object screenPlanList) {
            this.screenPlanList = screenPlanList;
        }

        public Object getSell() {
            return sell;
        }

        public void setSell(Object sell) {
            this.sell = sell;
        }

        public String getShareurl() {
            return shareurl;
        }

        public void setShareurl(String shareurl) {
            this.shareurl = shareurl;
        }

        public String getStartPlay() {
            return startPlay;
        }

        public void setStartPlay(String startPlay) {
            this.startPlay = startPlay;
        }

        public int getStopshow() {
            return stopshow;
        }

        public void setStopshow(int stopshow) {
            this.stopshow = stopshow;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Object getType() {
            return type;
        }

        public void setType(Object type) {
            this.type = type;
        }

        public String getUniqueName() {
            return uniqueName;
        }

        public void setUniqueName(String uniqueName) {
            this.uniqueName = uniqueName;
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

        public List<?> getDxActors() {
            return dxActors;
        }

        public void setDxActors(List<?> dxActors) {
            this.dxActors = dxActors;
        }

        public List<?> getDxDirectors() {
            return dxDirectors;
        }

        public void setDxDirectors(List<?> dxDirectors) {
            this.dxDirectors = dxDirectors;
        }

        public List<?> getDxVideos() {
            return dxVideos;
        }

        public void setDxVideos(List<?> dxVideos) {
            this.dxVideos = dxVideos;
        }
    }


    public static class MerOrderBean implements Serializable{
        /**
         * address : null
         * beforeActivityPrice : null
         * beforeTicketPrice : 10
         * canRefund : null
         * coupon : null
         * createTime : 2019-01-20 19:12:47
         * deleteTime : null
         * deliveryType : null
         * details : null
         * disPrice : 5
         * featureAppNo : null
         * id : null
         * isReady : 1
         * merTicket : {"createTime":null,"deleteTime":null,"dxAppUser":{"age":null,"birthday":null,"businessid":null,"city":null,"collectNum":null,"commentNum":null,"country":null,"createTime":"2019-01-20 14:35:23","deleteTime":null,"dxInsiderInfo":{},"gender":1,"header":"","id":3,"insider":null,"lastsign":null,"loginDate":"2019-01-20 19:12:08","mobile":"17376551017","modifyTime":"2019-01-20 19:12:08","nickName":"17376551017","password":"e10adc3949ba59abbe56e057f20f883e","points":null,"province":null,"qqUserId":null,"sign":null,"signdays":null,"uuid":"4e631146-3e75-40ca-8a03-8f81caf5fd16","valid":true,"version":40,"wantseeNum":null,"watchedNum":null,"wbUserId":null,"wxOpenId":null,"wxUserId":null},"dxPlatTicket":{"achieveMoney":10,"amount":5,"applicationType":"","businessid":1,"changeFlag":null,"createTime":"2019-01-18 14:09:10","deleteTime":null,"endTime":"2019-03-09 00:00:00","fixedPayMoney":null,"giftType":null,"groupNumber":5,"id":2,"modifyTime":null,"name":"卖品代金券1","number":10,"platformType":1,"plusType":null,"reduceType":2,"remainNum":100,"sendNum":0,"startTime":"2018-12-31 00:00:00","status":1,"ticketDesc":"as 阿斯达","ticketType":2,"totalNum":100,"valid":true,"version":0},"endTime":"2019-03-20 00:00:00","id":5,"modifyTime":null,"startTime":"2019-01-20 00:00:00","status":0,"valid":true,"version":0}
         * merTicketList : [{"createTime":null,"deleteTime":null,"dxAppUser":{"age":null,"birthday":null,"businessid":null,"city":null,"collectNum":null,"commentNum":null,"country":null,"createTime":"2019-01-20 14:35:23","deleteTime":null,"dxInsiderInfo":{},"gender":1,"header":"","id":3,"insider":null,"lastsign":null,"loginDate":"2019-01-20 19:12:08","mobile":"17376551017","modifyTime":"2019-01-20 19:12:08","nickName":"17376551017","password":"e10adc3949ba59abbe56e057f20f883e","points":null,"province":null,"qqUserId":null,"sign":null,"signdays":null,"uuid":"4e631146-3e75-40ca-8a03-8f81caf5fd16","valid":true,"version":40,"wantseeNum":null,"watchedNum":null,"wbUserId":null,"wxOpenId":null,"wxUserId":null},"dxPlatTicket":{"achieveMoney":10,"amount":5,"applicationType":"","businessid":1,"changeFlag":null,"createTime":"2019-01-18 14:09:10","deleteTime":null,"endTime":"2019-03-09 00:00:00","fixedPayMoney":null,"giftType":null,"groupNumber":5,"id":2,"modifyTime":null,"name":"卖品代金券1","number":10,"platformType":1,"plusType":null,"reduceType":2,"remainNum":100,"sendNum":0,"startTime":"2018-12-31 00:00:00","status":1,"ticketDesc":"as 阿斯达","ticketType":2,"totalNum":100,"valid":true,"version":0},"endTime":"2019-03-20 00:00:00","id":5,"modifyTime":null,"startTime":"2019-01-20 00:00:00","status":0,"valid":true,"version":0},{"createTime":null,"deleteTime":null,"dxAppUser":{"age":null,"birthday":null,"businessid":null,"city":null,"collectNum":null,"commentNum":null,"country":null,"createTime":"2019-01-20 14:35:23","deleteTime":null,"dxInsiderInfo":{},"gender":1,"header":"","id":3,"insider":null,"lastsign":null,"loginDate":"2019-01-20 19:12:08","mobile":"17376551017","modifyTime":"2019-01-20 19:12:08","nickName":"17376551017","password":"e10adc3949ba59abbe56e057f20f883e","points":null,"province":null,"qqUserId":null,"sign":null,"signdays":null,"uuid":"4e631146-3e75-40ca-8a03-8f81caf5fd16","valid":true,"version":40,"wantseeNum":null,"watchedNum":null,"wbUserId":null,"wxOpenId":null,"wxUserId":null},"dxPlatTicket":{"achieveMoney":10,"amount":5,"applicationType":"","businessid":1,"changeFlag":null,"createTime":"2019-01-18 14:09:10","deleteTime":null,"endTime":"2019-03-09 00:00:00","fixedPayMoney":null,"giftType":null,"groupNumber":5,"id":2,"modifyTime":null,"name":"卖品代金券1","number":10,"platformType":1,"plusType":null,"reduceType":2,"remainNum":100,"sendNum":0,"startTime":"2018-12-31 00:00:00","status":1,"ticketDesc":"as 阿斯达","ticketType":2,"totalNum":100,"valid":true,"version":0},"endTime":"2019-03-20 00:00:00","id":8,"modifyTime":null,"startTime":"2019-01-20 00:00:00","status":0,"valid":true,"version":0}]
         * merchandiseInfo : null
         * modifyTime : null
         * phone : null
         * qrCode : null
         * ticketFlag1 : null
         * ticketFlag2 : null
         * totalNumber : 2
         * totalPrice : 10
         * valid : true
         * version : 0
         */

        private Object address;
        private Object beforeActivityPrice;
        private int beforeTicketPrice;
        private Object canRefund;
        private Object coupon;
        private String createTime;
        private Object deleteTime;
        private int deliveryType;
        private List<DetailsBean> details;
        private String disPrice;
        private Object featureAppNo;
        private Object id;
        private int isReady;
        private MerTicketBean merTicket;
        private Object merchandiseInfo;
        private Object modifyTime;
        private Object phone;
        private Object qrCode;
        private Object ticketFlag1;
        private Object ticketFlag2;
        private int totalNumber;
        private String totalPrice;
        private boolean valid;
        private int version;
        private List<MerTicketListBean> merTicketList;
        private String vipDisPrice;

        public String getVipDisPrice() {
            return vipDisPrice;
        }

        public void setVipDisPrice(String vipDisPrice) {
            this.vipDisPrice = vipDisPrice;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public Object getBeforeActivityPrice() {
            return beforeActivityPrice;
        }

        public void setBeforeActivityPrice(Object beforeActivityPrice) {
            this.beforeActivityPrice = beforeActivityPrice;
        }

        public int getBeforeTicketPrice() {
            return beforeTicketPrice;
        }

        public void setBeforeTicketPrice(int beforeTicketPrice) {
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

        public int getDeliveryType() {
            return deliveryType;
        }

        public void setDeliveryType(int deliveryType) {
            this.deliveryType = deliveryType;
        }

        public List<DetailsBean> getDetails() {
            return details;
        }

        public void setDetails(List<DetailsBean> details) {
            this.details = details;
        }

        public String getDisPrice() {
            return disPrice;
        }

        public void setDisPrice(String disPrice) {
            this.disPrice = disPrice;
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

        public Object getMerchandiseInfo() {
            return merchandiseInfo;
        }

        public void setMerchandiseInfo(Object merchandiseInfo) {
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

        public String getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(String totalPrice) {
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

        public List<MerTicketListBean> getMerTicketList() {
            return merTicketList;
        }

        public void setMerTicketList(List<MerTicketListBean> merTicketList) {
            this.merTicketList = merTicketList;
        }

        public static class DetailsBean implements Serializable {
            /**
             * createTime : 2019-01-10 14:37:47
             * deleteTime : null
             * id : 170
             * itemCombo : {"bussinessid":null,"cinemaCode":"33111001","createTime":"2018-12-28 11:46:55","deleteTime":null,"id":4,"imageUrl":"https://shapp.oss-cn-hangzhou.aliyuncs.com/image/upload/201812/28hYYnLz.jpg","merchandiseList":[{"businessId":1,"cinemaCode":"33111001","createTime":"2019-01-09 22:05:25","deleteTime":"2018-12-26 21:09:46","id":86,"imageUrl":"https://shapp.oss-cn-hangzhou.aliyuncs.com/image/upload/201812/27fVpOuq.jpg","itemClassId":"7","listingPrice":32,"merClassUid":"cf7dd573-26e6-42a1-9875-5ee9180776fa","merDesc":null,"merName":"中桶爆米花*1+现制水果茶*1+关东煮*1","merTypeId":4,"merUid":"3035bdee-b0fa-4e10-9e10-ea0844579aab","modifyTime":"2019-01-09 22:05:25","number":null,"originalPrice":null,"settlePrice":32,"showSeqNo":105,"valid":true,"version":44}],"modifyTime":"2018-12-31 10:23:36","name":"单人超值套餐","originalPrice":44,"personNum":1,"salePrice":32,"sort":1,"status":1,"valid":true,"version":2}
             * merchandise : null
             * modifyTime : null
             * number : 1
             * price : 32.0
             * totalPrice : 32.0
             * type : 2
             * valid : true
             * version : 0
             */

            private String createTime;
            private Object deleteTime;
            private int id;
            private ItemComboBean itemCombo;
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

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public ItemComboBean getItemCombo() {
                return itemCombo;
            }

            public void setItemCombo(ItemComboBean itemCombo) {
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
                 * modifyTime : 2018-12-31 17:36:19
                 * originalPrice : null
                 * cinemaCode : 33111001
                 * merTypeId : 4
                 * merDesc : null
                 * businessId : 1
                 * showSeqNo : 102
                 * version : 41
                 * imageUrl : https://shapp.oss-cn-hangzhou.aliyuncs.com/image/upload/201812/27CKTtqM.jpg
                 * listingPrice : 78
                 * id : 89
                 * itemClassId : 7
                 * deleteTime : 2018-12-26 21:09:46
                 * number : null
                 * settlePrice : 78
                 * valid : true
                 * merUid : 0e865ced-f4e2-4777-89c7-fe42e58c5426
                 * merClassUid : cf7dd573-26e6-42a1-9875-5ee9180776fa
                 * createTime : 2018-12-31 17:34:52
                 * merName : 大桶爆米花*1+现制水果茶/奶茶*3+哈根达斯
                 */

                private String modifyTime;
                private double originalPrice;
                private String cinemaCode;
                private int merTypeId;
                private Object merDesc;
                private int businessId;
                private int showSeqNo;
                private int version;
                private String imageUrl;
                private double listingPrice;
                private int id;
                private String itemClassId;
                private String deleteTime;
                private Object number;
                private double settlePrice;
                private boolean valid;
                private String merUid;
                private String merClassUid;
                private String createTime;
                private String name;

                public String getModifyTime() {
                    return modifyTime;
                }

                public void setModifyTime(String modifyTime) {
                    this.modifyTime = modifyTime;
                }

                public double getOriginalPrice() {
                    return originalPrice;
                }

                public void setOriginalPrice(double originalPrice) {
                    this.originalPrice = originalPrice;
                }

                public String getCinemaCode() {
                    return cinemaCode;
                }

                public void setCinemaCode(String cinemaCode) {
                    this.cinemaCode = cinemaCode;
                }

                public int getMerTypeId() {
                    return merTypeId;
                }

                public void setMerTypeId(int merTypeId) {
                    this.merTypeId = merTypeId;
                }

                public Object getMerDesc() {
                    return merDesc;
                }

                public void setMerDesc(Object merDesc) {
                    this.merDesc = merDesc;
                }

                public int getBusinessId() {
                    return businessId;
                }

                public void setBusinessId(int businessId) {
                    this.businessId = businessId;
                }

                public int getShowSeqNo() {
                    return showSeqNo;
                }

                public void setShowSeqNo(int showSeqNo) {
                    this.showSeqNo = showSeqNo;
                }

                public int getVersion() {
                    return version;
                }

                public void setVersion(int version) {
                    this.version = version;
                }

                public String getImageUrl() {
                    return imageUrl;
                }

                public void setImageUrl(String imageUrl) {
                    this.imageUrl = imageUrl;
                }

                public double getListingPrice() {
                    return listingPrice;
                }

                public void setListingPrice(double listingPrice) {
                    this.listingPrice = listingPrice;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getItemClassId() {
                    return itemClassId;
                }

                public void setItemClassId(String itemClassId) {
                    this.itemClassId = itemClassId;
                }

                public String getDeleteTime() {
                    return deleteTime;
                }

                public void setDeleteTime(String deleteTime) {
                    this.deleteTime = deleteTime;
                }

                public Object getNumber() {
                    return number;
                }

                public void setNumber(Object number) {
                    this.number = number;
                }

                public double getSettlePrice() {
                    return settlePrice;
                }

                public void setSettlePrice(double settlePrice) {
                    this.settlePrice = settlePrice;
                }

                public boolean isValid() {
                    return valid;
                }

                public void setValid(boolean valid) {
                    this.valid = valid;
                }

                public String getMerUid() {
                    return merUid;
                }

                public void setMerUid(String merUid) {
                    this.merUid = merUid;
                }

                public String getMerClassUid() {
                    return merClassUid;
                }

                public void setMerClassUid(String merClassUid) {
                    this.merClassUid = merClassUid;
                }

                public String getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(String createTime) {
                    this.createTime = createTime;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }

            public static class ItemComboBean implements Serializable {
                /**
                 * bussinessid : null
                 * cinemaCode : 33111001
                 * createTime : 2018-12-28 11:46:55
                 * deleteTime : null
                 * id : 4
                 * imageUrl : https://shapp.oss-cn-hangzhou.aliyuncs.com/image/upload/201812/28hYYnLz.jpg
                 * merchandiseList : [{"businessId":1,"cinemaCode":"33111001","createTime":"2019-01-09 22:05:25","deleteTime":"2018-12-26 21:09:46","id":86,"imageUrl":"https://shapp.oss-cn-hangzhou.aliyuncs.com/image/upload/201812/27fVpOuq.jpg","itemClassId":"7","listingPrice":32,"merClassUid":"cf7dd573-26e6-42a1-9875-5ee9180776fa","merDesc":null,"merName":"中桶爆米花*1+现制水果茶*1+关东煮*1","merTypeId":4,"merUid":"3035bdee-b0fa-4e10-9e10-ea0844579aab","modifyTime":"2019-01-09 22:05:25","number":null,"originalPrice":null,"settlePrice":32,"showSeqNo":105,"valid":true,"version":44}]
                 * modifyTime : 2018-12-31 10:23:36
                 * name : 单人超值套餐
                 * originalPrice : 44.0
                 * personNum : 1
                 * salePrice : 32.0
                 * sort : 1.0
                 * status : 1
                 * valid : true
                 * version : 2
                 */

                private Object bussinessid;
                private String cinemaCode;
                private String createTime;
                private Object deleteTime;
                private int id;
                private String imageUrl;
                private String modifyTime;
                private String name;
                private double originalPrice;
                private int personNum;
                private double salePrice;
                private double sort;
                private int status;
                private boolean valid;
                private int version;
                private List<MerchandiseListBean> merchandiseList;

                public Object getBussinessid() {
                    return bussinessid;
                }

                public void setBussinessid(Object bussinessid) {
                    this.bussinessid = bussinessid;
                }

                public String getCinemaCode() {
                    return cinemaCode;
                }

                public void setCinemaCode(String cinemaCode) {
                    this.cinemaCode = cinemaCode;
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

                public double getOriginalPrice() {
                    return originalPrice;
                }

                public void setOriginalPrice(double originalPrice) {
                    this.originalPrice = originalPrice;
                }

                public int getPersonNum() {
                    return personNum;
                }

                public void setPersonNum(int personNum) {
                    this.personNum = personNum;
                }

                public double getSalePrice() {
                    return salePrice;
                }

                public void setSalePrice(double salePrice) {
                    this.salePrice = salePrice;
                }

                public double getSort() {
                    return sort;
                }

                public void setSort(double sort) {
                    this.sort = sort;
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

                public List<MerchandiseListBean> getMerchandiseList() {
                    return merchandiseList;
                }

                public void setMerchandiseList(List<MerchandiseListBean> merchandiseList) {
                    this.merchandiseList = merchandiseList;
                }

                public static class MerchandiseListBean implements Serializable {
                    /**
                     * businessId : 1
                     * cinemaCode : 33111001
                     * createTime : 2019-01-09 22:05:25
                     * deleteTime : 2018-12-26 21:09:46
                     * id : 86
                     * imageUrl : https://shapp.oss-cn-hangzhou.aliyuncs.com/image/upload/201812/27fVpOuq.jpg
                     * itemClassId : 7
                     * listingPrice : 32.0
                     * merClassUid : cf7dd573-26e6-42a1-9875-5ee9180776fa
                     * merDesc : null
                     * merName : 中桶爆米花*1+现制水果茶*1+关东煮*1
                     * merTypeId : 4
                     * merUid : 3035bdee-b0fa-4e10-9e10-ea0844579aab
                     * modifyTime : 2019-01-09 22:05:25
                     * number : null
                     * originalPrice : null
                     * settlePrice : 32.0
                     * showSeqNo : 105
                     * valid : true
                     * version : 44
                     */

                    private int businessId;
                    private String cinemaCode;
                    private String createTime;
                    private String deleteTime;
                    private int id;
                    private String imageUrl;
                    private String itemClassId;
                    private double listingPrice;
                    private String merClassUid;
                    private Object merDesc;
                    private String merName;
                    private int merTypeId;
                    private String merUid;
                    private String modifyTime;
                    private Object number;
                    private Object originalPrice;
                    private double settlePrice;
                    private int showSeqNo;
                    private boolean valid;
                    private int version;

                    public int getBusinessId() {
                        return businessId;
                    }

                    public void setBusinessId(int businessId) {
                        this.businessId = businessId;
                    }

                    public String getCinemaCode() {
                        return cinemaCode;
                    }

                    public void setCinemaCode(String cinemaCode) {
                        this.cinemaCode = cinemaCode;
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

                    public String getItemClassId() {
                        return itemClassId;
                    }

                    public void setItemClassId(String itemClassId) {
                        this.itemClassId = itemClassId;
                    }

                    public double getListingPrice() {
                        return listingPrice;
                    }

                    public void setListingPrice(double listingPrice) {
                        this.listingPrice = listingPrice;
                    }

                    public String getMerClassUid() {
                        return merClassUid;
                    }

                    public void setMerClassUid(String merClassUid) {
                        this.merClassUid = merClassUid;
                    }

                    public Object getMerDesc() {
                        return merDesc;
                    }

                    public void setMerDesc(Object merDesc) {
                        this.merDesc = merDesc;
                    }

                    public String getMerName() {
                        return merName;
                    }

                    public void setMerName(String merName) {
                        this.merName = merName;
                    }

                    public int getMerTypeId() {
                        return merTypeId;
                    }

                    public void setMerTypeId(int merTypeId) {
                        this.merTypeId = merTypeId;
                    }

                    public String getMerUid() {
                        return merUid;
                    }

                    public void setMerUid(String merUid) {
                        this.merUid = merUid;
                    }

                    public String getModifyTime() {
                        return modifyTime;
                    }

                    public void setModifyTime(String modifyTime) {
                        this.modifyTime = modifyTime;
                    }

                    public Object getNumber() {
                        return number;
                    }

                    public void setNumber(Object number) {
                        this.number = number;
                    }

                    public Object getOriginalPrice() {
                        return originalPrice;
                    }

                    public void setOriginalPrice(Object originalPrice) {
                        this.originalPrice = originalPrice;
                    }

                    public double getSettlePrice() {
                        return settlePrice;
                    }

                    public void setSettlePrice(double settlePrice) {
                        this.settlePrice = settlePrice;
                    }

                    public int getShowSeqNo() {
                        return showSeqNo;
                    }

                    public void setShowSeqNo(int showSeqNo) {
                        this.showSeqNo = showSeqNo;
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
        public static class MerTicketBean implements Serializable{
            /**
             * createTime : null
             * deleteTime : null
             * dxAppUser : {"age":null,"birthday":null,"businessid":null,"city":null,"collectNum":null,"commentNum":null,"country":null,"createTime":"2019-01-20 14:35:23","deleteTime":null,"dxInsiderInfo":{},"gender":1,"header":"","id":3,"insider":null,"lastsign":null,"loginDate":"2019-01-20 19:12:08","mobile":"17376551017","modifyTime":"2019-01-20 19:12:08","nickName":"17376551017","password":"e10adc3949ba59abbe56e057f20f883e","points":null,"province":null,"qqUserId":null,"sign":null,"signdays":null,"uuid":"4e631146-3e75-40ca-8a03-8f81caf5fd16","valid":true,"version":40,"wantseeNum":null,"watchedNum":null,"wbUserId":null,"wxOpenId":null,"wxUserId":null}
             * dxPlatTicket : {"achieveMoney":10,"amount":5,"applicationType":"","businessid":1,"changeFlag":null,"createTime":"2019-01-18 14:09:10","deleteTime":null,"endTime":"2019-03-09 00:00:00","fixedPayMoney":null,"giftType":null,"groupNumber":5,"id":2,"modifyTime":null,"name":"卖品代金券1","number":10,"platformType":1,"plusType":null,"reduceType":2,"remainNum":100,"sendNum":0,"startTime":"2018-12-31 00:00:00","status":1,"ticketDesc":"as 阿斯达","ticketType":2,"totalNum":100,"valid":true,"version":0}
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
                 * dxInsiderInfo : {}
                 * gender : 1
                 * header :
                 * id : 3
                 * insider : null
                 * lastsign : null
                 * loginDate : 2019-01-20 19:12:08
                 * mobile : 17376551017
                 * modifyTime : 2019-01-20 19:12:08
                 * nickName : 17376551017
                 * password : e10adc3949ba59abbe56e057f20f883e
                 * points : null
                 * province : null
                 * qqUserId : null
                 * sign : null
                 * signdays : null
                 * uuid : 4e631146-3e75-40ca-8a03-8f81caf5fd16
                 * valid : true
                 * version : 40
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
                private List<DxInsiderInfoBean> dxInsiderInfo;
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

                public List<DxInsiderInfoBean> getDxInsiderInfo() {
                    return dxInsiderInfo;
                }

                public void setDxInsiderInfo(List<DxInsiderInfoBean> dxInsiderInfo) {
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

                public static class DxInsiderInfoBean implements Serializable{
                }
            }

            public static class DxPlatTicketBean implements Serializable{
                /**
                 * achieveMoney : 10
                 * amount : 5
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
                 * ticketDesc : as 阿斯达
                 * ticketType : 2
                 * totalNum : 100
                 * valid : true
                 * version : 0
                 */

                private int achieveMoney;
                private int amount;
                private String applicationType;
                private int businessid;
                private Object changeFlag;
                private String createTime;
                private Object deleteTime;
                private String endTime;
                private String fixedPayMoney;
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

                public String getFixedPayMoney() {
                    return fixedPayMoney;
                }

                public void setFixedPayMoney(String fixedPayMoney) {
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

        public static class MerTicketListBean implements Serializable{
            /**
             * createTime : null
             * deleteTime : null
             * dxAppUser : {"age":null,"birthday":null,"businessid":null,"city":null,"collectNum":null,"commentNum":null,"country":null,"createTime":"2019-01-20 14:35:23","deleteTime":null,"dxInsiderInfo":{},"gender":1,"header":"","id":3,"insider":null,"lastsign":null,"loginDate":"2019-01-20 19:12:08","mobile":"17376551017","modifyTime":"2019-01-20 19:12:08","nickName":"17376551017","password":"e10adc3949ba59abbe56e057f20f883e","points":null,"province":null,"qqUserId":null,"sign":null,"signdays":null,"uuid":"4e631146-3e75-40ca-8a03-8f81caf5fd16","valid":true,"version":40,"wantseeNum":null,"watchedNum":null,"wbUserId":null,"wxOpenId":null,"wxUserId":null}
             * dxPlatTicket : {"achieveMoney":10,"amount":5,"applicationType":"","businessid":1,"changeFlag":null,"createTime":"2019-01-18 14:09:10","deleteTime":null,"endTime":"2019-03-09 00:00:00","fixedPayMoney":null,"giftType":null,"groupNumber":5,"id":2,"modifyTime":null,"name":"卖品代金券1","number":10,"platformType":1,"plusType":null,"reduceType":2,"remainNum":100,"sendNum":0,"startTime":"2018-12-31 00:00:00","status":1,"ticketDesc":"as 阿斯达","ticketType":2,"totalNum":100,"valid":true,"version":0}
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
                 * dxInsiderInfo : {}
                 * gender : 1
                 * header :
                 * id : 3
                 * insider : null
                 * lastsign : null
                 * loginDate : 2019-01-20 19:12:08
                 * mobile : 17376551017
                 * modifyTime : 2019-01-20 19:12:08
                 * nickName : 17376551017
                 * password : e10adc3949ba59abbe56e057f20f883e
                 * points : null
                 * province : null
                 * qqUserId : null
                 * sign : null
                 * signdays : null
                 * uuid : 4e631146-3e75-40ca-8a03-8f81caf5fd16
                 * valid : true
                 * version : 40
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
                private List<DxInsiderInfoBeanX> dxInsiderInfo;
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

                public List<DxInsiderInfoBeanX> getDxInsiderInfo() {
                    return dxInsiderInfo;
                }

                public void setDxInsiderInfo(List<DxInsiderInfoBeanX> dxInsiderInfo) {
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

                public static class DxInsiderInfoBeanX implements Serializable{
                }
            }

            public static class DxPlatTicketBeanX implements Serializable{
                /**
                 * achieveMoney : 10
                 * amount : 5
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
                 * ticketDesc : as 阿斯达
                 * ticketType : 2
                 * totalNum : 100
                 * valid : true
                 * version : 0
                 */

                private int achieveMoney;
                private int amount;
                private String applicationType;
                private int businessid;
                private Object changeFlag;
                private String createTime;
                private Object deleteTime;
                private String endTime;
                private String fixedPayMoney;
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

                public String getFixedPayMoney() {
                    return fixedPayMoney;
                }

                public void setFixedPayMoney(String fixedPayMoney) {
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
    public static class SeatTicketBean implements Serializable{
        /**
         * dxPlatTicket : {"platformType":1,"startTime":"2019-01-18 00:00:00","modifyTime":null,"status":1,"ticketDesc":"规则说明","amount":null,"ticketType":3,"applicationType":"","businessid":1,"giftType":null,"endTime":"2020-02-09 00:00:00","sendNum":0,"name":"观影券1","plusType":null,"version":0,"id":1,"number":10,"deleteTime":null,"valid":true,"reduceType":2,"totalNum":100,"changeFlag":null,"createTime":"2019-01-18 14:07:53","groupNumber":5,"achieveMoney":null,"remainNum":100,"fixedPayMoney":0}
         * modifyTime : null
         * status : 0
         * endTime : 2019-03-20 00:00:00
         * id : 1
         * dxAppUser : {"businessid":null,"id":2,"country":null,"signdays":null,"points":null,"version":5,"uuid":"8ce2006d-839a-4113-9f3d-22668b37b702","commentNum":null,"lastsign":null,"watchedNum":null,"dxInsiderInfo":[],"qqUserId":null,"insider":null,"birthday":null,"valid":true,"nickName":"18356302830","wantseeNum":null,"city":null,"collectNum":null,"wxUserId":null,"mobile":"18356302830","province":null,"sign":null,"wbUserId":null,"gender":1,"createTime":"2019-01-19 16:06:52","age":null,"loginDate":"2019-01-20 14:30:51","modifyTime":"2019-01-20 14:30:51","header":"","deleteTime":null,"password":"e10adc3949ba59abbe56e057f20f883e","wxOpenId":null}
         * valid : true
         * version : 0
         * startTime : 2019-01-20 00:00:00
         * createTime : null
         * deleteTime : null
         */

        private DxPlatTicketBean dxPlatTicket;
        private Object modifyTime;
        private int status;
        private String endTime;
        private int id;
        private DxAppUserBean dxAppUser;
        private boolean valid;
        private int version;
        private String startTime;
        private Object createTime;
        private Object deleteTime;

        public DxPlatTicketBean getDxPlatTicket() {
            return dxPlatTicket;
        }

        public void setDxPlatTicket(DxPlatTicketBean dxPlatTicket) {
            this.dxPlatTicket = dxPlatTicket;
        }

        public Object getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(Object modifyTime) {
            this.modifyTime = modifyTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
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

        public DxAppUserBean getDxAppUser() {
            return dxAppUser;
        }

        public void setDxAppUser(DxAppUserBean dxAppUser) {
            this.dxAppUser = dxAppUser;
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

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

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

        public static class DxPlatTicketBean implements Serializable{
            /**
             * platformType : 1
             * startTime : 2019-01-18 00:00:00
             * modifyTime : null
             * status : 1
             * ticketDesc : 规则说明
             * amount : null
             * ticketType : 3
             * applicationType :
             * businessid : 1
             * giftType : null
             * endTime : 2020-02-09 00:00:00
             * sendNum : 0
             * name : 观影券1
             * plusType : null
             * version : 0
             * id : 1
             * number : 10
             * deleteTime : null
             * valid : true
             * reduceType : 2
             * totalNum : 100
             * changeFlag : null
             * createTime : 2019-01-18 14:07:53
             * groupNumber : 5
             * achieveMoney : null
             * remainNum : 100
             * fixedPayMoney : 0
             */

            private int platformType;
            private String startTime;
            private Object modifyTime;
            private int status;
            private String ticketDesc;
            private Object amount;
            private int ticketType;
            private String applicationType;
            private int businessid;
            private Object giftType;
            private String endTime;
            private int sendNum;
            private String name;
            private Object plusType;
            private int version;
            private int id;
            private int number;
            private Object deleteTime;
            private boolean valid;
            private int reduceType;
            private int totalNum;
            private Object changeFlag;
            private String createTime;
            private int groupNumber;
            private Object achieveMoney;
            private int remainNum;
            private String fixedPayMoney;

            public int getPlatformType() {
                return platformType;
            }

            public void setPlatformType(int platformType) {
                this.platformType = platformType;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public Object getModifyTime() {
                return modifyTime;
            }

            public void setModifyTime(Object modifyTime) {
                this.modifyTime = modifyTime;
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

            public Object getAmount() {
                return amount;
            }

            public void setAmount(Object amount) {
                this.amount = amount;
            }

            public int getTicketType() {
                return ticketType;
            }

            public void setTicketType(int ticketType) {
                this.ticketType = ticketType;
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

            public Object getGiftType() {
                return giftType;
            }

            public void setGiftType(Object giftType) {
                this.giftType = giftType;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public int getSendNum() {
                return sendNum;
            }

            public void setSendNum(int sendNum) {
                this.sendNum = sendNum;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Object getPlusType() {
                return plusType;
            }

            public void setPlusType(Object plusType) {
                this.plusType = plusType;
            }

            public int getVersion() {
                return version;
            }

            public void setVersion(int version) {
                this.version = version;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public Object getDeleteTime() {
                return deleteTime;
            }

            public void setDeleteTime(Object deleteTime) {
                this.deleteTime = deleteTime;
            }

            public boolean isValid() {
                return valid;
            }

            public void setValid(boolean valid) {
                this.valid = valid;
            }

            public int getReduceType() {
                return reduceType;
            }

            public void setReduceType(int reduceType) {
                this.reduceType = reduceType;
            }

            public int getTotalNum() {
                return totalNum;
            }

            public void setTotalNum(int totalNum) {
                this.totalNum = totalNum;
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

            public int getGroupNumber() {
                return groupNumber;
            }

            public void setGroupNumber(int groupNumber) {
                this.groupNumber = groupNumber;
            }

            public Object getAchieveMoney() {
                return achieveMoney;
            }

            public void setAchieveMoney(Object achieveMoney) {
                this.achieveMoney = achieveMoney;
            }

            public int getRemainNum() {
                return remainNum;
            }

            public void setRemainNum(int remainNum) {
                this.remainNum = remainNum;
            }

            public String getFixedPayMoney() {
                return fixedPayMoney;
            }

            public void setFixedPayMoney(String fixedPayMoney) {
                this.fixedPayMoney = fixedPayMoney;
            }
        }

        public static class DxAppUserBean implements Serializable{
            /**
             * businessid : null
             * id : 2
             * country : null
             * signdays : null
             * points : null
             * version : 5
             * uuid : 8ce2006d-839a-4113-9f3d-22668b37b702
             * commentNum : null
             * lastsign : null
             * watchedNum : null
             * dxInsiderInfo : []
             * qqUserId : null
             * insider : null
             * birthday : null
             * valid : true
             * nickName : 18356302830
             * wantseeNum : null
             * city : null
             * collectNum : null
             * wxUserId : null
             * mobile : 18356302830
             * province : null
             * sign : null
             * wbUserId : null
             * gender : 1
             * createTime : 2019-01-19 16:06:52
             * age : null
             * loginDate : 2019-01-20 14:30:51
             * modifyTime : 2019-01-20 14:30:51
             * header :
             * deleteTime : null
             * password : e10adc3949ba59abbe56e057f20f883e
             * wxOpenId : null
             */

            private Object businessid;
            private int id;
            private Object country;
            private Object signdays;
            private Object points;
            private int version;
            private String uuid;
            private Object commentNum;
            private Object lastsign;
            private Object watchedNum;
            private Object qqUserId;
            private Object insider;
            private Object birthday;
            private boolean valid;
            private String nickName;
            private Object wantseeNum;
            private Object city;
            private Object collectNum;
            private Object wxUserId;
            private String mobile;
            private Object province;
            private Object sign;
            private Object wbUserId;
            private int gender;
            private String createTime;
            private Object age;
            private String loginDate;
            private String modifyTime;
            private String header;
            private Object deleteTime;
            private String password;
            private Object wxOpenId;
            private List<?> dxInsiderInfo;

            public Object getBusinessid() {
                return businessid;
            }

            public void setBusinessid(Object businessid) {
                this.businessid = businessid;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Object getCountry() {
                return country;
            }

            public void setCountry(Object country) {
                this.country = country;
            }

            public Object getSigndays() {
                return signdays;
            }

            public void setSigndays(Object signdays) {
                this.signdays = signdays;
            }

            public Object getPoints() {
                return points;
            }

            public void setPoints(Object points) {
                this.points = points;
            }

            public int getVersion() {
                return version;
            }

            public void setVersion(int version) {
                this.version = version;
            }

            public String getUuid() {
                return uuid;
            }

            public void setUuid(String uuid) {
                this.uuid = uuid;
            }

            public Object getCommentNum() {
                return commentNum;
            }

            public void setCommentNum(Object commentNum) {
                this.commentNum = commentNum;
            }

            public Object getLastsign() {
                return lastsign;
            }

            public void setLastsign(Object lastsign) {
                this.lastsign = lastsign;
            }

            public Object getWatchedNum() {
                return watchedNum;
            }

            public void setWatchedNum(Object watchedNum) {
                this.watchedNum = watchedNum;
            }

            public Object getQqUserId() {
                return qqUserId;
            }

            public void setQqUserId(Object qqUserId) {
                this.qqUserId = qqUserId;
            }

            public Object getInsider() {
                return insider;
            }

            public void setInsider(Object insider) {
                this.insider = insider;
            }

            public Object getBirthday() {
                return birthday;
            }

            public void setBirthday(Object birthday) {
                this.birthday = birthday;
            }

            public boolean isValid() {
                return valid;
            }

            public void setValid(boolean valid) {
                this.valid = valid;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public Object getWantseeNum() {
                return wantseeNum;
            }

            public void setWantseeNum(Object wantseeNum) {
                this.wantseeNum = wantseeNum;
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

            public Object getWxUserId() {
                return wxUserId;
            }

            public void setWxUserId(Object wxUserId) {
                this.wxUserId = wxUserId;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public Object getProvince() {
                return province;
            }

            public void setProvince(Object province) {
                this.province = province;
            }

            public Object getSign() {
                return sign;
            }

            public void setSign(Object sign) {
                this.sign = sign;
            }

            public Object getWbUserId() {
                return wbUserId;
            }

            public void setWbUserId(Object wbUserId) {
                this.wbUserId = wbUserId;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public Object getAge() {
                return age;
            }

            public void setAge(Object age) {
                this.age = age;
            }

            public String getLoginDate() {
                return loginDate;
            }

            public void setLoginDate(String loginDate) {
                this.loginDate = loginDate;
            }

            public String getModifyTime() {
                return modifyTime;
            }

            public void setModifyTime(String modifyTime) {
                this.modifyTime = modifyTime;
            }

            public String getHeader() {
                return header;
            }

            public void setHeader(String header) {
                this.header = header;
            }

            public Object getDeleteTime() {
                return deleteTime;
            }

            public void setDeleteTime(Object deleteTime) {
                this.deleteTime = deleteTime;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public Object getWxOpenId() {
                return wxOpenId;
            }

            public void setWxOpenId(Object wxOpenId) {
                this.wxOpenId = wxOpenId;
            }

            public List<?> getDxInsiderInfo() {
                return dxInsiderInfo;
            }

            public void setDxInsiderInfo(List<?> dxInsiderInfo) {
                this.dxInsiderInfo = dxInsiderInfo;
            }
        }
    }

    public static class ComboListBean implements Serializable{
        /**
         * id : 1
         * status : 1
         * version : 0
         * personNum : 1
         * valid : true
         * bussinessid : null
         * merchandiseList : [{"cardDiscountType":2,"modifyTime":"2019-01-16 16:44:10","originalPrice":null,"inventory":111,"packageDetail":[],"updateTime":"2012-09-01 00:00:00","businessId":1,"name":"配餐测试","version":1,"type":"package","imageUrl":null,"overInventorySellFlag":0,"id":5,"itemClassId":null,"deleteTime":null,"number":null,"onlinePrice":25,"goodsNum":null,"valid":true,"unit":"","cinemaId":31,"counterPrice":25,"createTime":"2019-01-16 16:44:06","listOrder":0,"dxId":7}]
         * imageUrl : https://hyg.happydoit.com/upload/201901/17jDlKNr.jpg
         * createTime : 2019-01-17 19:32:30
         * originalPrice : 5
         * salePrice : 5
         * deleteTime : null
         * modifyTime : null
         * sort : 0
         * cinemaId : 31
         * name : b套餐
         */

        private int id;
        private int status;
        private int version;
        private int personNum;
        private boolean valid;
        private Object bussinessid;
        private String imageUrl;
        private String createTime;
        private String originalPrice;
        private String salePrice;
        private Object deleteTime;
        private Object modifyTime;
        private int sort;
        private int cinemaId;
        private String name;
        private List<MerchandiseListBean> merchandiseList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public int getPersonNum() {
            return personNum;
        }

        public void setPersonNum(int personNum) {
            this.personNum = personNum;
        }

        public boolean isValid() {
            return valid;
        }

        public void setValid(boolean valid) {
            this.valid = valid;
        }

        public Object getBussinessid() {
            return bussinessid;
        }

        public void setBussinessid(Object bussinessid) {
            this.bussinessid = bussinessid;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getOriginalPrice() {
            return originalPrice;
        }

        public void setOriginalPrice(String originalPrice) {
            this.originalPrice = originalPrice;
        }

        public String getSalePrice() {
            return salePrice;
        }

        public void setSalePrice(String salePrice) {
            this.salePrice = salePrice;
        }

        public Object getDeleteTime() {
            return deleteTime;
        }

        public void setDeleteTime(Object deleteTime) {
            this.deleteTime = deleteTime;
        }

        public Object getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(Object modifyTime) {
            this.modifyTime = modifyTime;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getCinemaId() {
            return cinemaId;
        }

        public void setCinemaId(int cinemaId) {
            this.cinemaId = cinemaId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<MerchandiseListBean> getMerchandiseList() {
            return merchandiseList;
        }

        public void setMerchandiseList(List<MerchandiseListBean> merchandiseList) {
            this.merchandiseList = merchandiseList;
        }

        public static class MerchandiseListBean implements Serializable{
            /**
             * cardDiscountType : 2
             * modifyTime : 2019-01-16 16:44:10
             * originalPrice : null
             * inventory : 111
             * packageDetail : []
             * updateTime : 2012-09-01 00:00:00
             * businessId : 1
             * name : 配餐测试
             * version : 1
             * type : package
             * imageUrl : null
             * overInventorySellFlag : 0
             * id : 5
             * itemClassId : null
             * deleteTime : null
             * number : null
             * onlinePrice : 25
             * goodsNum : null
             * valid : true
             * unit :
             * cinemaId : 31
             * counterPrice : 25
             * createTime : 2019-01-16 16:44:06
             * listOrder : 0
             * dxId : 7
             */

            private int cardDiscountType;
            private String modifyTime;
            private Object originalPrice;
            private int inventory;
            private String updateTime;
            private int businessId;
            private String name;
            private int version;
            private String type;
            private Object imageUrl;
            private int overInventorySellFlag;
            private int id;
            private Object itemClassId;
            private Object deleteTime;
            private Object number;
            private int onlinePrice;
            private Object goodsNum;
            private boolean valid;
            private String unit;
            private int cinemaId;
            private int counterPrice;
            private String createTime;
            private int listOrder;
            private int dxId;
            private List<?> packageDetail;

            public int getCardDiscountType() {
                return cardDiscountType;
            }

            public void setCardDiscountType(int cardDiscountType) {
                this.cardDiscountType = cardDiscountType;
            }

            public String getModifyTime() {
                return modifyTime;
            }

            public void setModifyTime(String modifyTime) {
                this.modifyTime = modifyTime;
            }

            public Object getOriginalPrice() {
                return originalPrice;
            }

            public void setOriginalPrice(Object originalPrice) {
                this.originalPrice = originalPrice;
            }

            public int getInventory() {
                return inventory;
            }

            public void setInventory(int inventory) {
                this.inventory = inventory;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public int getBusinessId() {
                return businessId;
            }

            public void setBusinessId(int businessId) {
                this.businessId = businessId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getVersion() {
                return version;
            }

            public void setVersion(int version) {
                this.version = version;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public Object getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(Object imageUrl) {
                this.imageUrl = imageUrl;
            }

            public int getOverInventorySellFlag() {
                return overInventorySellFlag;
            }

            public void setOverInventorySellFlag(int overInventorySellFlag) {
                this.overInventorySellFlag = overInventorySellFlag;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Object getItemClassId() {
                return itemClassId;
            }

            public void setItemClassId(Object itemClassId) {
                this.itemClassId = itemClassId;
            }

            public Object getDeleteTime() {
                return deleteTime;
            }

            public void setDeleteTime(Object deleteTime) {
                this.deleteTime = deleteTime;
            }

            public Object getNumber() {
                return number;
            }

            public void setNumber(Object number) {
                this.number = number;
            }

            public int getOnlinePrice() {
                return onlinePrice;
            }

            public void setOnlinePrice(int onlinePrice) {
                this.onlinePrice = onlinePrice;
            }

            public Object getGoodsNum() {
                return goodsNum;
            }

            public void setGoodsNum(Object goodsNum) {
                this.goodsNum = goodsNum;
            }

            public boolean isValid() {
                return valid;
            }

            public void setValid(boolean valid) {
                this.valid = valid;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public int getCinemaId() {
                return cinemaId;
            }

            public void setCinemaId(int cinemaId) {
                this.cinemaId = cinemaId;
            }

            public int getCounterPrice() {
                return counterPrice;
            }

            public void setCounterPrice(int counterPrice) {
                this.counterPrice = counterPrice;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getListOrder() {
                return listOrder;
            }

            public void setListOrder(int listOrder) {
                this.listOrder = listOrder;
            }

            public int getDxId() {
                return dxId;
            }

            public void setDxId(int dxId) {
                this.dxId = dxId;
            }

            public List<?> getPackageDetail() {
                return packageDetail;
            }

            public void setPackageDetail(List<?> packageDetail) {
                this.packageDetail = packageDetail;
            }
        }
    }

    public static class SeatTicketListBean implements Serializable{
        /**
         * dxPlatTicket : {"platformType":1,"startTime":"2019-01-18 00:00:00","modifyTime":null,"status":1,"ticketDesc":"规则说明","amount":null,"ticketType":3,"applicationType":"","businessid":1,"giftType":null,"endTime":"2020-02-09 00:00:00","sendNum":0,"name":"观影券1","plusType":null,"version":0,"id":1,"number":10,"deleteTime":null,"valid":true,"reduceType":2,"totalNum":100,"changeFlag":null,"createTime":"2019-01-18 14:07:53","groupNumber":5,"achieveMoney":null,"remainNum":100,"fixedPayMoney":0}
         * modifyTime : null
         * status : 0
         * endTime : 2019-03-20 00:00:00
         * id : 1
         * dxAppUser : {"businessid":null,"id":2,"country":null,"signdays":null,"points":null,"version":5,"uuid":"8ce2006d-839a-4113-9f3d-22668b37b702","commentNum":null,"lastsign":null,"watchedNum":null,"dxInsiderInfo":[],"qqUserId":null,"insider":null,"birthday":null,"valid":true,"nickName":"18356302830","wantseeNum":null,"city":null,"collectNum":null,"wxUserId":null,"mobile":"18356302830","province":null,"sign":null,"wbUserId":null,"gender":1,"createTime":"2019-01-19 16:06:52","age":null,"loginDate":"2019-01-20 14:30:51","modifyTime":"2019-01-20 14:30:51","header":"","deleteTime":null,"password":"e10adc3949ba59abbe56e057f20f883e","wxOpenId":null}
         * valid : true
         * version : 0
         * startTime : 2019-01-20 00:00:00
         * createTime : null
         * deleteTime : null
         */

        private DxPlatTicketBeanX dxPlatTicket;
        private Object modifyTime;
        private int status;
        private String endTime;
        private int id;
        private DxAppUserBeanX dxAppUser;
        private boolean valid;
        private int version;
        private String startTime;
        private Object createTime;
        private Object deleteTime;

        public DxPlatTicketBeanX getDxPlatTicket() {
            return dxPlatTicket;
        }

        public void setDxPlatTicket(DxPlatTicketBeanX dxPlatTicket) {
            this.dxPlatTicket = dxPlatTicket;
        }

        public Object getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(Object modifyTime) {
            this.modifyTime = modifyTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
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

        public DxAppUserBeanX getDxAppUser() {
            return dxAppUser;
        }

        public void setDxAppUser(DxAppUserBeanX dxAppUser) {
            this.dxAppUser = dxAppUser;
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

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

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

        public static class DxPlatTicketBeanX implements Serializable{
            /**
             * platformType : 1
             * startTime : 2019-01-18 00:00:00
             * modifyTime : null
             * status : 1
             * ticketDesc : 规则说明
             * amount : null
             * ticketType : 3
             * applicationType :
             * businessid : 1
             * giftType : null
             * endTime : 2020-02-09 00:00:00
             * sendNum : 0
             * name : 观影券1
             * plusType : null
             * version : 0
             * id : 1
             * number : 10
             * deleteTime : null
             * valid : true
             * reduceType : 2
             * totalNum : 100
             * changeFlag : null
             * createTime : 2019-01-18 14:07:53
             * groupNumber : 5
             * achieveMoney : null
             * remainNum : 100
             * fixedPayMoney : 0
             */

            private int platformType;
            private String startTime;
            private Object modifyTime;
            private int status;
            private String ticketDesc;
            private String amount;
            private int ticketType;
            private String applicationType;
            private int businessid;
            private Object giftType;
            private String endTime;
            private int sendNum;
            private String name;
            private Object plusType;
            private int version;
            private int id;
            private int number;
            private Object deleteTime;
            private boolean valid;
            private int reduceType;
            private int totalNum;
            private Object changeFlag;
            private String createTime;
            private int groupNumber;
            private String achieveMoney;
            private int remainNum;
            private int fixedPayMoney;

            public int getPlatformType() {
                return platformType;
            }

            public void setPlatformType(int platformType) {
                this.platformType = platformType;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public Object getModifyTime() {
                return modifyTime;
            }

            public void setModifyTime(Object modifyTime) {
                this.modifyTime = modifyTime;
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

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public int getTicketType() {
                return ticketType;
            }

            public void setTicketType(int ticketType) {
                this.ticketType = ticketType;
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

            public Object getGiftType() {
                return giftType;
            }

            public void setGiftType(Object giftType) {
                this.giftType = giftType;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public int getSendNum() {
                return sendNum;
            }

            public void setSendNum(int sendNum) {
                this.sendNum = sendNum;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Object getPlusType() {
                return plusType;
            }

            public void setPlusType(Object plusType) {
                this.plusType = plusType;
            }

            public int getVersion() {
                return version;
            }

            public void setVersion(int version) {
                this.version = version;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public Object getDeleteTime() {
                return deleteTime;
            }

            public void setDeleteTime(Object deleteTime) {
                this.deleteTime = deleteTime;
            }

            public boolean isValid() {
                return valid;
            }

            public void setValid(boolean valid) {
                this.valid = valid;
            }

            public int getReduceType() {
                return reduceType;
            }

            public void setReduceType(int reduceType) {
                this.reduceType = reduceType;
            }

            public int getTotalNum() {
                return totalNum;
            }

            public void setTotalNum(int totalNum) {
                this.totalNum = totalNum;
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

            public int getGroupNumber() {
                return groupNumber;
            }

            public void setGroupNumber(int groupNumber) {
                this.groupNumber = groupNumber;
            }

            public String getAchieveMoney() {
                return achieveMoney;
            }

            public void setAchieveMoney(String achieveMoney) {
                this.achieveMoney = achieveMoney;
            }

            public int getRemainNum() {
                return remainNum;
            }

            public void setRemainNum(int remainNum) {
                this.remainNum = remainNum;
            }

            public int getFixedPayMoney() {
                return fixedPayMoney;
            }

            public void setFixedPayMoney(int fixedPayMoney) {
                this.fixedPayMoney = fixedPayMoney;
            }
        }

        public static class DxAppUserBeanX implements Serializable{
            /**
             * businessid : null
             * id : 2
             * country : null
             * signdays : null
             * points : null
             * version : 5
             * uuid : 8ce2006d-839a-4113-9f3d-22668b37b702
             * commentNum : null
             * lastsign : null
             * watchedNum : null
             * dxInsiderInfo : []
             * qqUserId : null
             * insider : null
             * birthday : null
             * valid : true
             * nickName : 18356302830
             * wantseeNum : null
             * city : null
             * collectNum : null
             * wxUserId : null
             * mobile : 18356302830
             * province : null
             * sign : null
             * wbUserId : null
             * gender : 1
             * createTime : 2019-01-19 16:06:52
             * age : null
             * loginDate : 2019-01-20 14:30:51
             * modifyTime : 2019-01-20 14:30:51
             * header :
             * deleteTime : null
             * password : e10adc3949ba59abbe56e057f20f883e
             * wxOpenId : null
             */

            private Object businessid;
            private int id;
            private Object country;
            private Object signdays;
            private Object points;
            private int version;
            private String uuid;
            private Object commentNum;
            private Object lastsign;
            private Object watchedNum;
            private Object qqUserId;
            private Object insider;
            private Object birthday;
            private boolean valid;
            private String nickName;
            private Object wantseeNum;
            private Object city;
            private Object collectNum;
            private Object wxUserId;
            private String mobile;
            private Object province;
            private Object sign;
            private Object wbUserId;
            private int gender;
            private String createTime;
            private Object age;
            private String loginDate;
            private String modifyTime;
            private String header;
            private Object deleteTime;
            private String password;
            private Object wxOpenId;
            private List<?> dxInsiderInfo;

            public Object getBusinessid() {
                return businessid;
            }

            public void setBusinessid(Object businessid) {
                this.businessid = businessid;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Object getCountry() {
                return country;
            }

            public void setCountry(Object country) {
                this.country = country;
            }

            public Object getSigndays() {
                return signdays;
            }

            public void setSigndays(Object signdays) {
                this.signdays = signdays;
            }

            public Object getPoints() {
                return points;
            }

            public void setPoints(Object points) {
                this.points = points;
            }

            public int getVersion() {
                return version;
            }

            public void setVersion(int version) {
                this.version = version;
            }

            public String getUuid() {
                return uuid;
            }

            public void setUuid(String uuid) {
                this.uuid = uuid;
            }

            public Object getCommentNum() {
                return commentNum;
            }

            public void setCommentNum(Object commentNum) {
                this.commentNum = commentNum;
            }

            public Object getLastsign() {
                return lastsign;
            }

            public void setLastsign(Object lastsign) {
                this.lastsign = lastsign;
            }

            public Object getWatchedNum() {
                return watchedNum;
            }

            public void setWatchedNum(Object watchedNum) {
                this.watchedNum = watchedNum;
            }

            public Object getQqUserId() {
                return qqUserId;
            }

            public void setQqUserId(Object qqUserId) {
                this.qqUserId = qqUserId;
            }

            public Object getInsider() {
                return insider;
            }

            public void setInsider(Object insider) {
                this.insider = insider;
            }

            public Object getBirthday() {
                return birthday;
            }

            public void setBirthday(Object birthday) {
                this.birthday = birthday;
            }

            public boolean isValid() {
                return valid;
            }

            public void setValid(boolean valid) {
                this.valid = valid;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public Object getWantseeNum() {
                return wantseeNum;
            }

            public void setWantseeNum(Object wantseeNum) {
                this.wantseeNum = wantseeNum;
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

            public Object getWxUserId() {
                return wxUserId;
            }

            public void setWxUserId(Object wxUserId) {
                this.wxUserId = wxUserId;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public Object getProvince() {
                return province;
            }

            public void setProvince(Object province) {
                this.province = province;
            }

            public Object getSign() {
                return sign;
            }

            public void setSign(Object sign) {
                this.sign = sign;
            }

            public Object getWbUserId() {
                return wbUserId;
            }

            public void setWbUserId(Object wbUserId) {
                this.wbUserId = wbUserId;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public Object getAge() {
                return age;
            }

            public void setAge(Object age) {
                this.age = age;
            }

            public String getLoginDate() {
                return loginDate;
            }

            public void setLoginDate(String loginDate) {
                this.loginDate = loginDate;
            }

            public String getModifyTime() {
                return modifyTime;
            }

            public void setModifyTime(String modifyTime) {
                this.modifyTime = modifyTime;
            }

            public String getHeader() {
                return header;
            }

            public void setHeader(String header) {
                this.header = header;
            }

            public Object getDeleteTime() {
                return deleteTime;
            }

            public void setDeleteTime(Object deleteTime) {
                this.deleteTime = deleteTime;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public Object getWxOpenId() {
                return wxOpenId;
            }

            public void setWxOpenId(Object wxOpenId) {
                this.wxOpenId = wxOpenId;
            }

            public List<?> getDxInsiderInfo() {
                return dxInsiderInfo;
            }

            public void setDxInsiderInfo(List<?> dxInsiderInfo) {
                this.dxInsiderInfo = dxInsiderInfo;
            }
        }
    }
}
