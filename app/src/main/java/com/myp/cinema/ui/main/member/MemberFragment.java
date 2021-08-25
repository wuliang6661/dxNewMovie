package com.myp.cinema.ui.main.member;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.meg7.widget.CircleImageView;
import com.myp.cinema.R;
import com.myp.cinema.base.MyApplication;
import com.myp.cinema.config.ConditionEnum;
import com.myp.cinema.entity.UserBO;
import com.myp.cinema.mvp.MVPBaseFragment;
import com.myp.cinema.ui.SettingActivity;
import com.myp.cinema.ui.WebViewActivity;
import com.myp.cinema.ui.feedbacklist.FeedBackListActivity;

import com.myp.cinema.ui.foodorderlist.FoodOrderActivity;
import com.myp.cinema.ui.membercard.MemberCardActivity;
import com.myp.cinema.ui.personcollect.PersonCollectActivity2;
import com.myp.cinema.ui.personcomment.PersonCommentActivity;
import com.myp.cinema.ui.personcoupon.PersonCouponActivity;
import com.myp.cinema.ui.personorder.PersonOrderActivity;
import com.myp.cinema.ui.personread.PersonReadActivity;
import com.myp.cinema.ui.personsetting.PersonSettingActivity;
import com.myp.cinema.ui.personsome.PersonSomeActivity;
import com.myp.cinema.ui.personwantsee.PersonWantSeeActivity;
import com.myp.cinema.ui.signin.SignInActivity;
import com.myp.cinema.ui.userlogin.LoginActivity;
import com.myp.cinema.util.LogUtils;
import com.myp.cinema.util.ScreenUtils;
import com.myp.cinema.util.SizeUtils;
import com.myp.cinema.util.StringUtils;
import com.myp.cinema.util.ToastUtils;
import com.myp.cinema.widget.pulltozoomview.PullToZoomScrollViewEx;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;

/**
 * 个人中心fragment
 */

public class MemberFragment extends MVPBaseFragment<MemberContract.View, MemberPresenter>
        implements MemberContract.View, View.OnClickListener {

    @Bind(R.id.scollview)
    PullToZoomScrollViewEx scollview;

    /**
     * headler区域
     */
    private ImageView signIn;
    private CircleImageView personImg;
    private ImageButton settingBtn;
    private TextView personName;
    private LinearLayout signBtn;
    private ImageView signImg;
    private TextView openCardTxt;
    private LinearLayout signedLayout;
    private LinearLayout notSignLayout;

    /**
     * 主要content区域
     */
    TextView moviesCommentNum, moviesReadNum, moviesWantSeeNum, moviesCollectNum;
    RelativeLayout openCard,rlComment, rlSeenMovie, rlWantSeeMovie, rlCollect;
    private LinearLayout llCall;

    /** 新改版头部四个布局按钮 */
    private RelativeLayout rlTicketOrder;/** 影票订单 */
    private RelativeLayout rlProductOrder;/** 小食订单 */
    private RelativeLayout rlCoupon;/** 优惠券 */
    private RelativeLayout rlCard;/** 会员卡 */
    private TextView ticketPoint;/** 影票数量 */
    private TextView productPoint;/** 小食订单 */
    private TextView couponPoint;/** 优惠券 */
    private TextView cardPoint;/** 会员卡 */
    private ImageView imageView;

    private TextView txtPhone;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_member, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadViewForPullToZoomScrollView(scollview);
        setPullToZoomViewLayoutParams(scollview);
        initViews();
    }


    /**
     * 初始化控件
     */
    private void initViews() {
        rlComment = (RelativeLayout) scollview.getPullRootView().findViewById(R.id.rlComment);
        openCard = (RelativeLayout)scollview.getPullRootView().findViewById(R.id.openCard);
        rlSeenMovie = (RelativeLayout) scollview.getPullRootView().findViewById(R.id.rlSeenMovie);
        rlWantSeeMovie = (RelativeLayout) scollview.getPullRootView().findViewById(R.id.rlWantSeeMovie);
        rlCollect = (RelativeLayout) scollview.getPullRootView().findViewById(R.id.rlCollect);
        txtPhone = scollview.getPullRootView().findViewById(R.id.txtPhone);
        llCall = scollview.getPullRootView().findViewById(R.id.llCall);
        signIn = (ImageView) scollview.getHeaderView().findViewById(R.id.sign_img);
        signIn.setVisibility(View.GONE);
        personImg = (CircleImageView) scollview.getHeaderView().findViewById(R.id.person_img);
        ViewGroup.LayoutParams params1 = personImg.getLayoutParams();
        params1.height = (int)(ScreenUtils.getScreenWidth() * 0.16);
        params1.width = (int)(ScreenUtils.getScreenWidth() * 0.16);
        personImg.setLayoutParams(params1);
        settingBtn = (ImageButton) scollview.getHeaderView().findViewById(R.id.settingBtn);
        signBtn = (LinearLayout) scollview.getHeaderView().findViewById(R.id.signBtn);
        signImg = (ImageView) scollview.getHeaderView().findViewById(R.id.signImg);
        personName = (TextView) scollview.getHeaderView().findViewById(R.id.person_name);
        rlTicketOrder = scollview.getHeaderView().findViewById(R.id.rlTicketOrder);
        rlProductOrder = scollview.getHeaderView().findViewById(R.id.rlProductOrder);
        rlCoupon = scollview.getHeaderView().findViewById(R.id.rlCoupon);
        rlCard = scollview.getHeaderView().findViewById(R.id.rlCard);
        ticketPoint = scollview.getHeaderView().findViewById(R.id.ticketPoint);
        productPoint = scollview.getHeaderView().findViewById(R.id.productPoint);
        couponPoint = scollview.getHeaderView().findViewById(R.id.couponPoint);
        cardPoint = scollview.getHeaderView().findViewById(R.id.cardPoint);
        openCardTxt = scollview.getPullRootView().findViewById(R.id.openCardTxt);
        signedLayout = scollview.getPullRootView().findViewById(R.id.signedLayout);
        notSignLayout = scollview.getPullRootView().findViewById(R.id.notSignLayout);

        imageView = scollview.getZoomView().findViewById(R.id.imageView);

        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        params.height = (int) (ScreenUtils.getScreenWidth() * 0.54);
        imageView.setLayoutParams(params);

        rlComment.setOnClickListener(this);
        openCard.setOnClickListener(this);
        rlSeenMovie.setOnClickListener(this);
        rlWantSeeMovie.setOnClickListener(this);
        rlCollect.setOnClickListener(this);
        personImg.setOnClickListener(this);
        personName.setOnClickListener(this);
        rlTicketOrder.setOnClickListener(this);
        rlProductOrder.setOnClickListener(this);
        rlCoupon.setOnClickListener(this);
        rlCard.setOnClickListener(this);
        llCall.setOnClickListener(this);
        settingBtn.setOnClickListener(this);
        signBtn.setOnClickListener(this);
        if (MyApplication.cinemaBo != null) {
            txtPhone.setText(MyApplication.cinemaBo.getContact());
        }else {
            txtPhone.setText("暂无");
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){
            if (MyApplication.cinemaBo != null) {
                txtPhone.setText(MyApplication.cinemaBo.getContact());
            }else {
                txtPhone.setText("暂无");
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.person_img:
            case R.id.person_name:
                if (MyApplication.isLogin == ConditionEnum.LOGIN) {   //已登录
                    Intent intent = new Intent(getActivity(), PersonSettingActivity.class);
                    startActivityForResult(intent, 1);
                } else if (MyApplication.isLogin == ConditionEnum.NOLOGIN) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivityForResult(intent, 1);
                }
                break;
            case R.id.rlSeenMovie:   //看过
                if (goLogin()) {
                    gotoActivity(PersonReadActivity.class, false);
                }
                break;
            case R.id.rlComment:   //影评
                if (goLogin()) {
                    gotoActivity(PersonCommentActivity.class, false);
                }
                break;
            case R.id.openCard:
                if (goLogin()) {
                    gotoActivity(MemberCardActivity.class, false);
                }
                break;
            case R.id.rlWantSeeMovie:   //想看
                if (goLogin()) {
                    gotoActivity(PersonWantSeeActivity.class, false);
                }
                break;
            case R.id.rlCollect:   //收藏
                if (goLogin()) {
                    gotoActivity(PersonCollectActivity2.class, false);
                }
                break;
            case R.id.settingBtn:  //基本设置
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivityForResult(intent, 2);
                break;
            case R.id.signBtn://签到
                if (goLogin()) {
                    Intent intent1 = new Intent(getActivity(), WebViewActivity.class);
                    intent1.putExtra("url","https://dxm.happydoit.com/api/Signin/signin?appUserId="+MyApplication.user.getId());
                    startActivity(intent1);
                }
                break;
            case R.id.rlTicketOrder:/** 影票订单 */
                if (goLogin()) {
                    gotoActivity(PersonOrderActivity.class, false);
                }
                break;
            case R.id.rlProductOrder:/** 卖品订单 */
                if (goLogin()) {
                    gotoActivity(FoodOrderActivity.class, false);
                }
                break;
            case R.id.rlCoupon:/** 优惠券 */
                if (goLogin()) {
                    gotoActivity(PersonCouponActivity.class, false);
                }
                break;
            case R.id.rlCard:/** 会员卡 */
                if (goLogin()) {
                    gotoActivity(MemberCardActivity.class, false);
                }
                break;
            case R.id.llCall:
                if (MyApplication.cinemaBo != null) {
                    callPhone();
                }else {
                    ToastUtils.showShortToast("当前影院暂无客服电话");
                }
                break;
                default:
                    break;
        }
    }

    private void callPhone() {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+ MyApplication.cinemaBo.getContact()));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        switch (resultCode) {
            case 1:
                UserBO userBO = (UserBO) data.getSerializableExtra("user");
                setDatas(userBO);
                break;
            case 2:
                boolean isLogout = data.getBooleanExtra("isLogout", false);
                if (isLogout) {
                    personName.setText("点击登录或注册");
                    personImg.setImageResource(R.mipmap.new_default_header);
                    ticketPoint.setVisibility(View.GONE);
                    productPoint.setVisibility(View.GONE);
                    couponPoint.setVisibility(View.GONE);
                    cardPoint.setVisibility(View.GONE);
                }
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (MyApplication.isLogin == ConditionEnum.LOGIN) {
            setDatas(MyApplication.user);
            mPresenter.loadMemberRecord(MyApplication.user.getId());
            mPresenter.getStatus(MyApplication.user.getId());
        }
    }

    /**
     * 为界面设置值
     */
    private void setDatas(UserBO userBO) {
        if (!StringUtils.isEmpty(userBO.getHeader())) {
            Glide.with(getActivity()).load(userBO.getHeader()).into(personImg);
        }
        if (!StringUtils.isEmpty(userBO.getNickName())) {
            personName.setText(userBO.getNickName());
        } else {
            personName.setText(userBO.getMobile());
        }
    }


    private void loadViewForPullToZoomScrollView(PullToZoomScrollViewEx scrollView) {
        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.member_header_layout, null);
        View zoomView = LayoutInflater.from(getActivity()).inflate(R.layout.member_zoom_layout, null);
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.member_content_layout, null);
        scrollView.setHeaderView(headView);
        scrollView.setZoomView(zoomView);
        scrollView.setScrollContentView(contentView);
    }

    // 设置头部的View的宽高。
    private void setPullToZoomViewLayoutParams(PullToZoomScrollViewEx scrollView) {
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        int mScreenHeight = localDisplayMetrics.heightPixels;
        int mScreenWidth = localDisplayMetrics.widthPixels;
        LinearLayout.LayoutParams localObject = new LinearLayout.LayoutParams(mScreenWidth,
                (int) (9.0F * (mScreenWidth / 16.0F)));
        scrollView.setHeaderViewSize(mScreenWidth, (int) (mScreenWidth * 0.64) );
//        scrollView.setHeaderLayoutParams(localObject);
    }

    @Override
    public void onRequestError(String msg) {
        LogUtils.showToast(msg);
    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void getUser(UserBO userBO) {
        setDatas(userBO);

    }

    @Override
    public void getStatus(ResponseBody responseBody) throws JSONException, IOException {
        String s = new String(responseBody.bytes());
        JSONObject jsonObject = new JSONObject(s);
        int status = jsonObject.optInt("status");
        if (status == 1) {//成功
            if (jsonObject.optInt("ticketNum") > 0){
                ticketPoint.setVisibility(View.VISIBLE);
                ticketPoint.setText(String.format("%s",jsonObject.optInt("ticketNum")));
            }else {
                ticketPoint.setVisibility(View.GONE);
            }

            if (jsonObject.optInt("goodsNum") > 0){
                productPoint.setVisibility(View.VISIBLE);
                productPoint.setText(String.format("%s",jsonObject.optInt("goodsNum")));
            }else {
                productPoint.setVisibility(View.GONE);
            }

            if (jsonObject.optInt("discountNum") > 0){
                couponPoint.setVisibility(View.VISIBLE);
                couponPoint.setText(String.format("%s",jsonObject.optInt("discountNum")));
            }else {
                couponPoint.setVisibility(View.GONE);
            }
            if (jsonObject.optString("cardTitle") != null
                    && !jsonObject.optString("cardTitle").equals("")) {
                openCardTxt.setText(jsonObject.optString("cardTitle"));
                openCard.setVisibility(View.VISIBLE);
            }else {
                openCard.setVisibility(View.GONE);
            }

            if (jsonObject.optBoolean("isSign")){
                signImg.setImageDrawable(getResources().getDrawable(R.mipmap.new_sign_icon));
                signedLayout.setVisibility(View.VISIBLE);
                notSignLayout.setVisibility(View.GONE);
            }else {
                signImg.setImageDrawable(getResources().getDrawable(R.mipmap.new_sign_no));
                signedLayout.setVisibility(View.GONE);
                notSignLayout.setVisibility(View.VISIBLE);
            }

        }
    }
}