package com.myp.cinema.ui.personread.persongrade;

import com.myp.cinema.mvp.BasePresenter;
import com.myp.cinema.mvp.BaseRequestView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class PersonGradeContract {
    interface View extends BaseRequestView {

        void submitSurcess();
    }

    interface Presenter extends BasePresenter<View> {

        void loadSubmitComment(String appId, String movieId, String souce, String comment);
    }
}
