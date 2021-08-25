package com.myp.cinema.ui.main.store;

import com.myp.cinema.api.HttpInterfaceIml;
import com.myp.cinema.entity.LunBoBO;
import com.myp.cinema.entity.ShopBO;
import com.myp.cinema.mvp.BasePresenterImpl;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import rx.Subscriber;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class StorePresenter extends BasePresenterImpl<StoreContract.View>
        implements StoreContract.Presenter {

//    int pageNo = 1;

    @Override
    public void lunboList(String scoce,String cinemaId) {
        HttpInterfaceIml.lunboList(scoce,cinemaId).subscribe(new Subscriber<List<LunBoBO>>() {
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

    @Override
    public void loadCreditsShop(String cinemaId, final int  pageNo) {
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
                    mView.getShopList(s,pageNo);
                }
            }
        });
    }

    @Override
    public void getScore(String appUserId) {
        HttpInterfaceIml.getCoins(appUserId).subscribe(new Subscriber<ResponseBody>() {
            @Override
            public void onCompleted() {
                if (mView != null) {
                    mView.onRequestEnd();
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView != null) {
                    mView.onRequestError(e.getMessage());
                }
            }

            @Override
            public void onNext(ResponseBody s) {
                if (mView == null)
                    return;
                try {
                    mView.getScore(s);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
