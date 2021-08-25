package com.myp.cinema.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/2/27.
 */
public class CriticBO implements Serializable {

    /**
     * comment : hello
     * createTime : 2018-01-30 13:40:44
     * dxAppUser : {"header":"http://192.168.1.64:8080/upload/dxheader/1503380225138.jpg","nickName":"17826866256"}
     * id : 14
     * leftNum : -1
     * score : 10
     * upvoteNum : 0
     * upvoteStatus : 0
     */

    private String comment;
    private String createTime;
    private DxAppUserBo dxAppUser;
    private Long id;
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public DxAppUserBo getDxAppUser() {
        return dxAppUser;
    }

    public void setDxAppUser(DxAppUserBo dxAppUser) {
        this.dxAppUser = dxAppUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLeftNum() {
        return leftNum;
    }

    public void setLeftNum(String leftNum) {
        this.leftNum = leftNum;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getUpvoteNum() {
        return upvoteNum;
    }

    public void setUpvoteNum(Integer upvoteNum) {
        this.upvoteNum = upvoteNum;
    }

    public Integer getUpvoteStatus() {
        return upvoteStatus;
    }

    public void setUpvoteStatus(Integer upvoteStatus) {
        this.upvoteStatus = upvoteStatus;
    }

    private String leftNum;
    private Double score;
    private Integer upvoteNum;
    private Integer upvoteStatus;



    public static class DxAppUserBo {
        /**
         * header : http://192.168.1.64:8080/upload/dxheader/1503380225138.jpg
         * nickName : 17826866256
         */

        private String header;
        private String nickName;

        public String getHeader() {
            return header;
        }

        public void setHeader(String header) {
            this.header = header;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }
    }
}
