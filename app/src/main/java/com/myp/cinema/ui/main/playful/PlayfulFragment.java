package com.myp.cinema.ui.main.playful;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.donkingliang.banner.CustomBanner;
import com.myp.cinema.R;
import com.myp.cinema.base.MyApplication;
import com.myp.cinema.config.ConditionEnum;
import com.myp.cinema.entity.CinemaBo;
import com.myp.cinema.entity.LunBoBO;
import com.myp.cinema.entity.ShopBO;
import com.myp.cinema.mvp.MVPBaseFragment;
import com.myp.cinema.ui.WebViewActivity;
import com.myp.cinema.ui.moviesmessage.MoviesMessageActivity;
import com.myp.cinema.ui.playgame.PlayGameActivity;
import com.myp.cinema.ui.userlogin.LoginActivity;
import com.myp.cinema.util.DividerGridItemDecoration;
import com.myp.cinema.util.LogUtils;
import com.myp.cinema.util.StringUtils;
import com.myp.cinema.widget.lgrecycleadapter.LGRecycleViewAdapter;
import com.myp.cinema.widget.lgrecycleadapter.LGViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 好玩的fragment
 */

public class PlayfulFragment extends MVPBaseFragment<PlayfulContract.View, PlayfulPresenter>
        implements PlayfulContract.View, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {


    @Bind(R.id.go_back)
    LinearLayout goBack;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.right_bg_01)
    ImageView rightBg01;
    @Bind(R.id.right_bg)
    ImageView rightBg;
    @Bind(R.id.right_bg_layout)
    LinearLayout rightBgLayout;
    @Bind(R.id.choujiang_layout)
    RelativeLayout choujiangLayout;
    @Bind(R.id.banner)
    CustomBanner banner;
    @Bind(R.id.right_img)
    ImageView rightImg;
    @Bind(R.id.look_at_more)
    TextView lookAtMore;
    @Bind(R.id.duihuan_all)
    RelativeLayout duihuanAll;
    @Bind(R.id.recyle)
    RecyclerView recyle;
    @Bind(R.id.jinbirecy)
    RecyclerView jinbirecy;
    @Bind(R.id.swipe)
    SwipeRefreshLayout mSwipeLayout;
    private boolean isRefresh = false;//是否刷新中
    private List<LunBoBO> lunBoBOs;
    private CinemaBo cinemaBo;
    private List<ShopBO> shopBOs;
    private ArrayList<String> mDatas= new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_playful, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        goBack.setVisibility(View.GONE);
        title.setText("游戏");
        initViews();
    }

    /**
     * 初始化控件属性
     */
    private void initViews() {
        mSwipeLayout.setOnRefreshListener(this);
        invitionSwipeRefresh(mSwipeLayout);
//        mSwipeLayout.setRefreshing(true);
        lookAtMore.setOnClickListener(this);
        mSwipeLayout.measure(0, 0);
        mSwipeLayout.setRefreshing(true);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                mPresenter.lunboList("movie", cinemaBo == null ? null : cinemaBo.getCinemaId());
                mPresenter.loadCreditsShop(MyApplication.cinemaBo.getCinemaId(), false);
                //显示或隐藏刷新进度条
                mSwipeLayout.setRefreshing(false);
                //修改adapter的数据
                isRefresh = false;
            }
        }, 2000);
        LinearLayoutManager ms = new LinearLayoutManager(getActivity());
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
        //     LinearLayoutManager 种 含有3 种布局样式  第一个就是最常用的 1.横向 , 2. 竖向,3.偏移
        recyle.setLayoutManager(ms);

        if (MyApplication.isLogin == ConditionEnum.NOLOGIN) {  //未登录进入登陆
            choujiangLayout.setVisibility(View.GONE);
        } else if (MyApplication.isLogin == ConditionEnum.LOGIN) {   //已登录正式跳转
            choujiangLayout.setVisibility(View.VISIBLE);//获取头数据显示点击跳转webview
        }
        initData();
        GridLayoutManager lay =  new FullyGridLayoutManager(getActivity(),2);
        jinbirecy.setLayoutManager(lay);
        jinbirecy.addItemDecoration(new DividerGridItemDecoration(getActivity()));
        LGRecycleViewAdapter<String> jinbiadapter = new LGRecycleViewAdapter<String>(mDatas) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.jinbiitem;
            }

            @Override
            public void convert(LGViewHolder holder, String s, int position) {
                TextView  idnum = (TextView) holder.getView(R.id.id_num) ;
                idnum.setText(s);
            }
        };
        jinbirecy.setAdapter(jinbiadapter);

    }
    protected void initData()
    {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'K'; i++)
        {
            mDatas.add("" + (char) i);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onRefresh() {
        //检查是否处于刷新状态
        if (!isRefresh) {
            isRefresh = true;
            //模拟加载网络数据，这里设置4秒，正好能看到4色进度条
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    mPresenter.lunboList("movie", cinemaBo == null ? null : cinemaBo.getCinemaId());
                    mPresenter.loadCreditsShop(MyApplication.cinemaBo.getCinemaId(), false);
                    //显示或隐藏刷新进度条
                    mSwipeLayout.setRefreshing(false);
                    //修改adapter的数据
                    isRefresh = false;
                }
            }, 4000);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (MyApplication.isLogin == ConditionEnum.NOLOGIN) {  //未登录进入登陆
            choujiangLayout.setVisibility(View.GONE);
        } else if (MyApplication.isLogin == ConditionEnum.LOGIN) {   //已登录正式跳转
            choujiangLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.look_at_more:
                if (MyApplication.isLogin == ConditionEnum.NOLOGIN) {  //未登录进入登陆
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
//                    getActivity().startActivityForResult(intent, 1);
                    startActivity(intent);
                } else if (MyApplication.isLogin == ConditionEnum.LOGIN) {   //已登录正式跳转
                    gotoActivity(PlayGameActivity.class, false);
                }
                break;
        }
    }

    public void setCinemaBo(CinemaBo cinemaBo) {
        if (cinemaBo == null) {
            return;
        }
        this.cinemaBo = cinemaBo;
        if (mSwipeLayout != null) {
            mSwipeLayout.setRefreshing(true);   //进入页面开始刷新
        }
        mPresenter.lunboList("movie", cinemaBo == null ? null : cinemaBo.getCinemaId());
        mPresenter.loadCreditsShop(MyApplication.cinemaBo.getCinemaId(), false);
    }

    @Override
    public void getLunBo(List<LunBoBO> lunBoBOs) {
        this.lunBoBOs = lunBoBOs;
        setBannerAdapter();
    }

    @Override
    public void getShopList(List<ShopBO> shops) {
        this.shopBOs = shops;
        setAdapter();
    }

    /**
     * 设置电影列表数据
     */
    private void setAdapter() {
        LGRecycleViewAdapter<ShopBO> adapter = new LGRecycleViewAdapter<ShopBO>(shopBOs) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.playfulfragment_item;
            }

            @Override
            public void convert(LGViewHolder holder, ShopBO shopBO, int position) {
                TextView shopname = (TextView) holder.getView(R.id.shop_name);
                TextView shopcredits = (TextView) holder.getView(R.id.shop_credits);
                ImageView shopimg = (ImageView) holder.getView(R.id.shop_img);
                shopname.setText(shopBO.getGoodName());
                shopcredits.setText(shopBO.getPrice() + "德信币");
                Glide.with(getActivity())
                        .load(shopBO.getImageUrl())
                        .into(shopimg);
            }
        };
        recyle.setAdapter(adapter);
        adapter.setOnItemClickListener(R.id.shop_img, new LGRecycleViewAdapter.ItemClickListener() {
            @Override
            public void onItemClicked(View view, int position) {
                if (MyApplication.isLogin == ConditionEnum.NOLOGIN) {  //未登录进入登陆
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
//                    getActivity().startActivityForResult(intent, 1);
                    startActivity(intent);
                } else if (MyApplication.isLogin == ConditionEnum.LOGIN) {   //已登录正式跳转
                    gotoActivity(PlayGameActivity.class, false);
                }
            }
        });
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
                        .error(R.drawable.zhanwei2)
                        .into((ImageView) view);
            }
        }, lunBoBOs);
        if (lunBoBOs.size() == 1) {   //只有一张轮播图   不滚动
            banner.stopTurning();
        } else {
            banner.startTurning(4000);
            //设置轮播图的滚动速度
            banner.setScrollDuration(300);
        }
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
    public void onRequestError(String msg) {
        LogUtils.showToast(msg);
        mSwipeLayout.setRefreshing(false);
    }

    @Override
    public void onRequestEnd() {
        mSwipeLayout.setRefreshing(false);
    }
}