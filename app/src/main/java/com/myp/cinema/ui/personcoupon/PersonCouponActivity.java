package com.myp.cinema.ui.personcoupon;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myp.cinema.R;
import com.myp.cinema.api.HttpInterfaceIml;
import com.myp.cinema.entity.UserCouponBO;
import com.myp.cinema.jpush.MessageEvent;
import com.myp.cinema.mvp.MVPBaseActivity;
import com.myp.cinema.util.AppManager;
import com.myp.cinema.util.LogUtils;
import com.myp.cinema.util.ToastUtils;
import com.myp.cinema.widget.superadapter.CommonAdapter;
import com.myp.cinema.widget.superadapter.ViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import okhttp3.ResponseBody;
import rx.Subscriber;


/**
 * MVPPlugin
 * 我的优惠券页面
 */

public class PersonCouponActivity extends MVPBaseActivity<PersonCouponContract.View,
        PersonCouponPresenter> implements PersonCouponContract.View, View.OnClickListener,AdapterView.OnItemClickListener {

    @Bind(R.id.txtCouponNum)
    TextView txtCouponNum;//优惠券数量
    @Bind(R.id.rlAddCoupon)
    RelativeLayout rlAddCoupon;//添加优惠券
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @Bind(R.id.list)
    ListView list;
    @Bind(R.id.none_layout)
    LinearLayout none_layout;

    private int page = 1;
    private CommonAdapter<UserCouponBO> adapter;

    private List<UserCouponBO> data= new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.act_coupon;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("我的优惠券");

        rlAddCoupon.setOnClickListener(this);
        list.setOnItemClickListener(this);
        none_layout.setVisibility(View.GONE);
        list.setVisibility(View.VISIBLE);
        mPresenter.loadPersonCoupon(page,10);
        personCouponNum();
        setPullRefresher();
    }

    private void setPullRefresher(){
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page=1;
                mPresenter.loadPersonCoupon(page,10);
                refreshLayout.finishRefresh(1000);
                refreshlayout.finishRefresh(2000);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshlayout) {
                page++;
                mPresenter.loadPersonCoupon(page,10);
                refreshLayout.finishLoadMore(1000);
                refreshlayout.finishLoadMore(2000);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rlAddCoupon:  //添加优惠券
                showCouponDialog();
                break;
            case R.id.txtCouponNum:

                break;
                default:
                    break;
        }
    }

    /**
     * 设置适配器
     */
    private void setAdapter() {
        adapter = new CommonAdapter<UserCouponBO>(this, R.layout.item_youhuijuan, data) {
            @Override
            protected void convert(ViewHolder holder, final UserCouponBO item, int position) {
//                viewHolder.setText(R.id.movies_name, item.getMovieName());
                ImageView ivState2 = holder.getConvertView().findViewById(R.id.ivState2);
                if (item.getDxPlatTicket().getTicketType() == 2){
                    holder.setText(R.id.buyTickets,"购买");
                }else {
                    holder.setText(R.id.buyTickets,"购票");
                }
                if (item.getStatus() == 0){ //0 可用  1 已使用  2 已过期
                    holder.getView(R.id.coupon_bg_yes).setVisibility(View.VISIBLE);
                    holder.getView(R.id.coupon_bg_no).setVisibility(View.GONE);
                    setTextColor((TextView)holder.getView(R.id.youhui_type),item.getDxPlatTicket().getName(),"#312927");
                    holder.setText(R.id.youhui_time,String.format("有效期：%s 至 %s",item.getDxPlatTicket().getStartTime().substring(0,10),
                            item.getDxPlatTicket().getEndTime().substring(0,10)));
                    if (item.getDxPlatTicket().getTicketType() == 3){
                        holder.setText(R.id.txtNumYes,String.format("%s",1));
                        holder.setText(R.id.youhui_price,"张");
                        if (String.valueOf(Double.valueOf(item.getDxPlatTicket().getFixedPayMoney())) != null
                                && item.getDxPlatTicket().getFixedPayMoney() == 0 ) {
                            Glide.with(PersonCouponActivity.this)
                                    .load(R.mipmap.zero)
                                    .into((ImageView) holder.getView(R.id.ivType));
                        }else {
                            Glide.with(PersonCouponActivity.this)
                                    .load(R.mipmap.see_coupon_yes)
                                    .into((ImageView) holder.getView(R.id.ivType));
                        }
                    }else {
                        if (item.getDxPlatTicket().getPlatformType() == 2){//自己添加的优惠券
                            holder.setText(R.id.txtNumYes,String.format("%s",1));
                            holder.setText(R.id.youhui_price,"张");
                            Glide.with(PersonCouponActivity.this)
                                    .load(R.mipmap.see_coupon_yes)
                                    .into((ImageView) holder.getView(R.id.ivType));
                        }else {
                            holder.setText(R.id.txtNumYes, String.format("%s", item.getDxPlatTicket().getAmount()));
                            holder.setText(R.id.youhui_price, "元");
                            Glide.with(PersonCouponActivity.this)
                                    .load(R.mipmap.money_coupon_yes)
                                    .into((ImageView) holder.getView(R.id.ivType));
                        }
                    }
                }else if (item.getStatus() == 1){
                    holder.getView(R.id.coupon_bg_yes).setVisibility(View.GONE);
                    holder.getView(R.id.coupon_bg_no).setVisibility(View.VISIBLE);
                    Glide.with(PersonCouponActivity.this).load(R.mipmap.coupon_used).into(ivState2);
                    setTextColor((TextView)holder.getView(R.id.youhui_type),item.getDxPlatTicket().getName(),"#888888");
                    holder.setText(R.id.youhui_time,String.format("有效期：%s 至 %s",item.getDxPlatTicket().getStartTime().substring(0,10),
                            item.getDxPlatTicket().getEndTime().substring(0,10)));
                    if (item.getDxPlatTicket().getTicketType() == 3){//观影券
                        holder.setText(R.id.txtNumYes,String.format("%s",1));
                        holder.setText(R.id.youhui_price,"张");
                        holder.setText(R.id.txtNumNo,String.format("%s",1));
                        holder.setText(R.id.youhui_price_yes,"张");
                        if (String.valueOf(Double.valueOf(item.getDxPlatTicket().getFixedPayMoney())) != null
                                && item.getDxPlatTicket().getFixedPayMoney() == 0 ) {
                            Glide.with(PersonCouponActivity.this)
                                    .load(R.mipmap.zero)
                                    .into((ImageView) holder.getView(R.id.ivType));
                        }else {
                            Glide.with(PersonCouponActivity.this)
                                    .load(R.mipmap.see_coupon_no)
                                    .into((ImageView) holder.getView(R.id.ivType));
                        }
                    }else {
                        if (item.getDxPlatTicket().getPlatformType() == 2){
                            holder.setText(R.id.txtNumYes,String.format("%s",1));
                            holder.setText(R.id.youhui_price,"张");
                            holder.setText(R.id.txtNumNo,String.format("%s",1));
                            holder.setText(R.id.youhui_price_yes,"张");
                            Glide.with(PersonCouponActivity.this)
                                    .load(R.mipmap.see_coupon_no)
                                    .into((ImageView) holder.getView(R.id.ivType));
                        }else {
                            holder.setText(R.id.txtNumYes, String.format("%s", item.getDxPlatTicket().getAmount()));
                            holder.setText(R.id.youhui_price, "元");
                            holder.setText(R.id.txtNumNo, String.format("%s", item.getDxPlatTicket().getAmount()));
                            holder.setText(R.id.youhui_price_yes, "元");
                            Glide.with(PersonCouponActivity.this)
                                    .load(R.mipmap.money_coupon_no)
                                    .into((ImageView) holder.getView(R.id.ivType));
                        }
                    }
                    Glide.with(PersonCouponActivity.this)
                            .load(R.mipmap.coupon_used)
                            .into((ImageView)holder.getView(R.id.ivState2));

                }else {
                    holder.getView(R.id.coupon_bg_yes).setVisibility(View.GONE);
                    holder.getView(R.id.coupon_bg_no).setVisibility(View.VISIBLE);
                    Glide.with(PersonCouponActivity.this).load(R.mipmap.coupon_outtime).into(ivState2);
                    setTextColor((TextView)holder.getView(R.id.youhui_type),item.getDxPlatTicket().getName(),"#888888");
                    holder.setText(R.id.youhui_time,String.format("有效期：%s 至 %s",item.getDxPlatTicket().getStartTime().substring(0,10),
                            item.getDxPlatTicket().getEndTime().substring(0,10)));
                    if (item.getDxPlatTicket().getTicketType() == 3){
                        holder.setText(R.id.txtNumYes,String.format("%s",1));
                        holder.setText(R.id.youhui_price,"张");
                        holder.setText(R.id.txtNumNo,String.format("%s",1));
                        holder.setText(R.id.youhui_price_yes,"张");
                        if (String.valueOf(Double.valueOf(item.getDxPlatTicket().getFixedPayMoney())) != null
                                && item.getDxPlatTicket().getFixedPayMoney() == 0 ) {
                            Glide.with(PersonCouponActivity.this)
                                    .load(R.mipmap.see_coupon_no)
                                    .into((ImageView) holder.getView(R.id.ivType));
                        }else {
                            Glide.with(PersonCouponActivity.this)
                                    .load(R.mipmap.see_coupon_no)
                                    .into((ImageView) holder.getView(R.id.ivType));
                        }
                    }else {
                        if (item.getDxPlatTicket().getPlatformType() == 2){
                            holder.setText(R.id.txtNumYes,String.format("%s",1));
                            holder.setText(R.id.youhui_price,"张");
                            holder.setText(R.id.txtNumNo,String.format("%s",1));
                            holder.setText(R.id.youhui_price_yes,"张");
                            Glide.with(PersonCouponActivity.this)
                                    .load(R.mipmap.see_coupon_no)
                                    .into((ImageView) holder.getView(R.id.ivType));
                        }else {
                            holder.setText(R.id.txtNumYes, String.format("%s", item.getDxPlatTicket().getAmount()));
                            holder.setText(R.id.youhui_price, "元");
                            holder.setText(R.id.txtNumNo, String.format("%s", item.getDxPlatTicket().getAmount()));
                            holder.setText(R.id.youhui_price_yes, "元");
                            Glide.with(PersonCouponActivity.this)
                                    .load(R.mipmap.money_coupon_no)
                                    .into((ImageView) holder.getView(R.id.ivType));
                        }
                    }
                    Glide.with(PersonCouponActivity.this)
                            .load(R.mipmap.coupon_used)
                            .into((ImageView)holder.getView(R.id.ivState2));
                }
                holder.getView(R.id.buyTickets).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AppManager.getAppManager().goBackMain();
                        if (item.getDxPlatTicket().getName().contains("卖品")){
                            EventBus.getDefault().post(new MessageEvent("showMain", "no"));
                        }else {
                            EventBus.getDefault().post(new MessageEvent("showMain", "yes"));
                        }
                    }
                });

            }
        };
        list.setAdapter(adapter);
    }

    private void setTextColor(TextView textView,String text,String color) {
        SpannableStringBuilder builder = new SpannableStringBuilder(text);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor(color));
        builder.setSpan(colorSpan, 0, text.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        textView.setText(builder);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle = new Bundle();
        bundle.putString("id",String.valueOf(data.get(position).getId()));
        gotoActivity(CouponDetailActivity.class,bundle,false);
    }

    @Override
    public void getCoupon(List<UserCouponBO> userCouponBOS) {
        stopProgress();
        if(page==1){
            if(userCouponBOS.size()==0){
                refreshLayout.setVisibility(View.GONE);
                none_layout.setVisibility(View.VISIBLE);
            }else {
                data.clear();
                data.addAll(userCouponBOS);
                refreshLayout.setVisibility(View.VISIBLE);
                setAdapter();
                adapter.notifyDataSetChanged();
            }
        }else {
            data.addAll(userCouponBOS);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getAddResult(ResponseBody responseBody) throws IOException, JSONException {
        String s = new String(responseBody.bytes());
        JSONObject jsonObject = new JSONObject(s);
        int status = jsonObject.optInt("status");
        if (status == 1){
            ToastUtils.showShortToast(jsonObject.optString("message"));
            page=1;
            mPresenter.loadPersonCoupon(page,10);
        }else {
            ToastUtils.showShortToast(jsonObject.optString("message"));
        }
    }

    @Override
    public void onRequestError(String msg) {
        stopProgress();
        LogUtils.showToast(msg);
    }

    @Override
    public void onRequestEnd() {
        stopProgress();
    }


    /**
     * 优惠券数量
     */
    private void personCouponNum(){
        HttpInterfaceIml.personCouponNum().subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                stopProgress();
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShortToast(e.getMessage());
            }

            @Override
            public void onNext(String s) {
                stopProgress();
                txtCouponNum.setText(String.format("共%s张有效优惠券",s));
            }
        });
    }


    private void showCouponDialog() {
        // 构造对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.AlertDialog);
        final LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.exchange_coupon_dialog, null);
        final RelativeLayout rlClose = (RelativeLayout) v.findViewById(R.id.rlClose);
        final EditText etEnter = (EditText) v.findViewById(R.id.etEnter);
        final RelativeLayout rlSubmit = (RelativeLayout) v.findViewById(R.id.rlSubmit);
        etEnter.setHint("请输入电子兑换券券码");
        etEnter.setHintTextColor(Color.parseColor("#888888"));
        builder.setView(v);
        builder.setCancelable(true);
        final Dialog noticeDialog = builder.create();
        noticeDialog.getWindow().setGravity(Gravity.BOTTOM);
        noticeDialog.setCancelable(false);
        noticeDialog.show();
        rlClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noticeDialog.dismiss();
            }
        });
        rlSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(etEnter.getText())){
                    showProgress("");
                    mPresenter.addPersonCoupon(etEnter.getText().toString());
                }
                noticeDialog.dismiss();
            }
        });
        noticeDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                InputMethodManager inputMethodManager =
                        (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(
                        getWindow().getDecorView().getWindowToken(), 0);
            }
        });
        WindowManager.LayoutParams layoutParams = noticeDialog.getWindow().getAttributes();
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        noticeDialog.getWindow().setAttributes(layoutParams);
        noticeDialog.getWindow().setWindowAnimations(R.style.anim_menu_bottombar);

    }


}
