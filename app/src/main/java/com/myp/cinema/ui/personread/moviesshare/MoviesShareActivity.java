package com.myp.cinema.ui.personread.moviesshare;


import android.os.Bundle;
import androidx.annotation.Nullable;

import com.myp.cinema.R;
import com.myp.cinema.mvp.MVPBaseActivity;


/**
 * MVPPlugin
 * 电影分享页面
 */

public class MoviesShareActivity extends MVPBaseActivity<MoviesShareContract.View,
        MoviesSharePresenter> implements MoviesShareContract.View {

    @Override
    protected int getLayout() {
        return R.layout.act_share_movies;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("分享");

    }
}
