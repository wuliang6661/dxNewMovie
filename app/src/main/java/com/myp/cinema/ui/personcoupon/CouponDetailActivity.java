package com.myp.cinema.ui.personcoupon;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.myp.cinema.R;
import com.myp.cinema.api.HttpInterfaceIml;
import com.myp.cinema.base.BaseActivity;
import com.myp.cinema.entity.CouponDetailBO;
import com.myp.cinema.jpush.MessageEvent;
import com.myp.cinema.util.AppManager;
import com.myp.cinema.util.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import butterknife.Bind;
import rx.Subscriber;

/**
 * Created by witness on 2018/12/3.
 *  优惠券详情
 */

public class CouponDetailActivity extends BaseActivity implements View.OnClickListener{

    @Bind(R.id.youhui_type)
    TextView youhui_type;
    @Bind(R.id.txtRange)
    TextView txtRange;
    @Bind(R.id.youhui_time)
    TextView youhui_time;
    @Bind(R.id.txtNumYes)
    TextView txtNumYes;
    @Bind(R.id.movieRange)
    TextView movieRange;
    @Bind(R.id.rlGoMain)
    RelativeLayout rlGoMain;
    @Bind(R.id.ivMoney)
    ImageView ivMoney;
    @Bind(R.id.youhui_price)
    TextView youhui_price;
    @Bind(R.id.decribe)
    TextView decribe;
    @Bind(R.id.buyTickets)
    TextView buyTickets;
    private String id;
    @Override
    protected int getLayout() {
        return R.layout.coupon_detail_layout;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("我的优惠券");
        rlGoMain.setOnClickListener(this);
        buyTickets.setOnClickListener(this);
        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("id");
        showProgress("加载中");
        getMessage(id);
    }


    private void getMessage(String id){
        HttpInterfaceIml.personCouponDetail(id).subscribe(new Subscriber<CouponDetailBO>() {
            @Override
            public void onCompleted() {
                stopProgress();
            }

            @Override
            public void onError(Throwable e) {
                stopProgress();
                ToastUtils.showShortToast(e.getMessage());
            }

            @Override
            public void onNext(CouponDetailBO s) {
                stopProgress();
                setData(s);
            }
        });
    }

    private void setData(CouponDetailBO couponDetailBO){
        if (couponDetailBO.getDxPlatTicket().getTicketType() == 3){
            Glide.with(CouponDetailActivity.this)
                    .load(R.mipmap.zero)
                    .into(ivMoney);
            txtNumYes.setText("1");
            youhui_price.setText("张");
        }else {
            if (couponDetailBO.getDxPlatTicket().getPlatformType() == 2){
                Glide.with(CouponDetailActivity.this)
                        .load(R.mipmap.see_coupon_yes)
                        .into(ivMoney);
                txtNumYes.setText("1");
                youhui_price.setText("张");
            }else {
                Glide.with(CouponDetailActivity.this)
                        .load(R.mipmap.money_coupon_yes)
                        .into(ivMoney);
                txtNumYes.setText(String.valueOf(couponDetailBO.getDxPlatTicket().getAmount()));
                youhui_price.setText("元");
            }
        }
        youhui_type.setText(couponDetailBO.getDxPlatTicket().getName());
        youhui_time.setText(String.format("有效期：%s 至 %s",couponDetailBO.getDxPlatTicket().getStartTime().substring(0,10),
                couponDetailBO.getDxPlatTicket().getEndTime().substring(0,10)));
        decribe.setText(couponDetailBO.getDxPlatTicket().getTicketDesc());
        if (couponDetailBO.getDxPlatTicket().getTicketType() == 2){
            buyTickets.setText("去购买");
        }else {
            buyTickets.setText("去购票");
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buyTickets:
            case R.id.rlGoMain:
                AppManager.getAppManager().goBackMain();
                EventBus.getDefault().post(new MessageEvent("showMain", "yes"));
                break;
            default:
                break;
        }
    }
}
