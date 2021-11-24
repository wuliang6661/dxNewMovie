package com.myp.cinema.ui.userlogin;

import android.app.Activity;

import com.myp.cinema.config.LocalConfiguration;
import com.myp.cinema.util.LogUtils;
import com.tencent.connect.UserInfo;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

/**
 * Created by wuliang on 2017/7/19.
 * <p>
 * 第三方登陆的工具类
 */

public class LoginUtils {

    private Tencent mTencent; //qq主操作对象
    private String scope; //获取信息的范围参数
    private UserInfo userInfo; //qq用户信息
    private Activity context;

    public LoginUtils(Activity context) {
        this.context = context;
        //初始化qq主操作对象
        mTencent = Tencent.createInstance(LocalConfiguration.QQ_APP_ID, context.getApplicationContext(), "com.myp.cinema.fileprovider");
        //要所有权限，不然会再次申请增量权限，这里不要设置成get_user_info,add_t
        scope = "all";
    }


    public void login() {
        //如果session无效，就开始登录
        if (!mTencent.isSessionValid()) {
            //开始qq授权登录
            mTencent.login(context, scope, loginListener);
        }
    }


    IUiListener loginListener = new IUiListener() {
        @Override
        public void onError(UiError arg0) {
        }

        /**
         * 返回json数据样例
         *
         * {"ret":0,"pay_token":"D3D678728DC580FBCDE15722B72E7365",
         * "pf":"desktop_m_qq-10000144-android-2002-",
         * "query_authority_cost":448,
         * "authority_cost":-136792089,
         * "openid":"015A22DED93BD15E0E6B0DDB3E59DE2D",
         * "expires_in":7776000,
         * "pfkey":"6068ea1c4a716d4141bca0ddb3df1bb9",
         * "msg":"",
         * "access_token":"A2455F491478233529D0106D2CE6EB45",
         * "login_cost":499}
         */
        @Override
        public void onComplete(Object value) {
            if (value == null) {
                return;
            }
            try {
                JSONObject jo = (JSONObject) value;
                int ret = jo.getInt("ret");
                System.out.println("json=" + String.valueOf(jo));
                if (ret == 0) {
                    LogUtils.showToast("登录成功");
                    String openID = jo.getString("openid");
                    String accessToken = jo.getString("access_token");
                    String expires = jo.getString("expires_in");
                    mTencent.setOpenId(openID);
                    mTencent.setAccessToken(accessToken, expires);
                    userInfo = new UserInfo(context, mTencent.getQQToken());
                    userInfo.getUserInfo(userInfoListener);
                }
            } catch (Exception e) {
            }
        }

        @Override
        public void onCancel() {
        }

        @Override
        public void onWarning(int i) {

        }
    };

    IUiListener userInfoListener = new IUiListener() {

        @Override
        public void onError(UiError arg0) {
        }

        /**
         * 返回用户信息样例
         *
         * {"is_yellow_year_vip":"0","ret":0,
         * "figureurl_qq_1":"http:\/\/q.qlogo.cn\/qqapp\/1104732758\/015A22DED93BD15E0E6B0DDB3E59DE2D\/40",
         * "figureurl_qq_2":"http:\/\/q.qlogo.cn\/qqapp\/1104732758\/015A22DED93BD15E0E6B0DDB3E59DE2D\/100",
         * "nickname":"攀爬←蜗牛","yellow_vip_level":"0","is_lost":0,"msg":"",
         * "city":"黄冈","
         * figureurl_1":"http:\/\/qzapp.qlogo.cn\/qzapp\/1104732758\/015A22DED93BD15E0E6B0DDB3E59DE2D\/50",
         * "vip":"0","level":"0",
         * "figureurl_2":"http:\/\/qzapp.qlogo.cn\/qzapp\/1104732758\/015A22DED93BD15E0E6B0DDB3E59DE2D\/100",
         * "province":"湖北",
         * "is_yellow_vip":"0","gender":"男",
         * "figureurl":"http:\/\/qzapp.qlogo.cn\/qzapp\/1104732758\/015A22DED93BD15E0E6B0DDB3E59DE2D\/30"}
         */
        @Override
        public void onComplete(Object arg0) {
            if (arg0 == null) {
                return;
            }
            try {
                JSONObject jo = (JSONObject) arg0;
                int ret = jo.getInt("ret");
                System.out.println("json=" + String.valueOf(jo));
                String nickName = jo.getString("nickname");
                String gender = jo.getString("gender");
                LogUtils.showToast("你好，" + nickName);
            } catch (Exception e) {
            }
        }

        @Override
        public void onCancel() {
        }

        @Override
        public void onWarning(int i) {

        }
    };
}
