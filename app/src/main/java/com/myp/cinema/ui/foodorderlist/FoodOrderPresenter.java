package com.myp.cinema.ui.foodorderlist;

import com.myp.cinema.api.HttpInterfaceIml;
import com.myp.cinema.entity.LockSeatsBO;
import com.myp.cinema.mvp.BasePresenterImpl;

import java.util.List;

import rx.Subscriber;

/**
 * Created by Witness on 2019/1/22
 * Describe:
 */
public class FoodOrderPresenter extends BasePresenterImpl<FoodOrderContract.View>
                    implements FoodOrderContract.Presenter{
    @Override
    public void loadOrderList(String orderType, String payStatus, int orderPage, String orderSize) {
        HttpInterfaceIml.orderList(orderType, payStatus,orderPage,orderSize).subscribe(new Subscriber<List<LockSeatsBO>>() {
            @Override
            public void onCompleted() {
                if (mView == null)
                    return;
                mView.onRequestEnd();
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null)
                    return;
                mView.onRequestError(e.getMessage());
            }

            @Override
            public void onNext(List<LockSeatsBO> s) {
                if (mView == null)
                    return;
                mView.getOrderList(s);
            }
        });
    }
}
