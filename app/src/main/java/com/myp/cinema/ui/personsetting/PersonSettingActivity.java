package com.myp.cinema.ui.personsetting;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.myp.cinema.R;
import com.myp.cinema.base.MyApplication;
import com.myp.cinema.config.ConditionEnum;
import com.myp.cinema.entity.UserBO;
import com.myp.cinema.mvp.MVPBaseActivity;
import com.myp.cinema.ui.InfoActivity;
import com.myp.cinema.ui.personsetting.personupdate.PersonUpdateActivity;
import com.myp.cinema.util.LogUtils;
import com.myp.cinema.util.StringUtils;
import com.myp.cinema.widget.AvatarImageView;
import com.squareup.picasso.Picasso;

import java.io.File;

import butterknife.Bind;


/**
 * MVPPlugin
 * 会员详情设置
 */

public class PersonSettingActivity extends MVPBaseActivity<PersonSettingContract.View,
        PersonSettingPresenter> implements PersonSettingContract.View, View.OnClickListener {


    @Bind(R.id.person_img)
    AvatarImageView personImg;
    @Bind(R.id.go_back)
    LinearLayout goBack;
    @Bind(R.id.person_name)
    TextView personName;
    @Bind(R.id.person_sex)
    TextView personSex;
    @Bind(R.id.person_phone)
    TextView personPhone;
    @Bind(R.id.update_password)
    RelativeLayout updatePassword;
    @Bind(R.id.person_name_layout)
    RelativeLayout personNameLayout;
    @Bind(R.id.person_sex_layout)
    RelativeLayout personSexLayout;
    @Bind(R.id.security)
    RelativeLayout security;

    UserBO userBO = MyApplication.user;


    @Override
    protected int getLayout() {
        return R.layout.act_person_setting;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("修改资料");

        goBack.setOnClickListener(this);
        initDatas();
        personImg.setContext(this);
        personImg.setFragmentManager(getSupportFragmentManager());
        personImg.setAfterCropListener(new AvatarImageView.AfterCropListener() {
            @Override
            public void afterCrop(Bitmap photo, File file) {
                LogUtils.I(file.getAbsolutePath());
                mPresenter.updataUserImage(file);
            }
        });
        updatePassword.setOnClickListener(this);
        personNameLayout.setOnClickListener(this);
        personSexLayout.setOnClickListener(this);
        security.setOnClickListener(this);
    }


    private void initDatas() {
        if (MyApplication.isLogin == ConditionEnum.LOGIN) {   //已登录
            if (StringUtils.isEmpty(userBO.getHeader())) {
                personImg.setImageResource(R.drawable.defalt_person_img);
            } else {
                Picasso.with(this).load(userBO.getHeader()).into(personImg);
            }
            if (!StringUtils.isEmpty(userBO.getNickName())) {
                personName.setText(userBO.getNickName());
            } else {
                personName.setText(userBO.getMobile());
            }
            personPhone.setText(userBO.getMobile().substring(0, 3) + "****"
                    + userBO.getMobile().substring(7, userBO.getMobile().length()));
            if ("1".equals(userBO.getGender())) {
                personSex.setText("男");
            } else if ("2".equals(userBO.getGender())) {
                personSex.setText("女");
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go_back:
                Intent intent = new Intent();
                intent.putExtra("user", userBO);
                setResult(1, intent);
                finish();
                break;
            case R.id.update_password:   //修改密码
                Intent intent1 = new Intent(this, PersonUpdateActivity.class);
                intent1.putExtra("type", "password");
                startActivityForResult(intent1, 1);
                break;
            case R.id.person_name_layout:   //修改昵称
                Intent intent2 = new Intent(this, PersonUpdateActivity.class);
                intent2.putExtra("type", "personName");
                startActivityForResult(intent2, 1);
                break;
            case R.id.person_sex_layout:   //修改性别
                showAleetDialog();
                break;
            case R.id.security:
                startActivity(new Intent(this, InfoActivity.class));
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        personImg.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        switch (resultCode) {
            case 0x11:    //修改密码
                userBO = (UserBO) data.getSerializableExtra("user");
                initDatas();
                break;
        }
    }


    /**
     * 显示切换性别弹窗
     */
    private void showAleetDialog() {
        new AlertView.Builder().setContext(this)
                .setStyle(AlertView.Style.ActionSheet)
                .setTitle(null)
                .setMessage(null)
                .setCancelText("取消")
                .setOthers(new String[]{"男", "女"})
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, int position) {
                        if (position == 0 || position == 1) {
                            position++;
                            mPresenter.updateUserSex(position);
                        }
                    }
                })
                .build()
                .setCancelable(true)
                .show();
    }


    @Override
    public void getUserImage(UserBO userBO) {
        MyApplication.user = userBO;
        this.userBO = userBO;
        Picasso.with(this).load(userBO.getHeader()).into(personImg);
        LogUtils.showToast("头像修改成功！");
    }

    @Override
    public void getUserSex(UserBO userBO) {
        MyApplication.user = userBO;
        this.userBO = userBO;
        initDatas();
        LogUtils.showToast("性别修改成功！");
    }

    @Override
    public void onRequestError(String msg) {
        LogUtils.showToast(msg);
    }

    @Override
    public void onRequestEnd() {

    }

}