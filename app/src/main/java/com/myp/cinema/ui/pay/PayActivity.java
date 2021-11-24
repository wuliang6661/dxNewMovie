package com.myp.cinema.ui.pay;


import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.myp.cinema.R;
import com.myp.cinema.base.MyApplication;
import com.myp.cinema.entity.ConfirmPayBO;
import com.myp.cinema.entity.LockSeatsBO;
import com.myp.cinema.entity.SubmitPrdectOrderBO;
import com.myp.cinema.mvp.MVPBaseActivity;
import com.myp.cinema.ui.FragmentPaerAdapter;
import com.myp.cinema.ui.pay.cardpay.CardPayFragment;
import com.myp.cinema.ui.pay.mentpay.MentPayFragment;
import com.myp.cinema.util.AppManager;
import com.myp.cinema.util.TimeConstants;
import com.myp.cinema.util.TimeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


/**
 * MVPPlugin
 * 订单支付界面
 */

public class PayActivity extends MVPBaseActivity<PayContract.View, PayPresenter>
        implements PayContract.View, RadioGroup.OnCheckedChangeListener,
        ViewPager.OnPageChangeListener, View.OnClickListener {

    @Bind(R.id.go_back)
    LinearLayout goBack;
    @Bind(R.id.count_down_time)
    TextView countDownTime;
    @Bind(R.id.pay_card)
    RadioButton payCard;
    @Bind(R.id.pay_ment)
    RadioButton payMent;
    @Bind(R.id.radio_layout)
    RadioGroup radioLayout;
    @Bind(R.id.view_pager)
    ViewPager viewPager;
    @Bind(R.id.order_price)
    TextView orderPrice;
    @Bind(R.id.submit_button)
    Button submitButton;
    @Bind(R.id.buttom_layout)
    LinearLayout buttomLayout;
    @Bind(R.id.other_price)
    TextView otherPrice;

    List<Fragment> fragments;
    CardPayFragment cardPayFragment;
    MentPayFragment mentPayFragment;

    ConfirmPayBO orderBO;
    LockSeatsBO lockSeatsBO;
    SubmitPrdectOrderBO submitPrdectOrderBO;

    private int confrim = Integer.MAX_VALUE;  //判断从哪个页面进入 1 从提交订单页面进入

    CountDownTimer timer;
    private int size;


    @Override
    protected int getLayout() {
        return R.layout.act_pay;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("付款");
        Bundle bundle = getIntent().getExtras();
        confrim = bundle.getInt("confrim", Integer.MAX_VALUE);
        if (confrim == 1) {
            orderBO = MyApplication.confirmPayBO;
            lockSeatsBO = (LockSeatsBO) bundle.getSerializable("lockSeatsBO");
            MyApplication.orderBO = lockSeatsBO;
            size = bundle.getInt("size");
            orderPrice.setText(String.format("￥ %s",lockSeatsBO.getPayPrice()));
            otherPrice.setText("(含服务费¥" + MyApplication.cinemaBo.getTotalFee() + "/张)");
            inittion();
            if (orderBO != null) {
                startTimeDown(TimeUtils.getTimeSpan(TimeUtils.millis2String(
                        orderBO.getOrderExpireSecond() * 1000, "yyyy-MM-dd HH:mm:ss"),
                        TimeUtils.millis2String(TimeUtils.getNowTimeMills()),
                        TimeConstants.MSEC));
            }else {
                startTimeDown(TimeUtils.getTimeSpan(TimeUtils.millis2String(
                        lockSeatsBO.getOrderExpireSecond() * 1000, "yyyy-MM-dd HH:mm:ss"),
                        TimeUtils.millis2String(TimeUtils.getNowTimeMills()),
                        TimeConstants.MSEC));
            }
        } else {
            submitPrdectOrderBO = (SubmitPrdectOrderBO) bundle.getSerializable("order");
            orderPrice.setText(String.format("￥ %s",submitPrdectOrderBO.getPayPrice()));
            otherPrice.setVisibility(View.GONE);
            inittion();
            startTimeDown(TimeUtils.getTimeSpan(TimeUtils.millis2String(
                    submitPrdectOrderBO.getOrderExpireSecond()*1000,"yyyy-MM-dd HH:mm:ss"),
                    TimeUtils.millis2String(TimeUtils.getNowTimeMills()),
                    TimeConstants.MSEC));
        }
    }

    /**
     * 初始化界面
     */
    private void inittion() {
        fragments = new ArrayList<>();
        cardPayFragment = new CardPayFragment();
        mentPayFragment = new MentPayFragment();
        if (orderBO != null) {
            mentPayFragment.setOrderBO(orderBO);
        }else {
            mentPayFragment.setProdectBO(submitPrdectOrderBO);
        }
        mentPayFragment.setSize(size);
        if (orderBO != null) {
            cardPayFragment.setOrderBO(orderBO);
        }else {
            cardPayFragment.setProdectBO(submitPrdectOrderBO);
        }
        fragments.add(cardPayFragment);
        fragments.add(mentPayFragment);
        FragmentPaerAdapter adapter = new FragmentPaerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        radioLayout.setOnCheckedChangeListener(this);
        viewPager.setOnPageChangeListener(this);
        goBack.setOnClickListener(this);
        buttomLayout.setVisibility(View.GONE);
    }


    /**
     * 启动订单倒计时
     */
    private void startTimeDown(long downTime) {
        timer = new CountDownTimer(downTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                countDownTime.setText(TimeUtils.millis2String(millisUntilFinished, "mm:ss"));
            }

            @Override
            public void onFinish() {
                new AlertView("提示", "订单已过期！", null, new String[]{"确定"}, null, PayActivity.this, AlertView.Style.Alert, new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, int position) {
                        AppManager.getAppManager().goBackMain();
                    }
                }).show();
                cardPayFragment.dissDiaLog();   //如果会员卡支付弹窗正在显示，关闭
            }
        };
        timer.start();
    }


    private void showDialog() {
        LayoutInflater factory = LayoutInflater.from(this);//提示框
        final View view = factory.inflate(R.layout.dialog_order_pay, null);//这里必须是final的
        TextView cancle = (TextView) view.findViewById(R.id.off_commit);
        TextView commit = (TextView) view.findViewById(R.id.commit);
        TextView title = (TextView) view.findViewById(R.id.title);
        TextView message = (TextView) view.findViewById(R.id.message);
        title.setText("提示");
        message.setText("是否退出支付？");
        cancle.setText("取消");
        commit.setText("确定");
        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //事件
                dialog.dismiss();
                if (confrim == 1) {    //如果从提交订单页进来 ，返回首页
                    AppManager.getAppManager().goBackMain();
                } else {
                    finish();
                }
            }
        });
        dialog.setView(view);
        dialog.show();
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.pay_card:
                payCard.setTextColor(Color.parseColor("#ffffff"));
                payMent.setTextColor(Color.parseColor("#32e8e8"));
                viewPager.setCurrentItem(0);
                break;
            case R.id.pay_ment:
                payCard.setTextColor(Color.parseColor("#32e8e8"));
                payMent.setTextColor(Color.parseColor("#ffffff"));
                viewPager.setCurrentItem(1);
                break;
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 0) {
            payCard.setChecked(true);
            buttomLayout.setVisibility(View.GONE);
        } else {
            payMent.setChecked(true);
            buttomLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        showDialog();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            showDialog();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
