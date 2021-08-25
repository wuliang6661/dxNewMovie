package com.myp.cinema.ui.main.store;

import com.myp.cinema.entity.LunBoBO;
import com.myp.cinema.entity.ShopBO;
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

public class StoreContract {
    interface View extends BaseRequestView {

        void getLunBo(List<LunBoBO> lunBoBOs);

        void getShopList(List<ShopBO> shops, int pageNo);

        void getScore(ResponseBody responseBody) throws JSONException, IOException;
    }

    interface Presenter extends BasePresenter<View> {

        void lunboList(String scoce, String cinemaId);

        void loadCreditsShop(String cinemaId, int pageNo);

        void getScore(String appUserId);

    }
}
