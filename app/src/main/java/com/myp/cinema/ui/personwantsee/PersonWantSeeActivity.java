package com.myp.cinema.ui.personwantsee;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.myp.cinema.R;
import com.myp.cinema.base.MyApplication;
import com.myp.cinema.entity.MoviesByCidBO;
import com.myp.cinema.mvp.MVPBaseActivity;
import com.myp.cinema.ui.moviesmessage.MoviesMessageActivity;
import com.myp.cinema.ui.moviespresell.PresellMoviesActivity;
import com.myp.cinema.ui.moviessession.SessionActivity;
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
 * 想看的电影
 */

public class PersonWantSeeActivity extends MVPBaseActivity<PersonWantSeeContract.View,
        PersonWantSeePresenter> implements PersonWantSeeContract.View, AdapterView.OnItemClickListener {
    @Bind(R.id.list)
    ListView list;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @Bind(R.id.none_layout)
    LinearLayout nonelayout;
    @Bind(R.id.text_layout)
    TextView textlayout;
    private CommonAdapter<MoviesByCidBO> adapter;
    private List<MoviesByCidBO> data =  new ArrayList<>();
    private  int page = 1;
    @Override
    protected int getLayout() {
        return R.layout.list_layout;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("想看");
        mPresenter.loadWandSeeMovie(MyApplication.user.getId(), MyApplication.cinemaBo.getCinemaId(),1);
        list.setOnItemClickListener(this);

        setPullRefresher();
        adapter();
    }
    private void adapter() {
        adapter = new CommonAdapter<MoviesByCidBO>(this, R.layout.item_movies_want, data) {

            @Override
            protected void convert(ViewHolder helper, final MoviesByCidBO item, int position) {
                helper.setText(R.id.movies_name, item.getMovieName());
                if (!StringUtils.isEmpty(item.getStartPlay())) {
                    helper.setText(R.id.movies_time, item.getStartPlay().split(" ")[0] + " 上映");
                } else {
                    helper.setText(R.id.movies_time, "");
                }
                helper.setText(R.id.movies_type, item.getMovieType());
                helper.setText(R.id.movies_message, item.getSummary());
                if ("0.0".equals(item.getPoint())) {
                    helper.getView(R.id.movies_num).setVisibility(View.GONE);
                } else {
                    helper.setText(R.id.movies_num, item.getPoint() + "分");
                    helper.getView(R.id.movies_num).setVisibility(View.VISIBLE);
                }
                if (!StringUtils.isEmpty(item.getPicture())) {
                    helper.setImageUrl(R.id.movie_img, item.getPicture());
                } else {
                    helper.setImageResource(R.id.movie_img, R.color.act_bg01);
                }
                Button payButton = helper.getView(R.id.movies_submit);
                payButton.setVisibility(View.VISIBLE);
                if ("1".equals(item.getSell())) {
                    payButton.setBackgroundResource(R.drawable.button_borld_yello);
                    payButton.setText("预售");
                } else if ("2".equals(item.getSell())) {
                    payButton.setBackgroundResource(R.drawable.submit_button_bg);
                    payButton.setText("购买");
                } else {
                    payButton.setVisibility(View.GONE);
                }
                payButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goSession(item);
                    }
                });
            }
        };
    }

    private void setPullRefresher(){
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.loadWandSeeMovie(MyApplication.user.getId(), MyApplication.cinemaBo.getCinemaId(),1);
                page=1;
                smartRefreshLayout.finishRefresh(1000);
                refreshlayout.finishRefresh(2000);
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                mPresenter.loadWandSeeMovie(MyApplication.user.getId(), MyApplication.cinemaBo.getCinemaId(),page);
                smartRefreshLayout.finishLoadMore(1000);
                refreshLayout.finishLoadMore(2000);
            }
        });
    }


    /**
     * 点击购票事件处理
     */
    private void goSession(MoviesByCidBO moviesByCidBO) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("movies", moviesByCidBO);
        gotoActivity(SessionActivity.class, bundle, false);
    }

    @Override
    public void getWantSeeMovie(List<MoviesByCidBO> moviesByCidBOs,int pages) {
        Log.d("ListView", "第"+page+"页"+moviesByCidBOs.size());
        if(pages==1){
            if(moviesByCidBOs.size()==0){
                smartRefreshLayout.setVisibility(View.GONE);
                nonelayout.setVisibility(View.VISIBLE);
                textlayout.setText("您当前还没有想看的电影哦");
            }else {
                data.clear();
                data.addAll(moviesByCidBOs);
                list.setAdapter(adapter);
            }
        }else {
            data.addAll(moviesByCidBOs);
            adapter.setmDatas(data);

        }

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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if ("1".equals(data.get(position).getSell())) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("movie", data.get(position));
            gotoActivity(PresellMoviesActivity.class, bundle, false);
        } else {
            Bundle bundle = new Bundle();
            bundle.putSerializable("movie", data.get(position));
            gotoActivity(MoviesMessageActivity.class, bundle, false);
        }
    }

}
