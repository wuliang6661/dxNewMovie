package com.myp.cinema.ui.personorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.myp.cinema.R;
import com.myp.cinema.entity.OrderBO;
import com.myp.cinema.mvp.MVPBaseFragment;
import com.myp.cinema.ui.personorder.notpaymessage.NotPayMessageActivity;
import com.myp.cinema.ui.personorder.ordermessage.OrderMessageActivity;
import com.myp.cinema.util.CimemaUtils;
import com.myp.cinema.util.LogUtils;
import com.myp.cinema.util.StringUtils;
import com.myp.cinema.widget.superadapter.CommonAdapter;
import com.myp.cinema.widget.superadapter.ViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/2/11.已完成订单
 */

public class completed extends MVPBaseFragment<completedContract.View, completedPresenter>
        implements completedContract.View ,AdapterView.OnItemClickListener {
    @Bind(R.id.list)
    ListView list;
    @Bind(R.id.text_layout)
    TextView textlayout;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @Bind(R.id.none_layout)
    LinearLayout nonelayout;
    CommonAdapter<OrderBO> adapter;
    private List<OrderBO> data= new ArrayList<>();
    private int page = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movielayout, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.loadOrderList( 1);
        list.setOnItemClickListener(this);
        setPullRefresher();
        adapter();
    }
    private void adapter() {
        adapter = new CommonAdapter<OrderBO>(getActivity(), R.layout.item_order, data) {
            @Override
            protected void convert(ViewHolder helper, OrderBO item, int position) {
                helper.setText(R.id.movies_name, item.getDxMovie().getMovieName());
                helper.setText(R.id.movies_type, item.getDxMovie().getMovieDimensional() +
                        item.getDxMovie().getMovieLanguage());
                helper.setText(R.id.movies_address, item.getCinemaName() + " " + item.getHallName());
                helper.setText(R.id.movies_seat, CimemaUtils.getSeats(item.getSeats()));
                helper.setText(R.id.movies_time, item.getPlayName().substring(0, item.getPlayName().length() - 3));
                helper.setText(R.id.movies_num, item.getTicketNum());
                if ("3".equals(item.getPayStatus())||"1".equals(item.getPayStatus())) {   //已完成的票价
                    helper.setText(R.id.order_price, "总价：¥" + item.getTicketRealPrice());
                    if(item.getRefundStatus().equals("1")){
                        helper.setVisible(R.id.biaoshi, true);
                    }else {

                        helper.setVisible(R.id.biaoshi, false);
                    }
                }
                if (StringUtils.isEmpty(item.getDxMovie().getPicture())) {
                    helper.setImageResource(R.id.movies_img, R.color.act_bg01);
                } else {
                    helper.setImageUrl(R.id.movies_img, item.getDxMovie().getPicture());
                }
            }
        };
    }

    private void setPullRefresher(){
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.loadOrderList( 1);
                page=1;
                smartRefreshLayout.finishRefresh(1000);
                refreshlayout.finishRefresh(2000);
            }
        });
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                mPresenter.loadOrderList( page);
                smartRefreshLayout.finishLoadmore(1000);
                refreshlayout.finishLoadmore(2000);
            }
        });
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("order", data.get(position));
        bundle.putString("refundStatus", data.get(position).getRefundStatus());
        bundle.putString("cinemaId", data.get(position).getCinemaId());
        bundle.putString("id", data.get(position).getId());
        gotoActivity(OrderMessageActivity.class, bundle, false);

    }

    @Override
    public void onRequestError(String msg) {
        LogUtils.showToast(msg);
    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void getOrderList(List<OrderBO> orderList, int pages) {
        if(pages==1){
            if(orderList.size()==0){
                smartRefreshLayout.setVisibility(View.GONE);
                nonelayout.setVisibility(View.VISIBLE);
                textlayout.setText("您当前没有已完成的订单");
            }else {
                data.clear();
                data.addAll(orderList);
                list.setAdapter(adapter);
            }

        }else {
            data.addAll(orderList);
            adapter.setmDatas(data);
        }

    }
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}