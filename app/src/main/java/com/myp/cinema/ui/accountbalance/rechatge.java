package com.myp.cinema.ui.accountbalance;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.myp.cinema.R;
import com.myp.cinema.api.HttpInterfaceIml;
import com.myp.cinema.base.BaseActivity;
import com.myp.cinema.base.MyApplication;
import com.myp.cinema.config.LocalConfiguration;
import com.myp.cinema.entity.PayBO;
import com.myp.cinema.entity.PayResult;
import com.myp.cinema.entity.WXPayBO;

import com.myp.cinema.mvp.MVPBaseActivity;
import com.myp.cinema.ui.balance.balance;
import com.myp.cinema.ui.orderconfrim.OrderSurcessActivity;
import com.myp.cinema.util.LogUtils;
import com.myp.cinema.util.ToastUtils;
import com.myp.cinema.widget.superadapter.CommonAdapter;
import com.myp.cinema.widget.superadapter.ViewHolder;
import com.myp.cinema.wxapi.WXUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.Subscriber;

import static android.R.attr.data;

/**
 * Created by Administrator on 2018/1/24.充值界面
 */

public class rechatge extends MVPBaseActivity<rechatgeContract.View,
        rechatgePresenter> implements rechatgeContract.View {

    private RecyclerView recyclerView;
    private DemoAdapter adapter;
    private TextView te;
    private String countid;
    private String chongzhijine = "mr";
    private ImageView weixinxuanze;
    private ImageView zhifubaoxuanze;
    private int zhifutype = 1;
    private boolean guding;
    private String cardNum;
    private RelativeLayout xuanzeweixin;
    private RelativeLayout xuanzezhifubao;
    private ImageView imageView3;
    private ImageView image5;
    private double xx;
    private String xxx;
    private String cardPrice;
    private TextView yuetext;
    private TextView textyuedu;
    private String cinemaId;


    @Override
    protected int getLayout() {
        return R.layout.rechatgeactivity;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("充值");
        getdata();
        Intent intent = getIntent();
        //从Intent当中根据key取得value
        if (intent != null) {
            cardNum = intent.getStringExtra("cardcode");
            cardPrice = intent.getStringExtra("cardPrice");

        }
        recyclerView = (RecyclerView) findViewById(R.id.recylerview);
        te = (TextView) findViewById(R.id.te);
        textyuedu = (TextView) findViewById(R.id.textyuedu);
        textyuedu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(rechatge.this, bendih5.class));
            }
        });
        yuetext = (TextView) findViewById(R.id.yuetext);
        yuetext.setText("余额：" + cardPrice + "元");
        weixinxuanze = (ImageView) findViewById(R.id.weixinxuanze);
        zhifubaoxuanze = (ImageView) findViewById(R.id.zhifubaoxuanze);
        xuanzeweixin = (RelativeLayout) findViewById(R.id.xuanzeweixin);
        xuanzezhifubao = (RelativeLayout) findViewById(R.id.xuanzezhifubao);
        image5 = (ImageView) findViewById(R.id.image5);
        imageView3 = (ImageView) findViewById(R.id.imageView3);

        weixinxuanze.setVisibility(View.VISIBLE);
        zhifubaoxuanze.setVisibility(View.GONE);
        imageView3.setVisibility(View.GONE);
        image5.setVisibility(View.VISIBLE);
        xuanzeweixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weixinxuanze.setVisibility(View.VISIBLE);
                zhifubaoxuanze.setVisibility(View.GONE);
                imageView3.setVisibility(View.GONE);
                image5.setVisibility(View.VISIBLE);
                zhifutype = 1;
            }
        });
        xuanzezhifubao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weixinxuanze.setVisibility(View.GONE);
                zhifubaoxuanze.setVisibility(View.VISIBLE);
                image5.setVisibility(View.GONE);
                imageView3.setVisibility(View.VISIBLE);
                zhifutype = 2;
            }
        });
        weixinxuanze.setVisibility(View.VISIBLE);
        recyclerView.setHasFixedSize(true);
        //recycleview设置布局方式，GridView (一行三列)
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        EventBus.getDefault().register(this);
        linsener();
    }


    private void getdata() {
        if (MyApplication.cinemaBo != null) {
            cinemaId = MyApplication.cinemaBo.getCinemaId();
        } else {
            cinemaId = "20004292";
        }
        mPresenter.rechatgejine();

    }

    @Override
    public void onRequestError(String msg) {

    }

    @Override
    public void onRequestEnd() {

    }


    @Override
    public void getRechat(List<RechatBo> rechats) {
        ArrayList<ItemModel> list = new ArrayList<>();
        for (int i = 0; i < rechats.size(); i++) {
            String count = rechats.get(i).getRechargeMoney();
            xx = Math.floor(Float.parseFloat(count));
            xxx = xx + "";
            if (xxx.indexOf(".") > 0) {
                xxx = xxx.replaceAll("0+?$", "");//去掉多余的0
                xxx = xxx.replaceAll("[.]$", "");//如最后一位是.则去掉
            }
            countid = rechats.get(i).getId();
            list.add(new ItemModel(ItemModel.TWO, xxx, countid, true));
        }
        list.add(new ItemModel(ItemModel.THREE, null, "", false));
        recyclerView.setAdapter(adapter = new DemoAdapter());
        adapter.replaceAll(list, this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getAdapterClickInfo(ItemModel model) {
        if (model.type == 1002) {
            chongzhijine = String.valueOf(model.dataa);
            guding = true;
        } else {
            chongzhijine = model.data.toString();
            guding = false;
        }
    }

    private void linsener() {
        te.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chongzhijine.equals("mr")) {
                    LogUtils.showToast("请选择充值项目");
                } else {
                    Double douNum1 = Double.valueOf(chongzhijine);
                    int intNum5 = douNum1.intValue();
                    if (intNum5 > 50000) {
                        LogUtils.showToast("充值金额过大");
                    } else {
                        if (zhifutype == 1) {
                            if (guding == true) {
                                weixin(guding);
                            } else {
                                weixin(guding);
                            }
                        }
                        if (zhifutype == 2) {
                            if (guding == true) {
                                zhifubaopay(guding);
                            } else {
                                zhifubaopay(guding);
                            }
                        }
                    }
                }
            }
        });
    }

    private void weixin(boolean guding) {
        if (guding == true) {
            int amountId = Integer.parseInt(chongzhijine);
            HttpInterfaceIml.weixinpay(cinemaId, 1, amountId, cardNum)
                    .subscribe(new Subscriber<WXPayBO>() {
                        @Override
                        public void onCompleted() {
                        }
                        @Override
                        public void onError(Throwable e) {
                            LogUtils.showToast(e.getMessage());
                        }
                        @Override
                        public void onNext(WXPayBO wxPayBO) {
                            LocalConfiguration.cardcode = cardNum;
                            WXUtils.payWXs(wxPayBO, 1);
                        }
                    });
        } else {
            xx = Math.floor(Float.parseFloat(chongzhijine));
            xxx = xx + "";
            if (xxx.indexOf(".") > 0) {
                xxx = xxx.replaceAll("0+?$", "");//去掉多余的0
                xxx = xxx.replaceAll("[.]$", "");//如最后一位是.则去掉
            }
            int rechargeMoney = Integer.parseInt(xxx);
            if (rechargeMoney < 100) {
                LogUtils.showToast("充值金额不能小于100");
            } else {
                HttpInterfaceIml.weixinpayta(cinemaId, 2, rechargeMoney, cardNum)
                        .subscribe(new Subscriber<WXPayBO>() {
                            @Override
                            public void onCompleted() {
                            }
                            @Override
                            public void onError(Throwable e) {
                                LogUtils.showToast(e.getMessage());
                            }
                            @Override
                            public void onNext(WXPayBO wxPayBO) {
                                LocalConfiguration.orderNum = "rechatge";
                                LocalConfiguration.cardcode = cardNum;
                                WXUtils.payWXs(wxPayBO, 1);
                            }
                        });
            }
        }
    }

    private void zhifubaopay(boolean guding) {
        if (guding == true) {
            int amountId = Integer.parseInt(chongzhijine);
            HttpInterfaceIml.zhifubaopay(cinemaId, 1, amountId, cardNum)
                    .subscribe(new Subscriber<PayBO>() {
                        @Override
                        public void onCompleted() {
                        }
                        @Override
                        public void onError(Throwable e) {
                            LogUtils.showToast(e.getMessage());
                        }
                        @Override
                        public void onNext(PayBO orderInfo) {
                            payAliPay(orderInfo.getAlipay());
                        }});
        } else {
            xx = Math.floor(Float.parseFloat(chongzhijine));
            xxx = xx + "";
            if (xxx.indexOf(".") > 0) {
                xxx = xxx.replaceAll("0+?$", "");//去掉多余的0
                xxx = xxx.replaceAll("[.]$", "");//如最后一位是.则去掉
            }
            int rechargeMoney = Integer.parseInt(xxx);
            if (rechargeMoney < 100) {
                LogUtils.showToast("充值金额不能小于100");
            } else {
                HttpInterfaceIml.zhifubaopayqita(cinemaId, 2, rechargeMoney, cardNum
                )
                        .subscribe(new Subscriber<PayBO>() {
                            @Override
                            public void onCompleted() {
                            }
                            @Override
                            public void onError(Throwable e) {
                                LogUtils.showToast(e.getMessage());
                            }
                            @Override
                            public void onNext(PayBO orderInfo) {
                                payAliPay(orderInfo.getAlipay());
                            }
                        });
            }
        }
    }

    private void payAliPay(final String orderInfo) {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(rechatge.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Message msg = new Message();
                msg.what = 0x11;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
    /**
     * 支付宝返回结果处理
     * <p>
     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
     */
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            PayResult payResult = new PayResult((Map<String, String>) msg.obj);
            String resultStatus = payResult.getResultStatus();
            // 判断resultStatus 为9000则代表支付成功
            if (TextUtils.equals(resultStatus, "9000")) {
                String cardcode = cardNum;
                Intent rechatg = new Intent(rechatge.this, PaysuccessActivity.class);
                rechatg.putExtra("cardcode", cardcode);
                startActivity(rechatg);
            } else {
                LogUtils.showToast("支付失败");
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
