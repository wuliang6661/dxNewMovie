package com.myp.cinema.ui.prodectorder;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.hedan.textdrawablelibrary.TextViewDrawable;
import com.myp.cinema.R;
import com.myp.cinema.base.MyApplication;
import com.myp.cinema.config.LocalConfiguration;
import com.myp.cinema.entity.CardBO;
import com.myp.cinema.entity.MerchandBO;
import com.myp.cinema.entity.PayBO;
import com.myp.cinema.entity.PayCardBO;
import com.myp.cinema.entity.PayResult;
import com.myp.cinema.entity.ProdectBO;
import com.myp.cinema.entity.ResuBo;
import com.myp.cinema.entity.SubmitPrdectOrderBO;
import com.myp.cinema.entity.WXPayBO;
import com.myp.cinema.mvp.MVPBaseActivity;
import com.myp.cinema.ui.membercard.AddCardActiivty;
import com.myp.cinema.ui.orderconfrim.OrderSurcessActivity;
import com.myp.cinema.ui.orderconfrim.SelectCoupon;
import com.myp.cinema.ui.pay.PayActivity;
import com.myp.cinema.ui.rechatge.rechatge;
import com.myp.cinema.util.LogUtils;
import com.myp.cinema.util.MD5;
import com.myp.cinema.util.SPUtils;
import com.myp.cinema.util.ScreenUtils;
import com.myp.cinema.util.StringUtils;
import com.myp.cinema.util.TimeUtils;
import com.myp.cinema.util.ToastUtils;
import com.myp.cinema.wxapi.WXUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by witness on 2018/11/19.
 *  确认小食订单
 */

public class ProdectOrderActivity extends MVPBaseActivity<ProdectOrderContract.View,
        ProdectOrderPresenter> implements ProdectOrderContract.View,
        AdapterView.OnItemClickListener,View.OnClickListener {

    @Bind(R.id.go_back)
    LinearLayout go_back;
    @Bind(R.id.submit_button)
    RelativeLayout submit_button;//确认订单
    @Bind(R.id.txtTotalPrice)
    TextView txtTotalPrice;//订单价格
    @Bind(R.id.prodectList)
    RecyclerView prodectList;//商品列表
    @Bind(R.id.tvPrice)
    TextView tvPrice;//小食总价
    @Bind(R.id.txtWayLine1)
    TextView txtWayLine1;//取餐方式第一行文字
    @Bind(R.id.txtWay)
    TextView txtWay;//取餐方式
    @Bind(R.id.txtPhone)
    TextView txtPhone;//手机号
    @Bind(R.id.selectProdectCoupon)
    RelativeLayout selectProdectCoupon;
    @Bind(R.id.txtProdectCouponNum)
    TextView txtProdectCouponNum;
    @Bind(R.id.rlUpdatePhone)
    RelativeLayout rlUpdatePhone;
    @Bind(R.id.rlComment)
    RelativeLayout rlComment;
    @Bind(R.id.commentContent)
    TextView commentContent;
    @Bind(R.id.decrease)
    TextView decrease;//满减活动金额
    @Bind(R.id.txtDecreaseInstruction)
    TextView txtDecreaseInstruction;//满减活动
    @Bind(R.id.txtDes)
    TextView txtDes;
    @Bind(R.id.txtDesNum)
    TextView txtDesNum;
    @Bind(R.id.rlDecreaseActivity)
    RelativeLayout rlDecreaseActivity;
    @Bind(R.id.txtPay)
    TextView txtPay;
    @Bind(R.id.textFinal)
    TextViewDrawable textFinal;
    @Bind(R.id.VIPPrice)
    TextView VIPPrice;//会员价
    @Bind(R.id.NormalPrice)
    TextView NormalPrice;//非会员价
    @Bind(R.id.cardPayBtn)
    RelativeLayout cardPayBtn;
    @Bind(R.id.cardPayImg)
    ImageView cardPayImg;
    @Bind(R.id.AlipayBtn)
    RelativeLayout AlipayBtn;
    @Bind(R.id.AlipayImg)
    ImageView AlipayImg;
    @Bind(R.id.WechatPayBtn)
    RelativeLayout WechatPayBtn;
    @Bind(R.id.WechatPayImg)
    ImageView WechatPayImg;


    private CommonAdapter<ProdectBO.DetailsBean> adapter;
    private List<ProdectBO.DetailsBean> data = new ArrayList<>();
    private ProdectBO prodectBO;
    private ArrayList<String> goodsId = new ArrayList<>();
    private SPUtils spUtils;
    private String merchandiseInfo="";
    private String phone = "";
    private String comment = "";
    private SubmitPrdectOrderBO submitPrdectOrderBO;
    private String merTicketId = "";

    private List<CardBO> cardListData = new ArrayList<>();//会员卡数据
    private String payType = "";//1 支付宝  2 微信  3 会员卡
    private String cardPrice;
    private String cardNumber;
    private PayCardBO payCardBO;    //会员卡支付信息

    @Override
    protected int getLayout() {
        return R.layout.prodect_order_layout;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("确认订单");
        spUtils = new SPUtils("ProdectItemNum");
        selectProdectCoupon.setOnClickListener(this);
        submit_button.setOnClickListener(this);
        rlUpdatePhone.setOnClickListener(this);
        rlComment.setOnClickListener(this);
        cardPayBtn.setOnClickListener(this);
        AlipayBtn.setOnClickListener(this);
        WechatPayBtn.setOnClickListener(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle !=null) {
            prodectBO = (ProdectBO) bundle.getSerializable("ProdectBO");
            goodsId = bundle.getStringArrayList("goodsId");
            setAdapter();
            data.addAll(prodectBO.getDetails());
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        prodectList.setLayoutManager(linearLayoutManager);
        tvPrice.setText(String.format("%s元",prodectBO.getDisPrice()));
//        txtTotalPrice.setText(String.format("%s",prodectBO.getDisPrice()));
        NormalPrice.setText(String.format("%s",prodectBO.getDisPrice()));
        VIPPrice.setText(String.format("%s",prodectBO.getVipDisPrice()));
        txtPhone.setText(String.valueOf(MyApplication.user.getMobile()));
        decrease.setText(String.format("-%s元",prodectBO.getTotalPrice()-prodectBO.getDisPrice()));
        if (prodectBO.getMerTicketList() != null
                && prodectBO.getMerTicketList().size()>0){
            selectProdectCoupon.setVisibility(View.VISIBLE);
        }else {
            selectProdectCoupon.setVisibility(View.GONE);
        }
        if (MyApplication.prodectWay == 0){
            txtWayLine1.setVisibility(View.VISIBLE);
            txtWay.setText("柜台自取");
        }else {
            txtWayLine1.setVisibility(View.GONE);
            txtWay.setText("送至影厅门口");
        }
        modifyPrice("");
        String content="下单即代表你同意《德信影城会员服务协议》";
        SpannableStringBuilder stringBuilder=new SpannableStringBuilder(content);
        ForegroundColorSpan foregroundColorSpan=new ForegroundColorSpan(Color.parseColor("#68AEEA"));
        stringBuilder.setSpan(foregroundColorSpan,8,stringBuilder.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textFinal.setText(stringBuilder);
    }

    private void modifyPrice(String id){
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
            mPresenter.getOrderPrice(MyApplication.cinemaBo.getCinemaId(),merchandiseInfo, id);
    }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    /**
     * 设置食品列表数据
     */
    private void setAdapter() {
        adapter = new CommonAdapter<ProdectBO.DetailsBean>(this,
                R.layout.item_classify_detail, data) {
            @Override
            protected void convert(ViewHolder holder, ProdectBO.DetailsBean item, int position) {
                holder.getView(R.id.ivAdd).setVisibility(View.GONE);
                ImageView ivAvatar = holder.getConvertView().findViewById(R.id.ivAvatar);
                TextView txtName = holder.getConvertView().findViewById(R.id.txtName);
                Glide.with(ProdectOrderActivity.this)
                        .load(item.getMerchandise().getImageUrl())
                        .asBitmap()
                        .centerCrop()
                        .dontAnimate()
                        .placeholder(R.drawable.zhanwei1)
                        .into((ImageView) holder.getView(R.id.ivAvatar));
                holder.setText(R.id.txtName,item.getMerchandise().getName());
                if (txtName.getLineCount() == 1){
                    txtName.setGravity(Gravity.START);
                }else {
                    txtName.setGravity(Gravity.START);
                }
                txtName.setTextSize(15);
                ViewGroup.LayoutParams params = ivAvatar.getLayoutParams();
                params.width = (int)(ScreenUtils.getScreenWidth() * 0.32);
                params.height = (int)(ScreenUtils.getScreenWidth() * 0.32);
                ivAvatar.setLayoutParams(params);

                TextView txtNoPrice = holder.getConvertView().findViewById(R.id.txtNoPrice);
                if (item.getMerchandise().getOriginalPrice() == 0.0) {
                    txtNoPrice.setVisibility(View.GONE);
                }else {
                    txtNoPrice.setVisibility(View.VISIBLE);
                    txtNoPrice.setText(String.format("￥ %s", item.getMerchandise().getOriginalPrice()));
                    txtNoPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                }
                holder.setText(R.id.txtPrice,String.format("￥ %s",item.getMerchandise().getOnlinePrice()));
                holder.getView(R.id.rlSelectP).setVisibility(View.GONE);
                holder.getView(R.id.rlNum).setVisibility(View.VISIBLE);
                holder.setText(R.id.num,item.getNumber()+"");
            }
        };
        prodectList.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.selectProdectCoupon:
                Bundle bundle2 = new Bundle();
                bundle2.putString("tag","prodectSingle");
                bundle2.putSerializable("prodectBO", prodectBO);
                Intent intent2 = new Intent(this,SelectCoupon.class);
                intent2.putExtras(bundle2);
                startActivityForResult(intent2,1);
                break;
            case R.id.submit_button:
                submitOrder();
                break;
            case R.id.rlUpdatePhone:
                showSelectDialog("请输入手机号");
                break;
            case R.id.rlComment:
                showSelectDialog("请输入备注信息");
                break;
            case R.id.cardPayBtn:
                showProgress("");
                mPresenter.loadCardUser();
                break;
            case R.id.AlipayBtn:
                payType = "1";
                cardPayImg.setImageDrawable(getResources().getDrawable(R.mipmap.pay_normal));
                AlipayImg.setImageDrawable(getResources().getDrawable(R.mipmap.pay_selected));
                WechatPayImg.setImageDrawable(getResources().getDrawable(R.mipmap.pay_normal));
                break;
            case R.id.WechatPayBtn:
                payType = "2";
                cardPayImg.setImageDrawable(getResources().getDrawable(R.mipmap.pay_normal));
                AlipayImg.setImageDrawable(getResources().getDrawable(R.mipmap.pay_normal));
                WechatPayImg.setImageDrawable(getResources().getDrawable(R.mipmap.pay_selected));
                break;
                default:
                    break;
        }
    }

    private void submitOrder(){
        LayoutInflater factory = LayoutInflater.from(this);//提示框
        final View view = factory.inflate(R.layout.dialog_order_pay, null);//这里必须是final的
        TextView cancle = (TextView) view.findViewById(R.id.off_commit);
        TextView commit = (TextView) view.findViewById(R.id.commit);
        TextView title = view.findViewById(R.id.title);
        TextView message = view.findViewById(R.id.message);
        title.setText("提示");
        if (MyApplication.cinemaBo != null) {
            message.setText(String.format("您购买的是【%s】的卖品，请确认！", MyApplication.cinemaBo.getCinemaName()));
        }
        cancle.setText("取消");
        commit.setText("确定");
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
                showProgress("提交订单中...");
                String myPhone;
                if (phone.equals("")){
                    myPhone = MyApplication.user.getMobile();
                }else {
                    myPhone = phone;
                }
                mPresenter.submitOrder(MyApplication.user.getId(),merchandiseInfo,
                        myPhone,comment,MyApplication.cinemaBo.getCinemaNumber(),merTicketId);
            }
        });
        dialog.setView(view);
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                int disprice = 0;
                int id = 0;
                try {
                    disprice = data.getExtras().getInt("discount");
                    id = data.getExtras().getInt("id");
                    txtProdectCouponNum.setText(String.format("-%s元",disprice));
                }catch (Exception e){
                    e.printStackTrace();
//                    txtProdectCouponNum.setText("-0元");
                }
                if (id > 0) {
                    modifyPrice(String.valueOf(id));
                    merTicketId = String.valueOf(id);
                }else {//不选择优惠券
                    modifyPrice("0");
                    merTicketId = "0";
                }
                break;
            case 2:
                showProgress("");
                mPresenter.loadCardUser();
                break;
                default:
                    break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onRequestError(String msg) {
        if(msg.equals("会员卡余额不足")){
            showDialog2(msg);
        }else {
            LogUtils.showToast(msg);
        }
        stopProgress();
    }

    @Override
    public void onRequestEnd() {
        stopProgress();
    }

    @Override
    public void getOrder(ProdectBO prodectBO) {
        this.prodectBO = prodectBO;
        if (prodectBO.getMerTicket() != null) {
            txtProdectCouponNum.setText(String.format("-%s元", prodectBO.getMerTicket().getDxPlatTicket().getAmount()));
            merTicketId = String.valueOf(prodectBO.getMerTicket().getId());
        }else {
            merTicketId = "";
        }
        tvPrice.setText(String.format("%s元",prodectBO.getDisPrice()));
//        txtTotalPrice.setText(String.format("%s元",prodectBO.getDisPrice()));
        NormalPrice.setText(String.format("%s元",prodectBO.getDisPrice()));
        VIPPrice.setText(String.format("%s元",prodectBO.getVipDisPrice()));
        if (prodectBO.getDisPrice() == 0){
            txtPay.setText("立即0元支付");
        }else {
            txtPay.setText("提交订单");
        }
    }

    @Override
    public void getSubmitOrderResult(SubmitPrdectOrderBO submitPrdectOrderBO) {
        this.submitPrdectOrderBO = submitPrdectOrderBO;
        switch (payType){
            case "1"://支付宝
                showProgress("加载中...");
                mPresenter.loadAliPay(submitPrdectOrderBO.getOrderNum());
                break;
            case "2"://微信
                showProgress("加载中...");
                mPresenter.loadWeChatPay(submitPrdectOrderBO. getOrderNum());
                break;
            case "3"://会员卡支付
                showProgress("");
                mPresenter.loadCardPay(submitPrdectOrderBO.getOrderNum(),cardNumber);
                break;
            default:
                break;
        }
//        Bundle bundle = new Bundle();
//        bundle.putInt("confrim", 2);
//        bundle.putSerializable("order", submitPrdectOrderBO);
//        gotoActivity(PayActivity.class, bundle, true);
    }

    @Override
    public void getAliPay(PayBO orderInfo) {
        payAliPay(orderInfo.getAlipay());
    }

    @Override
    public void getWxPay(WXPayBO wxPayBO) {
        LocalConfiguration.ordertype = 1;
        LocalConfiguration.orderNum = submitPrdectOrderBO.getOrderNum();
        WXUtils.payWX(wxPayBO);
    }

    @Override
    public void getcardPay(ResuBo resuBo) {
        Bundle bundle = new Bundle();
        bundle.putString("order", submitPrdectOrderBO.getOrderNum());
        gotoActivity(OrderSurcessActivity.class, bundle, false);
    }

    @Override
    public void getCardList(List<CardBO> cardBOs) {
        stopProgress();
        if (cardBOs.size() > 0) {
            cardListData.clear();
            cardListData.addAll(cardBOs);
            chooseCard();
        } else {
            openCardDialog();
        }
    }

    @Override
    public void getCardPrice(PayCardBO payCardBO) {
        this.payCardBO = payCardBO;
        showProgress("");
        showPwdDialog();
    }

    @Override
    public void getPayCard(ResuBo pay) {
        if(pay.getResultX()==1){
            LogUtils.showToast("支付成功");
            Intent intent = new Intent(ProdectOrderActivity.this,ProdectOrderSuccess.class);
            Bundle bundle = new Bundle();
            bundle.putString("order", submitPrdectOrderBO.getOrderNum());
            intent.putExtras(bundle);     //将bundle传入intent中。
            startActivity(intent);

        }
    }

    /**
     * 显示会员卡支付密码弹窗
     */
    private void showPwdDialog() {
        stopProgress();
        LayoutInflater factory = LayoutInflater.from(this);//提示框
        View dia = factory.inflate(R.layout.dialog_card_pay, null);//这里必须是final的
        Button cancle = (Button) dia.findViewById(R.id.pay_cancle);
        Button commit = (Button) dia.findViewById(R.id.pay_yes);
        TextView price = (TextView) dia.findViewById(R.id.order_price);
        TextView poundagePrice = (TextView) dia.findViewById(R.id.poundage_price);
        final EditText passWord = (EditText) dia.findViewById(R.id.edit_password);
        price.setText(String.format("¥ %s",payCardBO.getTotalPrice()));
        poundagePrice.setText(String.format("¥ %s",payCardBO.getTotalFee()));
        final AlertDialog dialog = new AlertDialog.Builder(this, R.style.AlertDialog).create();
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pwd = passWord.getText().toString().trim();
                if (StringUtils.isEmpty(pwd)) {
                    LogUtils.showToast("请输入密码！");
                } else {
                    dialog.dismiss();
                    showProgress("付款中...");
                    mPresenter.payCard(submitPrdectOrderBO.getOrderNum(), MD5.strToMd5Low32(pwd), cardNumber);
                }
            }
        });
        dialog.setView(dia);
        dialog.show();
    }

    private void showDialog2(String getMessage) {
        LayoutInflater factory = LayoutInflater.from(this);//提示框
        View dia = factory.inflate(R.layout.dialog2_card_pay, null);//这里必须是final的
        TextView cancle = (TextView) dia.findViewById(R.id.pay_cancle);
        TextView commit = (TextView) dia.findViewById(R.id.pay_yes);
        TextView texttt = (TextView) dia.findViewById(R.id.texttt);
        final AlertDialog dialog = new AlertDialog.Builder(this, R.style.AlertDialog).create();
        texttt.setText(getMessage);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rechatge = new Intent(ProdectOrderActivity.this, com.myp.cinema.ui.rechatge.rechatge.class);
                LocalConfiguration.isShouye = "1";
                rechatge.putExtra("cardPrice", cardPrice);
                rechatge.putExtra("cardcode",  cardNumber);
                startActivity(rechatge);
            }
        });
        dialog.setView(dia);
        dialog.show();
    }

    /**
     * 备注
     */
    private void showSelectDialog(final String text) {
        // 构造对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.AlertDialog);
        final LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.exchange_coupon_dialog, null);
        final RelativeLayout rlClose = (RelativeLayout) v.findViewById(R.id.rlClose);
        final EditText etEnter = (EditText) v.findViewById(R.id.etEnter);
        final RelativeLayout rlSubmit = (RelativeLayout) v.findViewById(R.id.rlSubmit);
        final TextView txtCo = v.findViewById(R.id.txtCo);
        txtCo.setVisibility(View.GONE);
        etEnter.setHint(text);
        etEnter.setHintTextColor(Color.parseColor("#878787"));
        builder.setView(v);
        builder.setCancelable(true);
        final Dialog noticeDialog = builder.create();
        noticeDialog.getWindow().setGravity(Gravity.BOTTOM);
        noticeDialog.setCancelable(false);
        noticeDialog.show();
        rlClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //隐藏软键盘
                InputMethodManager imm= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if(imm.isActive()){
                    imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                }
                noticeDialog.dismiss();
            }
        });
        rlSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText  = etEnter.getText().toString();
                if (text.equals("请输入手机号")){
                    if (!TextUtils.isEmpty(inputText)) {
                        txtPhone.setText(inputText);
                        phone = inputText;
                        //隐藏软键盘
                        InputMethodManager imm= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        if(imm.isActive()){
                            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                        }
                        noticeDialog.dismiss();
                    }else {
                        ToastUtils.showShortToast("你还没输入新的手机号呢...");
                    }
                }else {
                    if (!TextUtils.isEmpty(inputText)) {
                        commentContent.setText(inputText);
                        comment = inputText;
                        //隐藏软键盘
                        InputMethodManager imm= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        if(imm.isActive()){
                            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                        }
                        noticeDialog.dismiss();
                    }else {
                        ToastUtils.showShortToast("你还没输入备注呢...");
                    }
                }
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
    }

    /**
     * 支付宝调起操作
     */
    private void payAliPay(final String orderInfo) {
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(ProdectOrderActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);

                Message msg = new Message();
                msg.what = 0x11;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * 支付宝返回结果处理
     * <p>
     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
     */
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            PayResult payResult = new PayResult((Map<String, String>) msg.obj);
            String resultStatus = payResult.getResultStatus();
            // 判断resultStatus 为9000则代表支付成功
            if (TextUtils.equals(resultStatus, "9000")) {
                Bundle bundle = new Bundle();
                bundle.putString("order", submitPrdectOrderBO.getOrderNum());
                gotoActivity(OrderSurcessActivity.class, bundle, false);
            } else {
                LogUtils.showToast("支付失败");
            }
        }
    };


    /**
     * 选择会员卡
     */
    private void chooseCard(){
        LayoutInflater factory = LayoutInflater.from(this);//提示框
        final View view = factory.inflate(R.layout.choose_card_item_layout, null);//这里必须是final的
        Button notUseBtn = (Button) view.findViewById(R.id.notUseBtn);
        Button submitBtn = (Button) view.findViewById(R.id.submitBtn);
        RecyclerView cardList = view.findViewById(R.id.cardList);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        cardList.setLayoutManager(manager);
        CommonAdapter<CardBO> adapter = new CommonAdapter<CardBO>(this,R.layout.choosecard_item_layout,cardListData) {
            @Override
            protected void convert(ViewHolder holder, final CardBO item, int position) {
                holder.setText(R.id.cardName,item.getCardLevel());
                holder.setText(R.id.cardNum,String.format("余额￥%s",item.getBalance()));
                ImageView selectImg = holder.getConvertView().findViewById(R.id.selectImg);
                if (item.getCardNumber().equals(cardNumber)){
                    selectImg.setImageDrawable(getResources().getDrawable(R.mipmap.pay_selected));
                }else {
                    selectImg.setImageDrawable(getResources().getDrawable(R.mipmap.pay_normal));
                }
                holder.getView(R.id.button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cardNumber = item.getCardNumber();
                        notifyDataSetChanged();
                    }
                });
            }
        };
        cardList.setAdapter(adapter);

        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        notUseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardNumber = "";
                dialog.dismiss();
            }
        });
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //事件
                payType = "3";
                cardPayImg.setImageDrawable(getResources().getDrawable(R.mipmap.pay_selected));
                AlipayImg.setImageDrawable(getResources().getDrawable(R.mipmap.pay_normal));
                WechatPayImg.setImageDrawable(getResources().getDrawable(R.mipmap.pay_normal));
                dialog.dismiss();

            }
        });
        dialog.setView(view);
        dialog.show();
    }

    /**
     * 开卡
     */
    private void openCardDialog(){
        LayoutInflater factory = LayoutInflater.from(this);//提示框
        final View view = factory.inflate(R.layout.dialog_order_pay, null);//这里必须是final的
        TextView cancle = (TextView) view.findViewById(R.id.off_commit);
        TextView commit = (TextView) view.findViewById(R.id.commit);
        TextView title = view.findViewById(R.id.title);
        TextView message = view.findViewById(R.id.message);
        title.setText("暂无会员卡，是否去开通？");
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
                Intent intent = new Intent(ProdectOrderActivity.this, AddCardActiivty.class);
                startActivityForResult(intent, 2);
                dialog.dismiss();

            }
        });
        dialog.setView(view);
        dialog.show();
    }
}
