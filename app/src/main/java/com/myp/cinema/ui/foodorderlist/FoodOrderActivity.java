package com.myp.cinema.ui.foodorderlist;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.myp.cinema.R;
import com.myp.cinema.entity.LockSeatsBO;
import com.myp.cinema.mvp.MVPBaseActivity;
import com.myp.cinema.ui.personorder.ordermessage.OrderMessageActivity;
import com.myp.cinema.widget.superadapter.CommonAdapter;
import com.myp.cinema.widget.superadapter.ViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;

/**
 * Created by Witness on 2019/1/21
 * Describe: 食品订单列表
 */
public class FoodOrderActivity extends MVPBaseActivity<FoodOrderContract.View,FoodOrderPresenter>
                implements FoodOrderContract.View,AdapterView.OnItemClickListener{

    @Bind(R.id.list)
    ListView list;
    @Bind(R.id.text_layout)
    TextView textlayout;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @Bind(R.id.none_layout)
    LinearLayout nonelayout;
    @Bind(R.id.header)
    LinearLayout header;

    CommonAdapter<LockSeatsBO> adapter;
    private List<LockSeatsBO> data= new ArrayList<>();
    private List<LockSeatsBO.MerOrderBean.DetailsBean> prodectData = new ArrayList<>();
    private com.zhy.adapter.recyclerview.CommonAdapter<LockSeatsBO.MerOrderBean.DetailsBean> prodectAdapter;
    private int page = 1;

    @Override
    protected int getLayout() {
        return R.layout.movielayout;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        header.setVisibility(View.VISIBLE);
        goBack();
        setTitle("食品订单");
        showProgress("加载中");
        mPresenter.loadOrderList( "1","1",page,"10");
        list.setOnItemClickListener(this);
        setPullRefresher();
        adapter();
    }

    private void adapter() {
        adapter = new CommonAdapter<LockSeatsBO>(this, R.layout.prodect_item_layout, data) {
            @Override
            protected void convert(ViewHolder helper, final LockSeatsBO item, final int position) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(FoodOrderActivity.this);
                RecyclerView prodectList = helper.getConvertView().findViewById(R.id.prodectList);
                prodectList.setLayoutManager(layoutManager);
                if (data.get(position).getMerOrder().getDeliveryType() == 2){
                    helper.getView(R.id.ivProdectState).setVisibility(View.VISIBLE);
                }else {
                    helper.getView(R.id.ivProdectState).setVisibility(View.GONE);
                }
                ImageView ivProdectState = helper.getConvertView().findViewById(R.id.ivProdectState);
                ImageView ivFoodType = helper.getConvertView().findViewById(R.id.ivFoodType);
                if (data.get(position).getPayStatus() == 3) {   // 3 已退票，-1 订单取消 ，1 已支付 ，0 未支付  2 已过期
                    ivProdectState.setVisibility(View.VISIBLE);
                    ivProdectState.setImageDrawable(getResources().getDrawable(R.mipmap.ticket_refund));
                    ivFoodType.setImageDrawable(getResources().getDrawable(R.mipmap.food_no));
                }else if (data.get(position).getPayStatus() == 2){
                    ivProdectState.setVisibility(View.VISIBLE);
                    ivFoodType.setImageDrawable(getResources().getDrawable(R.mipmap.food_no));
                    ivProdectState.setImageDrawable(getResources().getDrawable(R.mipmap.coupon_outtime));
                }else {
                    ivProdectState.setVisibility(View.GONE);
                    ivFoodType.setImageDrawable(getResources().getDrawable(R.mipmap.food_yes));
                }
                prodectData.clear();
                prodectData.addAll(data.get(position).getMerOrder().getDetails());
                prodectAdapter = new com.zhy.adapter.recyclerview.CommonAdapter<LockSeatsBO.MerOrderBean.DetailsBean>(FoodOrderActivity.this,
                        R.layout.ticketlist_prodect_item,prodectData) {
                    @Override
                    protected void convert(com.zhy.adapter.recyclerview.base.ViewHolder mholder, LockSeatsBO.MerOrderBean.DetailsBean detailsBean, final int mposition) {
                        if (detailsBean.getType() == 2){ // 2 套餐
                            mholder.setText(R.id.merName,detailsBean.getItemCombo().getName());
                            mholder.setText(R.id.merNum,String.format("%s份",detailsBean.getNumber()));
                        }else {
                            mholder.setText(R.id.merName,detailsBean.getMerchandise().getName());
                            mholder.setText(R.id.merNum,String.format("%s份",detailsBean.getNumber()));
                        }
                        TextView name = mholder.getConvertView().findViewById(R.id.merName);
                        TextView number = mholder.getConvertView().findViewById(R.id.merNum);
                        if (data.get(position).getPayStatus() == 3
                                || data.get(position).getPayStatus() == 2
                                ||data.get(position).getPayStatus() == -1) {// 3 已退票，-1 订单取消 ，1 已支付 ，0 未支付  2 已过期
                            name.setTextColor(Color.parseColor("#888888"));
                            number.setTextColor(Color.parseColor("#888888"));
                        }else {
                            name.setTextColor(Color.parseColor("#312927"));
                            number.setTextColor(Color.parseColor("#312927"));
                        }
                        mholder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Bundle bundle = new Bundle();
                                bundle.putString("orderNum", item.getOrderNum());
                                bundle.putString("id",String.valueOf(item.getId()));
                                bundle.putString("cinemaId",item.getCinemaId());
                                Intent intent = new Intent(FoodOrderActivity.this,OrderMessageActivity.class);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        });
                    }
                };
                prodectList.setAdapter(prodectAdapter);
//                helper.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Bundle bundle = new Bundle();
//                        bundle.putString("orderNum", data.get(position).getOrderNum());
//                        Intent intent = new Intent(FoodOrderActivity.this,OrderMessageActivity.class);
//                        intent.putExtras(bundle);
//                        startActivity(intent);
//                    }
//                });
            }
        };
    }

    private void setPullRefresher(){
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page=1;
                mPresenter.loadOrderList( "1","1",page,"10");
                smartRefreshLayout.finishRefresh(1000);
                refreshlayout.finishRefresh(2000);
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshlayout) {
                page++;
                mPresenter.loadOrderList( "1","1",page,"10");
                smartRefreshLayout.finishLoadMore(1000);
                refreshlayout.finishLoadMore(2000);
            }
        });
    }

    @Override
    public void getOrderList(List<LockSeatsBO> orderList) {
        stopProgress();
        if(page==1){
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

    @Override
    public void onRequestError(String msg) {
        stopProgress();
    }

    @Override
    public void onRequestEnd() {
        stopProgress();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle = new Bundle();
        bundle.putString("orderNum", data.get(position).getOrderNum());
        bundle.putString("id",String.valueOf(data.get(position).getId()));
        gotoActivity(OrderMessageActivity.class, bundle, false);
    }
}
