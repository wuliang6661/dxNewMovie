package com.myp.cinema.ui.personorder.ordermessage;


import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myp.cinema.R;
import com.myp.cinema.api.HttpInterfaceIml;
import com.myp.cinema.base.MyApplication;
import com.myp.cinema.entity.OrderDetailBO;
import com.myp.cinema.entity.RefundBO;
import com.myp.cinema.mvp.MVPBaseActivity;
import com.myp.cinema.ui.applicationforrefund.applicationforrefund;
import com.myp.cinema.ui.personorder.PersonOrderActivity;
import com.myp.cinema.util.CimemaUtils;
import com.myp.cinema.util.LogUtils;
import com.myp.cinema.util.StringUtils;
import com.myp.cinema.util.ToastUtils;
import com.myp.cinema.util.ZxingUtils;
import com.squareup.picasso.Picasso;
import com.zhy.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import rx.Subscriber;


/**
 * MVPPlugin
 * 订单详情页面
 */

public class OrderMessageActivity extends MVPBaseActivity<OrderMessageContract.View,
        OrderMessagePresenter> implements OrderMessageContract.View , View.OnClickListener {
    @Bind(R.id.movies_img)
    ImageView movieImg;
    @Bind(R.id.movies_name)
    TextView moviesname;
    @Bind(R.id.movies_type)
    TextView moviestype;
    @Bind(R.id.movies_address)
    TextView moviesaddress;
    @Bind(R.id.movies_time)
    TextView moviestime;
    @Bind(R.id.movies_seat)
    TextView moviesSeat;
    @Bind(R.id.movies_num)
    TextView moviesnum;
    @Bind(R.id.collect_tickets2)
    TextView collectTickets2;//取票码
    @Bind(R.id.movies_price)
    TextView moviesPrice;
    @Bind(R.id.order_num)
    TextView orderNum;
    @Bind(R.id.phone_num)
    TextView phoneNum;
    @Bind(R.id.pay_time)
    TextView paytime;
    @Bind(R.id.add_ress)
    TextView address;
    @Bind(R.id.tuipiao)
    TextView tuipiao;
    @Bind(R.id.yingcheng)
    TextView yingcheng;
    @Bind(R.id.fenxiang_img)
    ImageView fenxiangimg;
    @Bind(R.id.call_phone)
    ImageView callphone;
    @Bind(R.id.layout_view)
    LinearLayout linear;
    @Bind(R.id.ivPrinted)
    ImageView ivPrinted;//已打印
    @Bind(R.id.ivGot)
    ImageView ivGot;//已领取
    @Bind(R.id.prodectList)
    RecyclerView prodectList;//小食列表
    @Bind(R.id.txtProdectCode)
    TextView txtProdectCode;//小食领取码
    @Bind(R.id.prodectCode)
    ImageView prodectCode;
    @Bind(R.id.ticketCode)
    ImageView ticketCode;
    @Bind(R.id.ivRefundTickets)
    ImageView ivRefundTickets;//已退票
    @Bind(R.id.go_back)
    LinearLayout goBack;
    @Bind(R.id.ivLine)
    ImageView ivLine;
    @Bind(R.id.llProdect)
    LinearLayout llProdect;
    @Bind(R.id.ivTicketCancle)
    ImageView ivTicketCancle;
    @Bind(R.id.ivProdectCancle)
    ImageView ivProdectCancle;
    @Bind(R.id.llTicketLayout)
    LinearLayout llTicketLayout;
    @Bind(R.id.LLTitle)
    LinearLayout LLTitle;
    @Bind(R.id.line1)
    ImageView line1;
    @Bind(R.id.commentContent)
    TextView commentContent;
    @Bind(R.id.numCode)
    TextView numCode;

    private String orderDetailNum="";
    private String id = "";
    private OrderDetailBO orderDetailBO;
    private List<OrderDetailBO.MerOrderBean.DetailsBean> prodectData = new ArrayList<>();
    private CommonAdapter<OrderDetailBO.MerOrderBean.DetailsBean> adapter;
    private String cinameId = "";

    @Override
    protected int getLayout() {
        return R.layout.act_order_message;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("订单详情");
        orderDetailNum = getIntent().getExtras().getString("orderNum");
        id = getIntent().getStringExtra("id");
        cinameId = getIntent().getStringExtra("cinemaId");
        showProgress("加载中...");
        if (cinameId != null) {
            mPresenter.quryOrderMessage(id, cinameId);
        }else {
            ToastUtils.showShortToast("获取影院信息失败");
        }
        fenxiangimg.setOnClickListener(this);
        callphone.setOnClickListener(this);
        tuipiao.setOnClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        prodectList.setLayoutManager(layoutManager);
    }



    public static String settingphone(String phone) {
        String phone_s = phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        return phone_s;
    }


    private void setdata(final OrderDetailBO orderMessage) {
        if (orderMessage.getMerOrder() != null) {
            prodectData.clear();
            prodectData.addAll(orderMessage.getMerOrder().getDetails());
            setAdapter();
        }
        if (orderMessage.getMerOrder() != null){
            llProdect.setVisibility(View.VISIBLE);
            ivLine.setVisibility(View.VISIBLE);
            if(orderMessage.getQrCode() ==null || orderMessage.getQrCode().isEmpty()){
                prodectCode.setBackgroundResource(R.drawable.erweimshixiao);
                txtProdectCode.setText("");
            }else {
                Bitmap bitmap = ZxingUtils.createQRImage(orderMessage.getQrCode().toString(), prodectCode.getWidth(), prodectCode.getHeight());
                prodectCode.setImageBitmap(bitmap);
                txtProdectCode.setText(orderMessage.getQrCode());
            }
        }else {
            llProdect.setVisibility(View.GONE);
            ivLine.setVisibility(View.GONE);
        }
        if (orderMessage.getDxMovie()!= null){
            llTicketLayout.setVisibility(View.VISIBLE);
            LLTitle.setVisibility(View.VISIBLE);
            line1.setVisibility(View.VISIBLE);
            if (StringUtils.isEmpty(orderMessage.getDxMovie().getPicture())) {
                movieImg.setImageResource(R.color.act_bg01);
            } else {
                Picasso.with(this).load(orderMessage.getDxMovie().getPicture()).into(movieImg);
            }
            moviesname.setText(orderMessage.getDxMovie().getMovieName());
            moviestype.setText(orderMessage.getDxMovie().getMovieDimensional());
            moviesaddress.setText(String.format("%s %s",orderMessage.getCinemaName(),orderMessage.getHallName()));
            if (!StringUtils.isEmpty(orderMessage.getPlayName())) {
                String time = orderMessage.getPlayName().substring(0, orderMessage.getPlayName().length() - 3);
                moviestime.setText(time);
            } else {
                moviestime.setText("");
            }
            moviesSeat.setText(CimemaUtils.getSeats(orderMessage.getSeats()));
            moviesnum.setText(String.format("%s张票", orderMessage.getTicketNum()));
            moviesPrice.setText(String.format("%s元",orderMessage.getPayPrice()));
            yingcheng.setText(orderMessage.getCinemaName());
        }else {
            llTicketLayout.setVisibility(View.GONE);
            LLTitle.setVisibility(View.GONE);
            ivLine.setVisibility(View.GONE);
            line1.setVisibility(View.GONE);
            yingcheng.setText(orderMessage.getCinemaName());
            moviesPrice.setText(String.format("%s元",orderMessage.getPayPrice()));
        }
        numCode.setText(orderMessage.getQrCode().substring(0,6));
        collectTickets2.setText(orderMessage.getQrCode().substring(6));
        orderNum.setText(orderMessage.getOrderNum());
        phoneNum.setText(settingphone(orderMessage.getDxAppUser().getMobile()));
        paytime.setText(orderMessage.getPayDate());
        if (orderMessage.getMemo() != null && !orderMessage.getMemo().equals("")) {
            commentContent.setText(orderMessage.getMemo());//备注
        }else {
            commentContent.setText("暂无备注");//备注
        }
        if(orderMessage.getRefundStatus() == null){
            tuipiao.setVisibility(View.GONE);
        }else {
            if(orderMessage.getRefundStatus().equals("1")){
                tuipiao.setVisibility(View.GONE);
                ivRefundTickets.setVisibility(View.VISIBLE);
                ivGot.setVisibility(View.VISIBLE);
                ivPrinted.setVisibility(View.VISIBLE);
                ivTicketCancle.setVisibility(View.VISIBLE);
                ivProdectCancle.setVisibility(View.VISIBLE);
                ivGot.setImageDrawable(getResources().getDrawable(R.mipmap.refund));
                ivPrinted.setImageDrawable(getResources().getDrawable(R.mipmap.refund));
            }else {
                if(orderMessage.getCanRefund()==1) {
                    tuipiao.setVisibility(View.VISIBLE);
                    tuipiao.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (orderMessage.getDxMovie()!= null) {
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("order", orderMessage);
                                bundle.putString("cinemaId", orderMessage.getCinema().getCinemaId());
                                bundle.putString("id", id);
                                gotoActivity(applicationforrefund.class, bundle, false);
                            }else if (orderMessage.getMerOrder() != null){
                                refundProdect(id,MyApplication.cinemaBo.getCinemaId());
                            }
                        }
                    });
                }else {
                    tuipiao.setVisibility(View.GONE);
                }
            }
        }
        address.setText(orderMessage.getCinema().getAddress());
        if(orderMessage.getQrCode() ==null || orderMessage.getQrCode().isEmpty()){
            ticketCode.setBackgroundResource(R.drawable.erweimshixiao);
        }else {
            Bitmap bitmap = ZxingUtils.createQRImage(orderMessage.getQrCode().substring(0,6)+"|"+orderMessage.getQrCode().substring(6), ticketCode.getWidth(), ticketCode.getHeight());
            ticketCode.setImageBitmap(bitmap);
        }
        fenxiangimg.setVisibility(View.GONE);
    }

    /**
     * 卖品退款
     * @param id,cinemaId
     */
    private void refundProdect(final String id, final String cinemaId){
        LayoutInflater factory = LayoutInflater.from(this);//提示框
        final View view = factory.inflate(R.layout.dialog_order_pay, null);//这里必须是final的
        TextView cancle = (TextView) view.findViewById(R.id.off_commit);
        TextView commit = (TextView) view.findViewById(R.id.commit);
        TextView message = view.findViewById(R.id.message);
        TextView title = view.findViewById(R.id.title);
        title.setText("是否确认退货？");
        message.setVisibility(View.GONE);
        cancle.setText("取消");
        commit.setText("确认");
        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //事件
                dialog.dismiss();
                showProgress("退货提交中...");
                goRefundProdect(id,cinemaId);
            }
        });
        dialog.setView(view);
        dialog.show();
    }

    /**
     * 退票接口
     * @param id
     * @param cinemaId
     */
    private void goRefundProdect(String id,String cinemaId){
        HttpInterfaceIml.refund(id,cinemaId).subscribe(new Subscriber<RefundBO>() {
            @Override
            public void onCompleted() {
               stopProgress();
            }

            @Override
            public void onError(Throwable e) {
              stopProgress();
              LogUtils.showToast(e.getMessage());
            }

            @Override
            public void onNext(RefundBO s) {
                stopProgress();
                LogUtils.showToast("退货成功");
                finish();
            }
        });
    }
    /**
     * 食品列表
     */
    private void setAdapter(){
        adapter = new CommonAdapter<OrderDetailBO.MerOrderBean.DetailsBean>(this,R.layout.orderdetail_prodect_item,prodectData) {
            @Override
            protected void convert(com.zhy.adapter.recyclerview.base.ViewHolder mholder, OrderDetailBO.MerOrderBean.DetailsBean detailsBean, int position) {
                if (detailsBean.getType() == 2){ // 2 套餐
                    mholder.setText(R.id.txtName,detailsBean.getItemCombo().getName());
                    mholder.setText(R.id.txtNumber,String.format("%s份",detailsBean.getNumber()));
                }else {
                    mholder.setText(R.id.txtName,detailsBean.getMerchandise().getName());
                    mholder.setText(R.id.txtNumber,String.format("%s份",detailsBean.getNumber()));
                }
            }
        };
        prodectList.setAdapter(adapter);
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

    @Override
    public void getOrderMessage(OrderDetailBO orderMessage) {
        stopProgress();
        this.orderDetailBO = orderMessage;
        setdata(orderMessage);
    }


    private void callPhone() {
        Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+orderDetailBO.getCinema().getContact()));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.call_phone:
                callPhone();
                break;
            case R.id.fenxiang_img:
//                logo = orderBO.getDxMovie().getPicture();
//                String content = orderBO.getDxMovie().getMovieName();
//                String url = orderMessage.getData().getShareUrl();
//                String title = "好友给你分享电影票";
//                ShareBO shareBO = new ShareBO(content,logo, url, title);
//                new ShareDialog(this, shareBO).showAtLocation(linear, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.tuipiao:
//                showCancleDialog();
                break;
                default:
                    break;
        }
    }
}
