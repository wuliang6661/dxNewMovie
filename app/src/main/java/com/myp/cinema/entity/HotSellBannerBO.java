package com.myp.cinema.entity;

import java.io.Serializable;

/**
 * Created by Witness on 2019/1/3
 * Describe: 热卖小食界面轮播图
 */
public class HotSellBannerBO implements Serializable {
    /**
     * dxMovie : null
     * imageUrl : https://shapp.oss-cn-hangzhou.aliyuncs.com/image/upload/201812/26qAXzLz.jpg
     * playType : 1
     * redirectUrl : https://mp.weixin.qq.com/s/ChpOw3KZTc6u2dD0s_yzNw
     */

    private Object dxMovie;
    private String imageUrl;
    private int playType;
    private String redirectUrl;

    public Object getDxMovie() {
        return dxMovie;
    }

    public void setDxMovie(Object dxMovie) {
        this.dxMovie = dxMovie;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getPlayType() {
        return playType;
    }

    public void setPlayType(int playType) {
        this.playType = playType;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
