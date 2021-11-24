package com.myp.cinema.ui.feedbacklist;


import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.myp.cinema.R;
import com.myp.cinema.entity.FeedBackListBO;
import com.myp.cinema.mvp.MVPBaseActivity;
import com.myp.cinema.util.LogUtils;
import com.myp.cinema.widget.superadapter.CommonAdapter;
import com.myp.cinema.widget.superadapter.ViewHolder;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**意见反馈列表
 * MVPPluginhFeedBackListActivity
 * 邮箱 784787081@qq.com
 */

public class FeedBackListActivity extends MVPBaseActivity<FeedBackListContract.View, FeedBackListPresenter> implements FeedBackListContract.View {
    @Bind(R.id.game_list)
    ListView list;
    @Bind(R.id.go_back)
    LinearLayout goBack;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.fenxiang_img)
    ImageView fenxiangImg;
    @Bind(R.id.right_bg_01)
    ImageView rightBg01;
    @Bind(R.id.right_bg)
    ImageView rightBg;
    @Bind(R.id.right_bg_layout)
    LinearLayout rightBgLayout;
    @Bind(R.id.add)
    TextView add;
    private CommonAdapter<FeedBackListBO> adapter;

    @Override
    protected int getLayout() {
        return R.layout.act_feedback_list;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("意见反馈");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(FeedBackActivity.class, false);
            }
        });
    }

    @Override
    public void onRequestError(String msg) {
        LogUtils.showToast(msg);
    }

    @Override
    public void onRequestEnd() {
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.loadFeedBackList();
    }

    @Override
    public void getFeedBackListBO(List<FeedBackListBO> feedBackListBOs) {
        Log.d("意见反馈", "getFeedBackListBO: " + feedBackListBOs.size());
       adapter = new CommonAdapter<FeedBackListBO>(this, R.layout.item_feedback_list, feedBackListBOs) {
            @Override
            protected void convert(ViewHolder helper, FeedBackListBO item, int position) {
                if(item.getHandleStatus()==0){//未处理
                    helper.setText(R.id.feedback_item_time, String.valueOf(item.getCreateTime()));
                    helper.setText(R.id.feedback_item_stataus, "待处理");
                    helper.setTextColor(R.id.feedback_item_stataus, Color.parseColor("#eb5e3a"));
                    helper.setBackgroundRes(R.id.feedback_item_stataus,R.drawable.listitemt);
                    helper.setText(R.id.feedback_item_suggestion, item.getSuggestion());
                    helper.setVisible(R.id.xianshi,false);
                    helper.setVisible(R.id.xianshi1,false);
                }else {
                    helper.setText(R.id.feedback_item_time, item.getCreateTime());
                    helper.setText(R.id.feedback_item_stataus, "已处理");
                    helper.setTextColor(R.id.feedback_item_stataus, Color.parseColor("#999999"));
                    helper.setBackgroundRes(R.id.feedback_item_stataus,R.drawable.listitemts);
                    helper.setText(R.id.feedback_item_suggestion, item.getSuggestion());
                    helper.setText(R.id.feedback_item_Method, item.getHandleMethod());
                    helper.setText(R.id.timeout, item.getHandleTime());
                    helper.setVisible(R.id.xianshi,true);
                    helper.setVisible(R.id.xianshi1,true);
                }
            }
        };
        list.setAdapter(adapter);
    }
}

