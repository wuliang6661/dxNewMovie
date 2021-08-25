package com.myp.cinema.entity;

import java.io.Serializable;

/**
 * Created by wuliang on 2017/6/6.
 * <p>
 * 用户bean
 */

public class UserBO implements Serializable {


    /**
     * age : null
     * city : null
     * country : null
     * createTime : 2017-06-06 13:22:05
     * deleteTime : null
     * gender : 1
     * header : null
     * id : 11
     * loginDate : null
     * mobile : 15629127249
     * modifyTime : null
     * nickName : null
     * password : null
     * province : null
     * sign : null
     * valid : true
     * version : 0
     * uuid
     */

    private String age;
    private String city;
    private String country;
    private String createTime;
    private String deleteTime;
    private String gender;
    private String header;
    private String id;
    private String loginDate;
    private String mobile;
    private String modifyTime;
    private String nickName;
    private String password;
    private String province;
    private String sign;
    private String valid;
    private String version;
    private String uuid;
    private String collectNum;
    private String commentNum;
    private String wantseeNum;
    private String watchedNum;
    private String alertPhoto;

    public String getAlertPhoto() {
        return alertPhoto;
    }

    public void setAlertPhoto(String alertPhoto) {
        this.alertPhoto = alertPhoto;
    }

    public String getValid() {
        return valid;
    }

    public String getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(String collectNum) {
        this.collectNum = collectNum;
    }

    public String getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(String commentNum) {
        this.commentNum = commentNum;
    }

    public String getWantseeNum() {
        return wantseeNum;
    }

    public void setWantseeNum(String wantseeNum) {
        this.wantseeNum = wantseeNum;
    }

    public String getWatchedNum() {
        return watchedNum;
    }

    public void setWatchedNum(String watchedNum) {
        this.watchedNum = watchedNum;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(String deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String isValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
