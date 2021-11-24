package com.myp.cinema.ui.main.home.nextmovies;


import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.myp.cinema.R;
import com.myp.cinema.entity.CinemaBo;
import com.myp.cinema.entity.MoviesSoonBO;
import com.myp.cinema.mvp.MVPBaseFragment;
import com.myp.cinema.ui.moviespresell.PresellMoviesActivity;
import com.myp.cinema.util.TimeUtils;
import com.myp.cinema.widget.expandlist.adapter.DockingExpandableListViewAdapter;
import com.myp.cinema.widget.expandlist.controller.IDockingHeaderUpdateListener;
import com.myp.cinema.widget.expandlist.view.DockingExpandableListView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * MVPPlugin
 * 即将上映
 */

public class NextMoviesFragment extends MVPBaseFragment<NextMoviesContract.View,
        NextMoviesPresenter> implements NextMoviesContract.View{
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @Bind(R.id.next_movies_list)
    DockingExpandableListView nextMoviesList;


    CinemaBo cinemaBo;

    List<MoviesSoonBO> moviesSoonBO;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_next_movies, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    private void initViews() {
        setPullRefresher();
    }
    private void setPullRefresher(){
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.loadMoviesSoon(cinemaBo.getCinemaId());
                smartRefreshLayout.finishRefresh(1000);
                refreshlayout.finishRefresh(2000);
            }
        });

    }

    /**
     * 设置数据进入列表
     */
    private void setAdapter() {
        DemoDockingAdapterDataSource listData = new DemoDockingAdapterDataSource(getActivity(), moviesSoonBO);
        nextMoviesList.setGroupIndicator(null);
        nextMoviesList.setOverScrollMode(View.OVER_SCROLL_NEVER);
        nextMoviesList.setAdapter(new DockingExpandableListViewAdapter(getActivity(), nextMoviesList, listData));
        nextMoviesList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {  //点击不可关闭
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
        View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.next_movies_header, nextMoviesList, false);
        nextMoviesList.setDockingHeader(headerView, new IDockingHeaderUpdateListener() {  //设置悬浮显示
            @Override
            public void onUpdate(View headerView, int groupPosition, boolean expanded) {
                TextView date = (TextView) headerView.findViewById(R.id.date_month);
                TextView week = (TextView) headerView.findViewById(R.id.date_week);
                date.setText(TimeUtils.string2MonAndDay(moviesSoonBO.get(groupPosition).getDate()));
                week.setText(TimeUtils.string2Week2(moviesSoonBO.get(groupPosition).getDate()));
            }
        });
        nextMoviesList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition,
                                        int childPosition, long id) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("movie", moviesSoonBO.get(groupPosition).getMovieList().get(childPosition));
                gotoActivity(PresellMoviesActivity.class, bundle, false);
                return false;
            }
        });
        int groupCount = nextMoviesList.getCount();   //默认展开
        for (int i = 0; i < groupCount; i++) {
            nextMoviesList.expandGroup(i);
        }
    }

    public void setCinemaBo(CinemaBo cinemaBo) {
        this.cinemaBo = cinemaBo;
        if (mPresenter != null) {
            mPresenter.loadMoviesSoon(cinemaBo.getCinemaId());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    @Override
    public void onRequestError(String msg) {
//        LogUtils.showToast(msg);
    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void getMoviesSoon(List<MoviesSoonBO> moviesSoonBO) {
        this.moviesSoonBO = moviesSoonBO;
        setAdapter();
    }
}
