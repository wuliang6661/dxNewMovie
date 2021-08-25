package com.myp.cinema.ui.main.home.movieslist;

import com.myp.cinema.entity.ActivityBO;
import com.myp.cinema.entity.LunBoAndBO;
import com.myp.cinema.entity.MoviesByCidBO;
import com.myp.cinema.mvp.BasePresenter;
import com.myp.cinema.mvp.BaseRequestView;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MoviesListContract {
    interface View extends BaseRequestView {

        void getMoviesHot(List<MoviesByCidBO> moviesHotBO);

        void getLunBo(LunBoAndBO lunBoAndBOs);

        void getActivity(ActivityBO activityBO);
    }

    interface Presenter extends BasePresenter<View> {

        void moviesHot(String cinemaId);

        void lunboList(String scoce,String cinemaId);

        void loadActivity(String cinemaId);
    }
}
