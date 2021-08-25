package com.myp.cinema.ui.personorder.notpaymessage;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.myp.cinema.R;
import com.myp.cinema.entity.LockSeatsBO;
import com.myp.cinema.entity.OrderNumBO;
import com.myp.cinema.mvp.MVPBaseActivity;
import com.myp.cinema.ui.pay.PayActivity;
import com.myp.cinema.util.CimemaUtils;
import com.myp.cinema.util.LogUtils;
import com.myp.cinema.util.StringUtils;
import com.squareup.picasso.Picasso;

import butterknife.Bind;


/**
 * MVPPlugin
 * 未支付的订单详情
 */

public class NotPayMessageActivity extends MVPBaseActivity<NotPayMessageContract.View,
        NotPayMessagePresenter> implements NotPayMessageContract.View, View.OnClickListener {


    @Bind(R.id.movies_img)
    ImageView moviesImg;
    @Bind(R.id.movies_name)
    TextView moviesName;
    @Bind(R.id.movies_type)
    TextView moviesType;
    @Bind(R.id.movies_address)
    TextView moviesAddress;
    @Bind(R.id.movies_time)
    TextView moviesTime;
    @Bind(R.id.movies_seat)
    TextView moviesSeat;
    @Bind(R.id.movies_num)
    TextView moviesNum;
    @Bind(R.id.phone_num)
    TextView phoneNum;
    @Bind(R.id.order_num)
    TextView orderNum;
    @Bind(R.id.order_price)
    TextView orderPrice;
    @Bind(R.id.go_pay)
    Button goPay;
    @Bind(R.id.order_cancle)
    Button orderCancle;

    LockSeatsBO orderBO;

    @Override
    protected int getLayout() {
        return R.layout.act_not_pay_order;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("待支付详情");

        orderBO = (LockSeatsBO) getIntent().getExtras().getSerializable("order");
        invition();
        goPay.setOnClickListener(this);
        orderCancle.setOnClickListener(this);
    }

    /**
     * 初始化界面
     */
    private void invition() {
        moviesName.setText(orderBO.getDxMovie().getMovieName());
        moviesType.setText(orderBO.getDxMovie().getMovieDimensional() + orderBO.getDxMovie().getMovieLanguage());
        moviesAddress.setText(orderBO.getCinemaName() + " " + orderBO.getHallName());
        moviesTime.setText(orderBO.getPlayName().substring(0, orderBO.getPlayName().length() - 3));
        moviesNum.setText(String.valueOf(orderBO.getTicketNum()));
        phoneNum.setText(orderBO.getOrderPhone());
        orderNum.setText("订单号：" + orderBO.getOrderNum());
        orderPrice.setText("总价：¥" + orderBO.getPayPrice());
        moviesSeat.setText(CimemaUtils.getSeats(orderBO.getSeats()));
        if (StringUtils.isEmpty(orderBO.getDxMovie().getPicture())) {
            moviesImg.setImageResource(R.color.act_bg01);
        } else {
            Picasso.with(this).load(orderBO.getDxMovie().getPicture()).into(moviesImg);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go_pay:
                Bundle bundle = new Bundle();
                bundle.putInt("confrim",1);
                bundle.putSerializable("lockSeatsBO", orderBO);
                gotoActivity(PayActivity.class, bundle, false);
                break;
            case R.id.order_cancle:
                cancleOrder();
                break;
        }
    }

    private void cancleOrder() {
        LayoutInflater factory = LayoutInflater.from(this);//提示框
        final View view = factory.inflate(R.layout.dialog_order_pay, null);//这里必须是final的
        TextView cancle = (TextView) view.findViewById(R.id.off_commit);
        TextView commit = (TextView) view.findViewById(R.id.commit);
        TextView title = (TextView) view.findViewById(R.id.title);
        TextView message = (TextView) view.findViewById(R.id.message);
        title.setText("提示");
        message.setText("确认取消订单？");
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
                showProgress("加载中...");
                mPresenter.orderCancle(orderBO.getOrderNum());
            }
        });
        dialog.setView(view);
        dialog.show();
    }

    @Override
    public void getOrderCancle(OrderNumBO orderNumBO) {
        if (!StringUtils.isEmpty(orderNumBO.getOrderNum())) {
            Intent intent = new Intent();
            setResult(1, intent);
            finish();
        }
    }

    @Override
    public void onRequestError(String msg) {
        LogUtils.showToast(msg);
    }

    @Override
    public void onRequestEnd() {

    }
}
