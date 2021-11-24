package com.myp.cinema.ui.main.hotwire;


import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myp.cinema.R;
import com.myp.cinema.base.BaseFragment;
import com.myp.cinema.ui.main.hotwire.consultationFragment.consultationFragment;
import com.myp.cinema.ui.main.hotwire.welfareFragment.welfareFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 好消息fragment
 */

public class HotwireFragment extends BaseFragment{
    @Bind(R.id.movie)
    TextView movie;
    @Bind(R.id.article)
    TextView article;
    @Bind(R.id.go_back)
    LinearLayout goBack;
    @Bind(R.id.title)
    TextView title;
    private welfareFragment fg2;
    private consultationFragment fg3;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_hotwire, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        goBack.setVisibility(View.GONE);
        title.setText("福利");
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
        FragmentTransaction fragmentTransaction =  getFragmentManager().beginTransaction();
        hideFragments(fragmentTransaction);//隐藏所有fragment
        switch (index) {
            case 1:
                if (fg2 == null) {
                    fg2 = new welfareFragment();
                    fragmentTransaction.add(R.id.frameLayout, fg2);
                } else {
                    fragmentTransaction.show(fg2);
                }
                break;
            case 2:
                if (fg3 == null) {
                    fg3 = new consultationFragment();
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