package com.myp.cinema.ui.feedbacklist;

import com.myp.cinema.api.HttpInterfaceIml;
import com.myp.cinema.entity.FeedBackListBO;
import com.myp.cinema.mvp.BasePresenterImpl;

import java.util.List;

import rx.Subscriber;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class FeedBackListPresenter extends BasePresenterImpl<FeedBackListContract.View> implements FeedBackListContract.Presenter{

    @Override
    public void loadFeedBackList() {
        HttpInterfaceIml.feedBackList().subscribe(new Subscriber<List<FeedBackListBO>>() {
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
            public void onNext(List<FeedBackListBO> s) {
                if (mView == null)
                    return;
                mView.getFeedBackListBO(s);
            }
        });
    }
}
