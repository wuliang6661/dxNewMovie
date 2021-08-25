package com.myp.cinema.ui.hotsellprodect;

import com.myp.cinema.entity.FeedBackListBO;
import com.myp.cinema.mvp.BasePresenter;
import com.myp.cinema.mvp.BaseRequestView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class SortDetailContract {
    interface View extends BaseRequestView {
        void getSortDetailListBO(List<FeedBackListBO> feedBackListBOs);
    }

    interface Presenter extends BasePresenter<View> {
        void loadSortDetail();
    }
}
