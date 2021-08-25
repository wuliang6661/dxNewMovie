package com.myp.cinema.ui.personorder;

import com.myp.cinema.entity.LockSeatsBO;
import com.myp.cinema.mvp.BasePresenter;
import com.myp.cinema.mvp.BaseRequestView;

import java.util.List;

/**
 * Created by Administrator on 2018/2/11.
 */

public class hangintheairContract {
    interface View extends BaseRequestView {

        void getOrderList(List<LockSeatsBO> orderList);
    }

    interface Presenter extends BasePresenter<View> {

        void loadOrderList(String orderType,String payStatus,int orderPage,String orderSize);
    }
}
