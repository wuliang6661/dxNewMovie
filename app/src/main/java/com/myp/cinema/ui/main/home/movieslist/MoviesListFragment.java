package com.myp.cinema.ui.main.home.movieslist;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.donkingliang.banner.CustomBanner;
import com.myp.cinema.R;
import com.myp.cinema.base.MyApplication;
import com.myp.cinema.entity.ActivityBO;
import com.myp.cinema.entity.CinemaBo;
import com.myp.cinema.entity.LunBoAndBO;
import com.myp.cinema.entity.MoviesByCidBO;
import com.myp.cinema.mvp.MVPBaseFragment;
import com.myp.cinema.ui.WebViewActivity;
import com.myp.cinema.ui.membercard.BindCard;
import com.myp.cinema.ui.moviesmessage.MoviesMessageActivity;
import com.myp.cinema.ui.moviespresell.PresellMoviesActivity;
import com.myp.cinema.ui.moviessession.SessionActivity;
import com.myp.cinema.util.LogUtils;
import com.myp.cinema.util.MyListView;
import com.myp.cinema.util.SPUtils;
import com.myp.cinema.util.ScreenUtils;
import com.myp.cinema.util.ShareUtils;
import com.myp.cinema.util.StringUtils;
import com.myp.cinema.widget.MyscrollView;
import com.myp.cinema.widget.superadapter.CommonAdapter;
import com.myp.cinema.widget.superadapter.ViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * MVPPlugin
 * 正在热映页面
 */

public class MoviesListFragment extends MVPBaseFragment<MoviesListContract.View,
        MoviesListPresenter> implements MoviesListContract.View,
        AdapterView.OnItemClickListener {

    @Bind(R.id.list)
    ListView list;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout smartRefreshLayout;

    CustomBanner banner;
    CinemaBo cinemaBo;

    List<MoviesByCidBO> moviesList;

    CommonAdapter<MoviesByCidBO> adapter;
    private List<LunBoAndBO.BannersBo> lunBoBOs;
    private MyListView adslist;
    private List<LunBoAndBO.AdsBo> adsBOs;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_swift_layout, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (MyApplication.user != null) {//用户首次注册送礼的图片
            if (!MyApplication.alertPhoto .equals("")) {//登录送积分或红包？
                showPictureDialog(MyApplication.alertPhoto);
                MyApplication.alertPhoto = "";
            }
        }
    }

    /**
     * 初始化视图
     */
    private void initViews() {
        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.lunbo_layout, null);
        banner = headView.findViewById(R.id.banner);
        ViewGroup.LayoutParams params = banner.getLayoutParams();
        params.height = (int)(ScreenUtils.getScreenWidth() * 0.41);
        banner.setLayoutParams(params);
        adslist = headView.findViewById(R.id.ads_list);
        adslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!StringUtils.isEmpty(adsBOs.get(position).getArticleUrl())) {
                    Bundle bundle = new Bundle();
                    bundle.putString("url", adsBOs.get(position).getArticleUrl()+"&flag=1");
                    gotoActivity(WebViewActivity.class, bundle, false);
                } else {
                }
            }
        });
        list.addHeaderView(headView);
         list.setOnItemClickListener(this);
        setPullRefresher();
    }
    private void setPullRefresher(){
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.lunboList("movie", cinemaBo == null ? null : cinemaBo.getCinemaId());
                if (cinemaBo == null) {
                    LogUtils.showToast("当前城市无影院信息！");
                } else {
                    mPresenter.moviesHot(cinemaBo.getCinemaId());
                }
                smartRefreshLayout.finishRefresh(1000);
                refreshlayout.finishRefresh(2000);
            }
        });

    }



    /**
     * 设置电影列表数据
     */
    private void setAdapter() {
        adapter = new CommonAdapter<MoviesByCidBO>(getActivity(),
                R.layout.item_movie_list_layout, moviesList) {

            @Override
            protected void convert(ViewHolder helper, final MoviesByCidBO item, int position) {
                if (StringUtils.isEmpty(item.getUniqueName())) {
                    helper.setText(R.id.movies_name, item.getMovieName());
                } else {
                    helper.setText(R.id.movies_name, item.getUniqueName());
                }
                if (item.getDxDirectors() != null && item.getDxDirectors().size()>0){
                    StringBuilder stringBuffer = new StringBuilder();
                    for (int i = 0; i < item.getDxDirectors().size(); i++) {
                        stringBuffer.append(item.getDxDirectors().get(i).getName().trim()).append(",");
                    }
                    String directors = stringBuffer.substring(0, stringBuffer.length() - 1);
                    helper.setText(R.id.moviesDirector,"导演："+directors);
                }else {
                    helper.setText(R.id.moviesDirector,"");
                }
                if (item.getDxActors() != null && item.getDxActors().size()>0){
                    StringBuilder stringBuffer = new StringBuilder();
                    for (int i = 0; i < item.getDxActors().size(); i++) {
                        stringBuffer.append(item.getDxActors().get(i).getName().trim()).append(",");
                    }
                    String actors = stringBuffer.substring(0, stringBuffer.length() - 1);
                    helper.setText(R.id.moviesActors,"演员："+actors);
                }else {
                    helper.setText(R.id.moviesActors,"");
                }
//                if (!StringUtils.isEmpty(item.getStartPlay())) {
//                    helper.setText(R.id.moives_time, item.getStartPlay().split(" ")[0] + " 上映");
//                } else {
//                    helper.setText(R.id.moives_time, "");
//                }
//                helper.setText(R.id.movies_message, item.getSummary());
                if ("0.0".equals(item.getPoint())) {
                    helper.getView(R.id.movies_point).setVisibility(View.GONE);
                } else {
                    helper.setText(R.id.movies_point, item.getPoint() + "分");
                    helper.getView(R.id.movies_point).setVisibility(View.VISIBLE);
                }
                if (!StringUtils.isEmpty(item.getPicture())) {
                    helper.setImageUrl(R.id.movies_img, item.getPicture());
                } else {
                    helper.setImageResource(R.id.movies_img, R.color.act_bg01);
                }
                switch (item.getMovieDimensional()) {
                    case "2D":
                        helper.setText(R.id.moviesTypeOne,"2D");
                        break;
                    case "3D":
                        helper.setText(R.id.moviesTypeOne,"3D");
                        break;
                    default:
                        helper.setText(R.id.moviesTypeOne,"3D IMAX");
                        break;
                }
                ImageButton payButton = helper.getView(R.id.pay_button);
                if ("1".equals(item.getSell())) {
                    payButton.setBackgroundResource(R.mipmap.presell_btn);
                } else if ("2".equals(item.getSell())) {
                    payButton.setBackgroundResource(R.mipmap.sell_btn);
                } else {
                    payButton.setBackgroundResource(R.mipmap.sell_btn);
                }
                helper.getView(R.id.pay_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("movies", item);
                        gotoActivity(SessionActivity.class, bundle, false);
                    }
                });
            }
        };
        list.setAdapter(adapter);
    }


    /**
     * 设置轮播图视图
     */
    private void setBannerAdapter() {
        banner.setPages(new CustomBanner.ViewCreator<LunBoAndBO.BannersBo>() {
            @Override
            public View createView(Context context, int i) {
                ImageView imageView = new ImageView(context);
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                        , ViewGroup.LayoutParams.MATCH_PARENT);
                imageView.setLayoutParams(params);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                return imageView;
            }

            @Override
            public void updateUI(Context context, View view, int i, LunBoAndBO.BannersBo lunBoBO) {
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
        banner.setOnPageClickListener(new CustomBanner.OnPageClickListener<LunBoAndBO.BannersBo>() {
            @Override
            public void onPageClick(int position, LunBoAndBO.BannersBo str) {
                if ("1".equals(str.getPlayType())) {   //页面跳转
                    if (!StringUtils.isEmpty(str.getRedirectUrl())) {
                        if (str.getRedirectUrl().contains("https://dxm.happydoit.com/wx/user/openCard")){//跳到开卡界面
                            gotoActivity(BindCard.class, false);
                        }else {
                            Bundle bundle = new Bundle();
                            bundle.putString("url", str.getRedirectUrl());
                            gotoActivity(WebViewActivity.class, bundle, false);
                        }
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
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }



    public void setCinemaBo(CinemaBo cinemaBo) {
        if (cinemaBo == null) {
            return;
        }
        this.cinemaBo = cinemaBo;
        if (mPresenter != null) {
            mPresenter.lunboList("movie", cinemaBo == null ? null : cinemaBo.getCinemaId());
            mPresenter.moviesHot(cinemaBo == null ? null : cinemaBo.getCinemaId());
        }

        if (MyApplication.cinemaBo != null) {
            if (MyApplication.isActivityPicShow) {
                if (mPresenter != null) {
                    mPresenter.loadActivity(MyApplication.cinemaBo.getCinemaId());
                    MyApplication.isActivityPicShow = false;
                }
            }
        }
    }


    @Override
    public void onRequestError(String msg) {
//        LogUtils.showToast(msg);
        list.setVisibility(View.GONE);
    }

    @Override
    public void onRequestEnd() {
    }

    @Override
    public void getMoviesHot(List<MoviesByCidBO> moviesHotBO) {
        list.setVisibility(View.VISIBLE);
        this.moviesList = moviesHotBO;
        MyApplication.movies = moviesHotBO;
        setAdapter();
    }

    @Override
    public void getLunBo(LunBoAndBO lunBoBOs) {
        this.lunBoBOs = lunBoBOs.getBanners();
        setBannerAdapter();
        this.adsBOs = lunBoBOs.getAds();
        setAdsAdapter();

    }

    @Override
    public void getActivity(ActivityBO activityBO) {
        if (activityBO.getImageUrl() != null && !activityBO.getImageUrl().equals("")){
            showActivityDialog(activityBO.getImageUrl(),activityBO.getJumpAddress());
        }
    }

    private void setAdsAdapter() {
        CommonAdapter  adapter1 = new CommonAdapter<LunBoAndBO.AdsBo>(getActivity(),
                R.layout.item_ads_list_layout, adsBOs) {

            @Override
            protected void convert(ViewHolder helper, final LunBoAndBO.AdsBo item, int position) {
                if (!StringUtils.isEmpty(item.getStarBanner())) {
                    ImageView  view = helper.getView(R.id.ads_item);
                    Glide.with(getActivity()).load(item.getStarBanner())
                            .diskCacheStrategy(DiskCacheStrategy.RESULT)
                            .error(R.drawable.zhanwei2)
                            .into(view);
                } else {
                    helper.setImageResource(R.id.ads_item, R.color.act_bg01);
                }
            }
        };
        adslist.setAdapter(adapter1);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {   //轮播图
            return;
        }
        if ("1".equals(moviesList.get(position - 1).getSell())) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("movie", moviesList.get(position - 1));
            gotoActivity(PresellMoviesActivity.class, bundle, false);
        } else {
            Bundle bundle = new Bundle();
            bundle.putSerializable("movie", moviesList.get(position - 1));
            gotoActivity(MoviesMessageActivity.class, bundle, false);
        }
    }

    /**
     * 显示活动图片
     */
    private void showPictureDialog(String image) {
        // 构造对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.AlertDialog);
        final LayoutInflater inflater = LayoutInflater.from(getActivity());
        View v = inflater.inflate(R.layout.login_prize_dialog, null);
        final ImageView ivPrize = v.findViewById(R.id.ivPrize);
        final ImageView ivClose = v.findViewById(R.id.ivClose);
        Glide.with(getActivity())
                .load(image)
                .into(ivPrize);
        ivClose.setVisibility(View.GONE);
        builder.setView(v);
        builder.setCancelable(true);
        final Dialog noticeDialog = builder.create();
        noticeDialog.getWindow().setGravity(Gravity.CENTER);
        noticeDialog.show();
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noticeDialog.dismiss();
            }
        });
        ivPrize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noticeDialog.dismiss();
            }
        });
        WindowManager.LayoutParams layoutParams = noticeDialog.getWindow().getAttributes();
        layoutParams.width = (int)((ScreenUtils.getScreenWidth() * 0.8));
        layoutParams.height = (int)((ScreenUtils.getScreenHeight() * 0.7));
        noticeDialog.getWindow().setAttributes(layoutParams);
    }


    /**
     * 显示活动图片
     */
    Dialog noticeDialog;
    private void showActivityDialog(String image, final String jumpAddress) {
        MyApplication.isActivityPicShow = false;
        // 构造对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.AlertDialog);
        final LayoutInflater inflater = LayoutInflater.from(getActivity());
        View v = inflater.inflate(R.layout.activity_dialog_layout, null);
        final ImageView ivPrize = v.findViewById(R.id.ivPrize);
        final ImageView ivClose = v.findViewById(R.id.ivClose);
        Glide.with(getActivity())
                .load(image)
                .into(ivPrize);
        ViewGroup.LayoutParams params = ivPrize.getLayoutParams();
        params.width = (int) (ScreenUtils.getScreenWidth() * 0.66);
        params.height = (int) (ScreenUtils.getScreenWidth() * 0.99);
        ivPrize.setLayoutParams(params);

        builder.setView(v);
        builder.setCancelable(true);
        noticeDialog = builder.create();
        noticeDialog.getWindow().setGravity(Gravity.CENTER);
        noticeDialog.show();
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noticeDialog.dismiss();
            }
        });
        ivPrize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jumpAddress == null || jumpAddress.equals("")) return;
                if (goLogin()) {
                    if (jumpAddress.contains("https://dxm.happydoit.com/wx/user/openCard")){//跳到开卡界面
                        gotoActivity(BindCard.class, false);
                    }else {
                        Intent intent = new Intent(getActivity(), WebViewActivity.class);
                        intent.putExtra("url", jumpAddress);
                        startActivity(intent);
                        noticeDialog.dismiss();
                    }
                }
            }
        });
        WindowManager.LayoutParams layoutParams = noticeDialog.getWindow().getAttributes();
        layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        noticeDialog.getWindow().setAttributes(layoutParams);
    }


}