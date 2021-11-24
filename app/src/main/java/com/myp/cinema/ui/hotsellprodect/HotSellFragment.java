package com.myp.cinema.ui.hotsellprodect;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.appbar.AppBarLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.donkingliang.banner.CustomBanner;
import com.google.gson.Gson;
import com.myp.cinema.R;
import com.myp.cinema.base.MyApplication;
import com.myp.cinema.entity.HotSellBO;
import com.myp.cinema.entity.HotSellBannerBO;
import com.myp.cinema.entity.MerchandBO;
import com.myp.cinema.entity.ProdectBO;
import com.myp.cinema.jpush.MessageEvent;
import com.myp.cinema.mvp.MVPBaseFragment;
import com.myp.cinema.ui.prodectorder.ProdectOrderActivity;
import com.myp.cinema.util.SPUtils;
import com.myp.cinema.util.ScreenUtils;
import com.myp.cinema.util.ToastUtils;
import com.myp.cinema.widget.VpSwipeRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by witness on 2018/11/14.
 *  热卖小食
 */

public class HotSellFragment extends MVPBaseFragment<HotSellContract.View,HotSellPresenter>
                            implements HotSellContract.View,CheckListener,View.OnClickListener{

    @Bind(R.id.refresh_root)
    VpSwipeRefreshLayout refresh_root;
    @Bind(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @Bind(R.id.banner)
    CustomBanner banner;
    @Bind(R.id.hotLocationCinema)
    TextView hotLocationCinema;//banner上面的影院名称
    @Bind(R.id.rlLocateDismiss)
    RelativeLayout rlLocateDismiss;//置顶显示的影院名称布局，banner显示的时候Gone，banner消失的时候Visible
    @Bind(R.id.txtLocationCinemaBelow)
    TextView txtLocationCinemaBelow;//置顶显示的影院名称
    @Bind(R.id.txtDes)
    TextView txtDes;
    @Bind(R.id.txtDesNum)
    TextView txtDesNum;
    @Bind(R.id.llProdect)
    LinearLayout llProdect;//商品布局
    @Bind(R.id.rvLeft)
    RecyclerView rvLeft;//左侧布局
    @Bind(R.id.flRight)
    FrameLayout flRight;//右侧布局
    @Bind(R.id.tvShopCarNum)
    TextView tvShopCarNum;//购物车数量
    @Bind(R.id.tvPrice)
    TextView tvPrice;//商品总价
    @Bind(R.id.llSure)
    LinearLayout llSure;//选好了
    @Bind(R.id.rlDecreaseActivity)
    RelativeLayout rlDecreaseActivity;
    @Bind(R.id.txtDecreaseInstruction)
    TextView txtDecreaseInstruction;
    @Bind(R.id.go_back)
    LinearLayout go_back;
    @Bind(R.id.title)
    TextView title;

    private SortAdapter mSortAdapter;
    private SortDetailFragment mSortDetailFragment;
    private LinearLayoutManager mLinearLayoutManager;
    private int targetPosition;//点击左边某一个具体的item的位置
    private boolean isMoved;
    private SortBean mSortBean;
    private List<HotSellBO> hotSellBO = new ArrayList<>();
    private List<HotSellBannerBO> bannerBOList = new ArrayList<>();
    private SPUtils spUtils;
    private ArrayList<String> goodsId = new ArrayList<>();//选中的食品id
    private String merchandiseInfo = "";//视频id和number 的json
    private ProdectBO prodectBO;
    private boolean isVisible = false;//当前页面是否可见
    private boolean isFirstopen = true;//第一次打开app

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hot_sell_layout, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    private void initViews(){

        spUtils = new SPUtils("ProdectItemNum");
        go_back.setVisibility(View.GONE);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        rvLeft.setLayoutManager(mLinearLayoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        rvLeft.addItemDecoration(decoration);
        invitionSwipeRefresh(refresh_root);
        setListener();
        EventBus.getDefault().register(this);

        ViewGroup.LayoutParams params = banner.getLayoutParams();
        params.height = (int)(ScreenUtils.getScreenWidth() * 0.41);
        banner.setLayoutParams(params);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        spUtils.clear();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstopen) {
            if (mSortDetailFragment != null) {
                mSortDetailFragment.clearData();
            }
            merchandiseInfo = "";
            goodsId.clear();
            tvShopCarNum.setVisibility(View.GONE);
            tvPrice.setText(String.format("￥ %s", 0));
            if (MyApplication.cinemaBo != null) {
                String name = MyApplication.cinemaBo.getCinemaName();
                txtLocationCinemaBelow.setText(name);
                hotLocationCinema.setText(name);
                title.setText(name);
            }else {
                title.setText("卖品");
            }
            initData();
            isFirstopen = false;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            if (mSortDetailFragment != null) {
                mSortDetailFragment.clearData();
            }
            merchandiseInfo = "";
            goodsId.clear();
            tvShopCarNum.setVisibility(View.GONE);
            tvPrice.setText(String.format("￥ %s", 0));
            isVisible = true;
            if (MyApplication.cinemaBo != null) {
                String name = MyApplication.cinemaBo.getCinemaName();
                txtLocationCinemaBelow.setText(name);
                hotLocationCinema.setText(name);
                title.setText(name);
            }else {
                title.setText("卖品");
            }
            initData();
        }else {
            isVisible = false;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            if (mSortDetailFragment != null) {
                mSortDetailFragment.clearData();
                tvShopCarNum.setVisibility(View.GONE);
                tvPrice.setText(String.format("￥ %s", 0));
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.llSure:
                if (MyApplication.isSuccess) {//点击选择卖品的接口走完之后才可以点
                    MyApplication.selectedId = -1;//清空之前选择的优惠券
                    if (!merchandiseInfo.equals("")) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("ProdectBO", prodectBO);
                        bundle.putStringArrayList("goodsId", goodsId);
                        gotoActivity(ProdectOrderActivity.class, bundle, false);
                    } else {
                        ToastUtils.showShortToast("你还没选择好吃的呢");
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void getHotSellData(List<HotSellBO> hotSellBO) {
        refresh_root.setRefreshing(false);
        if (hotSellBO.size() != 0) {
            rvLeft.setVisibility(View.VISIBLE);
            flRight.setVisibility(View.VISIBLE);
            llSure.setEnabled(true);
            this.hotSellBO = hotSellBO;
            MyApplication.hotSellBO = hotSellBO;
            List<HotSellBO> mHotSellBO = hotSellBO;
            for (int i=0;i<hotSellBO.size();i++){
                if (hotSellBO.get(i).getMerchandiseList() == null
                        || hotSellBO.get(i).getMerchandiseList().size() == 0){
                    mHotSellBO.remove(i);
                }
            }
            mSortAdapter = new SortAdapter(getActivity(), mHotSellBO, new RvListener() {
                @Override
                public void onItemClick(int id, int position) {
                    if (mSortDetailFragment != null) {
                        isMoved = true;
                        targetPosition = position;
                        setChecked(position, true);
                    }
                }
            });
            rvLeft.setAdapter(mSortAdapter);
            createFragment();
        }else {
            rvLeft.setVisibility(View.GONE);
            flRight.setVisibility(View.GONE);
            llSure.setEnabled(false);
            if (isVisible) {
                ToastUtils.showShortToast("该影城暂无商品");
            }
            mSortAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getBanners(List<HotSellBannerBO> hotSellBO) {
        refresh_root.setRefreshing(false);
        if (hotSellBO.size() != 0) {
            this.bannerBOList = hotSellBO;
            setBannerAdapter();
        }
    }

    @Override
    public void getOrder(ProdectBO prodectBO) {
        stopProgress();
        this.prodectBO = prodectBO;
        MyApplication.isSuccess = true;
        if (prodectBO != null) {
            if (prodectBO.getDiscountDetail() != null && !prodectBO.getDiscountDetail().equals("")){
                rlDecreaseActivity.setVisibility(View.VISIBLE);
                txtDecreaseInstruction.setText(prodectBO.getDiscountDetail());
            }else {
                rlDecreaseActivity.setVisibility(View.GONE);
            }
            if (prodectBO.getTotalNumber() > 0) {
                tvShopCarNum.setVisibility(View.VISIBLE);
                tvShopCarNum.setText(String.valueOf(prodectBO.getTotalNumber()));
                tvPrice.setText(String.format("￥ %s", prodectBO.getDisPrice()));
            } else {
                tvShopCarNum.setVisibility(View.GONE);
                tvPrice.setText(String.format("￥ %s", 0));
            }
        }
    }

    @Override
    public void onRequestError(String msg) {
        refresh_root.setRefreshing(false);
        MyApplication.isSuccess = true;
        stopProgress();
    }

    @Override
    public void onRequestEnd() {
        stopProgress();
        MyApplication.isSuccess = true;
        refresh_root.setRefreshing(false);
    }

    private void initData(){
        if (MyApplication.cinemaBo != null){
            showProgress("加载中...");
            mPresenter.loadHotSellData(MyApplication.cinemaBo.getCinemaId());
            mPresenter.loadBanners(MyApplication.cinemaBo.getCinemaId(), "merchandise");
        }
    }

    public void createFragment() {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        mSortDetailFragment = new SortDetailFragment();
        mSortDetailFragment.setListener(this);
        fragmentTransaction.add(R.id.flRight, mSortDetailFragment);
        fragmentTransaction.commit();
    }

    private void setChecked(int position, boolean isLeft) {
        Log.d("p-------->", String.valueOf(position));
        if (isLeft) {
            mSortAdapter.setCheckedPosition(position);
            //此处的位置需要根据每个分类的集合来进行计算
            int count = 0;
            for (int i = 0; i < position; i++) {
                count += hotSellBO.get(i).getMerchandiseList().size();
            }
            count += position;
            mSortDetailFragment.setData(count);
            ItemHeaderDecoration.setCurrentTag(String.valueOf(targetPosition));//凡是点击左边，将左边点击的位置作为当前的tag
        } else {
            if (isMoved) {
                isMoved = false;
            } else
                mSortAdapter.setCheckedPosition(position);
            ItemHeaderDecoration.setCurrentTag(String.valueOf(position));//如果是滑动右边联动左边，则按照右边传过来的位置作为tag

        }
        moveToCenter(position);

    }

    //将当前选中的item居中
    private void moveToCenter(int position) {
        //将点击的position转换为当前屏幕上可见的item的位置以便于计算距离顶部的高度，从而进行移动居中
        View childAt = rvLeft.getChildAt(position - mLinearLayoutManager.findFirstVisibleItemPosition());
        if (childAt != null) {
            int y = (childAt.getTop() - rvLeft.getHeight() / 2);
            rvLeft.smoothScrollBy(0, y);
        }

    }

    /**
     * 初始化下拉刷新控件
     */
    protected void invitionSwipeRefresh(SwipeRefreshLayout mSwipeLayout) {
        // 设置下拉圆圈上的颜色，蓝色、绿色、橙色、红色
        mSwipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mSwipeLayout.setDistanceToTriggerSync(400);// 设置手指在屏幕下拉多少距离会触发下拉刷新
        mSwipeLayout.setProgressBackgroundColor(R.color.white); // 设定下拉圆圈的背景
        mSwipeLayout.setSize(SwipeRefreshLayout.DEFAULT); // 设置圆圈的大小
    }

    private void setListener(){
        llSure.setOnClickListener(this);
        refresh_root.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(verticalOffset >= 0){
                    refresh_root.setEnabled(true);
                }else{
                    refresh_root.setEnabled(false);
                }
                if (verticalOffset < - 380){
                    rlLocateDismiss.setVisibility(View.VISIBLE);
                }else {
                    rlLocateDismiss.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void check(int position, boolean isScroll) {
        setChecked(position, isScroll);
    }

    /**
     * 设置轮播图视图
     */
    private void setBannerAdapter() {
        banner.setPages(new CustomBanner.ViewCreator<HotSellBannerBO>() {
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
            public void updateUI(Context context, View view, int i, HotSellBannerBO lunBoBO) {
                Glide.with(context).load(lunBoBO.getImageUrl())
                        .diskCacheStrategy(DiskCacheStrategy.RESULT)
                        .error(R.drawable.zhanwei2)
                        .into((ImageView) view);
            }
        }, bannerBOList);
        if (bannerBOList.size() == 1) {   //只有一张轮播图   不滚动
            banner.stopTurning();
        } else {
            banner.startTurning(4000);
            //设置轮播图的滚动速度
            banner.setScrollDuration(300);
        }
        //设置轮播图的点击事件
        banner.setOnPageClickListener(new CustomBanner.OnPageClickListener<HotSellBannerBO>() {
            @Override
            public void onPageClick(int position, HotSellBannerBO str) {

            }
        });
    }

    /**
     * 显示购买数量,接收来自ClassifyDetailAdapter的消息
     * @param messageEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent messageEvent){
        if (messageEvent.getMessageType().equals("goodsNum")){
            String id = String.valueOf(messageEvent.getPassValue());
            if (!goodsId.contains(id)) {
                goodsId.add(id);
            }
            modifyPrice();
        }
    }

    private void modifyPrice(){
        Gson gson=new Gson();
        List<MerchandBO> list = new ArrayList<>();
        for (int i=0;i<goodsId.size();i++) {
            if (spUtils.getInt(String.valueOf(goodsId.get(i))) != 0) {
                MerchandBO merchandBO = new MerchandBO(Long.parseLong(goodsId.get(i)),Long.parseLong(String.valueOf(spUtils.getInt(String.valueOf(goodsId.get(i))))));
                list.add(merchandBO);
            }
        }
        if (list.size()>0) {
            merchandiseInfo = gson.toJson(list);
            mPresenter.getOrderPrice(MyApplication.cinemaBo.getCinemaId(),merchandiseInfo,"");
        }else {
            stopProgress();
            tvPrice.setText(String.format("￥ %s", 0));
            tvShopCarNum.setVisibility(View.GONE);
            merchandiseInfo = "";
            MyApplication.isSuccess = true;
        }
    }
}
