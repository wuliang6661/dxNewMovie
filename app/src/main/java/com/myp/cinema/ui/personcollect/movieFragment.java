package com.myp.cinema.ui.personcollect;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.myp.cinema.R;
import com.myp.cinema.base.MyApplication;
import com.myp.cinema.entity.MoviesByCidBO;
import com.myp.cinema.mvp.MVPBaseFragment;
import com.myp.cinema.ui.moviesmessage.MoviesMessageActivity;
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
 * Created by Administrator on 2018/1/19.
 */
public class movieFragment extends MVPBaseFragment<movieContract.View, moviePresenter>
        implements movieContract.View ,AdapterView.OnItemClickListener {
    @Bind(R.id.list)
    ListView list;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @Bind(R.id.none_layout)
    LinearLayout nonelayout;

    private int page = 1;
    private CommonAdapter<MoviesByCidBO> adapter;
    private List<MoviesByCidBO> data = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movielayout, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.loadCollectMovie(MyApplication.user.getId(),1);
        list.setOnItemClickListener(this);
        setPullRefresher();
        adapter();
    }

    private void adapter() {
        adapter = new CommonAdapter<MoviesByCidBO>(getActivity(),
                R.layout.item_movies_want, data) {

            @Override
            protected void convert(ViewHolder helper, MoviesByCidBO item, int position) {
                helper.getView(R.id.movies_submit).setVisibility(View.GONE);
                helper.setText(R.id.movies_name, item.getMovieName());
                if (!StringUtils.isEmpty(item.getStartPlay())) {
                    helper.setText(R.id.movies_time, item.getStartPlay().split(" ")[0] + " 上映");
                } else {
                    helper.setText(R.id.movies_time, "");
                }
                helper.setText(R.id.movies_type, item.getMovieType());
                helper.setText(R.id.movies_message, item.getSummary());
                helper.getView(R.id.movies_num).setVisibility(View.GONE);
//                if ("0.0".equals(item.getPoint())) {
//                    helper.getView(R.id.movies_num).setVisibility(View.GONE);
//                } else {
//                    helper.setText(R.id.movies_num, item.getPoint() + "分");
//                    helper.getView(R.id.movies_num).setVisibility(View.VISIBLE);
//                }
                if (!StringUtils.isEmpty(item.getPicture())) {
                    helper.setImageUrl(R.id.movie_img, item.getPicture());
                } else {
                    helper.setImageResource(R.id.movie_img, R.color.act_bg01);
                }
            }
        };
    }

    private void setPullRefresher(){
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.loadCollectMovie(MyApplication.user.getId(),1);
                page=1;
                smartRefreshLayout.finishRefresh(1000);
                refreshlayout.finishRefresh(2000);
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshlayout) {
                page++;
                mPresenter.loadCollectMovie(MyApplication.user.getId(),page);
                smartRefreshLayout.finishLoadMore(1000);
                refreshlayout.finishLoadMore(2000);
            }
        });
    }

    @Override
    public void getCollectList(List<MoviesByCidBO> moviesByCidBOs,int page) {
            if(page==1){
                if(moviesByCidBOs.size()==0){
                    smartRefreshLayout.setVisibility(View.GONE);
                    nonelayout.setVisibility(View.VISIBLE);
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("movie", data.get(position ));
        gotoActivity(MoviesMessageActivity.class, bundle, false);
    }

    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}