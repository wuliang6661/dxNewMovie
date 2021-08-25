package com.myp.cinema.ui.prodectorder;


import com.myp.cinema.entity.CardBO;
import com.myp.cinema.entity.PayBO;
import com.myp.cinema.entity.PayCardBO;
import com.myp.cinema.entity.ProdectBO;
import com.myp.cinema.entity.ResuBo;
import com.myp.cinema.entity.SubmitPrdectOrderBO;
import com.myp.cinema.entity.WXPayBO;
import com.myp.cinema.mvp.BasePresenter;
import com.myp.cinema.mvp.BaseRequestView;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ProdectOrderContract {
    interface View extends BaseRequestView {

        void getOrder(ProdectBO prodectBO);

        void getSubmitOrderResult(SubmitPrdectOrderBO submitPrdectOrderBO);

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
        void getcardPay(ResuBo resuBo);


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


    }

    interface Presenter extends BasePresenter<View> {


        void getOrderPrice(String cinemaId,String merchandiseinfo,String merTicketId);

        void submitOrder(String cinemaId,String merchandiseinfo,String phone,String memo,String cinemaNumber,String merTicketId);

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


    }
}