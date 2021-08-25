package com.myp.cinema.ui.pay.mentpay;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.myp.cinema.R;
import com.myp.cinema.config.LocalConfiguration;
import com.myp.cinema.entity.ConfirmPayBO;
import com.myp.cinema.entity.PayBO;
import com.myp.cinema.entity.PayResult;
import com.myp.cinema.entity.ResuBo;
import com.myp.cinema.entity.SubmitPrdectOrderBO;
import com.myp.cinema.entity.WXPayBO;
import com.myp.cinema.mvp.MVPBaseFragment;
import com.myp.cinema.ui.orderconfrim.OrderSurcessActivity;
import com.myp.cinema.util.LogUtils;
import com.myp.cinema.util.ToastUtils;
import com.myp.cinema.wxapi.WXUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;

/**
 * Created by wuliang on 2017/6/1.
 * <p>
 * 网上支付
 */

public class MentPayFragment extends MVPBaseFragment<MentPayContract.View,
        MentPayPresenter> implements MentPayContract.View, View.OnClickListener {

    @Bind(R.id.pay_weixin)
    RelativeLayout payWeixin;
    @Bind(R.id.pay_zhifubao)
    RelativeLayout payZhifubao;
    @Bind(R.id.edit_text)
    EditText edittext;
    @Bind(R.id.text_dh)
    TextView textdh;
    @Bind(R.id.pay_zhifuba)
    RelativeLayout payzhifuba;
    @Bind(R.id.liner_sr)
    LinearLayout linersr;
    @Bind(R.id.duihuanjuan)
    ImageView duihuanjuan;

    private ConfirmPayBO orderBO;
    private SubmitPrdectOrderBO submitPrdectOrderBO;
    private int size;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_pay_ment, null);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textdh.setOnClickListener(this);
        payWeixin.setOnClickListener(this);
        payZhifubao.setOnClickListener(this);
        payzhifuba.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * 电影票订单
     * @param orderBO
     */
    public void setOrderBO(ConfirmPayBO orderBO) {
        this.orderBO = orderBO;
    }

    /**
     * 食品订单
     * @param submitPrdectOrderBO
     */
    public void setProdectBO(SubmitPrdectOrderBO submitPrdectOrderBO){
        this.submitPrdectOrderBO = submitPrdectOrderBO;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pay_weixin:
                if (orderBO != null) {
                    showProgress("加载中...");
                    mPresenter.loadWeChatPay(orderBO.getOrderNum());
                }else {
                    showProgress("加载中...");
                    mPresenter.loadWeChatPay(submitPrdectOrderBO.getOrderNum());
                }
                break;
            case R.id.pay_zhifubao:
                if (orderBO != null) {
                    showProgress("加载中...");
                    mPresenter.loadAliPay(orderBO.getOrderNum());
                }else {
                    showProgress("加载中...");
                    mPresenter.loadAliPay(submitPrdectOrderBO.getOrderNum());
                }
                break;
            case R.id.text_dh:
                String coupon = edittext.getText().toString().trim();
                if(coupon==null||coupon.equals("")){
                    LogUtils.showToast("请输入兑换劵");
                }else{
                    if (orderBO != null) {
                        showProgress("加载中...");
                        mPresenter.loadcardPay(orderBO.getOrderNum(), coupon);
                    }else {
                        showProgress("加载中...");
                        mPresenter.loadcardPay(submitPrdectOrderBO.getOrderNum(), coupon);
                    }
                }
                break;
            case R.id.pay_zhifuba:
                if(size>1){
                    linersr.setVisibility(View.GONE);
                    ToastUtils.showShortToast("一张券只能兑换一张电影票");
                }else {
                    duihuanjuan.animate().rotation(90);
                    linersr.setVisibility(View.VISIBLE);

               }

                break;

        }
    }

    @Override
    public void onRequestError(String msg) {
        LogUtils.showToast(msg);
        stopProgress();
    }

    @Override
    public void onRequestEnd() {
        stopProgress();
    }

    @Override
    public void getAliPay(PayBO orderInfo) {
        payAliPay(orderInfo.getAlipay());
    }

    @Override
    public void getWxPay(WXPayBO wxPayBO) {
        if (orderBO != null) {
            LocalConfiguration.orderNum = orderBO.getOrderNum();
            WXUtils.payWX(wxPayBO);
            LocalConfiguration.ordertype = 0;
        }else {
            LocalConfiguration.orderNum = submitPrdectOrderBO.getOrderNum();
            WXUtils.payWX(wxPayBO);
            LocalConfiguration.ordertype = 1;
        }
    }

    @Override
    public void getcardPay(ResponseBody resuBo) throws JSONException, IOException {
        String s = new String(resuBo.bytes());
        JSONObject jsonObject = new JSONObject(s);
        int status = jsonObject.optInt("status");
        if (status == 1) {
            if (orderBO != null) {
                Bundle bundle = new Bundle();
                bundle.putString("order", orderBO.getOrderNum());
                gotoActivity(OrderSurcessActivity.class, bundle, false);
            }else {
                Bundle bundle = new Bundle();
                bundle.putString("order", submitPrdectOrderBO.getOrderNum());
                gotoActivity(OrderSurcessActivity.class, bundle, false);
            }
        }
    }

    /**
     * 支付宝调起操作
     */
    private void payAliPay(final String orderInfo) {
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(getActivity());
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
                if (orderBO != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("order", orderBO.getOrderNum());
                    gotoActivity(OrderSurcessActivity.class, bundle, false);
                }else {
                    Bundle bundle = new Bundle();
                    bundle.putString("order", submitPrdectOrderBO.getOrderNum());
                    gotoActivity(OrderSurcessActivity.class, bundle, false);
                }
            } else {
                LogUtils.showToast("支付失败");
            }
        }
    };

}
