package com.myp.cinema.ui.personcoupon;

import com.myp.cinema.entity.UserCouponBO;
import com.myp.cinema.mvp.BasePresenter;
import com.myp.cinema.mvp.BaseRequestView;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class PersonCouponContract {
    interface View extends BaseRequestView {

        void getCoupon(List<UserCouponBO> couponList);

        void getAddResult(ResponseBody responseBody) throws IOException, JSONException;
    }

    interface Presenter extends BasePresenter<View> {

        void loadPersonCoupon(int pageNo,int pageSize);

        void addPersonCoupon(String code);
    }
}
