package com.myp.cinema.ui.orderconfrim;

import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myp.cinema.R;
import com.myp.cinema.api.HttpInterfaceIml;
import com.myp.cinema.base.BaseActivity;
import com.myp.cinema.base.MyApplication;
import com.myp.cinema.entity.OrderBO;
import com.myp.cinema.jpush.MessageEvent;
import com.myp.cinema.ui.personorder.ordermessage.OrderMessageActivity;
import com.myp.cinema.util.AppManager;
import com.myp.cinema.util.CimemaUtils;
import com.myp.cinema.util.LogUtils;
import com.myp.cinema.util.StringUtils;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import rx.Subscriber;

/**
 * Created by wuliang on 2017/6/2.
 * <p>
 * 订单支付成功
 */

public class OrderSurcessActivity extends BaseActivity implements View.OnClickListener {


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
    @Bind(R.id.order_num)
    TextView orderNum;
    @Bind(R.id.order_price)
    TextView orderPrice;
    @Bind(R.id.back_home)
    Button backHome;
    @Bind(R.id.order_message)
    Button orderMessage;
    @Bind(R.id.go_back)
    LinearLayout goBack;

    private OrderBO orderBO;

    @Override
    protected int getLayout() {
        return R.layout.act_order_scress;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("支付成功");

        String orderNum = getIntent().getExtras().getString("order");
        showProgress("加载中...");
        if (MyApplication.user != null) {
            queryOrder(String.valueOf(MyApplication.user.getId()), orderNum);
        }
        backHome.setOnClickListener(this);
        orderMessage.setOnClickListener(this);
        goBack.setOnClickListener(this);
        EventBus.getDefault().post(new MessageEvent("prodectClear", "yes"));//发给SortDetailFragment
    }

    /**
     * 设置界面数据
     */
    private void setData() {
        moviesName.setText(orderBO.getDxMovie().getMovieName());
        moviesAddress.setText(orderBO.getCinemaName() + " " + orderBO.getHallName());
        moviesType.setText(orderBO.getDxMovie().getMovieDimensional() + " " + orderBO.getDxMovie().getMovieLanguage());
        if (!StringUtils.isEmpty(orderBO.getPlayName())) {
            String time = orderBO.getPlayName().substring(0, orderBO.getPlayName().length() - 3);
            moviesTime.setText(time);
        } else {
            moviesTime.setText("");
        }
        moviesSeat.setText(CimemaUtils.getSeats(orderBO.getSeats()));
        moviesNum.setText(String.valueOf(orderBO.getTicketNum()));
        orderPrice.setText("¥" + orderBO.getPayPrice());
        orderNum.setText("订单号：" + orderBO.getOrderNum());
        if (StringUtils.isEmpty(orderBO.getDxMovie().getPicture())) {
            moviesImg.setImageResource(R.color.act_bg01);
        } else {
            Picasso.with(this).load(orderBO.getDxMovie().getPicture()).into(moviesImg);
        }
    }


    /**
     * 查询单个订单
     */
    private void queryOrder(String appUserId ,String orderNum) {
        HttpInterfaceIml.orderQuery(appUserId,orderNum).subscribe(new Subscriber<OrderBO>() {
            @Override
            public void onCompleted() {
                stopProgress();
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.showToast(e.getMessage());
                stopProgress();
            }

            @Override
            public void onNext(OrderBO order) {
                orderBO = order;
                setData();
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_home:   //返回首页
            case R.id.go_back:
                AppManager.getAppManager().goBackMain();
                break;
            case R.id.order_message:   //订单详情
                Bundle bundle = new Bundle();
                bundle.putSerializable("order", orderBO);
                bundle.putString("cinemaId", orderBO.getCinemaId());
                bundle.putString("id", String.valueOf(orderBO.getId()));
                bundle.putString("cinemaId",orderBO.getCinemaId());
                gotoActivity(OrderMessageActivity.class, bundle, false);
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
