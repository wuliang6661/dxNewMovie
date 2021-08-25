package com.myp.cinema.ui.personcoupon;

import com.myp.cinema.api.HttpInterfaceIml;
import com.myp.cinema.entity.UserCouponBO;
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

public class PersonCouponPresenter extends BasePresenterImpl<PersonCouponContract.View>
        implements PersonCouponContract.Presenter {

    @Override
    public void loadPersonCoupon(int pageNo, int pageSize) {
        HttpInterfaceIml.personCoupon(pageNo,pageSize).subscribe(new Subscriber<List<UserCouponBO>>() {
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
            public void onNext(List<UserCouponBO> s) {
                if (mView == null)
                    return;
                mView.getCoupon(s);
            }
        });
    }

    @Override
    public void addPersonCoupon(String code) {
        HttpInterfaceIml.personAddCoupon(code).subscribe(new Subscriber<ResponseBody>() {
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
            public void onNext(ResponseBody responseBody) {
                if (mView == null)
                    return;
                try {
                    mView.getAddResult(responseBody);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
