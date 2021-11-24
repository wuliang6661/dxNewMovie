package com.myp.cinema.ui;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.myp.cinema.R;
import com.myp.cinema.base.BaseActivity;

/**
 * Created by wuliang on 2017/5/4.
 * <p>
 * 程序报错进入的页面
 */

public class CustomErrorActivity extends BaseActivity {

    @Override
    protected int getLayout() {
        return R.layout.custom_error_act;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}
