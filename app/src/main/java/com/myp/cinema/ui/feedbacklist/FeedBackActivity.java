package com.myp.cinema.ui.feedbacklist;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.myp.cinema.R;
import com.myp.cinema.api.HttpInterfaceIml;
import com.myp.cinema.base.BaseActivity;
import com.myp.cinema.util.LogUtils;
import com.myp.cinema.util.RegexUtils;
import com.myp.cinema.util.StringUtils;

import butterknife.Bind;
import rx.Subscriber;

/**
 * Created by wuliang on 2017/6/13.
 * <p>
 * 意见反馈页面
 */

public class FeedBackActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.edit_feed)
    EditText editFeed;
    @Bind(R.id.edit_phone)
    EditText editPhone;
    @Bind(R.id.submit)
    Button submit;

    String phone;
    String feed;

    @Override
    protected int getLayout() {
        return R.layout.act_feedback;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("意见反馈");
        submit.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (isQualified()) {
            sunmitFeed();
        }
    }
    /**
     * 判断输入是否合格
     */
    private boolean isQualified() {
        phone = editPhone.getText().toString().trim();
        feed = editFeed.getText().toString().trim();
        if (StringUtils.isEmpty(feed)) {
            LogUtils.showToast("请输入反馈！");
            return false;
        }
        if (StringUtils.isEmpty(phone)) {
            LogUtils.showToast("请输入联系电话！");
            return false;
        }
        if (!RegexUtils.isMobileExact(phone)) {
            LogUtils.showToast("请输入正确的电话！");
            return false;
        }
        return true;
    }
    /**
     * 提交反馈
     */
    private void sunmitFeed() {
        HttpInterfaceIml.submitFeed(phone, feed).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }
            @Override
            public void onError(Throwable e) {
                LogUtils.showToast(e.getMessage());
            }

            @Override
            public void onNext(String s) {
                finish();
                LogUtils.showToast("提交成功!");
            }
        });
    }
}
