package com.myp.cinema.ui.applicationforrefund;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.myp.cinema.R;
import com.myp.cinema.entity.OrderDetailBO;
import com.myp.cinema.entity.RefundBO;
import com.myp.cinema.mvp.MVPBaseActivity;
import com.myp.cinema.util.AppManager;
import com.myp.cinema.util.CimemaUtils;
import com.myp.cinema.util.LogUtils;
import com.myp.cinema.util.StringUtils;
import com.squareup.picasso.Picasso;

import butterknife.Bind;

/**
 * Created by Administrator on 2018/2/5.
 */

public class applicationforrefund extends MVPBaseActivity<tuipiaoshenqingContract.View,
        tuipiaoshenqingPresenter> implements tuipiaoshenqingContract.View {

    private TextView tijiao;
    private String id;
    private String cinemaId;
    private OrderDetailBO orderBO;
    @Bind(R.id.order_number)
    TextView ordernumber;
    @Bind(R.id.total)
    TextView total;
    @Bind(R.id.movies_img)
    ImageView movieImg;
    @Bind(R.id.movies_name)
    TextView moviesname;
    @Bind(R.id.movies_type)
    TextView moviestype;
    @Bind(R.id.movies_address)
    TextView moviesaddress;
    @Bind(R.id.movies_time)
    TextView moviestime;
    @Bind(R.id.movies_seat)
    TextView moviesSeat;
    @Bind(R.id.movies_numm)
    TextView moviesnum;
    @Bind(R.id.refundHandFee)
    TextView refundhandFee;
    @Bind(R.id.refundFee)
    TextView refundFee;
    private boolean can=true;

    @Override
    protected int getLayout() {
        return R.layout.act_tuipiaoshenqing;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("申请退票");
        cinemaId =  getIntent().getStringExtra("cinemaId");
        id =  getIntent().getStringExtra("id");
        orderBO = (OrderDetailBO) getIntent().getExtras().getSerializable("order");
        ordernumber.setText("订单号："+orderBO.getOrderNum());
        total.setText("总价：￥"+orderBO.getTicketRealPrice());
        if (StringUtils.isEmpty(orderBO.getDxMovie().getPicture())) {
            movieImg.setImageResource(R.color.act_bg01);
        } else {
            Picasso.with(this).load(orderBO.getDxMovie().getPicture()).into(movieImg);
        }
        moviesname.setText(orderBO.getDxMovie().getMovieName());
        moviestype.setText(orderBO.getDxMovie().getMovieDimensional());
        moviesaddress.setText(orderBO.getCinemaName() + " " + orderBO.getHallName());
        if (!StringUtils.isEmpty(orderBO.getPlayName())) {
            String time = orderBO.getPlayName().substring(0, orderBO.getPlayName().length() - 3);
            moviestime.setText(time);
        } else {
            moviestime.setText("");
        }
        moviesSeat.setText(CimemaUtils.getSeats(orderBO.getSeats()));
        moviesnum.setText(String.valueOf(orderBO.getTicketNum()));
        mPresenter.refundinfo(id);
        tijiao = (TextView)findViewById(R.id.tijiao);
        tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tijiao.setVisibility(View.GONE);
                    showProgress("加载中...");
                    if(cinemaId ==null || cinemaId.isEmpty()){
                        LogUtils.showToast("影院地址为空");
                    }else {
                        mPresenter.refund(id,cinemaId);
                    }

            }
        });

    }

    @Override
    public void onRequestError(String msg) {
        stopProgress();
        LogUtils.showToast(msg);

    }

    @Override
    public void onRequestEnd() {
        stopProgress();
    }

    @Override
    public void getrefundinfo(RefundBO refundBO) {
        refundhandFee.setText("每张"+String.valueOf(refundBO.getData().getRefundHandFee())+"元");
        refundFee.setText(String.valueOf(refundBO.getData().getRefundFee())+"元");

    }

    @Override
    public void getrefund(RefundBO orderMessage) {
        stopProgress();
        if(orderMessage.getStatus()==1){
//            stopProgress();
            tijiao.setVisibility(View.GONE);
            LayoutInflater factory = LayoutInflater.from(this);//提示框
            final View view = factory.inflate(R.layout.dialogg_layout, null);//这里必须是final的
            TextView cancle = (TextView) view.findViewById(R.id.cancle);

            final AlertDialog dialog = new AlertDialog.Builder(this).create();
            cancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AppManager.getAppManager().goBackMain();
                }
            });
            dialog.setView(view);
            dialog.show();
            WindowManager m = this.getWindowManager();
            Display d = m.getDefaultDisplay();
            android.view.WindowManager.LayoutParams p = dialog.getWindow().getAttributes();
            p.width = (int) (d.getWidth() * 0.8);
            dialog.getWindow().setAttributes(p);
        }else {
            tijiao.setVisibility(View.VISIBLE);
            LogUtils.showToast(orderMessage.getMessage());
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
