package com.myp.cinema.ui.pay.mentpay;

import com.myp.cinema.entity.PayBO;
import com.myp.cinema.entity.ResuBo;
import com.myp.cinema.entity.WXPayBO;
import com.myp.cinema.mvp.BasePresenter;
import com.myp.cinema.mvp.BaseRequestView;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MentPayContract {
    interface View extends BaseRequestView {

        /**
         * 获取支付宝返回
         *
         * @param orderInfo
         */
        void getAliPay(PayBO orderInfo);

        /**
         * 获取微信返回
         */
        void getWxPay(WXPayBO wxPayBO);
        /**
         * 获取会员卡返回
         */
        void getcardPay(ResponseBody resuBo) throws JSONException, IOException;
    }

    interface Presenter extends BasePresenter<View> {

        /**
         * 支付宝支付
         *
         * @param orderNum
         */
        void loadAliPay(String orderNum);

        /**
         * 微信支付
         */
        void loadWeChatPay(String orderNum);
        /**
         * 会员卡支付
         */
        void loadcardPay(String orderNum,String coupon);

    }
}
