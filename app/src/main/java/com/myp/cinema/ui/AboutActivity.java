package com.myp.cinema.ui;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.myp.cinema.R;
import com.myp.cinema.base.BaseActivity;
import com.myp.cinema.util.AppUtils;

import butterknife.Bind;

/**
 * Created by wuliang on 2017/7/3.
 * <p>
 * 关于我们
 */

public class AboutActivity extends BaseActivity {


    @Bind(R.id.versions_text)
    TextView versionsText;
    @Bind(R.id.imgView)
    ImageView imgView;
//    @Bind(R.id.lottie_anination)
//    LottieAnimationView animationView;

    @Override
    protected int getLayout() {
        return R.layout.act_about;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("关于我们");

        versionsText.setText("版本号：V" + AppUtils.getAppVersionName(this));

//        animationView.setAnimation("datag.json");
//        animationView.loop(true);
//        // 自定义速度与时长
//        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f).setDuration(500);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                animationView.setProgress((Float) animation.getAnimatedValue());
//            }
//        });
//        animator.start();
    }
}
