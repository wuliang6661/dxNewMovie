package com.myp.cinema.ui.selectorcity;


import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;

import com.mcxtzhang.indexlib.IndexBar.widget.IndexBar;
import com.mcxtzhang.indexlib.suspension.SuspensionDecoration;
import com.myp.cinema.R;
import com.myp.cinema.entity.CityBO;
import com.myp.cinema.mvp.MVPBaseActivity;
import com.myp.cinema.util.LogUtils;

import java.util.List;

import butterknife.Bind;


/**
 * MVPPlugin
 * 选择城市
 */

public class SelectorCityActivity extends MVPBaseActivity<SelectorCityContract.View,
        SelectorCityPresenter> implements SelectorCityContract.View {

    @Bind(R.id.rv)
    RecyclerView mRv;
    @Bind(R.id.indexBar)
    IndexBar mIndexBar;
    @Bind(R.id.tvSideBarHint)
    TextView mTvSideBarHint;

    private CityAdapter mAdapter;
    private LinearLayoutManager mManager;
    private SuspensionDecoration mDecoration;

    List<CityBO> mDatas;

    @Override
    protected int getLayout() {
        return R.layout.act_selector_city;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("选择城市");

        mPresenter.loadCityList();
    }

    private void setDatas() {
        mRv.setLayoutManager(mManager = new LinearLayoutManager(this));
        mAdapter = new CityAdapter(this, mDatas);
        mRv.setAdapter(mAdapter);
        mRv.addItemDecoration(mDecoration = new SuspensionDecoration(this, mDatas));
        //如果add两个，那么按照先后顺序，依次渲染。
        mRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        //使用indexBar
        mTvSideBarHint = (TextView) findViewById(R.id.tvSideBarHint);//HintTextView
        mIndexBar = (IndexBar) findViewById(R.id.indexBar);//IndexBar

        mIndexBar.setmPressedShowTextView(mTvSideBarHint)//设置HintTextView
                .setNeedRealIndex(false)//设置需要真实的索引
                .setmLayoutManager(mManager);//设置RecyclerView的LayoutManager

        mIndexBar.setmSourceDatas(mDatas)//设置数据
                .invalidate();
        mAdapter.setDatas(mDatas);
        mDecoration.setmDatas(mDatas);
        mAdapter.setOnClickItem(new CityAdapter.OnClickItem() {
            @Override
            public void onClick(CityBO cityBO) {
                Intent intent = new Intent();
                intent.putExtra("city", cityBO);
                setResult(1, intent);
                finish();
            }
        });
    }


    @Override
    public void onRequestError(String msg) {
        LogUtils.showToast(msg);
    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void getCityList(List<CityBO> cityBOs) {
        this.mDatas = cityBOs;
        setDatas();
    }
}
