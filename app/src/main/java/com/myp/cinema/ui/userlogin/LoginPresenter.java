package com.myp.cinema.ui.userlogin;


import com.myp.cinema.api.HttpInterfaceIml;
import com.myp.cinema.entity.UserBO;
import com.myp.cinema.entity.threelandingBo;
import com.myp.cinema.mvp.BasePresenterImpl;

import rx.Subscriber;

/**
 * MVPPlugin
 * �wuliang
 * <p>
 * 数据请求处理类 (业务处理)
 */

public class LoginPresenter extends BasePresenterImpl<LoginContract.View>
        implements LoginContract.Presenter {

    @Override
    public void loadUserLogin(String phone, String password) {
        HttpInterfaceIml.userLogin(phone, password, null).subscribe(new Subscriber<UserBO>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (mView == null)
                    return;
                mView.onRequestError(e.getMessage());
            }

            @Override
            public void onNext(UserBO s) {
                if (mView == null)
                    return;
                mView.getUser(s);
            }
        });
    }
    @Override
    public void userLoginid(String wxUserId, String wbUserId, String qqUserId, final int style) {
        HttpInterfaceIml.userLoginid(wxUserId, wbUserId, qqUserId).subscribe(new Subscriber<threelandingBo>() {
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable e) {
                if (mView == null)
                    return;
                mView.onRequestError(e.getMessage());
            }
            @Override
            public void onNext(threelandingBo s) {
                if (mView == null)
                    return;
                mView.getUserid(s,style);
            }
        });
    }

}
