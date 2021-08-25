package com.myp.cinema.ui.playcredits;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.myp.cinema.R;
import com.myp.cinema.api.HttpInterfaceIml;
import com.myp.cinema.base.BaseActivity;
import com.myp.cinema.entity.ShopOrderBO;
import com.myp.cinema.ui.userlogin.LoginActivity;
import com.myp.cinema.util.LogUtils;
import com.myp.cinema.widget.superadapter.CommonAdapter;
import com.myp.cinema.widget.superadapter.ViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.Bind;
import rx.Subscriber;

/**
 * Created by wuliang on 2017/7/17.
 * <p>
 * 金币兑换记录
 */

public class CreditsHistory extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {


    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @Bind(R.id.list)
    ListView list;
    @Bind(R.id.none_layout)
    LinearLayout noneLayout;

    List<ShopOrderBO> shopOrderBOs;

    @Override
    protected int getLayout() {
        return R.layout.list_layout;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("兑换记录");
        getCreditsOrder();
    }


    /**
     * 设置适配器
     */
    private void setAdapter() {
        CommonAdapter<ShopOrderBO> adapter = new CommonAdapter<ShopOrderBO>(this, R.layout.item_credits_shop, shopOrderBOs) {
            @Override
            protected void convert(ViewHolder viewHolder, ShopOrderBO item, int position) {
                viewHolder.setText(R.id.shop_name, item.getGood().getGoodName());
                viewHolder.setText(R.id.shop_message, "市场价" + item.getGood().getPrice());
                viewHolder.setText(R.id.shop_credits, "-" + item.getConsumePoint() + "德信币");
                viewHolder.setImageUrl(R.id.shop_img, item.getGood().getImageUrl());
                viewHolder.setText(R.id.submit_button, "已兑换");
            }
        };
        list.setAdapter(adapter);
    }


    /**
     * 获取兑换记录
     */
    private void getCreditsOrder() {
        HttpInterfaceIml.creditsOrder().subscribe(new Subscriber<List<ShopOrderBO>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if ("未登录".equals(e.getMessage())) {
                    Intent intent = new Intent(CreditsHistory.this, LoginActivity.class);
                    startActivityForResult(intent, 1);
                } else {
                    LogUtils.showToast(e.getMessage());
                }
            }

            @Override
            public void onNext(List<ShopOrderBO> s) {
                if (s == null || s.size() == 0) {
                    noneLayout.setVisibility(View.VISIBLE);
                } else {
                    noneLayout.setVisibility(View.GONE);
                    shopOrderBOs = s;
                    setAdapter();
                }
            }
        });
    }


    @Override
    public void onRefresh() {
        getCreditsOrder();
    }
}
