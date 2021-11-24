package com.myp.cinema.ui.personcollect;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import android.view.View;

import android.widget.FrameLayout;
import android.widget.TextView;

import com.myp.cinema.R;
import com.myp.cinema.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/1/19.
 */

public class PersonCollectActivity2 extends BaseActivity {
    @Bind(R.id.movie)
    TextView movie;
    @Bind(R.id.article)
    TextView article;
    @Bind(R.id.frameLayout)
    FrameLayout fragment;
    private movieFragment fg2;
    private articleFragment fg3;
    @Override
    protected int getLayout() {
        return R.layout.collectactivity;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("我的收藏");
        setChioceItem(1);
        movie.setTextColor(Color.parseColor("#FFFFFF"));
        article.setTextColor(Color.parseColor("#32e8e8"));
        movie.setBackgroundColor(Color.parseColor("#32b8e8"));
        article.setBackgroundColor(Color.parseColor("#FFFFFF"));
    }

    @OnClick({R.id.movie, R.id.article})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.movie:
                movie.setTextColor(Color.parseColor("#FFFFFF"));
                article.setTextColor(Color.parseColor("#32e8e8"));
                movie.setBackgroundColor(Color.parseColor("#32b8e8"));
                article.setBackgroundColor(Color.parseColor("#FFFFFF"));
                setChioceItem(1);
                break;
            case R.id.article:
                movie.setTextColor(Color.parseColor("#32e8e8"));
                article.setTextColor(Color.parseColor("#FFFFFF"));
                article.setBackgroundColor(Color.parseColor("#32b8e8"));
                movie.setBackgroundColor(Color.parseColor("#FFFFFF"));
                setChioceItem(2);
                break;
        }
    }
    private void setChioceItem(int index) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideFragments(fragmentTransaction);//隐藏所有fragment
        switch (index) {
            case 1:
                if (fg2 == null) {
                    fg2 = new movieFragment();
                    fragmentTransaction.add(R.id.frameLayout, fg2);
                } else {
                    fragmentTransaction.show(fg2);
                }
                break;
            case 2:
                if (fg3 == null) {
                    fg3 = new articleFragment();
                    fragmentTransaction.add(R.id.frameLayout, fg3);
                } else {
                    fragmentTransaction.show(fg3);
                }
                break;

        }
        fragmentTransaction.commit(); // 提交
    }
    /**
     * 隐藏Fragment
     *
     * @param fragmentTransaction
     */
    private void hideFragments(FragmentTransaction fragmentTransaction) {
        if (fg2 != null) {
            fragmentTransaction.hide(fg2);
        }
        if (fg3 != null) {
            fragmentTransaction.hide(fg3);
        }

    }
}

