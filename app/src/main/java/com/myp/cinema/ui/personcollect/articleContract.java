package com.myp.cinema.ui.personcollect;

import com.myp.cinema.entity.HotWireBO;
import com.myp.cinema.mvp.BasePresenter;
import com.myp.cinema.mvp.BaseRequestView;

import java.util.List;

/**
 * Created by Administrator on 2018/1/20.
 */

public class articleContract {
    interface View extends BaseRequestView {

        void getCollection(List<HotWireBO> hotWireBOs, int pageNo);
    }

    interface Presenter extends BasePresenter<articleContract.View> {

        void loadCollection(int pageNo);
    }
}
