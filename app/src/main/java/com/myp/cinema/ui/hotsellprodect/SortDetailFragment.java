package com.myp.cinema.ui.hotsellprodect;


import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.myp.cinema.R;
import com.myp.cinema.base.MyApplication;
import com.myp.cinema.entity.HotSellBO;
import com.myp.cinema.jpush.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class SortDetailFragment extends BaseFragment<SortDetailPresenter, String> implements CheckListener{
    private RecyclerView mRv;
    private ClassifyDetailAdapter mAdapter;
    private GridLayoutManager mManager;
    private List<RightBean> mDatas = new ArrayList<>();
    private ItemHeaderDecoration mDecoration;
    private boolean move = false;
    private int mIndex = 0;
    private CheckListener checkListener;
    private View view;
    private List<HotSellBO> hotSellBOList;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sort_detail;
    }

    @Override
    protected void initCustomView(View view) {
        mRv = (RecyclerView) view.findViewById(R.id.rv);
    }

    @Override
    protected void initListener() {
        EventBus.getDefault().register(this);
        mRv.addOnScrollListener(new RecyclerViewListener());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void clearData(){
        mAdapter.clearSp();
        mAdapter.notifyDataSetChanged();
    }
    @Override
    protected SortDetailPresenter initPresenter() {
        showRightPage(1);
        mManager = new GridLayoutManager(mContext, 1);
        //通过isTitle的标志来判断是否是title
        mManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return mDatas.get(position).isTitle() ? 1 : 1;
            }
        });
        mRv.setLayoutManager(mManager);
        mAdapter = new ClassifyDetailAdapter(mContext, mDatas, new RvListener() {
            @Override
            public void onItemClick(int id, int position) {
                String content = "";
                switch (id) {
                    case R.id.root:
                        content = "title";
                        break;
                    case R.id.content:
                        content = "content";
                        break;

                }
//                Toast.makeText(getActivity(),mDatas.get(position).getName(),Toast.LENGTH_SHORT).show();
            }
        });

        mRv.setAdapter(mAdapter);
        mDecoration = new ItemHeaderDecoration(mContext, mDatas);
        mRv.addItemDecoration(mDecoration);
        mDecoration.setCheckListener(checkListener);
        initData();
        return new SortDetailPresenter();
    }

    @Override
    protected void getData() {

    }
    private void initData() {
        hotSellBOList = MyApplication.hotSellBO;
        for (int i = 0; i < hotSellBOList.size(); i++) {
            RightBean head = new RightBean(hotSellBOList.get(i).getItemClass().getName());
            if (hotSellBOList.get(i).getMerchandiseList() != null && hotSellBOList.get(i).getMerchandiseList().size()>0) {
                //头部设置为true
                head.setTitle(true);
                head.setTitleName(hotSellBOList.get(i).getItemClass().getName());
                head.setTag(String.valueOf(i));
                mDatas.add(head);
            }
            for (int j = 0; j < hotSellBOList.get(i).getMerchandiseList().size(); j++) {
                RightBean body = new RightBean(hotSellBOList.get(i).getMerchandiseList().get(j).getName());
                body.setTag(String.valueOf(i));
                String name = hotSellBOList.get(i).getItemClass().getName();
                body.setTitleName(name);
                body.setImgsrc(hotSellBOList.get(i).getMerchandiseList().get(j).getImageUrl());
                body.setPrice(String.valueOf(hotSellBOList.get(i).getMerchandiseList().get(j).getOnlinePrice()));
                body.setListintPrice(String.valueOf(hotSellBOList.get(i).getMerchandiseList().get(j).getOriginalPrice()));
                body.setId(String.valueOf(hotSellBOList.get(i).getMerchandiseList().get(j).getId()));
                mDatas.add(body);
            }

        }
        mAdapter.notifyDataSetChanged();
        mDecoration.setData(mDatas);
    }

    public void setData(int n) {
        mIndex = n;
        mRv.stopScroll();
        smoothMoveToPosition(n);
    }

    public void setListener(CheckListener listener) {
        this.checkListener = listener;
    }

    private void smoothMoveToPosition(int n) {
        int firstItem = mManager.findFirstVisibleItemPosition();
        int lastItem = mManager.findLastVisibleItemPosition();
        Log.d("first--->", String.valueOf(firstItem));
        Log.d("last--->", String.valueOf(lastItem));
        if (n <= firstItem) {
            mRv.scrollToPosition(n);
        } else if (n <= lastItem) {
            Log.d("pos---->", String.valueOf(n) + "VS" + firstItem);
            int top = mRv.getChildAt(n - firstItem).getTop();
            Log.d("top---->", String.valueOf(top));
            mRv.scrollBy(0, top);
        } else {
            mRv.scrollToPosition(n);
            move = true;
        }
    }

    @Override
    public void check(int position, boolean isScroll) {
        checkListener.check(position, isScroll);

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void refreshView(int code, String data) {

    }


    private class RecyclerViewListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (move && newState == RecyclerView.SCROLL_STATE_IDLE) {
                move = false;
                int n = mIndex - mManager.findFirstVisibleItemPosition();
                Log.d("n---->", String.valueOf(n));
                if (0 <= n && n < mRv.getChildCount()) {
                    int top = mRv.getChildAt(n).getTop();
                    Log.d("top--->", String.valueOf(top));
                    mRv.smoothScrollBy(0, top);
                }
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (move) {
                move = false;
                int n = mIndex - mManager.findFirstVisibleItemPosition();
                if (0 <= n && n < mRv.getChildCount()) {
                    int top = mRv.getChildAt(n).getTop();
                    mRv.scrollBy(0, top);
                }
            }
        }
    }

    /**
     * 清空卖品数量
     * @param messageEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent messageEvent){
        if (messageEvent.getMessageType().equals("prodectClear")){
            mAdapter.clearSp();
        }
    }
}
