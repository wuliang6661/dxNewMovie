package com.myp.cinema.ui.personread.persongrade;


import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hedgehog.ratingbar.RatingBar;
import com.myp.cinema.R;
import com.myp.cinema.base.MyApplication;
import com.myp.cinema.mvp.MVPBaseActivity;
import com.myp.cinema.util.AppManager;
import com.myp.cinema.util.LogUtils;
import com.myp.cinema.util.StringUtils;

import butterknife.Bind;


/**
 * MVPPlugin
 * 评分页面
 */

public class PersonGradeActivity extends MVPBaseActivity<PersonGradeContract.View,
        PersonGradePresenter> implements PersonGradeContract.View, View.OnClickListener,
        RatingBar.OnRatingChangeListener {

    @Bind(R.id.movies_name)
    TextView moviesName;
    @Bind(R.id.grade_num)
    TextView gradeNum;
    @Bind(R.id.edit_pinglun)
    EditText editPinglun;
    @Bind(R.id.submit_button)
    Button submitButton;
    @Bind(R.id.ratingbar)
    RatingBar ratingbar;

    String comment;
    String souce = "8.0";  //默认分数8分
    String movieId;
    String movieName;

    @Override
    protected int getLayout() {
        return R.layout.act_grade;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("评分");

        movieId = getIntent().getExtras().getString("moviesId");
        movieName = getIntent().getExtras().getString("movieName");
        moviesName.setText(movieName);
        gradeNum.setText(souce + "分");
        submitButton.setOnClickListener(this);
        ratingbar.setStar(4);
        ratingbar.setOnRatingChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        comment = editPinglun.getText().toString().trim();
        switch (v.getId()) {
            case R.id.submit_button:
                if (!StringUtils.isEmpty(comment)) {
                    mPresenter.loadSubmitComment(MyApplication.user.getId(),
                            movieId, souce, comment);
                } else {
                    LogUtils.showToast("未填写评论！");
                }
//                gotoActivity(MoviesShareActivity.class, false);
                break;
        }
    }

    @Override
    public void onRatingChange(float RatingCount) {
        souce = RatingCount * 2 + "";
        gradeNum.setText(souce + "分");
    }

    @Override
    public void onRequestError(String msg) {
        LogUtils.showToast(msg);
    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void submitSurcess() {
        LogUtils.showToast("评论成功！");
        AppManager.getAppManager().goBackMain();
    }
}
