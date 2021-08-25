package com.myp.cinema.ui.detailed;

import com.myp.cinema.entity.RecentBean;
import com.myp.cinema.entity.SumptionBo;
import com.myp.cinema.mvp.BasePresenter;
import com.myp.cinema.mvp.BaseRequestView;

import java.util.List;

/**
 * Created by Administrator on 2018/1/28.
 */

public class RecentContract {
    interface View extends BaseRequestView {
        void getcosumption(List<RecentBean> sumptionBo, int pageNo);

    }

    interface Presenter extends BasePresenter<View> {

        void loadcosumption(int pageNo, String cardNum);
    }
}

