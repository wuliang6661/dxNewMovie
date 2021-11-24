package com.myp.cinema.ui.membercard;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.myp.cinema.R;
import com.myp.cinema.api.HttpInterfaceIml;
import com.myp.cinema.base.BaseActivity;
import com.myp.cinema.base.MyApplication;
import com.myp.cinema.entity.CardBO;
import com.myp.cinema.util.LogUtils;
import com.myp.cinema.util.MD5;
import com.myp.cinema.util.StringUtils;

import butterknife.Bind;
import rx.Subscriber;

/**
 * Created by wuliang on 2017/6/1.
 * <p>
 * 绑定会员卡
 */

public class AddCardActiivty extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.card_num_edit)
    EditText cardNumEdit;
    @Bind(R.id.password_edit)
    EditText passwordEdit;
    @Bind(R.id.submitBtn)
    Button submitBtn;


    String cardNum;
    String pwd;
    String verification;


    @Override
    protected int getLayout() {
        return R.layout.act_add_card;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("绑定会员卡");
        submitBtn.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        cardNum = cardNumEdit.getText().toString().trim();
        pwd = passwordEdit.getText().toString().trim();
        switch (v.getId()) {
            case R.id.submitBtn:
                if (isCardNum()) {
                    submitBtn.setEnabled(false);
                    bingCard();
                }
                break;

        }
    }

    /**
     * 检测输入
     */
    private boolean isCardNum() {
        if (StringUtils.isEmpty(cardNum)) {
            LogUtils.showToast("请输入会员卡号!");
            return false;
        }
        if (StringUtils.isEmpty(pwd)) {
            LogUtils.showToast("请输入密码！");
            return false;
        }
        return true;
    }





    /**
     * 绑定会员卡
     */
    private void bingCard() {
        if (MyApplication.cinemaBo == null) {
            LogUtils.showToast("请先选择影院，绑定会员卡！");
            return;
        }
        HttpInterfaceIml.cardBindUser(MyApplication.cinemaBo.getCinemaId(), cardNum,
                MD5.strToMd5Low32(pwd))
                .subscribe(new Subscriber<CardBO>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.showToast(e.getMessage());
                        submitBtn.setEnabled(true);
                    }

                    @Override
                    public void onNext(CardBO s) {
                        submitBtn.setEnabled(true);
                        LogUtils.showToast("绑定成功!");
                        Intent intent = new Intent();
                        setResult(1, intent);
                        finish();
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
