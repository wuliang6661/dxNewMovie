package com.myp.cinema.ui.phonecode2two;

import com.myp.cinema.entity.threelandingBo;
import com.myp.cinema.mvp.BasePresenter;
import com.myp.cinema.mvp.BaseRequestView;

/**
 * Created by Administrator on 2018/1/14.
 */


public class phonecode2twoContract {
    interface View extends BaseRequestView {
        void getThirdregistBo(threelandingBo thirdregistBo);
    }

    interface Presenter extends BasePresenter<View> {
        void thirdregist(String cinemaId,String mobile,String pwd,String header,String nickname,String gender,String wxUserId,String wbUserId,String qqUserId);
    }
}