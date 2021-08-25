package com.myp.cinema.ui.hotsellprodect;


import com.myp.cinema.api.HttpInterfaceIml;
import com.myp.cinema.entity.HotSellBO;
import com.myp.cinema.entity.HotSellBannerBO;
import com.myp.cinema.entity.ProdectBO;
import com.myp.cinema.mvp.BasePresenterImpl;

import java.util.List;

import rx.Subscriber;

/**
 * Created by Witness on 2019/1/3
 * Describe:
 */
public class HotSellPresenter extends BasePresenterImpl<HotSellContract.View>
                                implements HotSellContract.Presenter{
    @Override
    public void loadHotSellData(String cinemaCode) {
        HttpInterfaceIml.getHotSell(cinemaCode).subscribe(new Subscriber<List<HotSellBO>>() {
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
            public void onNext(List<HotSellBO> s) {
                if (mView == null)
                    return;

                mView.getHotSellData(s);
            }
        });
    }

    @Override
    public void loadBanners(String cinemaCode, String category) {
        HttpInterfaceIml.getBanner(cinemaCode,category).subscribe(new Subscriber<List<HotSellBannerBO>>() {
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
            public void onNext(List<HotSellBannerBO> s) {
                if (mView == null)
                    return;

                mView.getBanners(s);
            }
        });
    }

    @Override
    public void getOrderPrice(String cinemaId,String merchandiseinfo,String merTicketId) {
        HttpInterfaceIml.getOrderPrice(cinemaId,merchandiseinfo,merTicketId).subscribe(new Subscriber<ProdectBO>() {
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
            public void onNext(ProdectBO s) {
                if (mView == null)
                    return;

                mView.getOrder(s);
            }
        });
    }
}
