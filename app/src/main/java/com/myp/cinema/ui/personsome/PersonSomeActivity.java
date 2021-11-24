package com.myp.cinema.ui.personsome;


import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.myp.cinema.R;
import com.myp.cinema.mvp.MVPBaseActivity;
import com.myp.cinema.widget.superadapter.CommonAdapter;
import com.myp.cinema.widget.superadapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


/**
 * MVPPlugin
 * 我的约会
 */

public class PersonSomeActivity extends MVPBaseActivity<PersonSomeContract.View,
        PersonSomePresenter> implements PersonSomeContract.View {

    @Bind(R.id.list)
    ListView listview;
    @Bind(R.id.none_layout)
    LinearLayout noneLayout;

    @Override
    protected int getLayout() {
        return R.layout.listview_layout;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("我的约会");

//        setAdapter();
        listview.setVisibility(View.GONE);
        noneLayout.setVisibility(View.VISIBLE);
    }


    private void setAdapter() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        CommonAdapter<String> adapter = new CommonAdapter<String>(this, R.layout.item_yue_movies, list) {

            @Override
            protected void convert(ViewHolder helper, String item, int position) {
                Button submit = helper.getView(R.id.submit_button);
                submit.setBackgroundResource(R.drawable.button_yello);
                submit.setText("邀约成功");
                helper.getView(R.id.time_layout).setVisibility(View.GONE);
            }

        };
        listview.setAdapter(adapter);
    }


}
