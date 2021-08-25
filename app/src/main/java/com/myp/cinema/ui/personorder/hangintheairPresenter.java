package com.myp.cinema.ui.personorder;

import com.myp.cinema.api.HttpInterfaceIml;
import com.myp.cinema.entity.LockSeatsBO;
import com.myp.cinema.mvp.BasePresenterImpl;

import java.util.List;

import rx.Subscriber;

/**
 * Created by Administrator on 2018/2/11.
 */

public class hangintheairPresenter extends BasePresenterImpl<hangintheairContract.View> implements hangintheairContract.Presenter{

    @Override
    public void loadOrderList(String orderType,String payStatus,int orderPage,String orderSize) {
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
