package com.myp.cinema.ui.balance;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.myp.cinema.R;
import com.myp.cinema.entity.RechBo;
import com.myp.cinema.entity.SumptionBo;
import com.myp.cinema.mvp.MVPBaseFragment;
import com.myp.cinema.util.LogUtils;
import com.myp.cinema.widget.superadapter.CommonAdapter;
import com.myp.cinema.widget.superadapter.ViewHolder;
import com.myp.cinema.widget.swiferefresh.SwipeRefreshVie;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/1/28.
 * 消费明细界面
 */

public class onsumptiondetailfragment extends MVPBaseFragment<onsumptiondetailContract.View, onsumptiondetailPresenter>
        implements onsumptiondetailContract.View{
    private String cardNum;
    @Bind(R.id.list)
    ListView listview;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    private ArrayList<SumptionBo> data= new ArrayList<>();
    private CommonAdapter<SumptionBo> adapter;
    private int page = 1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.onsumptiondetaillayout, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.loadcosumption(1,cardNum);
        setPullRefresher();
        adapter();
    }
    private void adapter() {
        adapter = new CommonAdapter<SumptionBo>(getActivity(),
                R.layout.item_onsumption, data) {
            @Override
            protected void convert(ViewHolder helper, SumptionBo item, int position) {
                helper.setText(R.id.shijian, item.getPayDate());
                helper.setText(R.id.chongzhijine,"-"+ String.valueOf(item.getTicketRealPrice())+"元");
                helper.setText(R.id.card_num,"共计"+ String.valueOf(item.getTicketNum())+"张");

            }
        };
    }
    private void setPullRefresher(){
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.loadcosumption(1,cardNum);
                page=1;
                smartRefreshLayout.finishRefresh(1000);
                refreshlayout.finishRefresh(2000);
            }
        });
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                mPresenter.loadcosumption(page,cardNum);
                smartRefreshLayout.finishLoadmore(1000);
                refreshlayout.finishLoadmore(2000);
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
    public void getcosumption(List<SumptionBo> sumptionBo, int pages) {
        if (pages == 1) {
            data.clear();
            data.addAll(sumptionBo);
            listview.setAdapter(adapter);
        } else {
            data.addAll(sumptionBo);
            adapter.setmDatas(data);
        }
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        cardNum = ((balance) activity).getTitles();
    }

}

