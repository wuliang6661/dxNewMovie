package com.myp.cinema.ui.main.playful;

import com.myp.cinema.api.HttpInterfaceIml;
import com.myp.cinema.entity.LunBoBO;
import com.myp.cinema.entity.ShopBO;
import com.myp.cinema.mvp.BasePresenterImpl;

import java.util.List;

import rx.Subscriber;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class PlayfulPresenter extends BasePresenterImpl<PlayfulContract.View> implements PlayfulContract.Presenter{

    int pageNo = 1;
    @Override
    public void loadCreditsShop(String cinemaId, boolean isPageAdd) {
        if (isPageAdd) {
            pageNo++;
        } else {
            pageNo = 1;
        }
        HttpInterfaceIml.creditsShop(pageNo + "", cinemaId).subscribe(new Subscriber<List<ShopBO>>() {
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
            public void onNext(List<ShopBO> s) {
                if (mView != null) {
                    mView.getShopList(s);
                }
            }
        });
    }
    @Override
    public void lunboList(String souce, String cinemaId) {
        HttpInterfaceIml.lunboList(souce, cinemaId).subscribe(new Subscriber<List<LunBoBO>>() {
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
            public void onNext(List<LunBoBO> s) {
                if (mView == null)
                    return;
                mView.getLunBo(s);
            }
        });
    }

    
}
