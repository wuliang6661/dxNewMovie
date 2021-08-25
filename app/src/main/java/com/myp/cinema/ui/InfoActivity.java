package com.myp.cinema.ui;

import android.os.Bundle;
import com.myp.cinema.R;
import com.myp.cinema.base.BaseActivity;

/**
 * Description : InfoActivity
 *  隐私政策
 * @author WITNESS
 * @date 2020/8/24
 */
public class InfoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("用户协议与隐私政策");
        goBack();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_info;
    }
}