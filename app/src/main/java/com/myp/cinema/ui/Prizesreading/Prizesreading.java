package com.myp.cinema.ui.Prizesreading;

import com.myp.cinema.R;
import com.myp.cinema.mvp.MVPBaseActivity;
import com.myp.cinema.ui.WebViewActivity;
import com.myp.cinema.util.LogUtils;
import com.myp.cinema.util.StringUtils;
import com.myp.cinema.widget.superadapter.CommonAdapter;
import com.myp.cinema.widget.superadapter.ViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2018/1/24.game_list
 */

public class Prizesreading extends MVPBaseActivity<PrizesreadingContract.View,
        PrizesreadingPresenter> implements PrizesreadingContract.View, AdapterView.OnItemClickListener {
    @Bind(R.id.game_list)
    ListView list;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    private ArrayList<HomeTopBean.DataBo> data = new ArrayList<>();
    private CommonAdapter<HomeTopBean.DataBo> adapter;
    private int page = 1;

    @Override
    protected int getLayout() {
        return R.layout.act_prizes_read;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("有奖阅读");
        list.setOnItemClickListener(this);
        mPresenter.loadTaskList(1);
        setPullRefresher();
        adapter();
    }

    private void adapter() {
        adapter = new CommonAdapter<HomeTopBean.DataBo>(this, R.layout.item_prizes_read, data) {
            @Override
            protected void convert(ViewHolder viewHolder, HomeTopBean.DataBo item, int position) {
                if (StringUtils.isEmpty(item.getPic())) {
                    viewHolder.setImageResource(R.id.prizes_image, R.drawable.zhanwei2);
                } else {
                    viewHolder.setImageUrl(R.id.prizes_image, item.getPic());
                }
                viewHolder.setText(R.id.prizes_text, item.getTitle());
                viewHolder.setText(R.id.read_text, item.getDescription());
            }
        };
    }

    private void setPullRefresher() {
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.loadTaskList(1);
                page = 1;
                smartRefreshLayout.finishRefresh(1000);
                refreshlayout.finishRefresh(2000);
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                mPresenter.loadTaskList(page);
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
    public void getTaskList(List<HomeTopBean.DataBo> gameBOs, int pageNos) {
        if (pageNos == 1) {
            data.clear();
            data.addAll(gameBOs);
            list.setAdapter(adapter);
        } else {
            data.addAll(gameBOs);
            adapter.setmDatas(data);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle = new Bundle();
        bundle.putString("title", data.get(position).getTitle());
        bundle.putString("title", data.get(position).getTitle());
        bundle.putString("pic", data.get(position).getPic());
        bundle.putString("description", data.get(position).getDescription());
        bundle.putString("url", data.get(position).getArticleUrl() + "&flag=1");
        String yes = "yes";
        bundle.putString("fenxiang", yes);
        bundle.putString("back", "yes");
        gotoActivity(WebViewActivity.class, bundle, false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}
