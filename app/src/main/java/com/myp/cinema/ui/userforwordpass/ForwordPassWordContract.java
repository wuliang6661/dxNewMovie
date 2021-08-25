package com.myp.cinema.ui.userforwordpass;

import com.myp.cinema.entity.UserBO;
import com.myp.cinema.mvp.BasePresenter;
import com.myp.cinema.mvp.BaseRequestView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ForwordPassWordContract {
    interface View extends BaseRequestView {

        void getUserBo(UserBO userBO);

    }

    interface Presenter extends BasePresenter<View> {

        /**
         * @param passWord
         * @param pwd      旧登陆密码（不传此参数，表示忘记密码时新增密码）
         */
        void loadForWordPassWord(String passWord, String pwd);
    }
}
