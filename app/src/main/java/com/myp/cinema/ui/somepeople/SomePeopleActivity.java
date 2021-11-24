package com.myp.cinema.ui.somepeople;


import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.myp.cinema.R;
import com.myp.cinema.mvp.MVPBaseActivity;
import com.myp.cinema.ui.someinvitation.IssuedInvitationActivity;

import butterknife.Bind;


/**
 * 约人看电影界面
 */

public class SomePeopleActivity extends MVPBaseActivity<SomePeopleContract.View,
        SomePeoplePresenter> implements SomePeopleContract.View, View.OnClickListener {

    @Bind(R.id.me_message)
    RadioButton meMessage;
    @Bind(R.id.he_enent)
    RadioButton heEnent;
    @Bind(R.id.radio_layout)
    RadioGroup radioLayout;
    @Bind(R.id.none_layout)
    LinearLayout noneLayout;
    @Bind(R.id.recyle)
    RecyclerView recyle;
    @Bind(R.id.fabu_button)
    ImageButton fabuButton;

    @Override
    protected int getLayout() {
        return R.layout.act_some_people;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("约人看电影");
        setRightImage(R.drawable.liaotainliebiao, null);
        fabuButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fabu_button:
                gotoActivity(IssuedInvitationActivity.class, false);
                break;
        }
    }
}
