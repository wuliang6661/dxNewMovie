package com.myp.cinema.ui.signin;


import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myp.cinema.R;
import com.myp.cinema.mvp.MVPBaseActivity;
import com.myp.cinema.widget.MyGridView;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;


/**
 * MVPPlugin
 * 签到页面
 */

public class SignInActivity extends MVPBaseActivity<SignInContract.View, SignInPresenter>
        implements SignInContract.View, View.OnClickListener {

    @Bind(R.id.sign_text)
    TextView signText;
    @Bind(R.id.left)
    LinearLayout left;
    @Bind(R.id.date)
    TextView date;
    @Bind(R.id.right)
    LinearLayout right;
    @Bind(R.id.grid_list)
    MyGridView gridList;


    SignGridAdapter adapter;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private int year, moth;

    @Override
    protected int getLayout() {
        return R.layout.act_sign_in;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("签到");

        initView();
        left.setOnClickListener(this);
        right.setOnClickListener(this);
    }


    private void initView() {
        String data = sdf.format(new Date());
        date.setText(data.split("-")[0] + "-" + data.split("-")[1]);
        adapter = new SignGridAdapter(this);
        year = Integer.valueOf(data.split("-")[0]);
        moth = Integer.valueOf(data.split("-")[1]);
        int day = Integer.valueOf(data.split("-")[2]);
        adapter.getStrings(year, moth, day);
        gridList.setAdapter(adapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left:
                if (moth - 1 == 0) {
                    year--;
                    moth = 12;
                } else {
                    moth--;
                }
                adapter.getStrings(year, moth, 0);
                date.setText(year + (moth < 10 ? "-0" : "-") + moth);
                adapter.notifyDataSetChanged();
                break;
            case R.id.right:
                if (moth + 1 > 12) {
                    year++;
                    moth = 1;
                } else {
                    moth++;
                }
                adapter.getStrings(year, moth, 0);
                date.setText(year + (moth < 10 ? "-0" : "-") + moth);
                adapter.notifyDataSetChanged();
                break;
        }
    }
}
