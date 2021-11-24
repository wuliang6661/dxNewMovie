package com.myp.cinema.ui.userforwordpass;

import android.os.Bundle;
import android.os.CountDownTimer;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.myp.cinema.R;
import com.myp.cinema.api.HttpInterfaceIml;
import com.myp.cinema.base.BaseActivity;
import com.myp.cinema.entity.PicVerificBO;
import com.myp.cinema.entity.UserBO;
import com.myp.cinema.util.LogUtils;
import com.myp.cinema.util.StringUtils;

import butterknife.Bind;
import rx.Subscriber;

/**
 * Created by wuliang on 2017/5/23.
 * <p>
 * 忘记密码获取验证码第一页
 */

public class VerifyActivity extends BaseActivity implements View.OnClickListener {


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

    String phone;
    String loadVersition = "";   //获取的正确验证码
    String versition;


    @Override
    protected int getLayout() {
        return R.layout.act_forword_one;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("身份验证");

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
        HttpInterfaceIml.userVerification(phone, "validate",picCode.getText().toString()).subscribe(new Subscriber<String>() {
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
                    Glide.with(VerifyActivity.this).load(s.getPath()).into(ivCode);
                }
            }
        });
    }



    /**
     * 身份验证
     */
    private void userVersiton() {
        HttpInterfaceIml.userVerifuser(phone, versition).subscribe(new Subscriber<UserBO>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                LogUtils.showToast(e.getMessage());
            }

            @Override
            public void onNext(UserBO s) {
                if (timer != null) {
                    timer.cancel();
                }
                Bundle bundle = new Bundle();
                bundle.putSerializable("user", s);
                gotoActivity(ForwordPassWordActivity.class, bundle, true);
            }
        });
    }


    /**
     * 验证输入
     */
    private boolean isRegister() {
        if (!phone.startsWith("1") || phone.length() != 11) {
            LogUtils.showToast("请输入正确的手机号码！");
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
