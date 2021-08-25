package com.myp.cinema.ui.hotsellprodect;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.myp.cinema.R;
import com.myp.cinema.base.MyApplication;
import com.myp.cinema.config.ConditionEnum;
import com.myp.cinema.jpush.MessageEvent;
import com.myp.cinema.ui.userlogin.LoginActivity;
import com.myp.cinema.util.SPUtils;
import com.myp.cinema.util.ScreenUtils;

import org.greenrobot.eventbus.EventBus;
import java.util.List;


public class ClassifyDetailAdapter extends RvAdapter<RightBean> {

    private int myPosition = -1;
    private boolean isOpen = false;
    private int goodsNum = 0;
    private SPUtils spUtils;
    private Context context;
    private List<RightBean> data;

    public ClassifyDetailAdapter(Context context, List<RightBean> list, RvListener listener) {
        super(context, list, listener);
        this.context = context;
        this.data = list;
        spUtils = new SPUtils("ProdectItemNum");
    }


    @Override
    protected int getLayoutId(int viewType) {
        return viewType == 0 ? R.layout.item_title : R.layout.item_classify_detail;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).isTitle() ? 0 : 1;
    }

    @Override
    protected RvHolder getHolder(View view, int viewType) {
        return new ClassifyHolder(view, viewType, listener);
    }

    public class ClassifyHolder extends RvHolder<RightBean> {
        TextView txtName;
        ImageView ivAvatar;
        TextView tvTitle;
        ImageView ivAdd;
        RelativeLayout rlSelect;
        ImageView ivDecrease;
        TextView txtNum;
        TextView txtPrice;//结算价
        TextView txtNoPrice;//零售价

        public ClassifyHolder(View itemView, int type, RvListener listener) {
            super(itemView, type, listener);
            switch (type) {
                case 0:
                    tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
                    break;
                case 1:
                    txtName = (TextView) itemView.findViewById(R.id.txtName);
                    ivAvatar = (ImageView) itemView.findViewById(R.id.ivAvatar);
                    ivAdd = (ImageView) itemView.findViewById(R.id.ivAdd);
                    rlSelect = (RelativeLayout) itemView.findViewById(R.id.rlSelect);
                    ivDecrease = (ImageView) itemView.findViewById(R.id.ivDecrease);
                    txtNum = (TextView) itemView.findViewById(R.id.txtNum);
                    txtPrice = itemView.findViewById(R.id.txtPrice);
                    txtNoPrice = itemView.findViewById(R.id.txtNoPrice);
                    break;
            }

        }

        @Override
        public void bindHolder(RightBean sortBean, final int position) {
            int itemViewType = ClassifyDetailAdapter.this.getItemViewType(position);
            switch (itemViewType) {
                case 0:
                    tvTitle.setText(sortBean.getName());
                    break;
                case 1:
                    txtName.setText(sortBean.getName());
                    if (txtName.getLineCount() == 1){
                        txtName.setGravity(Gravity.END);
                    }else {
                        txtName.setGravity(Gravity.START);
                    }
                    txtPrice.setText(String.format("￥ %s",sortBean.getPrice()));
                    txtNoPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    if (sortBean.getListintPrice().equals("0.0")){
                        txtNoPrice.setVisibility(View.GONE);
                    }else {
                        txtNoPrice.setVisibility(View.VISIBLE);
                        txtNoPrice.setText(String.format("￥ %s", sortBean.getListintPrice()));
                    }
                    ViewGroup.LayoutParams params = ivAvatar.getLayoutParams();
                    params.width = (int)(ScreenUtils.getScreenWidth() * 0.32);
                    params.height = (int)(ScreenUtils.getScreenWidth() * 0.32);
                    ivAvatar.setLayoutParams(params);
                    Glide.with(context)
                            .load(sortBean.getImgsrc())
                            .asBitmap()
                            .error(R.drawable.zhanwei1)
                            .into(ivAvatar);
                    if (spUtils.getInt(data.get(position).getId()) > 0){
                        rlSelect.setVisibility(View.VISIBLE);
                        txtNum.setText(String.format("%s", spUtils.getInt(data.get(position).getId())));
                    }else {
                        rlSelect.setVisibility(View.GONE);
                    }
                    ivAdd.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (MyApplication.isLogin == ConditionEnum.NOLOGIN) {
                                Intent intent = new Intent(context, LoginActivity.class);
                                context.startActivity(intent);
                                return;
                            }
                            if (MyApplication.isSuccess) {
                                MyApplication.isSuccess = false;
                                ivAdd.setAnimation(getShowAnimation());
                                int num = spUtils.getInt(data.get(position).getId());
                                if (num != -1) {
                                    goodsNum = num;
                                    goodsNum++;
                                } else {
                                    goodsNum = 0;
                                    goodsNum++;
                                }
                                spUtils.put(data.get(position).getId(), goodsNum);
                                EventBus.getDefault().post(new MessageEvent("goodsNum", data.get(position).getId()));//发给HotSellActivity
                                myPosition = position;
                                isOpen = true;
                                notifyDataSetChanged();
                            }
                        }
                    });
                    ivDecrease.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (MyApplication.isSuccess) {
                                MyApplication.isSuccess = false;
                                ivDecrease.setAnimation(getHiddenAnimation());
                                int num = spUtils.getInt(data.get(position).getId());
                                if (num != -1) {
                                    goodsNum = num;
                                    goodsNum--;
                                    myPosition = position;
                                } else {
                                    goodsNum = 0;
                                    goodsNum--;
                                }
                                spUtils.put(data.get(position).getId(), goodsNum);
                                EventBus.getDefault().post(new MessageEvent("goodsNum", data.get(position).getId()));//发给HotSellActivity
                                isOpen = false;
                                notifyDataSetChanged();
                            }
                        }
                    });
                    break;
            }

        }
    }

    //显示减号的动画
    private Animation getShowAnimation(){
        AnimationSet set = new AnimationSet(true);
        RotateAnimation rotate = new RotateAnimation(0,720,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        set.addAnimation(rotate);
        TranslateAnimation translate = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF,2f
                ,TranslateAnimation.RELATIVE_TO_SELF,0
                ,TranslateAnimation.RELATIVE_TO_SELF,0
                ,TranslateAnimation.RELATIVE_TO_SELF,0);
        set.addAnimation(translate);
        AlphaAnimation alpha = new AlphaAnimation(0,1);
        set.addAnimation(alpha);
        set.setDuration(500);
        return set;
    }
    //隐藏减号的动画
    private Animation getHiddenAnimation(){
        AnimationSet set = new AnimationSet(true);
        RotateAnimation rotate = new RotateAnimation(0,720,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        set.addAnimation(rotate);
        TranslateAnimation translate = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF,0
                ,TranslateAnimation.RELATIVE_TO_SELF,2f
                ,TranslateAnimation.RELATIVE_TO_SELF,0
                ,TranslateAnimation.RELATIVE_TO_SELF,0);
        set.addAnimation(translate);
        AlphaAnimation alpha = new AlphaAnimation(1,0);
        set.addAnimation(alpha);
        set.setDuration(500);
        return set;
    }

    public void clearSp(){
        spUtils.clear();
        notifyDataSetChanged();
    }
}
