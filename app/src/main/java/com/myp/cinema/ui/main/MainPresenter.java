package com.myp.cinema.ui.main;


import com.myp.cinema.api.HttpInterfaceIml;
import com.myp.cinema.entity.CinemaBo;
import com.myp.cinema.mvp.BasePresenterImpl;

import java.util.List;

import rx.Subscriber;

/**
 * MVPPlugin
 */

public class MainPresenter extends BasePresenterImpl<MainContract.View> implements MainContract.Presenter {

    @Override
    public void loadCinemaIds(String city, String location, String lacation) {
        HttpInterfaceIml.cinemaList(city, location, lacation).subscribe(new Subscriber<List<CinemaBo>>() {
            @Override
            public void onCompleted() {
                if (mView == null)
                    return;
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null)
                    return;
//                mView.onRequestError(e.getMessage());
            }

            @Override
            public void onNext(List<CinemaBo> s) {
                if (mView == null)
                    return;
                mView.onData(s);
            }
        });
    }
}