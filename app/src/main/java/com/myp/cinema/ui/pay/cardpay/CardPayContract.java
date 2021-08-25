package com.myp.cinema.ui.pay.cardpay;

import com.myp.cinema.entity.CardBO;
import com.myp.cinema.entity.PayCardBO;
import com.myp.cinema.entity.ResuBo;
import com.myp.cinema.mvp.BasePresenter;
import com.myp.cinema.mvp.BaseRequestView;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class CardPayContract {
    interface View extends BaseRequestView {

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
