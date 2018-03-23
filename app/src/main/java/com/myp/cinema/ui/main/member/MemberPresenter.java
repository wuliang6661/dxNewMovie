package com.myp.cinema.ui.main.member;

import com.myp.cinema.api.HttpInterfaceIml;
import com.myp.cinema.entity.UserBO;
import com.myp.cinema.mvp.BasePresenterImpl;

import rx.Subscriber;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MemberPresenter extends BasePresenterImpl<MemberContract.View>
        implements MemberContract.Presenter {

    @Override
    public void loadMemberRecord(String appUserId) {
        HttpInterfaceIml.memberRecord(appUserId).subscribe(new Subscriber<UserBO>() {
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
            public void onNext(UserBO s) {
                if (mView != null) {
                    mView.getUser(s);
                }
            }
        });
    }
}
