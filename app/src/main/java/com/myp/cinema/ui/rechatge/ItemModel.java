package com.myp.cinema.ui.rechatge;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/27.
 */
public class ItemModel implements Serializable {
    //左上角三角图标
    public static final int ONE = 1001;
    //textview布局
    public static final int TWO = 1002;
    //edittext布局
    public static final int THREE = 1003;
    public int type;
    public Object data;
    //是否免费的标志
    public Object dataa;
    public boolean isFree;
    public String activity;//充值活动
    public ItemModel(int type, Object data, Object dataa,boolean isFree,String activity) {
        this.type = type;
        this.data = data;
        this.dataa = dataa;
        this.activity = activity;
    }
}