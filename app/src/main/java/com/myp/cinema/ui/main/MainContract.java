package com.myp.cinema.ui.main;

import com.myp.cinema.entity.CinemaBo;
import com.myp.cinema.mvp.BasePresenter;
import com.myp.cinema.mvp.BaseRequestView;

import java.util.List;

/**
 * MVPPlugin
 * �wuliang
 * <p>
 * 主页的业务接口
 */

public class MainContract {
    interface View extends BaseRequestView {

        void onData(List<CinemaBo> cinemaIdBOs);

    }

    interface Presenter extends BasePresenter<View> {

        /**
         * 获取影厅列表
         */
        void loadCinemaIds(String city,String location,String lacation);
    }
}
