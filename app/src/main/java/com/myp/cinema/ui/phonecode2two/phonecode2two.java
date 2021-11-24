package com.myp.cinema.ui.phonecode2two;

/**
 * Created by Administrator on 2018/1/14.
 */


import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.myp.cinema.R;
import com.myp.cinema.base.MyApplication;
import com.myp.cinema.config.ConditionEnum;
import com.myp.cinema.entity.threelandingBo;
import com.myp.cinema.mvp.MVPBaseActivity;
import com.myp.cinema.ui.main.MainActivity;
import com.myp.cinema.util.LogUtils;
import com.myp.cinema.util.MD5;
import com.myp.cinema.util.StringUtils;
import com.myp.cinema.util.ToastUtils;

import butterknife.Bind;

/**
 * Created by Administrator on 2018/1/14.
 */

public class phonecode2two extends MVPBaseActivity<phonecode2twoContract.View,
        phonecode2twoPresenter> implements phonecode2twoContract.View, View.OnClickListener {

    @Bind(R.id.password_edit)
    EditText passwordEdit;
    @Bind(R.id.password_two)
    EditText passwordTwo;
    @Bind(R.id.register_button)
    Button registerButton;

    String password;
    String passWordTwo;


    private String userId;
    private int style;
    private String wxUserId;
    private String wbUserId;
    private String qqUserId;
    private String mobile;
    private String header;
    private String nickname;
    private String gender;
    private String genders;

    @Override
    protected int getLayout() {
        return R.layout.act_forword_two;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("设置密码");
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        mobile = bundle.getString("phone");
        nickname = bundle.getString("userName");
        header = bundle.getString("userIcon");
        genders = bundle.getString("userGender");
        userId = bundle.getString("userId");
        style = bundle.getInt("style");
        registerButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        password = passwordEdit.getText().toString().trim();
        passWordTwo = passwordTwo.getText().toString().trim();
        switch (v.getId()) {
            case R.id.register_button:
                if (isForwrodPass()) {
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
                    if(genders.equals("m")){
                        gender="1";
                    }
                    if(genders.equals("f")){
                        gender="2";
                    }
                    if (MyApplication.cinemaBo != null) {
                        mPresenter.thirdregist(MyApplication.cinemaBo.getCinemaId(), mobile, MD5.strToMd5Low32(password), header, nickname, gender, wxUserId, wbUserId, qqUserId);
                    }else {
                        ToastUtils.showShortToast("请先选择影院");
                    }
                }
                break;
        }
    }


    /**
     * 验证密码
     */
    private boolean isForwrodPass() {
        if (StringUtils.isEmpty(password) || StringUtils.isEmpty(passWordTwo)) {
            LogUtils.showToast("请输入密码！");
            return false;
        }
        if (password.length() < 6 || password.length() > 20) {
            LogUtils.showToast("密码的长度为6-20位的字母数字");
            return false;
        }
        if (!passWordTwo.equals(password)) {
            LogUtils.showToast("两次输入密码不一致！");
            return false;
        }
        return true;
    }


    @Override
    public void onRequestError(String msg) {
        LogUtils.showToast(msg);
    }

    @Override
    public void onRequestEnd() {
    }
    @Override
    public void getThirdregistBo(threelandingBo thirdregistBo) {
        if(thirdregistBo.getStatus()==1){
            Intent twoback= new Intent(phonecode2two.this, MainActivity.class);
            MyApplication.user = thirdregistBo.getData();
            MyApplication.spUtils.put("uuid", thirdregistBo.getData().getUuid());
            MyApplication.isLogin = ConditionEnum.LOGIN;
            if (thirdregistBo.getData().getAlertPhoto() != null) {
                MyApplication.alertPhoto = thirdregistBo.getData().getAlertPhoto();
            }
            startActivity(twoback);
            finish();
        }else {
            LogUtils.showToast(thirdregistBo.getMessage());
        }

    }
}
