package com.myp.cinema.ui.playgame;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.donkingliang.banner.CustomBanner;
import com.myp.cinema.R;
import com.myp.cinema.base.MyApplication;
import com.myp.cinema.entity.GameBO;
import com.myp.cinema.entity.LunBoBO;
import com.myp.cinema.mvp.MVPBaseActivity;
import com.myp.cinema.ui.WebViewActivity;
import com.myp.cinema.ui.moviesmessage.MoviesMessageActivity;
import com.myp.cinema.util.LogUtils;
import com.myp.cinema.util.StringUtils;
import com.myp.cinema.widget.superadapter.CommonAdapter;
import com.myp.cinema.widget.superadapter.ViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * MVPPlugin
 * 游戏任务
 */

public class PlayGameActivity extends MVPBaseActivity<PlayGameContract.View,
        PlayGamePresenter> implements PlayGameContract.View,
        AdapterView.OnItemClickListener{

    @Bind(R.id.game_list)
    ListView list;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    CustomBanner banner;
    CommonAdapter<GameBO> adapter;
    private List<LunBoBO> lunBoBOs;
    private int page = 1;
    List<GameBO> data = new ArrayList<GameBO>();


    @Override
    protected int getLayout() {
        return R.layout.act_play_game;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("游戏任务");
        setHeadle();
        list.setOnItemClickListener(this);
        mPresenter.loadGameList(1);
        if (MyApplication.cinemaBo != null) {
            mPresenter.lunboList("game", MyApplication.cinemaBo.getCinemaId());
        }

        setPullRefresher();
        adapter();

    }
    private void adapter() {
        adapter = new CommonAdapter<GameBO>(this, R.layout.item_play_game, data) {
            @Override
            protected void convert(ViewHolder helper, GameBO item, int position) {
                helper.setImageUrl(R.id.game_img, item.getImageUrl());
                helper.setText(R.id.game_name, item.getGameName());
                helper.setText(R.id.game_message, item.getGameDescription());
                helper.setText(R.id.game_integral, "+" + item.getPoint());
            }
        };
    }

    private void setPullRefresher(){
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.loadGameList(1);
                page=1;
                smartRefreshLayout.finishRefresh(1000);
                refreshlayout.finishRefresh(2000);
            }
        });
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                mPresenter.loadGameList(page);
                smartRefreshLayout.finishLoadmore(1000);
                refreshlayout.finishLoadmore(2000);
            }
        });
    }
    /**
     * 为list添加轮播图
     */
    private void setHeadle() {
        View view = getLayoutInflater().inflate(R.layout.lunbo_layout, null);
        banner = (CustomBanner) view.findViewById(R.id.banner);
        list.addHeaderView(view);
    }


    /**
     * 设置轮播图视图
     */
    private void setBannerAdapter() {
        banner.setPages(new CustomBanner.ViewCreator<LunBoBO>() {
            @Override
            public View createView(Context context, int i) {
                ImageView imageView = new ImageView(context);
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                        , ViewGroup.LayoutParams.MATCH_PARENT);
                imageView.setLayoutParams(params);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                return imageView;
            }

            @Override
            public void updateUI(Context context, View view, int i, LunBoBO lunBoBO) {
                Glide.with(context).load(lunBoBO.getImageUrl())
                        .diskCacheStrategy(DiskCacheStrategy.RESULT)
                        .into((ImageView) view);
            }
        }, lunBoBOs);
        banner.startTurning(4000);
        //设置轮播图的滚动速度
        banner.setScrollDuration(300);
        //设置轮播图的点击事件
        banner.setOnPageClickListener(new CustomBanner.OnPageClickListener<LunBoBO>() {
            @Override
            public void onPageClick(int position, LunBoBO str) {
                if ("1".equals(str.getPlayType())) {   //页面跳转
                    if (!StringUtils.isEmpty(str.getRedirectUrl())) {
                        Bundle bundle = new Bundle();
                        bundle.putString("url", str.getRedirectUrl());
                        gotoActivity(WebViewActivity.class, bundle, false);
                    }
                } else if ("2".equals(str.getPlayType())) {   //电影详情界面
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("movie", str.getDxMovie());
                    gotoActivity(MoviesMessageActivity.class, bundle, false);
                }
            }
        });
    }


    @Override
    public void getLunBo(List<LunBoBO> lunBoBOs) {
        this.lunBoBOs = lunBoBOs;
        setBannerAdapter();
    }

    @Override
    public void getGameList(List<GameBO> gameBOs) {
        if (page == 1) {
            data.clear();
            data.addAll(gameBOs);
            list.setAdapter(adapter);
        } else {
            data.addAll(gameBOs);
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("url", data.get(position - 1).getRedirectUrl());
        gotoActivity(WebViewActivity.class, bundle, false);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


}
