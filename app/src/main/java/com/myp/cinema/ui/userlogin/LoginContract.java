package com.myp.cinema.ui.userlogin;

import com.myp.cinema.entity.UserBO;
import com.myp.cinema.entity.threelandingBo;
import com.myp.cinema.mvp.BasePresenter;
import com.myp.cinema.mvp.BaseRequestView;

/**
 * MVPPlugin
 * Wuliang
 * <p>
 * 登陆页面的业务控制
 */

public class LoginContract {

    interface View extends BaseRequestView {

        void getUser(UserBO data);
        void getUserid(threelandingBo data,int style);

    }

    interface Presenter extends BasePresenter<View> {
        void loadUserLogin(String phone, String password);
        void userLoginid(String wxUserId,String wbUserId,String qqUserId,int style);


    }
}
