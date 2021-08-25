package com.myp.cinema.ui.personread;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.hedgehog.ratingbar.RatingBar;
import com.myp.cinema.R;
import com.myp.cinema.base.MyApplication;
import com.myp.cinema.entity.CommentBO;
import com.myp.cinema.mvp.MVPBaseActivity;
import com.myp.cinema.ui.personread.persongrade.PersonGradeActivity;
import com.myp.cinema.util.CimemaUtils;
import com.myp.cinema.util.LogUtils;
import com.myp.cinema.util.StringUtils;
import com.myp.cinema.widget.superadapter.CommonAdapter;
import com.myp.cinema.widget.superadapter.ViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * MVPPlugin
 * 看过的影片列表
 */

public class PersonReadActivity extends MVPBaseActivity<PersonReadContract.View,
        PersonReadPresenter> implements PersonReadContract.View {
    @Bind(R.id.list)
    ListView list;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @Bind(R.id.none_layout)
    LinearLayout nonelayout;
    @Bind(R.id.text_layout)
    TextView textlayout;
    private int page = 1;
    List<CommentBO> data = new ArrayList<CommentBO>();
    private CommonAdapter<CommentBO> adapter;
    @Override
    protected int getLayout() {
        return R.layout.list_personcomment;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("看过");
        mPresenter.loadPersonReadList(MyApplication.user.getId(), 1);
        setPullRefresher();
        adapter();
    }
    private void adapter() {
        adapter = new CommonAdapter<CommentBO>(this, R.layout.item_pingfen_movies, data) {
            @Override
            protected void convert(ViewHolder viewHolder, final CommentBO item, int position) {
                if (StringUtils.isEmpty(item.getPicture())) {
                    viewHolder.setImageResource(R.id.movie_img_layout, R.color.act_bg01);
                } else {
                    viewHolder.setImageUrl(R.id.movie_img_layout, item.getPicture());
                }
                viewHolder.setText(R.id.movies_name, item.getMovieName());
                RatingBar ratingBar = viewHolder.getView(R.id.ratingbar);
                if ("1".equals(item.getCommentRecord())) {  //已评
                    ratingBar.setStar(Float.parseFloat(item.getScore()) / 2);
                    viewHolder.getView(R.id.comment_type).setVisibility(View.VISIBLE);
                    viewHolder.getView(R.id.comment_num).setVisibility(View.VISIBLE);
                    viewHolder.setText(R.id.comment_num, CimemaUtils.getComment(Float.parseFloat(item.getScore())));
                    viewHolder.setText(R.id.movies_comment, item.getComment());
                    viewHolder.getView(R.id.pinfen_button).setVisibility(View.GONE);
                } else {
                    ratingBar.setStar(0);
                    viewHolder.getView(R.id.comment_type).setVisibility(View.GONE);
                    viewHolder.getView(R.id.comment_num).setVisibility(View.GONE);
                    viewHolder.setText(R.id.movies_comment, "快来写影评吧！");
                    viewHolder.getView(R.id.pinfen_button).setVisibility(View.VISIBLE);
                }
                viewHolder.getView(R.id.pinfen_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString("moviesId", item.getId());
                        bundle.putString("movieName", item.getMovieName());
                        gotoActivity(PersonGradeActivity.class, bundle, false);
                    }
                });
            }
        };
    }

    private void setPullRefresher(){
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.loadPersonReadList(MyApplication.user.getId(), 1);
                page=1;
                smartRefreshLayout.finishRefresh(1000);
                refreshlayout.finishRefresh(2000);
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshlayout) {
                page++;
                mPresenter.loadPersonReadList(MyApplication.user.getId(), page);
                smartRefreshLayout.finishLoadMore(1000);
                refreshlayout.finishLoadMore(2000);
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
    public void getReadList(List<CommentBO> commentBOs,int page) {
        if(page==1){
            if(commentBOs.size()==0){
                smartRefreshLayout.setVisibility(View.GONE);
                nonelayout.setVisibility(View.VISIBLE);
                textlayout.setText("您当前还没有看过的电影哦");
            }else {
                data.clear();
                data.addAll(commentBOs);
                list.setAdapter(adapter);
            }
        }else {
            data.addAll(commentBOs);
            adapter.setmDatas(data);
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}