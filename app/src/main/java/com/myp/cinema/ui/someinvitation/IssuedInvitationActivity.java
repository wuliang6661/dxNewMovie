package com.myp.cinema.ui.someinvitation;


import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.myp.cinema.R;
import com.myp.cinema.mvp.MVPBaseActivity;
import com.myp.cinema.ui.somesuccess.ReleaseSuccessActivity;
import com.myp.cinema.widget.mypicker.DataPickerDialog;
import com.myp.cinema.widget.mypicker.DatePickerDialog;
import com.myp.cinema.widget.mypicker.DateUtil;

import java.util.List;

import butterknife.Bind;


/**
 * 发布邀约
 */

public class IssuedInvitationActivity extends MVPBaseActivity<IssuedInvitationContract.View,
        IssuedInvitationPresenter> implements IssuedInvitationContract.View, View.OnClickListener {

    @Bind(R.id.submit_button)
    TextView submitButton;
    @Bind(R.id.cinema_name)
    TextView cinemaName;
    @Bind(R.id.cinema_address)
    TextView cinemaAddress;
    @Bind(R.id.update_cinema)
    TextView updateCinema;
    @Bind(R.id.cinema_distance)
    TextView cinemaDistance;
    @Bind(R.id.people_num)
    TextView peopleNum;
    @Bind(R.id.select_people_num)
    RelativeLayout selectPeopleNum;
    @Bind(R.id.date)
    TextView dateText;
    @Bind(R.id.select_date)
    RelativeLayout selectDate;
    @Bind(R.id.time)
    TextView time;
    @Bind(R.id.select_time)
    RelativeLayout selectTime;
    @Bind(R.id.recyle)
    RecyclerView recyle;

    private Dialog dateDialog, timeDialog, chooseDialog;

    @Override
    protected int getLayout() {
        return R.layout.act_issued_invitation;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("发布邀约");
        selectDate.setOnClickListener(this);
        submitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.select_date:
                showDateDialog(DateUtil.getDateForString("2017-05-18"));
                break;
            case R.id.submit_button:
                gotoActivity(ReleaseSuccessActivity.class, false);
                break;
        }
    }


    /**
     * 显示日期选择器
     *
     * @param date
     */
    private void showDateDialog(List<Integer> date) {
        DatePickerDialog.Builder builder = new DatePickerDialog.Builder(this);
        builder.setOnDateSelectedListener(new DatePickerDialog.OnDateSelectedListener() {
            @Override
            public void onDateSelected(int[] dates) {
                dateText.setText(dates[0] + "-" + (dates[1] > 9 ? dates[1] : ("0" + dates[1])) + "-"
                        + (dates[2] > 9 ? dates[2] : ("0" + dates[2])));
            }

            @Override
            public void onCancel() {
            }
        })
                .setMinYear(date.get(0) - 1)
                .setMinMonth(date.get(1) - 1)
                .setMinDay(date.get(2) - 1)
                .setSelectYear(date.get(0) - 1)
                .setSelectMonth(date.get(1) - 1)
                .setSelectDay(date.get(2) - 1);
        builder.setMaxYear(DateUtil.getYear() + 10);
        builder.setMaxMonth(DateUtil.getDateForString(DateUtil.getToday()).get(1) + 12);
        builder.setMaxDay(DateUtil.getDateForString(DateUtil.getToday()).get(2) + 300);
        dateDialog = builder.create();
        dateDialog.show();
    }


    /**
     * 显示自定义选择器
     */
    private void showChooseDialog(List<String> mlist) {
        DataPickerDialog.Builder builder = new DataPickerDialog.Builder(this);
        chooseDialog = builder.setData(mlist).setSelection(1).setTitle("取消")
                .setOnDataSelectedListener(new DataPickerDialog.OnDataSelectedListener() {
                    @Override
                    public void onDataSelected(String itemValue, int position) {
//                        mTextView.setText(itemValue);
                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();
        chooseDialog.show();
    }
}