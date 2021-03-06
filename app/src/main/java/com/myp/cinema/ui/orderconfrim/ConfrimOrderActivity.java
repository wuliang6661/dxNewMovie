package com.myp.cinema.ui.orderconfrim;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alipay.sdk.app.PayTask;
import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.hedan.textdrawablelibrary.TextViewDrawable;
import com.myp.cinema.R;
import com.myp.cinema.base.MyApplication;
import com.myp.cinema.config.LocalConfiguration;
import com.myp.cinema.entity.CardBO;
import com.myp.cinema.entity.ConfirmPayBO;
import com.myp.cinema.entity.CouponDataBean;
import com.myp.cinema.entity.LockSeatsBO;
import com.myp.cinema.entity.MerchandBO;
import com.myp.cinema.entity.MoviesByCidBO;
import com.myp.cinema.entity.MoviesSessionBO;
import com.myp.cinema.entity.OrderNumBO;
import com.myp.cinema.entity.PayBO;
import com.myp.cinema.entity.PayCardBO;
import com.myp.cinema.entity.PayResult;
import com.myp.cinema.entity.ResuBo;
import com.myp.cinema.entity.WXPayBO;
import com.myp.cinema.mvp.MVPBaseActivity;
import com.myp.cinema.ui.membercard.AddCardActiivty;
import com.myp.cinema.util.AppManager;
import com.myp.cinema.util.LogUtils;
import com.myp.cinema.util.MD5;
import com.myp.cinema.util.MathExtend;
import com.myp.cinema.util.RegexUtils;
import com.myp.cinema.util.SPUtils;
import com.myp.cinema.util.StringUtils;
import com.myp.cinema.util.TimeConstants;
import com.myp.cinema.util.TimeUtils;
import com.myp.cinema.util.ToastUtils;
import com.myp.cinema.wxapi.WXUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.ResponseBody;


/**
 * ??????????????????
 */

public class ConfrimOrderActivity extends MVPBaseActivity<ConfrimOrderContract.View,
        ConfrimOrderPresenter> implements ConfrimOrderContract.View, View.OnClickListener {


    @Bind(R.id.movies_address)
    TextView orderAddress;
    @Bind(R.id.movies_time)
    TextView orderTime;
    @Bind(R.id.movies_seat)
    TextView seatText;
    @Bind(R.id.order_price)
    TextView orderPrice;
    @Bind(R.id.decreasePrice)
    TextView decreasePrice;//??????????????????
    @Bind(R.id.realPrice)
    TextView realPrice;//??????????????????
    @Bind(R.id.submit_button)
    RelativeLayout submitButton;
    @Bind(R.id.movies_num)
    TextView moviesnum;
    @Bind(R.id.movies_name)
    TextView moviesName;
    @Bind(R.id.selectCoupon)
    RelativeLayout selectCoupon;//???????????????
    @Bind(R.id.txtCouponNum)
    TextView txtCouponNum;//????????????
    @Bind(R.id.prodectList)
    RecyclerView prodectList;//????????????
    @Bind(R.id.selectProdectCoupon)
    RelativeLayout selectProdectCoupon;//?????????????????????
    @Bind(R.id.txtProdectCouponNum)
    TextView txtProdectCouponNum;//?????????????????????
    @Bind(R.id.ProdectPrice)
    TextView ProdectPrice;//????????????
    @Bind(R.id.phoneNumber)
    TextView phoneNumber;
    @Bind(R.id.rlUpdatePhone)
    RelativeLayout rlUpdatePhone;
    @Bind(R.id.rlComment)
    RelativeLayout rlComment;
    @Bind(R.id.tComment)
    TextView tComment;
    @Bind(R.id.rlProdect)
    RelativeLayout rlProdect;
    @Bind(R.id.rlProdectPrice)
    RelativeLayout rlProdectPrice;
    @Bind(R.id.view1)
    View view1;
    @Bind(R.id.textFinal)
    TextViewDrawable textFinal;
    @Bind(R.id.cardPayImg)
    ImageView cardPayImg;
    @Bind(R.id.cardPayBtn)
    RelativeLayout cardPayBtn;
    @Bind(R.id.AlipayImg)
    ImageView AlipayImg;
    @Bind(R.id.AlipayBtn)
    RelativeLayout AlipayBtn;
    @Bind(R.id.WechatPayImg)
    ImageView WechatPayImg;
    @Bind(R.id.WechatPayBtn)
    RelativeLayout WechatPayBtn;
    @Bind(R.id.exchangeCouponImg)
    ImageView exchangeCouponImg;
    @Bind(R.id.exchangeCouponBtn)
    RelativeLayout exchangeCouponBtn;
    @Bind(R.id.addCouponList)
    RecyclerView addCouponList;
    @Bind(R.id.addCouponLayout)
    LinearLayout addCouponLayout;
    @Bind(R.id.count_down_time)
    TextView countDownTime;
    @Bind(R.id.decreaseLayout)
    LinearLayout decreaseLayout;

    private MoviesByCidBO movies;
    private MoviesSessionBO sessionBO;
    private int ticketNum;
    private String seatId;
    private String seats;
    private int size;
    private double zongjia;
    private int preferentialnumber;

    private LockSeatsBO lockSeatsBO;
    private LockSeatsBO modifyInfo;

    private CommonAdapter<LockSeatsBO.ComboListBean> adapter;
    private List<LockSeatsBO.ComboListBean> data = new ArrayList<>();
    private SPUtils spUtils;
    private String merchandiseInfo = "";//?????????????????????

    private int ticketCouponId = 0;//??????????????????id
    private int prodectCouponId = 0;//???????????????id

    private int mTicketCouponId = 0;//????????????????????????

    private String newPhoneNumber = "";
    private String comment = "";//??????
    private double uploadTotalPrice;//????????????????????????
    private boolean isSuccess = true;//??????????????????

    private String activityPriceNum;

    private CommonAdapter<CouponDataBean> addCouponAdapter;//???????????????
    private List<CouponDataBean> addCouponData = new ArrayList<>();
    private boolean isOpen = false;//?????????????????????

    private List<CardBO> cardListData = new ArrayList<>();//???????????????
    private String payType = "1";//0 ????????? 1 ?????????  2 ??????

    private PayCardBO payCardBO;    //????????????
    private String cardPrice;
    private String cardNumber;//?????????????????????
    private CountDownTimer timer;
    private ConfirmPayBO orderBO;
    private boolean orderExist = true;//????????????????????????????????????????????????????????????????????????????????????????????????????????????
    private String cardType = "";//1 ??????  2 ??????


    @Override
    protected int getLayout() {
        return R.layout.act_confrim_order;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("????????????");
        spUtils = new SPUtils("ConfirmOrder");
        getBundlen();
        inittion();
        submitButton.setOnClickListener(this);
        selectCoupon.setOnClickListener(this);
        selectProdectCoupon.setOnClickListener(this);
        rlUpdatePhone.setOnClickListener(this);
        rlComment.setOnClickListener(this);
        String content = "????????????????????????????????????????????????????????????";
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(content);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#68AEEA"));
        stringBuilder.setSpan(foregroundColorSpan, 8, stringBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textFinal.setText(stringBuilder);
        //?????????????????????
        cardPayImg.setImageDrawable(getResources().getDrawable(R.mipmap.pay_normal));
        AlipayImg.setImageDrawable(getResources().getDrawable(R.mipmap.pay_selected));
        WechatPayImg.setImageDrawable(getResources().getDrawable(R.mipmap.pay_normal));
        addCouponLayout.setVisibility(View.GONE);
        payType = "1";
        //?????????????????????
        startTimeDown(TimeUtils.getTimeSpan(TimeUtils.millis2String(
                lockSeatsBO.getOrderExpireSecond() * 1000, "yyyy-MM-dd HH:mm:ss"),
                TimeUtils.millis2String(TimeUtils.getNowTimeMills()),
                TimeConstants.MSEC));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
        spUtils.clear();
    }


    /**
     * ?????????????????????
     */
    private void startTimeDown(long downTime) {
        timer = new CountDownTimer(downTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                countDownTime.setText(TimeUtils.millis2String(millisUntilFinished, "mm:ss"));
            }

            @Override
            public void onFinish() {
                new AlertView("??????", "??????????????????", null, new String[]{"??????"}, null, ConfrimOrderActivity.this, AlertView.Style.Alert, new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, int position) {
                        AppManager.getAppManager().goBackMain();
                    }
                }).show();
                AppManager.getAppManager().goBackMain();
            }
        };
        timer.start();
    }

    /**
     * ???????????????????????????
     */
    private void getBundlen() {
        Bundle bundle = getIntent().getExtras();
        movies = (MoviesByCidBO) bundle.getSerializable("movies");
        sessionBO = (MoviesSessionBO) bundle.getSerializable("MoviesSession");
        lockSeatsBO = (LockSeatsBO) bundle.getSerializable("LockSeatsBO");
        if (lockSeatsBO != null && lockSeatsBO.getSeatTicket() != null) {
            MyApplication.selectedId = lockSeatsBO.getSeatTicket().getId();
        }
        modifyInfo = lockSeatsBO;
        ticketNum = bundle.getInt("num", 0);
        seatId = bundle.getString("seatId", "");
        seats = bundle.getString("seats", "");
        preferentialnumber = bundle.getInt("preferentialnumber", 0);
        uploadTotalPrice = bundle.getDouble("uploadTotalPrice");
        mTicketCouponId = bundle.getInt("ticketCouponId");
        ticketCouponId = mTicketCouponId;
        activityPriceNum = lockSeatsBO.getActivityPriceNum();
    }


    /**
     * ?????????????????????
     */
    private void inittion() {
        moviesName.setText(movies.getMovieName());
        orderAddress.setText(MyApplication.cinemaBo.getCinemaName());
        orderTime.setText(sessionBO.getStartTime());
        String[] seat = seats.split(",");
        size = seat.length;
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < seat.length; i++) {
            buffer.append(seat[i].split("_")[0] + "???" + seat[i].split("_")[1] + "??? ");
        }
        seatText.setText(buffer.toString());
        seatText.setText(String.format("%s %s", sessionBO.getHallName(), buffer.toString()));
        moviesnum.setText(String.format("%s???", String.valueOf(ticketNum)));
        if (payType.equals("0")){//?????????
            orderPrice.setText(String.format("%s", modifyInfo.getVipPayPrice()));
        }else {
            orderPrice.setText(String.format("%s", modifyInfo.getTotalDisprice()));
        }
        if (sessionBO.getGlobalLeftNum() == null) {
            double zong = (ticketNum) * Double.parseDouble(sessionBO.getMarketPrice());
            BigDecimal bd = new BigDecimal(zong);
            zongjia = bd.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        } else {
            if (sessionBO.getPartnerPrice() == null) {
                double zong = (ticketNum) * Double.parseDouble(sessionBO.getMarketPrice());
                BigDecimal bd = new BigDecimal(zong);
                zongjia = bd.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
            } else {
                if (sessionBO.getLeftScreenLimitNum() < sessionBO.getGlobalLeftNum()) {
                    if (ticketNum > sessionBO.getLeftScreenLimitNum()) {
                        double youhuijia = sessionBO.getLeftScreenLimitNum() * Double.parseDouble(sessionBO.getPartnerPrice());
                        double noyouhui = (ticketNum - sessionBO.getLeftScreenLimitNum()) * Double.parseDouble(sessionBO.getMarketPrice());
                        double zong = youhuijia + noyouhui;
                        BigDecimal bd = new BigDecimal(zong);
                        zongjia = bd.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                    } else {
                        double zong = (ticketNum) * Double.parseDouble(sessionBO.getPartnerPrice());
                        BigDecimal bd = new BigDecimal(zong);
                        zongjia = bd.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                    }
                } else {
                    if (ticketNum > sessionBO.getGlobalLeftNum()) {
                        double youhuijia = sessionBO.getGlobalLeftNum() * Double.parseDouble(sessionBO.getPartnerPrice());
                        double noyouhui = (ticketNum - sessionBO.getGlobalLeftNum()) * Double.parseDouble(sessionBO.getMarketPrice());
                        double zong = youhuijia + noyouhui;
                        BigDecimal bd = new BigDecimal(zong);
                        zongjia = bd.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                    } else {
                        double zong = (ticketNum) * Double.parseDouble(sessionBO.getPartnerPrice());
                        BigDecimal bd = new BigDecimal(zong);
                        zongjia = bd.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                    }
                }


            }
        }

        phoneNumber.setText(String.format("%s", MyApplication.user.getMobile()));
        if (payType.equals("0")){//?????????
            realPrice.setText(String.format("%s", modifyInfo.getVipPayPrice()));
            if (Double.parseDouble(MathExtend.subtract(modifyInfo.getBeforeTicketPrice(),
                    modifyInfo.getVipPayPrice())) == 0){
                decreasePrice.setText(String.format("-%s???",MathExtend.subtract(modifyInfo.getVipBeforeTicketPrice(),
                        modifyInfo.getVipTicketPayPrice())));
            }else {
                decreasePrice.setText(String.format("-%s???", MathExtend.subtract(modifyInfo.getVipBeforeTicketPrice(),
                        modifyInfo.getVipTicketPayPrice())));
            }
            if (Double.parseDouble(MathExtend.subtract(modifyInfo.getVipBeforeTicketPrice(),
                    modifyInfo.getVipTicketPayPrice())) == 0) {
                txtCouponNum.setText("?????????");
            } else {
                txtCouponNum.setText(String.format("-%s???", MathExtend.subtract(modifyInfo.getVipBeforeTicketPrice(),
                        modifyInfo.getVipTicketPayPrice())));
            }
        }else {
            realPrice.setText(String.format("%s", modifyInfo.getPayPrice()));
            if (Double.parseDouble(MathExtend.subtract(modifyInfo.getBeforeTicketPrice(),
                    modifyInfo.getPayPrice())) == 0){
                decreasePrice.setText(String.format("-%s???",MathExtend.subtract(modifyInfo.getBeforeTicketPrice(),
                        modifyInfo.getTotalDisprice())));
            }else {
                decreasePrice.setText(String.format("-%s???", MathExtend.subtract(modifyInfo.getBeforeTicketPrice(),
                        modifyInfo.getTotalDisprice())));
            }
            if (Double.parseDouble(MathExtend.subtract(modifyInfo.getBeforeTicketPrice(),
                    modifyInfo.getTotalDisprice())) == 0) {
                txtCouponNum.setText("?????????");
            } else {
                txtCouponNum.setText(String.format("-%s???", MathExtend.subtract(modifyInfo.getBeforeTicketPrice(),
                        modifyInfo.getTotalDisprice())));
            }
        }
        if (lockSeatsBO.getSeatTicketList() != null && lockSeatsBO.getSeatTicketList().size() > 0) {
            selectCoupon.setVisibility(View.VISIBLE);
        } else {
            selectCoupon.setVisibility(View.GONE);
        }
        if (modifyInfo.getMerOrder() != null
                && modifyInfo.getMerOrder().getMerTicketList().size() > 0) {
            selectProdectCoupon.setVisibility(View.GONE);
        } else {
            selectProdectCoupon.setVisibility(View.GONE);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        prodectList.setLayoutManager(layoutManager);
        data.clear();
        data.addAll(modifyInfo.getComboList());
        if (data.size() > 0) {
            prodectList.setVisibility(View.VISIBLE);
            rlProdect.setVisibility(View.VISIBLE);
            rlProdectPrice.setVisibility(View.VISIBLE);
            view1.setVisibility(View.VISIBLE);
            setAdapter();
        } else {
            prodectList.setVisibility(View.GONE);
            rlProdect.setVisibility(View.GONE);
            rlProdectPrice.setVisibility(View.GONE);
            view1.setVisibility(View.GONE);
        }

        LinearLayoutManager manager = new LinearLayoutManager(this);
        addCouponList.setLayoutManager(manager);
        addCouponList.setItemAnimator(new DefaultItemAnimator());
        addCouponList.setHasFixedSize(false);
        addCouponList.setNestedScrollingEnabled(false);
        setAddCouponAdapter();

    }

    /**
     * ???????????????????????????
     */
    TextWatcher mTextWatcher;
    private void setAddCouponAdapter(){
        addCouponAdapter = new CommonAdapter<CouponDataBean>(this,R.layout.add_coupon_item_layout,addCouponData) {
            @Override
            protected void convert(ViewHolder holder, final CouponDataBean item, final int position) {
                Button button = holder.getConvertView().findViewById(R.id.button);
                final EditText couponNum = holder.getConvertView().findViewById(R.id.couponNum);
                if (position == addCouponData.size() - 1){
                    button.setText("+ ??????");
                }else {
                    button.setText("- ??????");
                }
                holder.getView(R.id.button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (position == addCouponData.size() - 1){
                            if (ticketNum <= addCouponData.size()){
                                ToastUtils.showShortToast("???????????????????????????????????????");
                                return;
                            }
                            addCouponData.add(new CouponDataBean(position+1,""));
                            notifyItemInserted(addCouponData.size());
                        }else {
                            addCouponData.remove(position);
                            notifyItemRemoved(position);
                        }
                        notifyItemRangeChanged(0, addCouponData.size());
                        //???????????????
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        if (imm.isActive()) {
                            imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
                        }
                    }
                });
                //?????????????????????
                couponNum.clearFocus();
                couponNum.setTag(position);
                if (mTextWatcher !=null){
                    couponNum.removeTextChangedListener(mTextWatcher);
                }
                couponNum.setText(item.getNumber());
                mTextWatcher = new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        //?????????
                        int posTag = (int) couponNum.getTag();
                        addCouponData.get(posTag).setNumber(editable.toString().trim());
                    }
                };
                couponNum.addTextChangedListener(mTextWatcher);
            }
        };
        addCouponList.setAdapter(addCouponAdapter);
    }



    /**
     * ????????????
     */
    private int goodsNum = 0;
    private List<String> goodsId = new ArrayList<>();

    private void setAdapter() {
        adapter = new CommonAdapter<LockSeatsBO.ComboListBean>(this, R.layout.item_classify_detail, data) {
            @Override
            protected void convert(final ViewHolder holder, LockSeatsBO.ComboListBean merchandiseListBean, final int position) {
                holder.setText(R.id.txtName, data.get(position).getName());
                Glide.with(ConfrimOrderActivity.this)
                        .load(data.get(position).getImageUrl())
                        .asBitmap()
                        .centerCrop()
                        .dontAnimate()
                        .placeholder(R.drawable.zhanwei1)
                        .into((ImageView) holder.getView(R.id.ivAvatar));
                holder.setText(R.id.txtNoPrice, String.format("??? %s", data.get(position).getOriginalPrice()));
                holder.getView(R.id.line).setVisibility(View.VISIBLE);
                holder.setText(R.id.txtPrice, String.format("??? %s", data.get(position).getSalePrice()));

                if (goodsId.contains(String.valueOf(data.get(position).getId())) &&
                        spUtils.getInt(String.valueOf(data.get(position).getId())) == 0) {
                    goodsId.remove(String.valueOf(data.get(position).getId()));
                }
                if (spUtils.getInt(String.valueOf(data.get(position).getId())) > 0) {
                    holder.getView(R.id.rlSelect).setVisibility(View.VISIBLE);
                    holder.setText(R.id.txtNum, String.format("%s", spUtils.getInt(String.valueOf(data.get(position).getId()))));
                    int num2;
                    Double totalSinglePrice;
                    Double singlePrice = 0.0;
                    Double totlePrice = 0.0;
                    for (int i = 0; i < goodsId.size(); i++) {
                        num2 = spUtils.getInt(goodsId.get(i));
                        for (int j = 0; j < data.size(); j++) {
                            if (goodsId.get(i).equals(String.valueOf(data.get(j).getId()))) {
                                singlePrice = Double.valueOf(data.get(j).getSalePrice());
                            }
                        }
                        totalSinglePrice = new BigDecimal(MathExtend.multiply(singlePrice, Double.valueOf(String.valueOf(num2)))).doubleValue();
                        totlePrice = MathExtend.add(totlePrice, totalSinglePrice);
                    }
                } else {
                    holder.getView(R.id.rlSelect).setVisibility(View.GONE);
                    if (goodsId.size() == 0) {
                        ProdectPrice.setText(String.format("%s", 0));
                        if (payType.equals("0")){//?????????
                            realPrice.setText(String.format("%s", modifyInfo.getVipPayPrice()));
                            if (modifyInfo.getMerOrder() != null){
                                orderPrice.setText(String.format("%s", MathExtend.subtract(modifyInfo.getVipPayPrice(),modifyInfo.getMerOrder().getVipDisPrice())));
                            }else {
                                orderPrice.setText(String.format("%s",modifyInfo.getVipPayPrice()));
                            }
                            if (Double.parseDouble(MathExtend.subtract(modifyInfo.getVipBeforeTicketPrice(),
                                    modifyInfo.getVipTicketPayPrice())) == 0){
                                if (modifyInfo.getMerOrder() != null) {
                                    if (modifyInfo.getMerOrder().getMerTicket() != null) {
                                        decreasePrice.setText(String.format("-%s???",MathExtend.add(MathExtend.subtract(modifyInfo.getVipBeforeTicketPrice(),
                                                modifyInfo.getVipTicketPayPrice()),String.valueOf(modifyInfo.getMerOrder().getMerTicket().getDxPlatTicket().getAmount()))));
                                    }
                                } else {
                                    decreasePrice.setText(String.format("-%s???",MathExtend.subtract(modifyInfo.getVipBeforeTicketPrice(),
                                            modifyInfo.getVipTicketPayPrice())));
                                }
                            }else {
                                if (modifyInfo.getMerOrder() != null) {
                                    if (modifyInfo.getMerOrder().getMerTicket() != null) {
                                        decreasePrice.setText(String.format("-%s???",MathExtend.add(MathExtend.subtract(modifyInfo.getVipBeforeTicketPrice(),
                                                modifyInfo.getVipTicketPayPrice()),String.valueOf(modifyInfo.getMerOrder().getMerTicket().getDxPlatTicket().getAmount()))));
                                    }
                                } else {
                                    decreasePrice.setText(String.format("-%s???",MathExtend.subtract(modifyInfo.getVipBeforeTicketPrice(),
                                            modifyInfo.getVipTicketPayPrice())));
                                }
                            }
                        }else {
                            realPrice.setText(String.format("%s", modifyInfo.getPayPrice()));
                            if (modifyInfo.getMerOrder() != null){
                                orderPrice.setText(String.format("%s", MathExtend.subtract(modifyInfo.getPayPrice(),modifyInfo.getMerOrder().getDisPrice())));
                            }else {
                                orderPrice.setText(String.format("%s",modifyInfo.getPayPrice()));
                            }
                            if (Double.parseDouble(MathExtend.subtract(modifyInfo.getBeforeTicketPrice(),
                                    modifyInfo.getTotalDisprice())) == 0){
                                if (modifyInfo.getMerOrder() != null) {
                                    if (modifyInfo.getMerOrder().getMerTicket() != null) {
                                        decreasePrice.setText(String.format("-%s???",MathExtend.add(MathExtend.subtract(modifyInfo.getBeforeTicketPrice(),
                                                modifyInfo.getTotalDisprice()),String.valueOf(modifyInfo.getMerOrder().getMerTicket().getDxPlatTicket().getAmount()))));
                                    }
                                } else {
                                    decreasePrice.setText(String.format("-%s???",MathExtend.subtract(modifyInfo.getBeforeTicketPrice(),
                                            modifyInfo.getTotalDisprice())));
                                }
                            }else {
                                if (modifyInfo.getMerOrder() != null) {
                                    if (modifyInfo.getMerOrder().getMerTicket() != null) {
                                        decreasePrice.setText(String.format("-%s???",MathExtend.add(MathExtend.subtract(modifyInfo.getBeforeTicketPrice(),
                                                modifyInfo.getTotalDisprice()),String.valueOf(modifyInfo.getMerOrder().getMerTicket().getDxPlatTicket().getAmount()))));
                                    }
                                } else {
                                    decreasePrice.setText(String.format("-%s???",MathExtend.subtract(modifyInfo.getBeforeTicketPrice(),
                                            modifyInfo.getTotalDisprice())));
                                }
                            }
                        }
                    }
                    isSuccess = true;
                }

                holder.getView(R.id.ivAdd).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isSuccess) {
                            isSuccess = false;
                            int num = spUtils.getInt(String.valueOf(data.get(position).getId()));
                            if (num != -1) {
                                goodsNum = num;
                                goodsNum++;
                            } else {
                                goodsNum = 0;
                                goodsNum++;
                            }
                            spUtils.put(String.valueOf(data.get(position).getId()), goodsNum);
                            if (!goodsId.contains(String.valueOf(data.get(position).getId()))) {
                                goodsId.add(String.valueOf(data.get(position).getId()));
                            }
                            modifyPrice();
                            notifyDataSetChanged();
                        }
                    }
                });
                holder.getView(R.id.ivDecrease).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isSuccess) {
                            isSuccess = false;
                            int num = spUtils.getInt(String.valueOf(data.get(position).getId()));
                            if (num != -1) {
                                goodsNum = num;
                                goodsNum--;
                            } else {
                                goodsNum = 0;
                                goodsNum--;
                            }
                            spUtils.put(String.valueOf(data.get(position).getId()), goodsNum);
                            modifyPrice();
                            notifyDataSetChanged();
                        }
                    }
                });
            }
        };
        prodectList.setAdapter(adapter);
    }

    private void modifyPrice() {
        showProgress("");
        Gson gson = new Gson();
        List<MerchandBO> list = new ArrayList<>();
        for (int i = 0; i < goodsId.size(); i++) {
            if (spUtils.getInt(String.valueOf(goodsId.get(i))) != 0) {
                MerchandBO merchandBO = new MerchandBO(Long.parseLong(goodsId.get(i)), Long.parseLong(String.valueOf(spUtils.getInt(String.valueOf(goodsId.get(i))))));
                list.add(merchandBO);
            }
        }
        String ticketId;
        String prodectId;

        if (ticketCouponId != 0 && ticketCouponId != -1) {
            ticketId = String.valueOf(ticketCouponId);
        } else {
            ticketId = "0";
        }
        if (prodectCouponId != 0 && prodectCouponId != -1) {
            prodectId = String.valueOf(prodectCouponId);
        } else {
            prodectId = "0";
        }
        selectProdectCoupon.setEnabled(true);
        merchandiseInfo = gson.toJson(list);
        String merInfo = "";
        if (merchandiseInfo.equals("[]")){
            merInfo = "";
        }else {
            merInfo = merchandiseInfo;
        }
        String nowPayType;
        String myPayPrice;
        if (payType.equals("0")){
            nowPayType = "1";
            myPayPrice = modifyInfo.getPayPrice();
        }else {
            nowPayType = "0";
            myPayPrice = modifyInfo.getPayPrice();
        }
        //??????????????????
        mPresenter.modifyOrderPrice(MyApplication.cinemaBo.getCinemaId(), lockSeatsBO.getPartnerPrice(), lockSeatsBO.getMarketPrice(), activityPriceNum,
                String.valueOf(ticketNum), modifyInfo.getBeforeTicketPrice(), modifyInfo.getTotalDisprice(), myPayPrice,
                ticketId, prodectId, merInfo, String.valueOf(MyApplication.user.getId()), String.valueOf(sessionBO.getPreferPrice()),
                String.valueOf(sessionBO.getGlobalPreferPrice()), String.valueOf(sessionBO.getGlobalCanBuyNum()),nowPayType);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go_back:
                AppManager.getAppManager().goBackMain();
                break;
            case R.id.submit_button:
                showNoProgress("???????????????...");
                submitOrder();
                break;
            case R.id.call_phone:
                callPhone();
                break;
            case R.id.selectCoupon://???????????????
                Bundle bundle = new Bundle();
                bundle.putString("tag", "ticket");
                bundle.putSerializable("LockSeatsBO", lockSeatsBO);
                bundle.putDouble("totalMoney", zongjia);
                Intent intent = new Intent(this, SelectCoupon.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, 1);
                break;
            case R.id.selectProdectCoupon://?????????????????????
                Bundle bundle2 = new Bundle();
                bundle2.putString("tag", "prodect");
                bundle2.putSerializable("LockSeatsBO", modifyInfo);
                Intent intent2 = new Intent(this, SelectCoupon.class);
                intent2.putExtras(bundle2);
                startActivityForResult(intent2, 2);
                break;
            case R.id.rlUpdatePhone:
                showSelectDialog("??????????????????");
                break;
            case R.id.rlComment://??????
                showSelectDialog("???????????????");
                break;
            default:
                break;
        }
    }

    @OnClick({R.id.cardPayBtn, R.id.AlipayBtn, R.id.WechatPayBtn, R.id.exchangeCouponBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cardPayBtn:
                if (modifyInfo.getVipPayPrice() != null
                        && Double.parseDouble(modifyInfo.getVipPayPrice()) == 0){
                    ToastUtils.showShortToast("0??????????????????????????????");
                    return;
                }
                showProgress("");
                mPresenter.loadCardUser();
                break;
            case R.id.AlipayBtn:
                cardPayImg.setImageDrawable(getResources().getDrawable(R.mipmap.pay_normal));
                AlipayImg.setImageDrawable(getResources().getDrawable(R.mipmap.pay_selected));
                WechatPayImg.setImageDrawable(getResources().getDrawable(R.mipmap.pay_normal));
                exchangeCouponImg.setImageDrawable(getResources().getDrawable(R.drawable.xijiantou));
                addCouponLayout.setVisibility(View.GONE);
                payType = "1";
                modifyPrice();
                break;
            case R.id.WechatPayBtn:
                cardPayImg.setImageDrawable(getResources().getDrawable(R.mipmap.pay_normal));
                AlipayImg.setImageDrawable(getResources().getDrawable(R.mipmap.pay_normal));
                WechatPayImg.setImageDrawable(getResources().getDrawable(R.mipmap.pay_selected));
                exchangeCouponImg.setImageDrawable(getResources().getDrawable(R.drawable.xijiantou));
                addCouponLayout.setVisibility(View.GONE);
                payType = "2";
                modifyPrice();
                break;
            case R.id.exchangeCouponBtn:
                if (ticketCouponId != 0 && ticketCouponId != -1) {//?????????????????????????????????
                    ToastUtils.showShortToast("?????????????????????????????????????????????");
                    return;
                }
                if (!merchandiseInfo.equals("") && !merchandiseInfo.equals("[]")){//????????????????????????
                    ToastUtils.showShortToast("????????????????????????????????????");
                    return;
                }
                if (payType.equals("0")){//???????????????????????????
                    ToastUtils.showShortToast("????????????????????????????????????");
                    return;
                }
                if (!isOpen) {
                    isOpen = true;
                    addCouponData.clear();
                    addCouponData.add(new CouponDataBean(0,""));
                    addCouponAdapter.notifyDataSetChanged();
                    addCouponLayout.setVisibility(View.VISIBLE);
                    exchangeCouponImg.setImageDrawable(getResources().getDrawable(R.mipmap.down_img));
                    cardPayImg.setImageDrawable(getResources().getDrawable(R.mipmap.pay_normal));
                }else {
                    isOpen = false;
                    addCouponData.clear();
                    addCouponLayout.setVisibility(View.GONE);
                    exchangeCouponImg.setImageDrawable(getResources().getDrawable(R.drawable.xijiantou));
                }
                break;
        }
    }

    /**
     * ????????????
     */
    private void submitOrder(){
        String ticketId;
        String prodectId;
        String myPhone;
        if (ticketCouponId != 0 && ticketCouponId != -1) {//??????????????????
            ticketId = String.valueOf(ticketCouponId);
        } else {
            ticketId = "";
        }
        if (prodectCouponId != 0 && prodectCouponId != -1) {//???????????????
            prodectId = String.valueOf(prodectCouponId);
        } else {
            prodectId = "";
        }
        if (newPhoneNumber.equals("")) {//????????????
            myPhone = MyApplication.user.getMobile();
        } else {
            myPhone = newPhoneNumber;
        }
        String merInfo;
        if (merchandiseInfo.equals("[]")){
            merInfo = "";
        }else {
            merInfo = merchandiseInfo;
        }
        if (payType.equals("0")){//?????????
            if (modifyInfo.getVipPayPrice() != null
                    && Double.parseDouble(modifyInfo.getVipPayPrice()) == 0){
                mPresenter.loadZeroSubmitOrder(MyApplication.cinemaBo.getCinemaId(), null, seats, seatId, zongjia + "", ticketNum + "",
                        MyApplication.cinemaBo.getCinemaNumber(), sessionBO.getHallId(),
                        sessionBO.getDxId(), sessionBO.getCineMovieNum(), ticketId, merInfo, prodectId, myPhone, comment, String.valueOf(sessionBO.getPreferPrice()),
                        String.valueOf(sessionBO.getGlobalPreferPrice()), String.valueOf(sessionBO.getGlobalCanBuyNum()));
                return;
            }
        }else {
            if (modifyInfo.getPayPrice() != null
                    && Double.parseDouble(modifyInfo.getPayPrice()) == 0){
                mPresenter.loadZeroSubmitOrder(MyApplication.cinemaBo.getCinemaId(), null, seats, seatId, zongjia + "", ticketNum + "",
                        MyApplication.cinemaBo.getCinemaNumber(), sessionBO.getHallId(),
                        sessionBO.getDxId(), sessionBO.getCineMovieNum(), ticketId, merInfo, prodectId, myPhone, comment, String.valueOf(sessionBO.getPreferPrice()),
                        String.valueOf(sessionBO.getGlobalPreferPrice()), String.valueOf(sessionBO.getGlobalCanBuyNum()));
                return;
            }
        }
        mPresenter.loadSubmitOrder(MyApplication.cinemaBo.getCinemaId(), null, seats, seatId, zongjia + "", ticketNum + "",
                MyApplication.cinemaBo.getCinemaNumber(), sessionBO.getHallId(),
                sessionBO.getDxId(), sessionBO.getCineMovieNum(), ticketId, merInfo, prodectId, myPhone, comment, String.valueOf(sessionBO.getPreferPrice()),
                String.valueOf(sessionBO.getGlobalPreferPrice()), String.valueOf(sessionBO.getGlobalCanBuyNum()));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AppManager.getAppManager().goBackMain();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void callPhone() {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + MyApplication.cinemaBo.getContact()));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    /**
     * ????????????????????????
     */
//    private boolean isEditPhone() {
//        userPhone = phoneEdit.getText().toString().trim();
//        if (StringUtils.isEmpty(userPhone)) {
//            LogUtils.showToast("????????????????????????");
//            return false;
//        }
//        if (!RegexUtils.isMobileExact(userPhone)) {
//            LogUtils.showToast("?????????????????????????????????");
//            return false;
//        }
//        return true;
//    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String ticketId;
        String prodectId;
        switch (requestCode) {
            case 1:
                int disprice = 0;
                try {
                    disprice = data.getExtras().getInt("discount");
                    ticketCouponId = data.getExtras().getInt("id");

                    if (ticketCouponId > 0) {
                        ticketId = String.valueOf(ticketCouponId);
                    } else {
                        ticketId = "0";
                    }
                    if (prodectCouponId > 0) {
                        prodectId = String.valueOf(prodectCouponId);
                    } else {
                        prodectId = "0";
                    }
                    String nowPayType;
                    String myPayPrice;
                    if (payType.equals("0")){
                        nowPayType = "1";
                        myPayPrice = modifyInfo.getPayPrice();
                    }else {
                        nowPayType = "0";
                        myPayPrice = modifyInfo.getPayPrice();
                    }
                    mPresenter.modifyOrderPrice(MyApplication.cinemaBo.getCinemaId(), lockSeatsBO.getPartnerPrice(), lockSeatsBO.getMarketPrice(), activityPriceNum,
                            String.valueOf(ticketNum), modifyInfo.getBeforeTicketPrice(), modifyInfo.getTotalDisprice(), myPayPrice,
                            ticketId, prodectId, merchandiseInfo, String.valueOf(MyApplication.user.getId()), String.valueOf(sessionBO.getPreferPrice()),
                            String.valueOf(sessionBO.getGlobalPreferPrice()), String.valueOf(sessionBO.getGlobalCanBuyNum()),nowPayType);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                int disprice2 = 0;
                try {
                    disprice2 = data.getExtras().getInt("discount");
                    prodectCouponId = data.getExtras().getInt("id");

                    if (ticketCouponId > 0) {
                        ticketId = String.valueOf(ticketCouponId);
                    } else {
                        ticketId = "0";
                    }
                    if (prodectCouponId > 0) {
                        prodectId = String.valueOf(prodectCouponId);
                    } else {
                        prodectId = "0";
                    }
                    String nowPayType;
                    String myPayPrice;
                    if (payType.equals("0")){
                        nowPayType = "1";
                        myPayPrice = modifyInfo.getPayPrice();
                    }else {
                        nowPayType = "0";
                        myPayPrice = modifyInfo.getPayPrice();
                    }
                    mPresenter.modifyOrderPrice(MyApplication.cinemaBo.getCinemaId(), lockSeatsBO.getPartnerPrice(), lockSeatsBO.getMarketPrice(), activityPriceNum,
                            String.valueOf(ticketNum), modifyInfo.getBeforeTicketPrice(), modifyInfo.getTotalDisprice(), myPayPrice,
                            ticketId, prodectId, merchandiseInfo, String.valueOf(MyApplication.user.getId()), String.valueOf(sessionBO.getPreferPrice()),
                            String.valueOf(sessionBO.getGlobalPreferPrice()), String.valueOf(sessionBO.getGlobalCanBuyNum()),nowPayType);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 666://???????????????????????????????????????????????????
                mPresenter.loadCardUser();
                break;
            default:
                break;
        }
    }

    @Override
    public void getModifyInfo(ResponseBody responseBody) throws IOException, JSONException {
        String s = new String(responseBody.bytes());
        JSONObject jsonObject = new JSONObject(s);
        int status = jsonObject.optInt("status");
        if (status == 1){
            this.modifyInfo = JSON.parseObject(jsonObject.optString("data"),LockSeatsBO.class);
            MyApplication.orderBO = modifyInfo;
            isSuccess = true;
            decreaseLayout.setVisibility(View.VISIBLE);
            if (payType.equals("0")){//?????????
                realPrice.setText(String.format("%s", modifyInfo.getVipPayPrice()));
                if (modifyInfo.getMerOrder() != null){
                    orderPrice.setText(String.format("%s", MathExtend.subtract(modifyInfo.getVipPayPrice(),modifyInfo.getMerOrder().getVipDisPrice())));
                }else {
                    orderPrice.setText(String.format("%s",modifyInfo.getVipPayPrice()));
                }
                if (Double.parseDouble(MathExtend.subtract(modifyInfo.getVipBeforeTicketPrice(),
                        modifyInfo.getVipTicketPayPrice())) == 0){
                    if (modifyInfo.getMerOrder() != null) {
                        if (modifyInfo.getMerOrder().getMerTicket() != null) {
                            double decre = Double.parseDouble(MathExtend.add(MathExtend.subtract(modifyInfo.getVipBeforeTicketPrice(),
                                    modifyInfo.getVipTicketPayPrice()),String.valueOf(modifyInfo.getMerOrder().getMerTicket().getDxPlatTicket().getAmount())));
                            decreasePrice.setText(String.format("-%s???", decre));
                        }
                    } else {
                        double decre = Double.parseDouble(MathExtend.subtract(modifyInfo.getVipBeforeTicketPrice(),
                                modifyInfo.getVipTicketPayPrice()));
                        decreasePrice.setText(String.format("-%s???", decre));
                    }
                }else {
                    if (modifyInfo.getMerOrder() != null) {
                        if (modifyInfo.getMerOrder().getMerTicket() != null) {
                            double decr = Double.parseDouble(MathExtend.add(MathExtend.subtract(modifyInfo.getVipBeforeTicketPrice(),
                                    modifyInfo.getVipTicketPayPrice()),String.valueOf(modifyInfo.getMerOrder().getMerTicket().getDxPlatTicket().getAmount())));

                            decreasePrice.setText(String.format("-%s???", decr));
                        }
                    } else {
                        double desc = Double.parseDouble(MathExtend.subtract(modifyInfo.getVipBeforeTicketPrice(),
                                modifyInfo.getVipTicketPayPrice()));
                        decreasePrice.setText(String.format("-%s???", desc));
                    }
                }
                if (Double.parseDouble(MathExtend.subtract(modifyInfo.getVipBeforeTicketPrice(),
                        modifyInfo.getVipTicketPayPrice())) == 0) {
                    txtCouponNum.setText("?????????");
                } else {
                    txtCouponNum.setText(String.format("-%s???", MathExtend.subtract(modifyInfo.getVipBeforeTicketPrice(),
                            modifyInfo.getVipTicketPayPrice())));
                }

            }else {
                realPrice.setText(String.format("%s", modifyInfo.getPayPrice()));
                if (modifyInfo.getMerOrder() != null){
                    orderPrice.setText(String.format("%s", MathExtend.subtract(modifyInfo.getPayPrice(),modifyInfo.getMerOrder().getDisPrice())));
                }else {
                    orderPrice.setText(String.format("%s",modifyInfo.getPayPrice()));
                }
                if (Double.parseDouble(MathExtend.subtract(modifyInfo.getBeforeTicketPrice(),
                        modifyInfo.getTotalDisprice())) == 0){
                    if (modifyInfo.getMerOrder() != null) {
                        if (modifyInfo.getMerOrder().getMerTicket() != null) {
                            double desc = Double.parseDouble(MathExtend.add(MathExtend.subtract(modifyInfo.getBeforeTicketPrice(),
                                    modifyInfo.getTotalDisprice()),String.valueOf(modifyInfo.getMerOrder().getMerTicket().getDxPlatTicket().getAmount())));
                            decreasePrice.setText(String.format("-%s???", desc));
                        }
                    } else {
                        double desc = Double.parseDouble(MathExtend.subtract(modifyInfo.getBeforeTicketPrice(),
                                modifyInfo.getTotalDisprice()));
                        decreasePrice.setText(String.format("-%s???", desc));
                    }
                }else {
                    if (modifyInfo.getMerOrder() != null) {
                        if (modifyInfo.getMerOrder().getMerTicket() != null) {
                            double desc = Double.parseDouble(MathExtend.add(MathExtend.subtract(modifyInfo.getBeforeTicketPrice(),
                                    modifyInfo.getTotalDisprice()),String.valueOf(modifyInfo.getMerOrder().getMerTicket().getDxPlatTicket().getAmount())));
                            decreasePrice.setText(String.format("-%s???", desc));

                        }
                    } else {
                        double desc = Double.parseDouble(MathExtend.subtract(modifyInfo.getBeforeTicketPrice(),
                                modifyInfo.getTotalDisprice()));
                        decreasePrice.setText(String.format("-%s???",desc));
                    }
                }
                if (Double.parseDouble(MathExtend.subtract(modifyInfo.getBeforeTicketPrice(),
                        modifyInfo.getTotalDisprice())) == 0) {
                    txtCouponNum.setText("?????????");
                } else {
                    txtCouponNum.setText(String.format("-%s???", MathExtend.subtract(modifyInfo.getBeforeTicketPrice(),
                            modifyInfo.getTotalDisprice())));
                }
            }
            if (modifyInfo.getSeatTicket() != null) {
                MyApplication.selectedId = modifyInfo.getSeatTicket().getId();
            }else {

            }
            if (modifyInfo.getMerOrder() != null) {
                if (payType.equals("0")) {//?????????
                    ProdectPrice.setText(String.format("%s", modifyInfo.getMerOrder().getVipDisPrice()));
                }else {
                    ProdectPrice.setText(String.format("%s", modifyInfo.getMerOrder().getDisPrice()));
                }
                if (modifyInfo.getMerOrder().getMerTicket() != null) {
                    txtProdectCouponNum.setText(String.format("-%s???", modifyInfo.getMerOrder().getMerTicket().getDxPlatTicket().getAmount()));
                    prodectCouponId = modifyInfo.getMerOrder().getMerTicket().getId();
                } else {
                    prodectCouponId = 0;
                    txtProdectCouponNum.setText("?????????");
                }
            } else {
                txtProdectCouponNum.setText("?????????");
                prodectCouponId = 0;
            }
            if (modifyInfo.getMerOrder() != null) {
                if (modifyInfo.getMerOrder().getMerTicketList() != null &&
                        modifyInfo.getMerOrder().getMerTicketList().size() > 0) {
                    selectProdectCoupon.setVisibility(View.GONE);
                } else {
                    selectProdectCoupon.setVisibility(View.GONE);
                }
            } else {
                selectProdectCoupon.setVisibility(View.GONE);
            }
        }
    }

    /**
     * ?????????????????????
     */
    @Override
    public void getCardList(List<CardBO> cardBOs) {
        if (cardBOs.size() > 0) {
            cardListData.clear();
            cardListData.addAll(cardBOs);
            chooseCard();
        } else {
            showDialog2("?????????????????????????????????");
        }
    }

    /**
     * ?????????????????????
     */
    @Override
    public void getCardPrice(PayCardBO payCardBO) {
        this.payCardBO = payCardBO;
        showCardPayDialog();
    }

    /**
     * ???????????????
     */
    @Override
    public void getPayCard(ResuBo pay) {
        if(pay.getResultX()==1){
            LogUtils.showToast("????????????");
            if (orderBO != null) {
                Intent intent = new Intent(ConfrimOrderActivity.this,OrderSurcessActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("order", orderBO.getOrderNum());
                intent.putExtras(bundle);     //???bundle??????intent??????
                startActivity(intent);
            }
        }
    }
    /**
     * ????????????
     */
    @Override
    public void getOrderCancle(OrderNumBO orderNumBO) {
        stopProgress();
        if (!StringUtils.isEmpty(orderNumBO.getOrderNum())) {
            orderExist = false;//???????????????
            if (payType.equals("0")){//??????????????????????????????????????????????????????????????????
                if (cardType.equals("1")){//??????
                    Intent intent = new Intent(ConfrimOrderActivity.this, AddCardActiivty.class);
                    startActivityForResult(intent, 666);
                }else if (cardType.equals("2")){//??????
                    Intent rechatge = new Intent(ConfrimOrderActivity.this, com.myp.cinema.ui.rechatge.rechatge.class);
                    LocalConfiguration.isShouye = "1";
                    rechatge.putExtra("cardPrice", cardPrice);
                    rechatge.putExtra("cardcode", cardNumber);
                    startActivity(rechatge);
                }
            }
        }
    }

    @Override
    public void getAliPay(PayBO orderInfo) {
        payAliPay(orderInfo.getAlipay());
    }

    @Override
    public void getWxPay(WXPayBO wxPayBO) {
        LocalConfiguration.orderNum = orderBO.getOrderNum();
        WXUtils.payWX(wxPayBO);
        LocalConfiguration.ordertype = 0;
    }

    /**
     * ???????????????
     */
    @Override
    public void verifyCoupon(ResponseBody responseBody) throws IOException, JSONException {
        String s = new String(responseBody.bytes());
        JSONObject jsonObject = new JSONObject(s);
        int status = jsonObject.optInt("status");
        if (status == 1) {//?????????????????????????????????????????????
            if (jsonObject.optDouble("balance") == 0){
                if (orderBO != null) {
                    mPresenter.couponExchange(orderBO.getOrderNum(), getCoupon(addCouponData));
                }
            }else {
                switch (payType) {
                    case "1"://?????????
                        showProgress("?????????...");
                        mPresenter.loadAliPay(orderBO.getOrderNum());
                        break;
                    case "2"://??????
                        showProgress("?????????...");
                        mPresenter.loadWeChatPay(orderBO.getOrderNum());
                        break;
                }
            }

        }else {
            ToastUtils.showShortToast(jsonObject.optString("message"));
        }
    }

    /**
     *???????????????
     */
    @Override
    public void couponExchange(ResponseBody responseBody) throws IOException, JSONException {
        String s = new String(responseBody.bytes());
        JSONObject jsonObject = new JSONObject(s);
        int status = jsonObject.optInt("status");
        if (status == 1) {
            Bundle bundle = new Bundle();
            bundle.putString("order", orderBO.getOrderNum());
            gotoActivity(OrderSurcessActivity.class, bundle, false);
        }
    }

    /**
     * ?????????????????????
     */
    private void payAliPay(final String orderInfo) {
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(ConfrimOrderActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);

                Message msg = new Message();
                msg.what = 0x11;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // ??????????????????
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * ???????????????????????????
     * <p>
     * ???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
     */
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            PayResult payResult = new PayResult((Map<String, String>) msg.obj);
            String resultStatus = payResult.getResultStatus();
            // ??????resultStatus ???9000?????????????????????
            if (TextUtils.equals(resultStatus, "9000")) {
                if (orderBO != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("order", orderBO.getOrderNum());
                    gotoActivity(OrderSurcessActivity.class, bundle, false);
                }
            } else {
                LogUtils.showToast("????????????");
                if (orderBO != null) {//????????????
                    mPresenter.orderCancle(orderBO.getOrderNum());
                }
            }
        }
    };


    /**
     * ??????????????????
     */
    private void showCardPayDialog() {
        LayoutInflater factory = LayoutInflater.from(this);//?????????
        View dia = factory.inflate(R.layout.dialog_card_pay, null);//???????????????final???
        Button cancle = (Button) dia.findViewById(R.id.pay_cancle);
        Button commit = (Button) dia.findViewById(R.id.pay_yes);
        TextView price = (TextView) dia.findViewById(R.id.order_price);
        TextView poundagePrice = (TextView) dia.findViewById(R.id.poundage_price);
        final EditText passWord = (EditText) dia.findViewById(R.id.edit_password);
        price.setText("?? " + payCardBO.getTotalPrice());
        poundagePrice.setText("?? " + payCardBO.getTotalFee());
        final AlertDialog dialog = new AlertDialog.Builder(this, R.style.AlertDialog).create();
        dialog.setCancelable(false);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.getWindow().setWindowAnimations(R.style.anim_menu_bottombar);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //???????????????
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm.isActive()) {
                    imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
                }
                dialog.dismiss();
                mPresenter.orderCancle(orderBO.getOrderNum());
            }
        });
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pwd = passWord.getText().toString().trim();
                if (StringUtils.isEmpty(pwd)) {
                    LogUtils.showToast("??????????????????");
                } else {
                    //???????????????
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
                    }
                    dialog.dismiss();
                    if (orderBO != null && !cardNumber.equals("")) {
                        showProgress("?????????...");
                        mPresenter.payCard(orderBO.getOrderNum(), MD5.strToMd5Low32(pwd), cardNumber);
                    }else {
                        ToastUtils.showShortToast("?????????????????????");
                    }
                }
            }
        });
        dialog.setView(dia);
        dialog.show();
    }


    /**
     * ??????
     */
    private void showSelectDialog(final String tag) {
        // ???????????????
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialog);
        final LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.exchange_coupon_dialog, null);
        final RelativeLayout rlClose = (RelativeLayout) v.findViewById(R.id.rlClose);
        final EditText etEnter = (EditText) v.findViewById(R.id.etEnter);
        final RelativeLayout rlSubmit = (RelativeLayout) v.findViewById(R.id.rlSubmit);
        final TextView txtCo = v.findViewById(R.id.txtCo);
        txtCo.setVisibility(View.GONE);
        etEnter.setHint(tag);
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
                //???????????????
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm.isActive()) {
                    imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                }
                noticeDialog.dismiss();
            }
        });
        rlSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String etInput = etEnter.getText().toString();
                String time = String.valueOf(TimeUtils.getNowTimeMills());
                if (!TextUtils.isEmpty(etInput)) {
                    //???????????????
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                    }
                    if (tag.equals("???????????????")) {
                        tComment.setText(etInput);
                        comment = etInput;
                        noticeDialog.dismiss();
                    } else {
                        if (RegexUtils.isMobileExact(etInput)) {
                            newPhoneNumber = etInput;
                            phoneNumber.setText(newPhoneNumber);
                            noticeDialog.dismiss();
                        } else {
                            ToastUtils.showShortToast("???????????????????????????");
                        }
                    }
                } else {
                    if (tag.equals("???????????????")) {
                        ToastUtils.showShortToast("????????????????????????...");
                    } else {
                        ToastUtils.showShortToast("???????????????????????????...");
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
     * ?????????????????????????????????
     * @param getMessage
     */
    private void showDialog2(String getMessage) {
        LayoutInflater factory = LayoutInflater.from(this);//?????????
        View dia = factory.inflate(R.layout.dialog2_card_pay, null);//???????????????final???
        TextView cancle = (TextView) dia.findViewById(R.id.pay_cancle);
        TextView commit = (TextView) dia.findViewById(R.id.pay_yes);
        TextView texttt = (TextView) dia.findViewById(R.id.texttt);
        final AlertDialog dialog = new AlertDialog.Builder(this, R.style.AlertDialog).create();
        texttt.setText(getMessage);
        if (getMessage.equals("?????????????????????????????????")){
            commit.setText("?????????");
            cardType = "1";
        }else {
            cardType = "2";
        }

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgress("");
                mPresenter.orderCancle(orderBO.getOrderNum());
            }
        });
        dialog.setView(dia);
        dialog.show();
    }

    @Override
    public void onRequestError(String msg) {
        if(msg.equals("?????????????????????")){
            showDialog2(msg);
        }else {
            LogUtils.showToast(msg);
        }
        if (orderBO != null ){
            mPresenter.orderCancle(orderBO.getOrderNum());
        }
        stopProgress();
        isSuccess = true;
    }

    @Override
    public void onRequestEnd() {
        stopProgress();
        isSuccess = true;
    }

    @Override
    public void getOrder(ConfirmPayBO orderBO) {
        MyApplication.confirmPayBO = orderBO;
        this.orderBO = orderBO;
        if (addCouponData.size()>0 && !addCouponData.get(0).getNumber().equals("")){
            mPresenter.verifyCoupon(getCoupon(addCouponData),orderBO.getOrderNum());
        }else {
            switch (payType) {
                case "0"://?????????
                    showProgress("");
                    mPresenter.loadCardPay(orderBO.getOrderNum(), cardNumber);
                    break;
                case "1"://?????????
                    showProgress("?????????...");
                    mPresenter.loadAliPay(orderBO.getOrderNum());
                    break;
                case "2"://??????
                    showProgress("?????????...");
                    mPresenter.loadWeChatPay(orderBO.getOrderNum());
                    break;
            }
        }
    }

    @Override
    public void getZeroOrder(ConfirmPayBO orderBO) {
        MyApplication.confirmPayBO = orderBO;
        this.orderBO = orderBO;
        if (orderBO != null) {
            Bundle bundle = new Bundle();
            bundle.putString("order", orderBO.getOrderNum());
            gotoActivity(OrderSurcessActivity.class, bundle, false);
        }
    }

    /**
     * ???????????????
     */
    private String getCoupon(List<CouponDataBean> data) {
        StringBuffer buffer = new StringBuffer();
        for (int i=0;i<data.size();i++) {
            if (!data.get(i).getNumber().equals("")) {
                buffer.append(data.get(i).getNumber() + ",");
            }
        }
        return buffer.substring(0, buffer.length() - 1);
    }


    /**
     * ???????????????
     */
    private int selectCardPosition = -1;
    private void chooseCard(){
        LayoutInflater factory = LayoutInflater.from(this);//?????????
        final View view = factory.inflate(R.layout.choose_card_item_layout, null);//???????????????final???
        Button notUseBtn = (Button) view.findViewById(R.id.notUseBtn);
        Button submitBtn = (Button) view.findViewById(R.id.submitBtn);
        RecyclerView cardList = view.findViewById(R.id.cardList);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        cardList.setLayoutManager(manager);
        CommonAdapter<CardBO> adapter = new CommonAdapter<CardBO>(this,R.layout.choosecard_item_layout,cardListData) {
            @Override
            protected void convert(ViewHolder holder, final CardBO cardBO, final int position) {
                holder.setText(R.id.cardName,cardBO.getCardLevel());
                holder.setText(R.id.cardNum,String.format("?????????%s",cardBO.getBalance()));
                ImageView selectImg = holder.getConvertView().findViewById(R.id.selectImg);
                if (selectCardPosition == position){
                    selectImg.setImageDrawable(getResources().getDrawable(R.mipmap.pay_selected));
                }else {
                    selectImg.setImageDrawable(getResources().getDrawable(R.mipmap.pay_normal));
                }
                holder.getView(R.id.button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cardPrice = cardBO.getBalance();
                        cardNumber=cardBO.getCardNumber();
                        selectCardPosition = position;
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
                dialog.dismiss();
                cardNumber = "";
            }
        });
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //??????
                dialog.dismiss();
                payType = "0";
                addCouponData.clear();
                cardPayImg.setImageDrawable(getResources().getDrawable(R.mipmap.pay_selected));
                AlipayImg.setImageDrawable(getResources().getDrawable(R.mipmap.pay_normal));
                WechatPayImg.setImageDrawable(getResources().getDrawable(R.mipmap.pay_normal));
                addCouponLayout.setVisibility(View.GONE);
                exchangeCouponImg.setImageDrawable(getResources().getDrawable(R.drawable.xijiantou));
                modifyPrice();

            }
        });
        dialog.setView(view);
        dialog.show();
    }

}