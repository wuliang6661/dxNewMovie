package com.myp.cinema.ui.main.home.movieslist;

import com.myp.cinema.api.HttpInterfaceIml;
import com.myp.cinema.entity.ActivityBO;
import com.myp.cinema.entity.LunBoAndBO;
import com.myp.cinema.entity.MoviesByCidBO;
import com.myp.cinema.mvp.BasePresenterImpl;

import java.util.List;

import rx.Subscriber;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MoviesListPresenter extends BasePresenterImpl<MoviesListContract.View>
        implements MoviesListContract.Presenter {


    @Override
    public void moviesHot(String cinemaId) {
        HttpInterfaceIml.moviesHot(cinemaId).subscribe(new Subscriber<List<MoviesByCidBO>>() {
            @Override
            public void onCompleted() {
                if (mView == null)
                    return;
                mView.onRequestEnd();
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null)
                    return;
                mView.onRequestError(e.getMessage());
            }

            @Override
            public void onNext(List<MoviesByCidBO> s) {
                if (mView == null)
                    return;

                mView.getMoviesHot(s);
            }
        });
    }

    @Override
    public void lunboList(String souce, String cinemaId) {
        HttpInterfaceIml.lunboandList(souce, cinemaId).subscribe(new Subscriber<LunBoAndBO>() {
            @Override
            public void onCompleted() {
                if (mView == null)
                    return;
                mView.onRequestEnd();
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null)
                    return;
//                mView.onRequestError(e.getMessage());
            }

            @Override
            public void onNext(LunBoAndBO lunBoAndBOs) {
                if (mView == null)
                    return;
                mView.getLunBo(lunBoAndBOs);
            }
        });
    }

    @Override
    public void loadActivity(String cinemaId) {
        HttpInterfaceIml.getActivity(cinemaId).subscribe(new Subscriber<ActivityBO>() {
            @Override
            public void onCompleted() {
                if (mView == null)
                    return;
                mView.onRequestEnd();
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null)
                    return;
//                mView.onRequestError(e.getMessage());
            }

            @Override
            public void onNext(ActivityBO activityBO) {
                if (mView == null)
                    return;
                mView.getActivity(activityBO);
            }
        });
    }
}
