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
import com.myp.cinema.entity.LockSeatsBO;
import com.myp.cinema.mvp.MVPBaseFragment;
import com.myp.cinema.ui.personorder.notpaymessage.NotPayMessageActivity;
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
 * Created by Administrator on 2018/2/11.
 */

public class hangintheair  extends MVPBaseFragment<hangintheairContract.View, hangintheairPresenter>
        implements hangintheairContract.View ,AdapterView.OnItemClickListener {
    @Bind(R.id.list)
    ListView list;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @Bind(R.id.none_layout)
    LinearLayout nonelayout;
    @Bind(R.id.text_layout)
    TextView textlayout;

    CommonAdapter<LockSeatsBO> adapter;
    private List<LockSeatsBO> data= new ArrayList<>();
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
        mPresenter.loadOrderList( "0","0",page,"10");
        list.setOnItemClickListener(this);
        setPullRefresher();
        adapter();
    }
    private void adapter() {
        adapter = new CommonAdapter<LockSeatsBO>(getActivity(), R.layout.item_order, data) {
            @Override
            protected void convert(ViewHolder helper, LockSeatsBO item, int position) {
                helper.setText(R.id.movies_name, item.getDxMovie().getMovieName());
                helper.setText(R.id.movies_type, item.getDxMovie().getMovieDimensional() +
                        item.getDxMovie().getMovieLanguage());
                helper.setText(R.id.movies_address, item.getCinemaName() + " " + item.getHallName());
                helper.setText(R.id.movies_seat, CimemaUtils.getSeats(item.getSeats()));
                helper.setText(R.id.movies_time, item.getPlayName().substring(0, item.getPlayName().length() - 3));
                helper.setText(R.id.movies_num, String.valueOf(item.getTicketNum()));
                if (0 == item.getPayStatus()) {  //未完成的票价
                    helper.setText(R.id.order_price, "总价：¥" + item.getPayPrice());
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
                page=1;
                mPresenter.loadOrderList( "0","0",page,"10");
                smartRefreshLayout.finishRefresh(1000);
                refreshlayout.finishRefresh(2000);
            }
        });
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                mPresenter.loadOrderList( "0","0",page,"10");
                smartRefreshLayout.finishLoadmore(1000);
                refreshlayout.finishLoadmore(2000);
            }
        });
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("order", data.get(position));
        Intent intent = new Intent(getActivity(), NotPayMessageActivity.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, 1);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            mPresenter.loadOrderList( "0","0",page,"10");
        }
    }
    @Override
    public void onRequestError(String msg) {
        LogUtils.showToast(msg);
    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void getOrderList(List<LockSeatsBO> orderList) {
        if(page==1){
            if(orderList.size()==0){
                smartRefreshLayout.setVisibility(View.GONE);
                nonelayout.setVisibility(View.VISIBLE);
                textlayout.setText("您当前没有待支付的订单");
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
