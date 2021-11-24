package com.myp.cinema.ui.somesuccess;


import android.os.Bundle;
import androidx.annotation.Nullable;
import android.widget.TextView;

import com.myp.cinema.R;
import com.myp.cinema.mvp.MVPBaseActivity;
import com.myp.cinema.widget.MyListView;

import butterknife.Bind;


/**
 * 发布邀约成功界面
 */

public class ReleaseSuccessActivity extends MVPBaseActivity<ReleaseSuccessContract.View,
        ReleaseSuccessPresenter> implements ReleaseSuccessContract.View {

    @Bind(R.id.time_and_peoplenum)
    TextView timeAndPeoplenum;
    @Bind(R.id.movies_name)
    TextView moviesName;
    @Bind(R.id.cinema_address)
    TextView cinemaAddress;
    @Bind(R.id.list)
    MyListView list;
    @Bind(R.id.remain_time)
    TextView remainTime;

    @Override
    protected int getLayout() {
        return R.layout.act_release_success;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("发布邀约");
    }
}
