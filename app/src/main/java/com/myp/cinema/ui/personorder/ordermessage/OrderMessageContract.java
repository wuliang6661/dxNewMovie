package com.myp.cinema.ui.personorder.ordermessage;

import com.myp.cinema.entity.OrderDetailBO;
import com.myp.cinema.mvp.BasePresenter;
import com.myp.cinema.mvp.BaseRequestView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class OrderMessageContract {
    interface View extends BaseRequestView {

        void getOrderMessage(OrderDetailBO orderMessage);
    }

    interface Presenter extends BasePresenter<View> {

        /**
         * 查询订单详情
         */
        void quryOrderMessage(String id, String cinemaId);
    }
}
