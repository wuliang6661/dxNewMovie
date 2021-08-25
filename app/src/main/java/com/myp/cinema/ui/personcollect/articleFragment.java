package com.myp.cinema.ui.personcollect;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.myp.cinema.R;
import com.myp.cinema.base.MyApplication;
import com.myp.cinema.entity.HotWireBO;
import com.myp.cinema.mvp.MVPBaseFragment;
import com.myp.cinema.ui.WebViewActivity;
import com.myp.cinema.util.LogUtils;
import com.myp.cinema.util.ScreenUtils;
import com.myp.cinema.util.SizeUtils;
import com.myp.cinema.util.StringUtils;
import com.myp.cinema.util.TimeUtils;
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
public class articleFragment extends MVPBaseFragment<articleContract.View, articlePresenter>
        implements articleContract.View, AdapterView.OnItemClickListener {

    @Bind(R.id.list)
    ListView list;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @Bind(R.id.none_layout)
    LinearLayout nonelayout;
    private CommonAdapter<HotWireBO> adapter;
    private  int page = 1;
    private List<HotWireBO> data= new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.articlelayout, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (MyApplication.cinemaBo != null) {
            mPresenter.loadCollection( 1);
        } else {
            mPresenter.loadCollection( 1);
        }
        setPullRefresher();
        adapter();
        list.setOnItemClickListener(this);

    }
    private void setPullRefresher(){
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                if (MyApplication.cinemaBo != null) {
                    mPresenter.loadCollection( 1);
                } else {
                    mPresenter.loadCollection( 1);
                }
                page=1;
                smartRefreshLayout.finishRefresh(1000);
                refreshlayout.finishRefresh(2000);
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshlayout) {
                page++;
                if (MyApplication.cinemaBo != null) {
                    mPresenter.loadCollection( page);
                } else {
                    mPresenter.loadCollection( page);
                }
                smartRefreshLayout.finishLoadMore(1000);
                refreshlayout.finishLoadMore(2000);
            }
        });
    }
    private void adapter() {
        adapter = new CommonAdapter<HotWireBO>(getActivity(),
                R.layout.item_hotwrie, data) {
            @Override
            protected void convert(ViewHolder helper, HotWireBO item, int position) {
                LinearLayout itemLayout = helper.getView(R.id.item_layout);
                ViewGroup.LayoutParams params = itemLayout.getLayoutParams();
                int widthDb = ScreenUtils.getScreenWidth() - (SizeUtils.dp2px(11) * 2);
                params.width = widthDb;
                params.height = (int) (widthDb / 2 / 1.37f);
                itemLayout.setLayoutParams(params);
                helper.setText(R.id.hot_message, item.getTitle());
                helper.setText(R.id.hot_msg, item.getDescription());
                helper.setText(R.id.hot_time, TimeUtils.string2Pander(item.getPublishTime(), "yyyy-MM-dd"));
                if (StringUtils.isEmpty(item.getPic())) {
                    helper.setImageResource(R.id.hot_img, R.drawable.zhanwei2);
                } else {
                    helper.setImageUrl(R.id.hot_img, item.getPic());
                }
            }
        };
    }
    @Override
    public void getCollection(List<HotWireBO> hotWireBOs,int page) {
        if(page==1){
            if(hotWireBOs.size()==0){
                smartRefreshLayout.setVisibility(View.GONE);
                nonelayout.setVisibility(View.VISIBLE);
            }else {
                data.clear();
                data.addAll(hotWireBOs);
                list.setAdapter(adapter);
            }
        }else {
            data.addAll(hotWireBOs);
            adapter.setmDatas(data);
        }

    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle = new Bundle();
        bundle.putString("title", data.get(position).getTitle());
        bundle.putString("pic", data.get(position).getPic());
        bundle.putString("description", data.get(position).getDescription());
        bundle.putString("url", data.get(position).getArticleUrl()+"&flag=1");
        String yes = "yes";
        bundle.putString("back", "yes");
        bundle.putString("fenxiang", yes);
        gotoActivity(WebViewActivity.class, bundle, false);
    }

    @Override
    public void onRequestError(String msg) {
        LogUtils.showToast(msg);
    }

    @Override
    public void onRequestEnd() {
        }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
