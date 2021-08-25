package com.myp.cinema.config;

/**
 * Created by wuliang on 2017/3/6.
 * <p>
 * 程序的公共环境配置，或公共字段存放
 */

public class LocalConfiguration {

    //app的SharedPreferences缓存名字
    public static final String SP_NAME = "CINEMA_SP";
    public static  String SESSION ="";
    // 测试微信应用的 appId
//    public static final String WEIXIN_APP_ID = "wxb18d1464762457a0";
//    public static final String APP_SECRET = "da24ec8811c4a4a2d71ae352a37af0f0";

    //正式微信appId
    public static final String WEIXIN_APP_ID = "wx77fa35bb9adaed43";
    public static final String APP_SECRET = "31a74a452ca545e56945d9942f4fca48";

    //QQ的appId
    public static final String QQ_APP_ID = "1106256112";

    public static String orderNum;   //支付成功的订单号，微信支付时要用
    public static String cardcode;
    public static String isShouye;
    /**
     * 版本下载到手机的位置
     */
    public static final String DOWNLOAD_PATH = "/data/data/com.myp.cinema/download";
    public static final String appFileName = "cinema.apk";  //版本
    public static int isVoucher = -1;
    public static int ordertype = -1;//订单类型  0 电影票订单  1 食品订单

}
