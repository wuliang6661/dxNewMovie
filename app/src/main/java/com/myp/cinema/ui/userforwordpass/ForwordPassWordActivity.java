package com.myp.cinema.ui.userforwordpass;


import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.myp.cinema.R;
import com.myp.cinema.entity.UserBO;
import com.myp.cinema.mvp.MVPBaseActivity;
import com.myp.cinema.util.LogUtils;
import com.myp.cinema.util.MD5;
import com.myp.cinema.util.StringUtils;

import butterknife.Bind;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ForwordPassWordActivity extends MVPBaseActivity<ForwordPassWordContract.View,
        ForwordPassWordPresenter> implements ForwordPassWordContract.View, View.OnClickListener {

    @Bind(R.id.password_edit)
    EditText passwordEdit;
    @Bind(R.id.password_two)
    EditText passwordTwo;
    @Bind(R.id.register_button)
    Button registerButton;

    String password;
    String passWordTwo;

    @Override
    protected int getLayout() {
        return R.layout.act_forword_two;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("设置密码");

        registerButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        password = passwordEdit.getText().toString().trim();
        passWordTwo = passwordTwo.getText().toString().trim();
        switch (v.getId()) {
            case R.id.register_button:
                if (isForwrodPass()) {
                    mPresenter.loadForWordPassWord(MD5.strToMd5Low32(password), null);
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
    public void getUserBo(UserBO userBO) {
        LogUtils.showToast("密码修改成功！");
        finish();
    }

    @Override
    public void onRequestError(String msg) {
        LogUtils.showToast(msg);
    }

    @Override
    public void onRequestEnd() {

    }
}
