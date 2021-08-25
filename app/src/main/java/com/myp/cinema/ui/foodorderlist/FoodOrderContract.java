package com.myp.cinema.ui.foodorderlist;

import com.myp.cinema.entity.LockSeatsBO;
import com.myp.cinema.mvp.BasePresenter;
import com.myp.cinema.mvp.BaseRequestView;

import java.util.List;

/**
 * Created by Witness on 2019/1/22
 * Describe:
 */
public class FoodOrderContract {
    interface View extends BaseRequestView {

        void getOrderList(List<LockSeatsBO> orderList);
    }

    interface Presenter extends BasePresenter<View> {

        void loadOrderList(String orderType,String payStatus,int orderPage,String orderSize);
    }
}
