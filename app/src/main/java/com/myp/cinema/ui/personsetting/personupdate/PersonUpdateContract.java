package com.myp.cinema.ui.personsetting.personupdate;

import android.content.Context;

import com.myp.cinema.entity.UserBO;
import com.myp.cinema.mvp.BasePresenter;
import com.myp.cinema.mvp.BaseRequestView;
import com.myp.cinema.mvp.BaseView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class PersonUpdateContract {
    interface View extends BaseRequestView {

        void getData(UserBO userBO);

    }

    interface Presenter extends BasePresenter<View> {

        void updatePassWord(String oldPass, String newPass);


        void updateName(String name);

    }
}
