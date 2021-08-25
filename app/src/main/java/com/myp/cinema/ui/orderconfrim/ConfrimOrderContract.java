package com.myp.cinema.ui.orderconfrim;

import com.myp.cinema.entity.CardBO;
import com.myp.cinema.entity.ConfirmPayBO;
import com.myp.cinema.entity.LockSeatsBO;
import com.myp.cinema.entity.OrderNumBO;
import com.myp.cinema.entity.PayBO;
import com.myp.cinema.entity.PayCardBO;
import com.myp.cinema.entity.ResuBo;
import com.myp.cinema.entity.WXPayBO;
import com.myp.cinema.mvp.BasePresenter;
import com.myp.cinema.mvp.BaseRequestView;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ConfrimOrderContract {
    interface View extends BaseRequestView {

        void getOrder(ConfirmPayBO orderBO);

        void getZeroOrder(ConfirmPayBO orderBO);

        void getModifyInfo(ResponseBody responseBody) throws IOException, JSONException;

        /**
         * 会员卡列表
         *
         * @param cardBOs
         */
        void getCardList(List<CardBO> cardBOs);

        /**
         * 获取会员卡价格
         *
         * @param payCardBO
         */
        void getCardPrice(PayCardBO payCardBO);

        void getPayCard(ResuBo pay);

        void getOrderCancle(OrderNumBO orderNumBO);

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
         * 获取微信返回
         */
        void verifyCoupon(ResponseBody responseBody) throws IOException, JSONException;


        /**
         * 观影券兑换
         */
        void couponExchange(ResponseBody responseBody) throws IOException, JSONException;

    }

    interface Presenter extends BasePresenter<View> {

        void loadSubmitOrder(String cinemaId,String orderName,String seats,String seatId,String ticketOriginPrice,String ticketNum,
                             String cinemaNumber,String hallId,String playId,String cineMovieNum,String seatTicketId,
                             String merchandiseInfo,String merTicketId,String orderPhone,String memo,String preferPrice,
                             String globalPreferPrice,String globalCanBuyNum);

        /**
         * 修改订单价格
         * @param partnerPrice
         * @param marketPrice
         * @param activityPriceNum
         * @param ticketNum
         * @param beforeTicketPrice
         * @param totalDisprice
         * @param payPrice
         * @param seatTicketId
         * @param merchandiseInfo
         * @param appUserId
         */
        void modifyOrderPrice(String cinemaId,String partnerPrice,String marketPrice,String activityPriceNum,String ticketNum,
                              String beforeTicketPrice,String totalDisprice,String payPrice,String seatTicketId,
                              String merTicketId,String merchandiseInfo,String appUserId,String preferPrice,
                              String globalPreferPrice,String globalCanBuyNum,String payType);


        void loadZeroSubmitOrder(String cinemaId,String orderName,String seats,String seatId,String ticketOriginPrice,String ticketNum,
                             String cinemaNumber,String hallId,String playId,String cineMovieNum,String seatTicketId,
                             String merchandiseInfo,String merTicketId,String orderPhone,String memo,String preferPrice,
                             String globalPreferPrice,String globalCanBuyNum);

        void loadCardUser();


        /**
         * 获取会员卡支付价格
         *
         * @param orderNum
         * @param card
         */
        void loadCardPay(String orderNum, String card);


        /**
         * 会员卡支付
         *
         * @param orderNum
         * @param pwd
         */
        void payCard(String orderNum, String pwd, String card);


        /**
         * 取消订单
         */
        void orderCancle(String orderNum);


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
         * 校验观影券
         */
        void verifyCoupon(String coupon,String orderNum);

        /**
         * 观影券兑换
         */
        void couponExchange(String orderNum,String coupon);

    }
}
