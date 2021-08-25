package com.myp.cinema.ui.paysuccess;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myp.cinema.R;
import com.myp.cinema.base.BaseActivity;
import com.myp.cinema.base.MyApplication;
import com.myp.cinema.config.LocalConfiguration;
import com.myp.cinema.ui.detailed.detailed;
import com.myp.cinema.ui.pay.PayActivity;
import com.myp.cinema.util.AppManager;

import butterknife.Bind;

/**
 * Created by Administrator on 2018/1/28.充值成功
 */

public class PaysuccessActivity extends BaseActivity implements View.OnClickListener{
    @Bind(R.id.order_message)
    Button orderMessage;
    @Bind(R.id.go_back)
    LinearLayout goBack;
    @Bind(R.id.back_home)
    Button backHome;
    @Bind(R.id.teeee)
    TextView teeee;

    private String cardcode;
    @Override
    protected int getLayout() {
        return R.layout.act_paysuccess;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("充值");
        if(LocalConfiguration.isShouye==null){
            teeee.setText("订单支付成功！");
            backHome.setText("返回首页");
        }else {
            if(LocalConfiguration.isShouye.equals("1")){
                teeee.setText("充值成功");
                backHome.setText("返回");
                orderMessage.setText("充值记录");
            }else {
                teeee.setText("订单支付成功！");
                backHome.setText("返回首页");
            }
        }
        cardcode = getIntent().getExtras().getString("cardcode");
        backHome.setOnClickListener(this);
        orderMessage.setOnClickListener(this);
        goBack.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_home:   //返回首页
            case R.id.go_back:
                if(LocalConfiguration.isShouye==null){
                    AppManager.getAppManager().goBackMain();
                }else {
                    if(LocalConfiguration.isShouye.equals("1")){
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("order", MyApplication.orderBO);
                        gotoActivity(PayActivity.class, bundle, false);
                        LocalConfiguration.isShouye="0";
                    }else {
                        AppManager.getAppManager().goBackMain();
                    }
                }
                break;
            case R.id.order_message:   //订单详情
                Intent rechatg = new Intent(PaysuccessActivity.this, detailed.class);
                rechatg.putExtra("mingxi", "1");
                rechatg.putExtra("cardcode", cardcode);
                startActivity(rechatg);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AppManager.getAppManager().goBackMain();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}

