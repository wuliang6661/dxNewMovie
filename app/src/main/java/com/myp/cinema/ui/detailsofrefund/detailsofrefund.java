package com.myp.cinema.ui.detailsofrefund;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.widget.TextView;

import com.myp.cinema.R;
import com.myp.cinema.entity.OrderBO;
import com.myp.cinema.mvp.MVPBaseActivity;

/**
 * Created by Administrator on 2018/1/20.
 */

public class detailsofrefund extends MVPBaseActivity<tuipiaoContract.View,
        tuipiaoPresenter> implements tuipiaoContract.View{

    private OrderBO orderBO;
    private TextView te;

    @Override
    protected int getLayout() {
        return R.layout.tuipiaoactivity;

    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("申请退票");
        orderBO = (OrderBO) getIntent().getExtras().getSerializable("order");

    }
    @Override
    public void onRequestError(String msg) {

    }

    @Override
    public void onRequestEnd() {

    }
}
