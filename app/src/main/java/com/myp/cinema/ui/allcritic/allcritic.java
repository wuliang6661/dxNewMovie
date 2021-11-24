package com.myp.cinema.ui.allcritic;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hedgehog.ratingbar.RatingBar;
import com.myp.cinema.R;
import com.myp.cinema.entity.CriticBO;
import com.myp.cinema.mvp.MVPBaseActivity;
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

/**影评
 * Created by Administrator on 2018/2/27.
 */

public class allcritic extends MVPBaseActivity<AllCriticContract.View,
        AllCriticPresenter> implements AllCriticContract.View{
    @Bind(R.id.list)
    ListView list;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    private String Id;
    List<CriticBO> data = new ArrayList<CriticBO>();
    private CommonAdapter<CriticBO> adapter;
    private int page = 1;
    @Override
    protected int getLayout() {
        return R.layout.list_allcritic;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("全部影评");
        Id = (String)getIntent().getExtras().getSerializable("Id");
        mPresenter.loadMoviesCritic(Long.parseLong(Id),1);
        setPullRefresher();
        adapter();

    }
    private void adapter() {
        adapter = new CommonAdapter<CriticBO>(this, R.layout.item_critic_person, data) {
            @Override
            protected void convert(ViewHolder holder, CriticBO o, final int position) {
                ImageView  dianzan = (ImageView) holder.getView(R.id.dianzan);
                TextView nickname = (TextView) holder.getView(R.id.nickname);
                ImageView  personimg = (ImageView) holder.getView(R.id.person_img);
                TextView personName = (TextView) holder.getView(R.id.person_name);
                TextView score = (TextView) holder.getView(R.id.score);
                TextView createTime = (TextView) holder.getView(R.id.createTime);
                TextView upvoteNum = (TextView) holder.getView(R.id.upvoteNum);
                RatingBar ratingbar = (RatingBar) holder.getView(R.id.ratingbar);
                if(o.getUpvoteStatus()==0){//未点赞
                    dianzan.setBackgroundResource(R.drawable.mydianzan);
                }else if(o.getUpvoteStatus()==1){
                    dianzan.setBackgroundResource(R.drawable.myzan);
                }
                if (!StringUtils.isEmpty(o.getDxAppUser().getHeader())) {//头像
                    Glide.with(allcritic.this).load(o.getDxAppUser().getHeader()).into(personimg);
                }else {
                    personimg.setImageResource(R.drawable.default_head_img);
                }
                nickname.setText(o.getDxAppUser().getNickName());//昵称
                score.setText(String.valueOf(o.getScore()));
                personName.setText(o.getComment());
                createTime.setText(o.getCreateTime());
                upvoteNum.setText(String.valueOf(o.getUpvoteNum()));
                ratingbar.setStar(Float.parseFloat(String.valueOf(o.getScore())) / 2);
                holder.getView(R.id.dian).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenter.loadDianZan(data.get(position).getId(),position);
                    }
                });
            }
        };
    }
    private void setPullRefresher(){
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.loadMoviesCritic(Long.parseLong(Id),1);
                page=1;
                smartRefreshLayout.finishRefresh(1000);
                refreshlayout.finishRefresh(2000);
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                mPresenter.loadMoviesCritic(Long.parseLong(Id),page);
                smartRefreshLayout.finishLoadMore(1000);
                refreshLayout.finishLoadMore(2000);
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
    public void getCritic(List<CriticBO> criticBO,int page) {
        if (page == 1) {
            data.clear();
            data.addAll(criticBO);
            list.setAdapter(adapter);
        } else {
            data.addAll(criticBO);
            adapter.setmDatas(data);
        }
    }

    @Override
    public void getDianZan(CriticBO criticBO, int position) {
        if(criticBO.getUpvoteStatus()==0){
            adapter.getItem(position).setUpvoteStatus(0);
        }else if(criticBO.getUpvoteStatus()==1){
            adapter.getItem(position).setUpvoteStatus(1);
        }
        adapter.getItem(position).setUpvoteNum(criticBO.getUpvoteNum());
        adapter.setmDatas(data);

    }


}
