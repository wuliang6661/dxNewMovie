package com.myp.cinema.ui.feedbacklist;

import com.myp.cinema.entity.FeedBackListBO;
import com.myp.cinema.mvp.BasePresenter;
import com.myp.cinema.mvp.BaseRequestView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class FeedBackListContract {
    interface View extends BaseRequestView {
        void getFeedBackListBO(List<FeedBackListBO> feedBackListBOs);
    }

    interface  Presenter extends BasePresenter<View> {
        void loadFeedBackList();
    }
}
