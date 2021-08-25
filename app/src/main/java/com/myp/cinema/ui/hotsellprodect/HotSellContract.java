package com.myp.cinema.ui.hotsellprodect;

import com.myp.cinema.entity.HotSellBO;
import com.myp.cinema.entity.HotSellBannerBO;
import com.myp.cinema.entity.ProdectBO;
import com.myp.cinema.mvp.BasePresenter;
import com.myp.cinema.mvp.BaseRequestView;

import java.util.List;

/**
 * Created by Witness on 2019/1/3
 * Describe:
 */
public class HotSellContract {

    interface View extends BaseRequestView {
        void getHotSellData(List<HotSellBO> hotSellBO);

        void getBanners(List<HotSellBannerBO> hotSellBO);

        void getOrder(ProdectBO prodectBO);
    }

    interface Presenter extends BasePresenter<View> {
        void loadHotSellData(String cinemaCode);

        void loadBanners(String cinemaCode, String category);

        void getOrderPrice(String cinemaId,String merchandiseinfo, String merTicketId);
    }
}
