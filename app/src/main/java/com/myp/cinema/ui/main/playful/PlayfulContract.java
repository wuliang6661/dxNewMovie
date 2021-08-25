package com.myp.cinema.ui.main.playful;

import com.myp.cinema.entity.LunBoBO;
import com.myp.cinema.entity.ShopBO;
import com.myp.cinema.mvp.BasePresenter;
import com.myp.cinema.mvp.BaseRequestView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class PlayfulContract {
    interface View extends BaseRequestView {
        void getLunBo(List<LunBoBO> lunBoBOs);

        void getShopList(List<ShopBO> shops);

    }

    interface  Presenter extends BasePresenter<View> {
        void lunboList(String scoce,String cinemaId);
        void loadCreditsShop(String cinemaId, boolean isPageAdd);
    }
}
