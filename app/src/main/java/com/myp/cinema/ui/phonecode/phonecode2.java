package com.myp.cinema.ui.phonecode;

/**
 * Created by Administrator on 2018/1/14.
 */


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myp.cinema.R;
import com.myp.cinema.api.HttpInterfaceIml;
import com.myp.cinema.base.BaseActivity;
import com.myp.cinema.base.MyApplication;
import com.myp.cinema.config.ConditionEnum;
import com.myp.cinema.entity.PicVerificBO;
import com.myp.cinema.entity.threelandingBo;
import com.myp.cinema.ui.main.MainActivity;
import com.myp.cinema.ui.phonecode2two.phonecode2two;
import com.myp.cinema.util.LogUtils;
import com.myp.cinema.util.RegexUtils;
import com.myp.cinema.util.StringUtils;

import butterknife.Bind;
import rx.Subscriber;


/**
 * MVPPlugin
 * 注册页面
 */

public class phonecode2 extends BaseActivity implements View.OnClickListener {


    String phone;
    String loadVersition = "";   //获取的正确验证码
    String versition;
    @Bind(R.id.go_back)
    LinearLayout goBack;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.right_bg_01)
    ImageView rightBg01;
    @Bind(R.id.right_bg)
    ImageView rightBg;
    @Bind(R.id.right_bg_layout)
    LinearLayout rightBgLayout;
    @Bind(R.id.phone_edit)
    EditText phoneEdit;
    @Bind(R.id.verification_edit)
    EditText verificationEdit;
    @Bind(R.id.get_verification)
    Button getVerification;
    @Bind(R.id.register_button)
    Button registerButton;
    @Bind(R.id.picCode)
    EditText picCode;//图文验证
    @Bind(R.id.ivCode)
    ImageView ivCode;//图文验证


    private String userId;
    private int style;
    private String wxUserId;
    private String wbUserId;
    private String qqUserId;
    private String userName;
    private String userIcon;
    private String userGender;


    @Override
    protected int getLayout() {
        return R.layout.act_forword_one;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("身份验证");
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        userId = bundle.getString("userId");
        style = bundle.getInt("style");
        userName = bundle.getString("userName");
        userIcon = bundle.getString("userIcon");
        userGender = bundle.getString("userGender");
        registerButton.setOnClickListener(this);
        getVerification.setOnClickListener(this);
        ivCode.setOnClickListener(this);
        getPicVersition();
    }

    @Override
    public void onClick(View v) {
        phone = phoneEdit.getText().toString().trim();
        switch (v.getId()) {
            case R.id.register_button:
                versition = verificationEdit.getText().toString().trim();
                if (isRegister()) {
                    userVersiton();
                }
                break;
            case R.id.get_verification:
                if (phone.startsWith("1") && phone.length() == 11) {
                    if (StringUtils.isEmpty(picCode.getText().toString())){
                        LogUtils.showToast("图文验证码错误！");
                    }else {
                        getVersition();
                    }
                } else {
                    LogUtils.showToast("请输入正确的手机号！");
                }
                break;
            case R.id.ivCode:
                getPicVersition();
                break;
            default:
                break;
        }
    }


    /**
     * 获取验证码
     */
    private void getVersition() {
        HttpInterfaceIml.userVerification(phone, "register",picCode.getText().toString()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                LogUtils.showToast(e.getMessage());
            }

            @Override
            public void onNext(String s) {
                loadVersition = s;
                timer.start();
                getVerification.setEnabled(false);
            }
        });
    }


    /**
     * 获取图文验证码
     */
    private void getPicVersition() {
        HttpInterfaceIml.picVerification().subscribe(new Subscriber<PicVerificBO>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                LogUtils.showToast(e.getMessage());
            }

            @Override
            public void onNext(PicVerificBO s) {
                if (s != null) {
                    Glide.with(phonecode2.this).load(s.getPath()).into(ivCode);
                }
            }
        });
    }

    /**
     * 身份验证
     */
    private void userVersiton() {
        if (style == 1) {
            wxUserId = null;
            wbUserId = userId;
            qqUserId = null;
        }
        if (style == 2) {
            wxUserId = null;
            wbUserId = null;
            qqUserId = userId;
        }
        if (style == 3) {
            wxUserId = userId;
            wbUserId = null;
            qqUserId = null;
        }

        HttpInterfaceIml.phonebinding(userIcon,phone,wxUserId, wbUserId, qqUserId, versition).subscribe(new Subscriber<threelandingBo>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                LogUtils.showToast(e.getMessage());
            }

            @Override
            public void onNext(threelandingBo s) {

                if (timer != null) {
                    timer.cancel();
                }
                if(s.getStatus()==1){
                    Intent back= new Intent(phonecode2.this, MainActivity.class);
                    MyApplication.user = s.getData();
                    MyApplication.spUtils.put("uuid", s.getData().getUuid());
                    MyApplication.isLogin = ConditionEnum.LOGIN;
                    if (s.getData().getAlertPhoto() != null) {
                        MyApplication.alertPhoto = s.getData().getAlertPhoto();
                    }
                    startActivity(back);
                    finsh();
                }else {
                    if(s.getStatus()==0){
                        LogUtils.showToast(s.getMessage());
                    }else {
                        Intent phonecode2two = new Intent(phonecode2.this,phonecode2two.class);
                        phonecode2two.putExtra("phone", phone);
                        phonecode2two.putExtra("userName", userName);
                        phonecode2two.putExtra("userIcon", userIcon);
                        phonecode2two.putExtra("userGender", userGender);
                        phonecode2two.putExtra("style", style);
                        phonecode2two.putExtra("userId", userId);
                        startActivity(phonecode2two);
                    }

                }



            }
        });
    }

    private void finsh() {
        finish();
    }


    /**
     * 验证输入
     */
    private boolean isRegister() {
        if (!RegexUtils.isMobileExact(phone)) {
            LogUtils.showToast("请输入正确的手机号！");
            return false;
        }
        return true;
    }

    CountDownTimer timer = new CountDownTimer(60 * 1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            //每隔countDownInterval秒会回调一次onTick()方法
            getVerification.setText(millisUntilFinished / 1000 + " s");
        }

        @Override
        public void onFinish() {
            getVerification.setText("重新获取");
            getVerification.setEnabled(true);
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }
}