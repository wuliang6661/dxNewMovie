package com.myp.cinema.ui.membercard;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.myp.cinema.R;
import com.myp.cinema.api.HttpInterfaceIml;
import com.myp.cinema.base.BaseActivity;
import com.myp.cinema.base.MyApplication;
import com.myp.cinema.entity.OpenCardBO;
import com.myp.cinema.entity.PayBO;
import com.myp.cinema.entity.PayResult;
import com.myp.cinema.entity.WXPayBO;
import com.myp.cinema.jpush.MessageEvent;
import com.myp.cinema.util.LogUtils;
import com.myp.cinema.util.RegexUtils;
import com.myp.cinema.util.TimeUtils;
import com.myp.cinema.util.ToastUtils;
import com.myp.cinema.wxapi.WXUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Date;
import java.util.Map;

import butterknife.Bind;
import rx.Subscriber;


/**
 * Created by Witness on 2019/1/27
 * Describe: 开通会员卡
 */
public class BindCard extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.card_phone)
    EditText card_phone;
    @Bind(R.id.card_password)
    EditText card_password;
    @Bind(R.id.sure_password)
    EditText sure_password;
    @Bind(R.id.card_name)
    EditText card_name;
    @Bind(R.id.llMale)//男
    LinearLayout llMale;
    @Bind(R.id.llFemale)//女
    LinearLayout llFemale;
    @Bind(R.id.ivMale)
    ImageView ivMale;
    @Bind(R.id.ivFemale)
    ImageView ivFemale;
    @Bind(R.id.rechargeMoney)
    RelativeLayout rechargeMoney;
    @Bind(R.id.txtMoney)
    TextView txtMoney;
    @Bind(R.id.llBirthday)
    LinearLayout llBirthday;
    @Bind(R.id.card_birthday)
    EditText card_birthday;
    @Bind(R.id.imageNone)
    ImageView imageNone;//背景海报
    @Bind(R.id.cardRules)
    TextView cardRules;
    @Bind(R.id.idcard_name)//身份证
            EditText idcard_name;
    @Bind(R.id.cardName)
    EditText cardName;
    @Bind(R.id.txt)
    TextView txt;

    private int selectedGender = 1;//1 女  0  男
    private String rechargeNum = "";//充值金额
    String sex;
    private String birthday = "";//生日
    private String cardNameStr = "会员卡";

    @Override
    protected int getLayout() {
        return R.layout.bind_card;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setListener();
        if (MyApplication.cinemaBo != null) {
            getOpenMoney();
        }
        if (MyApplication.user != null) {
            card_phone.setText(MyApplication.user.getMobile());
        }
        EventBus.getDefault().register(this);
        getCardImage();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void setListener() {
        llMale.setOnClickListener(this);
        llFemale.setOnClickListener(this);
        rechargeMoney.setOnClickListener(this);
        llBirthday.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llMale:
                ivMale.setImageDrawable(getResources().getDrawable(R.mipmap.select_true));
                ivFemale.setBackground(getResources().getDrawable(R.drawable.circle));
                ivFemale.setImageDrawable(null);
                selectedGender = 0;
                break;
            case R.id.llFemale:
                ivFemale.setImageDrawable(getResources().getDrawable(R.mipmap.select_true));
                ivMale.setBackground(getResources().getDrawable(R.drawable.circle));
                ivMale.setImageDrawable(null);
                selectedGender = 1;
                break;
            case R.id.llBirthday:
                TimePickerView pvTime = new TimePickerBuilder(BindCard.this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        card_birthday.setText(String.valueOf(TimeUtils.date2String(date, "yyyy-MM-dd")));
                        birthday = String.valueOf(TimeUtils.date2String(date, "yyyy-MM-dd"));
                    }
                }).setSubmitColor(Color.parseColor("#32B8E8"))
                        .setCancelColor(Color.parseColor("#32B8E8"))
                        .build();
                pvTime.show();
                break;
            case R.id.rechargeMoney:
                if (TextUtils.isEmpty(card_phone.getText())) {
                    ToastUtils.showShortToast("请输入电话号码");
                    return;
                }
                if (TextUtils.isEmpty(card_password.getText())) {
                    ToastUtils.showShortToast("请输入密码");
                    return;
                }
                if (TextUtils.isEmpty(card_name.getText())) {
                    card_name.setText("");
                }
                if (birthday.equals("")) {
                    ToastUtils.showShortToast("请选择生日");
                    return;
                }
                if (!sure_password.getText().toString().equals(card_password.getText().toString())) {
                    ToastUtils.showShortToast("两次输入的密码不一致");
                    return;
                }
                if (selectedGender == 1) {
                    sex = "1";
                } else {
                    sex = "0";
                }
                if (rechargeNum.equals("")) {
                    ToastUtils.showShortToast("获取金额失败，请稍后重试");
                    return;
                }
                openCardDialog();
                break;
            default:
                break;
        }
    }

    private void openCardDialog() {
        LayoutInflater factory = LayoutInflater.from(this);//提示框
        final View view = factory.inflate(R.layout.dialog_order_pay, null);//这里必须是final的
        TextView cancle = view.findViewById(R.id.off_commit);
        TextView commit = view.findViewById(R.id.commit);
        TextView title = view.findViewById(R.id.title);
        TextView message = view.findViewById(R.id.message);
        title.setText("提示!");
        message.setText(String.format("您当前开通的是【德信影城】的%s，请核对后提交！", cardNameStr));
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
                if (!TextUtils.isEmpty(idcard_name.getText())) {
                    if (RegexUtils.isIDCard18(idcard_name.getText()) || RegexUtils.isIDCard15(idcard_name.getText())) {
                        showUpdateDialog();
                    } else {
                        ToastUtils.showShortToast("请输入正确的身份证号码");
                    }
                } else {
                    showUpdateDialog();
                }
            }
        });
        dialog.setView(view);
        dialog.show();
    }


    private void getOpenMoney() {
        HttpInterfaceIml.getCardPay(MyApplication.cinemaBo.getCinemaId()).subscribe(new Subscriber<OpenCardBO>() {
            @Override
            public void onCompleted() {
                stopProgress();
            }

            @Override
            public void onError(Throwable e) {
                finish();
                ToastUtils.showShortToast(e.getMessage());
            }

            @Override
            public void onNext(OpenCardBO s) {
                stopProgress();
                txtMoney.setText(String.format("(需充值%s元)", s.getInitMoney()));
                rechargeNum = s.getInitMoney();
                cardRules.setText(s.getOpenCardNotice());
                cardName.setText(s.getLevelName());
                cardNameStr = s.getLevelName();
                setTitle(s.getLevelName());
                if (s.getLevelName().contains("集团99看过瘾")){
                    txt.setText("立享优惠");
                }else {
                    txt.setText("立即开卡");
                }
            }
        });
    }

    /**
     * 开卡,支付宝
     *
     * @param phone
     * @param password
     * @param username
     * @param sex
     * @param rechargeMoney
     */
    private void bindCard(String phone, String password, String username, String sex, String birthday, String idcard, String rechargeMoney) {
        showProgress("加载中");
        HttpInterfaceIml.bindCard("1", MyApplication.cinemaBo.getCinemaId(),
                phone, password, username, sex, birthday, idcard, rechargeMoney).subscribe(new Subscriber<PayBO>() {
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
            public void onNext(PayBO s) {
                stopProgress();
                payAliPay(s.getAlipay());
            }
        });
    }

    /**
     * 开卡,微信
     *
     * @param phone
     * @param password
     * @param username
     * @param sex
     * @param rechargeMoney
     */
    private void bindCardWechat(String phone, String password, String username, String sex, String birthday, String idcard, String rechargeMoney) {
        showProgress("加载中");
        HttpInterfaceIml.bindCardWechat("1", MyApplication.cinemaBo.getCinemaId(),
                phone, password, username, sex, birthday, idcard, rechargeMoney).subscribe(new Subscriber<WXPayBO>() {
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
            public void onNext(WXPayBO s) {
                stopProgress();
                WXUtils.payWXs(s, 2);
            }
        });
    }

    /**
     * 支付弹窗
     */
    private void showUpdateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialog);
        final LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.pay_dialog, null);
        ImageView ivClose = v.findViewById(R.id.ivClose);
        final RelativeLayout rlAlipay = v.findViewById(R.id.rlAlipay);//支付宝
        final RelativeLayout rlWechatPay = v.findViewById(R.id.rlWechatPay);//微信支付
        final RelativeLayout rlCardPay = v.findViewById(R.id.rlCardPay);//会员卡支付
        rlCardPay.setVisibility(View.GONE);
        builder.setView(v);
        builder.setCancelable(true);
        final Dialog noticeDialog = builder.create();
        noticeDialog.getWindow().setGravity(Gravity.BOTTOM);
        noticeDialog.getWindow().setWindowAnimations(R.style.anim_menu_bottombar);
        noticeDialog.show();
        rlAlipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgress("加载中");
                bindCard(card_phone.getText().toString(),
                        card_password.getText().toString(),
                        card_name.getText().toString(),
                        sex, birthday, idcard_name.getText().toString(), rechargeNum);
            }
        });
        rlWechatPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgress("加载中");
                bindCardWechat(card_phone.getText().toString(),
                        card_password.getText().toString(),
                        card_name.getText().toString(),
                        sex, birthday, idcard_name.getText().toString(), rechargeNum);
            }
        });
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noticeDialog.dismiss();
            }
        });

        WindowManager.LayoutParams layoutParams = noticeDialog.getWindow().getAttributes();
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        noticeDialog.getWindow().setAttributes(layoutParams);
    }

    /**
     * 支付宝调起操作
     */
    private void payAliPay(final String orderInfo) {
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(BindCard.this);
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
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            PayResult payResult = new PayResult((Map<String, String>) msg.obj);
            String resultStatus = payResult.getResultStatus();
            // 判断resultStatus 为9000则代表支付成功
            if (TextUtils.equals(resultStatus, "9000")) {
                Intent intent = new Intent();
                setResult(2, intent);
                finish();
                LogUtils.showToast("开卡成功");
            } else {
                LogUtils.showToast("支付失败");
            }
        }
    };

    /**
     * finish,接收微信支付返回结果
     *
     * @param messageEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent messageEvent) {
        if (messageEvent.getMessageType().equals("success")) {
            Intent intent = new Intent();
            setResult(2, intent);
            finish();
            LogUtils.showToast("开卡成功");
        }
    }

    /**
     * 获取背景图片
     */
    private void getCardImage() {
//        HttpInterfaceIml.getCardImage(MyApplication.cinemaBo.getCinemaId()).subscribe(new Subscriber<CardBgBO>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
////                ToastUtils.showShortToast(e.getMessage());
//            }
//
//            @Override
//            public void onNext(CardBgBO s) {
//                if (s != null && s.getBindCardPic() != null) {
//                    Glide.with(BindCard.this).load(s.getBindCardPic()).into(imageNone);
//                }
//            }
//        });
    }

}
