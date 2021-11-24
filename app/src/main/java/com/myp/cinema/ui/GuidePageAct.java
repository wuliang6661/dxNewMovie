package com.myp.cinema.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;

import com.myp.cinema.R;
import com.myp.cinema.api.HttpInterfaceIml;
import com.myp.cinema.base.BaseActivity;
import com.myp.cinema.base.MyApplication;
import com.myp.cinema.config.ConditionEnum;
import com.myp.cinema.entity.UserBO;
import com.myp.cinema.ui.main.MainActivity;
import com.myp.cinema.util.StringUtils;

import rx.Subscriber;

/**
 * Created by wuliang on 2017/6/7.
 * <p>
 * 引导页
 */

public class GuidePageAct extends BaseActivity {


    String uuid;

    @Override
    protected int getLayout() {
        return R.layout.act_guide_page;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
        } else {
            uuid = MyApplication.spUtils.getString("uuid", "");
            if (StringUtils.isEmpty(uuid)) {   //未登陆过
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 停止刷新
                        gotoActivity(MainActivity.class, true);
                    }
                }, 3000); // 3秒后发送消息，停止刷新
            } else {
                uuidLogin();
            }
        }
    }

    /**
     * 使用uuid登陆
     */
    private void uuidLogin() {
        HttpInterfaceIml.userLogin(null, null, uuid).subscribe(new Subscriber<UserBO>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                MyApplication.isLogin = ConditionEnum.NOLOGIN;
                MyApplication.spUtils.remove("uuid");
                gotoActivity(MainActivity.class, true);
            }

            @Override
            public void onNext(UserBO userBO) {
                MyApplication.user = userBO;
                MyApplication.spUtils.put("uuid", userBO.getUuid());
                MyApplication.isLogin = ConditionEnum.LOGIN;
                gotoActivity(MainActivity.class, true);
            }
        });
    }
}