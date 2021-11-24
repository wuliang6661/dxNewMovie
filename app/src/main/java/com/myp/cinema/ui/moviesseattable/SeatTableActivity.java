package com.myp.cinema.ui.moviesseattable;


import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.myp.cinema.R;
import com.myp.cinema.base.MyApplication;
import com.myp.cinema.entity.LockSeatsBO;
import com.myp.cinema.entity.MoviesByCidBO;
import com.myp.cinema.entity.MoviesSessionBO;
import com.myp.cinema.entity.OrderNumBO;
import com.myp.cinema.entity.aCinemaSeatTableBO;
import com.myp.cinema.entity.preferentialnumberBo;
import com.myp.cinema.mvp.MVPBaseActivity;
import com.myp.cinema.ui.orderconfrim.ConfrimOrderActivity;
import com.myp.cinema.util.LogUtils;
import com.myp.cinema.util.StringUtils;
import com.myp.cinema.util.TimeUtils;
import com.myp.cinema.util.ToastUtils;
import com.myp.cinema.widget.AlwaysMarqueeTextView;
import com.myp.cinema.widget.seattable.SeatTable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import okhttp3.ResponseBody;

import static com.myp.cinema.widget.seattable.SeatTable.SEAT_TYPE_AVAILABLE;
import static com.myp.cinema.widget.seattable.SeatTable.SEAT_TYPE_NOT_AVAILABLE;
import static com.myp.cinema.widget.seattable.SeatTable.SEAT_TYPE_SELECTED;
import static com.myp.cinema.widget.seattable.SeatTable.SEAT_TYPE_SOLD;


/**
 * 选座购票界面
 */

public class SeatTableActivity extends MVPBaseActivity<SeatTableContract.View, SeatTablePresenter>
        implements SeatTableContract.View, View.OnClickListener {

    @Bind(R.id.seat_table)
    SeatTable seatTable;
    @Bind(R.id.movies_time)
    TextView moviesTime;
    @Bind(R.id.update_session)
    TextView updateSession;
    @Bind(R.id.cinema_address)
    TextView cinemaAddress;
    @Bind(R.id.seat1)
    TextView seat1;
    @Bind(R.id.seat2)
    TextView seat2;
    @Bind(R.id.seat3)
    TextView seat3;
    @Bind(R.id.seat4)
    TextView seat4;
    @Bind(R.id.seat11)
    TextView seat11;
    @Bind(R.id.seat22)
    TextView seat22;
    @Bind(R.id.seat33)
    TextView seat33;
    @Bind(R.id.seat44)
    TextView seat44;
    @Bind(R.id.lin_seat1)
    LinearLayout lin_seat1;
    @Bind(R.id.lin_seat2)
    LinearLayout lin_seat2;
    @Bind(R.id.lin_seat3)
    LinearLayout lin_seat3;
    @Bind(R.id.lin_seat4)
    LinearLayout lin_seat4;
    @Bind(R.id.order_price)
    TextView orderPrice;
    @Bind(R.id.submit_button)
    Button submitButton;
    @Bind(R.id.buttom_layout)
    LinearLayout buttomLayout;
    @Bind(R.id.x)
    TextView x;
    @Bind(R.id.movieName)
    AlwaysMarqueeTextView movieName;

    private Map<String, aCinemaSeatTableBO> setMap;   //座位取值简单
    private Map<String, aCinemaSeatTableBO> selector;   //选中的座位

    private List<Integer> noneRows;

    MoviesSessionBO sessionBO;    //场次bean
    MoviesByCidBO movies;   //当前选座的电影
    private double zongjia;
    private OrderNumBO isVip;
    private Integer preferentialnumber;
    private List<aCinemaSeatTableBO> xss = new ArrayList<>();
    private LockSeatsBO lockSeatsBO;
    private double uploadTotalPrice;//当前借口需要穿给后台的参数ticketOriginPrice

    @Override
    protected int getLayout() {
        return R.layout.act_seat_table;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarColor(1);
        sessionBO = (MoviesSessionBO) getIntent().getExtras().getSerializable("session");
        movies = (MoviesByCidBO) getIntent().getExtras().getSerializable("movies");
        isVip = (OrderNumBO) getIntent().getExtras().getSerializable("isVip");
        goBack();
        setTitle("选座购票");
        movieName.setText(movies.getMovieName());
        moviesTime.setText(String.format("%s %s",TimeUtils.string2Week(sessionBO.getStartTime()) ,sessionBO.getMovieDimensional()));
        LogUtils.I(sessionBO.getStartTime());
        LogUtils.I(TimeUtils.string2Week(sessionBO.getStartTime()));
        cinemaAddress.setText(MyApplication.cinemaBo.getCinemaName());
        selector = new HashMap<>();
        showProgress("加载中...");
        mPresenter.loadSeatTables(MyApplication.cinemaBo.getCinemaId(),
                sessionBO.getDxId(), sessionBO.getCineUpdateTime());
        seatTable.setScreenName(sessionBO.getHallName() + "银幕");//设置屏幕名称
        seatTable.setMaxSelected(4);//设置最多选中
        submitButton.setOnClickListener(this);
        updateSession.setOnClickListener(this);

    }

    /**
     * 设置状态栏字体颜色
     * @param i
     */
    private void setStatusBarColor(int i){
        //修改字体颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            if (i==1) {//黑色状态栏文字
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }else {//白色状态栏文字
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            }
        }
    }

    /**
     * 获取返回的座位信息
     * <p>
     * 下标从0开始，显示真实座位号需要加1
     */
    @Override
    public void getSeatData(List<aCinemaSeatTableBO> s) {
        packSeatData(s);
        xss = s;
        mPresenter.getsets(MyApplication.cinemaBo.getCinemaId(),sessionBO.getDxId());

    }

    @Override
    public void getpreferentialnumberBo(preferentialnumberBo s) {
        preferentialnumber = s.getGlobalCanBuyNum();
        if(s.getGlobalCanBuyNum()==null){
            x.setText("0");
        }else {
            x.setText(preferentialnumber+"");
        }
        seatTable.setSeatChecker(new SeatTable.SeatChecker() {

            @Override
            public boolean isValidSeat(int row, int column) {
                row++;
                column++;
                aCinemaSeatTableBO seatTableBO = setMap.get(row + "&&" + column);
                if ("0".equals(seatTableBO.getRowValue()) && "0".equals(seatTableBO.getColumnValue())) {
                    return false;
                }
                return true;

            }

            @Override
            public boolean isNotAvailable(int row, int column) {
                row++;
                column++;
                aCinemaSeatTableBO seatTableBO = setMap.get(row + "&&" + column);
                if ("repair".equals(seatTableBO.getSeatStatus())) {
                    return true;
                }
                return false;
            }

            @Override
            public boolean isSold(int row, int column) {
                row++;
                column++;
                aCinemaSeatTableBO seatTableBO = setMap.get(row + "&&" + column);
                if ("ok".equals(seatTableBO.getSeatStatus())) {
                    return false;
                }
                return true;
            }

            @Override
            public void checked(int row, int column) {
                row++;
                column++;
                addSeatTables(row, column);
            }

            @Override
            public void unCheck(int row, int column) {
                row++;
                column++;
                removeSeatTables(row, column);
            }

            @Override
            public int isFriends(int row, int column) {
                row++;
                column++;
                aCinemaSeatTableBO seatTableBO = setMap.get(row + "&&" + column);
                if (!StringUtils.isEmpty(seatTableBO.getPairValue())) {
                    aCinemaSeatTableBO left = setMap.get(row + "&&" + (column - 1));
                    if (left == null) {
                        return 1;
                    }
                    if (left.getPairValue().equals(seatTableBO.getPairValue())) {
                        return 2;
                    }
                    aCinemaSeatTableBO right = setMap.get(row + "&&" + (column + 1));
                    if (right == null) {
                        return 2;
                    }
                    if (right.getPairValue().equals(seatTableBO.getPairValue())) {
                        return 1;
                    }
                }
                return 0;
            }

            @Override
            public boolean isEmpty(int row, int column) {
                return true;
            }

            @Override
            public String[] checkedSeatTxt(int row, int column) {
                return null;
            }

            @Override
            public boolean isNoneRow(int row) {
                if (Collections.binarySearch(noneRows, row) >= 0) {
                    return true;
                }
                return false;
            }

        });
        int x = Integer.parseInt(xss.get(xss.size() - 1).getX());
        int y = Integer.parseInt(xss.get(xss.size() - 1).getY());
        seatTable.setData(x, y);
    }


    /**
     * 出始化空白行及座位重新封装
     */
    private void packSeatData(List<aCinemaSeatTableBO> s) {
        setMap = new HashMap<>();
        noneRows = new ArrayList<>();
        int xNum = 1;   //记录x
        aCinemaSeatTableBO bo;
        for (int i = 0; i < s.size(); i++) {
            bo = s.get(i);
            setMap.put(bo.getX() + "&&" + bo.getY(), bo);
            if ((xNum + "").equals(bo.getX())) {
                if ("0".equals(bo.getRowValue()) && "0".equals(bo.getColumnValue())) {   //走道
                } else {
                    xNum++;
                }
            } else if (Integer.parseInt(bo.getX()) > xNum) {
                noneRows.add(xNum);
                xNum++;
            }
        }
    }


    AnimationSet animationSet;

    /**
     * 选中座位，界面变化
     */

    private void addSeatTables(int row, int column) {
//        if (selector.size() == 0) {
//            animationSet = (AnimationSet) AnimationUtils.loadAnimation(this, R.anim.share_pop_in);
//            buttomLayout.startAnimation(animationSet);
//            buttomLayout.setVisibility(View.VISIBLE);
//        }
        aCinemaSeatTableBO seatTableBO = setMap.get(row + "&&" + column);
        selector.put(row + "&&" + column, seatTableBO);
        jisuan(selector.size());
        seatShow();
    }
    /**
     * 取消座位，界面控制
     */
    private void removeSeatTables(int row, int column) {
        selector.remove(row + "&&" + column);
        jisuan(selector.size());
//        if (selector.size() == 0) {
//            animationSet = (AnimationSet) AnimationUtils.loadAnimation(this, R.anim.share_pop_out);
//            buttomLayout.startAnimation(animationSet);
//            buttomLayout.setVisibility(View.GONE);
//        }
        seatShow();
    }

    private void jisuan(int size) {
                if(isVip.getIsVip()==1){
                    if ( preferentialnumber==null||preferentialnumber<=0) {
                        double zong = (size)* Double.parseDouble(String.valueOf(sessionBO.getPreferPrice()));
                        BigDecimal bd=new BigDecimal(zong);
                        zongjia =  bd.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();
                        orderPrice.setText("¥" + zongjia);
                        //传给后台的价格
                        double zong2 = (size)* Double.parseDouble(String.valueOf(sessionBO.getPartnerPrice()));
                        BigDecimal bd2=new BigDecimal(zong2);
                        uploadTotalPrice =  bd2.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();
                    } else {
                        if(size>preferentialnumber){
                            double  youhuijia =preferentialnumber * Double.parseDouble(String.valueOf(sessionBO.getGlobalPreferPrice()));
                            double noyouhui = (size-preferentialnumber)* Double.parseDouble(String.valueOf(sessionBO.getPreferPrice()));
                            double zong = youhuijia+noyouhui;
                            BigDecimal bd=new BigDecimal(zong);
                            zongjia =  bd.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();
                            orderPrice.setText("¥" + zongjia);

                            //传给后台的价格
                            double  youhuijia2 =preferentialnumber * Double.parseDouble(String.valueOf(sessionBO.getPartnerPrice()));
                            double noyouhui2 = (size-preferentialnumber)* Double.parseDouble(String.valueOf(sessionBO.getPartnerPrice()));
                            double zong2 = youhuijia2+noyouhui2;
                            BigDecimal bd2=new BigDecimal(zong2);
                            uploadTotalPrice =  bd2.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();
                        }else {
                            double zong = (size)* Double.parseDouble(String.valueOf(sessionBO.getGlobalPreferPrice()));
                            BigDecimal bd=new BigDecimal(zong);
                            zongjia =  bd.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();
                            orderPrice.setText("¥" + zongjia);

                            //传给后台的价格
                            double zong2 = (size)* Double.parseDouble(String.valueOf(sessionBO.getPartnerPrice()));
                            BigDecimal bd2=new BigDecimal(zong2);
                            uploadTotalPrice =  bd2.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();
                        }
                    }
                }else {
                    if(sessionBO.getGlobalLeftNum()==null){
                        double zong = (size)* Double.parseDouble(sessionBO.getMarketPrice());
                        BigDecimal bd=new BigDecimal(zong);
                        zongjia =  bd.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();
                        orderPrice.setText("¥" + zongjia);
                        uploadTotalPrice = zongjia;
                    }else {
                        if(sessionBO.getPartnerPrice()==null){
                            double zong = (size)* Double.parseDouble(sessionBO.getMarketPrice());
                            BigDecimal bd=new BigDecimal(zong);
                            zongjia =  bd.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();
                            orderPrice.setText("¥" + zongjia);
                            uploadTotalPrice = zongjia;
                        }else {
                            if(sessionBO.getLeftScreenLimitNum()<sessionBO.getGlobalLeftNum()){
                                if(size>sessionBO.getLeftScreenLimitNum()){
                                    double  youhuijia =sessionBO.getLeftScreenLimitNum() * Double.parseDouble(sessionBO.getPartnerPrice());
                                    double noyouhui = (size-sessionBO.getLeftScreenLimitNum())* Double.parseDouble(sessionBO.getMarketPrice());
                                    double zong = youhuijia+noyouhui;
                                    BigDecimal bd=new BigDecimal(zong);
                                    zongjia =  bd.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();
                                    orderPrice.setText("¥" + zongjia);
                                    uploadTotalPrice = zongjia;

                                }else {
                                    double zong = (size)* Double.parseDouble(sessionBO.getPartnerPrice());
                                    BigDecimal bd=new BigDecimal(zong);
                                    zongjia =  bd.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();
                                    orderPrice.setText("¥" + zongjia);
                                    uploadTotalPrice = zongjia;
                                }
                            }else {
                                if(size>sessionBO.getGlobalLeftNum()){
                                    double  youhuijia =sessionBO.getGlobalLeftNum() * Double.parseDouble(sessionBO.getPartnerPrice());
                                    double noyouhui = (size-sessionBO.getGlobalLeftNum())* Double.parseDouble(sessionBO.getMarketPrice());
                                    double zong = youhuijia+noyouhui;
                                    BigDecimal bd=new BigDecimal(zong);
                                    zongjia =  bd.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();
                                    orderPrice.setText("¥" + zongjia);
                                    uploadTotalPrice = zongjia;
                                }else {
                                    double zong = (size)* Double.parseDouble(sessionBO.getPartnerPrice());
                                    BigDecimal bd=new BigDecimal(zong);
                                    zongjia =  bd.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();
                                    orderPrice.setText("¥" + zongjia);
                                    uploadTotalPrice = zongjia;
                                }
                            }
                        }
                }

            }
        }

    /**
     * 设置底部座位显示
     */
    private void seatShow() {
        lin_seat1.setVisibility(View.GONE);
        lin_seat2.setVisibility(View.GONE);
        lin_seat3.setVisibility(View.GONE);
        lin_seat4.setVisibility(View.GONE);
        int i = 0;
        for (aCinemaSeatTableBO seatTableBO : selector.values()) {
            i++;
            switch (i) {
                case 1:
                    if(isVip.getIsVip()==1){
                        if (String.valueOf(preferentialnumber) == null && preferentialnumber<=0) {
                            seat11.setText("卡：¥ " + String.valueOf(sessionBO.getPreferPrice()));
                        }else {
                            if(preferentialnumber>=1){
                                seat11.setText("惠：¥ " + String.valueOf(sessionBO.getGlobalPreferPrice()));
                            }else {
                                seat11.setText("卡：¥ " + String.valueOf(sessionBO.getPreferPrice()));
                            }
                        }
                    }else {
                        if(sessionBO.getGlobalLeftNum()==null){
                            seat11.setText("原：¥ " + String.valueOf(sessionBO.getMarketPrice()));
                        }else {
                            if(sessionBO.getPartnerPrice()==null){
                                seat11.setText("原：¥ " + String.valueOf(sessionBO.getMarketPrice()));
                            }else {
                                if(sessionBO.getLeftScreenLimitNum()<sessionBO.getGlobalLeftNum()){
                                    if(sessionBO.getLeftScreenLimitNum()>=1){
                                        seat11.setText("惠：¥ " + String.valueOf(sessionBO.getPartnerPrice()));
                                    }else {
                                        seat11.setText("原：¥ " + String.valueOf(sessionBO.getMarketPrice()));
                                    }
                                }else {
                                    if(sessionBO.getGlobalLeftNum()>=1){
                                        seat11.setText("惠：¥ " + String.valueOf(sessionBO.getPartnerPrice()));
                                    }else {
                                        seat11.setText("原：¥ " + String.valueOf(sessionBO.getMarketPrice()));
                                    }
                                }
                            }
                        }
                    }
                    seat1.setText(seatTableBO.getRowValue() + "排" + seatTableBO.getColumnValue() + "座");
                    lin_seat1.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    if(isVip.getIsVip()==1){
                        if (String.valueOf(preferentialnumber) == null && preferentialnumber<=0) {
                            seat22.setText("卡：¥ " + String.valueOf(sessionBO.getPreferPrice()));
                        }else {
                            if(preferentialnumber>=2){
                                seat22.setText("惠：¥ " + String.valueOf(sessionBO.getGlobalPreferPrice()));
                            }else {
                                seat22.setText("卡：¥ " + String.valueOf(sessionBO.getPreferPrice()));
                            }
                        }
                    }else {
                        if(sessionBO.getGlobalLeftNum()==null){
                            seat22.setText("原：¥ " + String.valueOf(sessionBO.getMarketPrice()));
                        }else {
                            if(sessionBO.getPartnerPrice()==null){
                                seat22.setText("原：¥ " + String.valueOf(sessionBO.getMarketPrice()));
                            }else {
                                if(sessionBO.getLeftScreenLimitNum()<sessionBO.getGlobalLeftNum()){
                                    if(sessionBO.getLeftScreenLimitNum()>=2){
                                        seat22.setText("惠：¥ " + String.valueOf(sessionBO.getPartnerPrice()));
                                    }else {
                                        seat22.setText("原：¥ " + String.valueOf(sessionBO.getMarketPrice()));
                                    }
                                }else {
                                    if(sessionBO.getGlobalLeftNum()>=2){
                                        seat22.setText("惠：¥ " + String.valueOf(sessionBO.getPartnerPrice()));
                                    }else {
                                        seat22.setText("原：¥ " + String.valueOf(sessionBO.getMarketPrice()));
                                    }
                                }
                            }
                        }
                    }
                    seat2.setText(seatTableBO.getRowValue() + "排" + seatTableBO.getColumnValue() + "座");
                    lin_seat2.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    if(isVip.getIsVip()==1){
                        if (String.valueOf(preferentialnumber) == null && preferentialnumber<=0) {
                            seat33.setText("卡：¥ " + String.valueOf(sessionBO.getPreferPrice()));
                        }else {
                            if(preferentialnumber>=3){
                                seat33.setText("惠：¥ " + String.valueOf(sessionBO.getGlobalPreferPrice()));
                            }else {
                                seat33.setText("卡：¥ " + String.valueOf(sessionBO.getPreferPrice()));
                            }
                        }
                    }else {
                        if(sessionBO.getGlobalLeftNum()==null){
                            seat33.setText("原：¥ " + String.valueOf(sessionBO.getMarketPrice()));
                        }else {
                            if(sessionBO.getPartnerPrice()==null){
                                seat33.setText("原：¥ " + String.valueOf(sessionBO.getMarketPrice()));
                            }else {
                                if(sessionBO.getLeftScreenLimitNum()<sessionBO.getGlobalLeftNum()){
                                    if(sessionBO.getLeftScreenLimitNum()>=3){
                                        seat33.setText("惠：¥ " + String.valueOf(sessionBO.getPartnerPrice()));
                                    }else {
                                        seat33.setText("原：¥ " + String.valueOf(sessionBO.getMarketPrice()));
                                    }
                                }else {
                                    if(sessionBO.getGlobalLeftNum()>=3){
                                        seat33.setText("惠：¥ " + String.valueOf(sessionBO.getPartnerPrice()));
                                    }else {
                                        seat33.setText("原：¥ " + String.valueOf(sessionBO.getMarketPrice()));
                                    }
                                }
                            }
                        }
                    }
                    seat3.setText(seatTableBO.getRowValue() + "排" + seatTableBO.getColumnValue() + "座");
                    lin_seat3.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    if(isVip.getIsVip()==1){
                        if (String.valueOf(preferentialnumber) == null && preferentialnumber<=0) {
                            seat44.setText("卡：¥ " + String.valueOf(sessionBO.getPreferPrice()));
                        }else {
                            if(preferentialnumber>=4){
                                seat44.setText("惠：¥ " + String.valueOf(sessionBO.getGlobalPreferPrice()));
                            }else {
                                seat44.setText("卡：¥ " + String.valueOf(sessionBO.getPreferPrice()));
                            }
                        }
                    }else {
                        if(sessionBO.getGlobalLeftNum()==null){
                            seat44.setText("原：¥ " + String.valueOf(sessionBO.getMarketPrice()));
                        }else {
                            if(sessionBO.getPartnerPrice()==null){
                                seat44.setText("原：¥ " + String.valueOf(sessionBO.getMarketPrice()));
                            }else {
                                if(sessionBO.getLeftScreenLimitNum()<sessionBO.getGlobalLeftNum()){
                                    if(sessionBO.getLeftScreenLimitNum()>=4){
                                        seat44.setText("惠：¥ " + String.valueOf(sessionBO.getPartnerPrice()));
                                    }else {
                                        seat44.setText("原：¥ " + String.valueOf(sessionBO.getMarketPrice()));
                                    }
                                }else {
                                    if(sessionBO.getGlobalLeftNum()>=4){
                                        seat44.setText("惠：¥ " + String.valueOf(sessionBO.getPartnerPrice()));
                                    }else {
                                        seat44.setText("原：¥ " + String.valueOf(sessionBO.getMarketPrice()));
                                    }
                                }
                            }
                        }
                    }
                    seat4.setText(seatTableBO.getRowValue() + "排" + seatTableBO.getColumnValue() + "座");
                    lin_seat4.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit_button:
                if (selector.size() == 0) {
                    ToastUtils.showShortToast("请先选择座位");
                    return;
                }
                submitOrder();//提交选择
                break;
            case R.id.update_session:   //更换场次
                finish();
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
            message.setText(String.format("您购买的是【%s %s】场次，%s", MyApplication.cinemaBo.getCinemaName(),
                    TimeUtils.string2Week(sessionBO.getStartTime()),MyApplication.cinemaBo.getMessage()));
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
                //                issAvailable();
//                if (!isAvailable){
//                    ToastUtils.showShortToast("不要留下单个空座");
//                    return;
//                }
                showNoProgress("锁座中...");
                mPresenter.lockSeats(null,getSeats(),getSeatsId(),String.valueOf(uploadTotalPrice),String.valueOf(selector.size()),
                        MyApplication.cinemaBo.getCinemaNumber(),sessionBO.getHallId(),
                        sessionBO.getDxId(),sessionBO.getCineMovieNum(),String.valueOf(sessionBO.getPreferPrice()),
                        String.valueOf(sessionBO.getGlobalPreferPrice()),String.valueOf(sessionBO.getGlobalCanBuyNum()));
            }
        });
        dialog.setView(view);
        dialog.show();
    }

    /**
     * 获取选中的座位用1_1，2_2连起来
     */
    private String getSeats() {
        StringBuffer buffer = new StringBuffer();
        for (aCinemaSeatTableBO seatTableBO : selector.values()) {
            buffer.append(seatTableBO.getRowValue() + "_" + seatTableBO.getColumnValue() + ",");
        }
        return buffer.substring(0, buffer.length() - 1);
    }


    /**
     * 获取座位Id
     *
     * @return
     */
    private String getSeatsId() {
        StringBuffer buffer = new StringBuffer();
        for (aCinemaSeatTableBO seatTableBO : selector.values()) {
            buffer.append(seatTableBO.getCineSeatId() + ",");
        }
        return buffer.substring(0, buffer.length() - 1);
    }

    static boolean isAvailable = true;
    private void issAvailable(){
        for (aCinemaSeatTableBO seatTableBO : selector.values()) {//-1为当前位置
            int rowValue = Integer.parseInt(seatTableBO.getRowValue());
            int columnValue = Integer.parseInt(seatTableBO.getColumnValue());
            if (seatTable.getSeatType(rowValue,columnValue) == SEAT_TYPE_SELECTED) {
                setAvailable(rowValue, columnValue);
                if (!isAvailable){
                    break;
                }
            }else if (seatTable.getSeatType(rowValue,columnValue+1) == SEAT_TYPE_SELECTED){
                setAvailable(rowValue, columnValue + 1);
                if (!isAvailable){
                    break;
                }
            }else if (seatTable.getSeatType(rowValue,columnValue+2) == SEAT_TYPE_SELECTED){
                setAvailable(rowValue, columnValue + 2);
                if (!isAvailable){
                    break;
                }
            }else {
                setAvailable(rowValue, columnValue-1);
                if (!isAvailable){
                    break;
                }
            }
        }
    }

    private void setAvailable(int row,int column){
        if (seatTable.getSeatType(row,column - 1) == SEAT_TYPE_AVAILABLE
                && seatTable.getSeatType(row,column-2) == SEAT_TYPE_SOLD){//当前位置左边第一个可用，第二个已售，有一个空位
            if (seatTable.getSeatType(row,column + 1) == SEAT_TYPE_SOLD
                    || seatTable.getSeatType(row,column + 1) == SEAT_TYPE_SELECTED){
                isAvailable = true;
            }else {
                isAvailable = false;
            }
        }else if (seatTable.getSeatType(row,column - 1) == SEAT_TYPE_AVAILABLE
                && seatTable.getSeatType(row , column- 2) == SEAT_TYPE_SELECTED){//当前位置左边第一个可用，第二个已经选择，有一个空位
            isAvailable = false;
        }else if (seatTable.getSeatType(row,column - 1) == SEAT_TYPE_AVAILABLE
                && seatTable.getSeatType(row , column- 2) == SEAT_TYPE_NOT_AVAILABLE){//当前位置左边第一个可用，第二个不可选择，有一个空位
            if (seatTable.getSeatType(row,column - 2) == SEAT_TYPE_SOLD
                    || seatTable.getSeatType(row , column- 2) == SEAT_TYPE_NOT_AVAILABLE
                    || seatTable.getSeatType(row , column- 2) == SEAT_TYPE_SELECTED){
                if (seatTable.getSeatType(row,column +1) == SEAT_TYPE_AVAILABLE
                        && seatTable.getSeatType(row,column +2) == SEAT_TYPE_SOLD){
                    isAvailable = false;
                }else if (seatTable.getSeatType(row,column +1) == SEAT_TYPE_AVAILABLE
                        && seatTable.getSeatType(row,column +2) == SEAT_TYPE_SELECTED){
                    isAvailable = false;
                }else if (seatTable.getSeatType(row,column -1) == SEAT_TYPE_AVAILABLE
                        && seatTable.getSeatType(row,column -2) == SEAT_TYPE_NOT_AVAILABLE
                        && seatTable.getSeatType(row,column +1) == SEAT_TYPE_SOLD){
                    isAvailable = true;
                }else if (seatTable.getSeatType(row,column -1) == SEAT_TYPE_AVAILABLE
                        && seatTable.getSeatType(row,column -2) == SEAT_TYPE_NOT_AVAILABLE
                        && seatTable.getSeatType(row,column +1) == SEAT_TYPE_AVAILABLE){
                    isAvailable = false;
                }else {
                    isAvailable = true;
                }
            }else {
                isAvailable = false;
            }
        }else if (seatTable.getSeatType(row ,column- 1) == SEAT_TYPE_SOLD){//当前位置左边第一个已售，没有空位
            if (seatTable.getSeatType(row ,column-1) == SEAT_TYPE_AVAILABLE
                    && seatTable.getSeatType(row ,column) == SEAT_TYPE_SOLD ){
                isAvailable = false;
            }else if (seatTable.getSeatType(row,column-1) == SEAT_TYPE_AVAILABLE
                    && seatTable.getSeatType(row ,column)== SEAT_TYPE_SELECTED ){
                isAvailable = false;
            }else {
                isAvailable = true;
            }
        }else if (seatTable.getSeatType(row,column- 1) == SEAT_TYPE_SELECTED){//当前位置左边第一个已选择，没有空位
            if (seatTable.getSeatType(row,column-2) == SEAT_TYPE_AVAILABLE
                    && seatTable.getSeatType(row ,column - 3)== SEAT_TYPE_SOLD ){
                if (seatTable.getSeatType(row,column + 1) == SEAT_TYPE_NOT_AVAILABLE){
                    isAvailable = true;
                }else if (seatTable.getSeatType(row,column+1) == SEAT_TYPE_SELECTED
                        && seatTable.getSeatType(row ,column + 2)== SEAT_TYPE_NOT_AVAILABLE ){
                    isAvailable = true;
                }else if (seatTable.getSeatType(row,column+1) == SEAT_TYPE_SELECTED
                        && seatTable.getSeatType(row ,column + 2)== SEAT_TYPE_SELECTED
                        && seatTable.getSeatType(row ,column + 3)== SEAT_TYPE_SELECTED
                        && seatTable.getSeatType(row ,column + 4)== SEAT_TYPE_NOT_AVAILABLE ){
                    isAvailable = true;
                }else if (seatTable.getSeatType(row,column+1) == SEAT_TYPE_SELECTED
                        && seatTable.getSeatType(row ,column + 2)== SEAT_TYPE_SELECTED
                        && seatTable.getSeatType(row ,column + 3)== SEAT_TYPE_NOT_AVAILABLE){
                    isAvailable = true;
                }else {
                    isAvailable = false;
                }
            }else if (seatTable.getSeatType(row,column-2) == SEAT_TYPE_AVAILABLE
                    && seatTable.getSeatType(row ,column-3)== SEAT_TYPE_SELECTED ){
                isAvailable = false;
            }else if (seatTable.getSeatType(row,column-2) == SEAT_TYPE_NOT_AVAILABLE){
                isAvailable = true;
            }else if (seatTable.getSeatType(row,column+1) == SEAT_TYPE_AVAILABLE
                    && seatTable.getSeatType(row ,column + 2)== SEAT_TYPE_SOLD ){
                if (seatTable.getSeatType(row,column - 1) == SEAT_TYPE_SELECTED
                        && seatTable.getSeatType(row ,column -2)== SEAT_TYPE_SELECTED
                        && seatTable.getSeatType(row ,column -3)== SEAT_TYPE_SELECTED
                        && seatTable.getSeatType(row ,column -4)== SEAT_TYPE_NOT_AVAILABLE){
                    isAvailable = true;
                }else if (seatTable.getSeatType(row,column - 1) == SEAT_TYPE_SELECTED
                        && seatTable.getSeatType(row ,column -2)== SEAT_TYPE_SELECTED
                        && seatTable.getSeatType(row ,column -3)== SEAT_TYPE_NOT_AVAILABLE){
                    isAvailable = true;
                }else {
                    isAvailable = false;
                }
            }else if (seatTable.getSeatType(row,column+1) == SEAT_TYPE_AVAILABLE
                    && seatTable.getSeatType(row ,column + 2)== SEAT_TYPE_SELECTED ){
                isAvailable = false;
            }else if (seatTable.getSeatType(row,column+1) == SEAT_TYPE_AVAILABLE
                    && seatTable.getSeatType(row ,column + 2)== SEAT_TYPE_NOT_AVAILABLE ){
                isAvailable = false;
            }else if ((seatTable.getSeatType(row,column+1) == SEAT_TYPE_SELECTED)){
                isAvailable = true;
            }else {
                isAvailable = true;
            }
        }else if (seatTable.getSeatType(row,column-1 )== SEAT_TYPE_NOT_AVAILABLE){//当前座位的左边不可挑选
            isAvailable = true;
        }else if (seatTable.getSeatType(row,column+1) == SEAT_TYPE_AVAILABLE
                && seatTable.getSeatType(row,column+2) == SEAT_TYPE_SOLD){//当前位置右边第一个可用，第二个已售，有一个空位
            isAvailable = false;
        }else if (seatTable.getSeatType(row,column+1) == SEAT_TYPE_AVAILABLE
                && seatTable.getSeatType(row,column+2) == SEAT_TYPE_SELECTED){//当前位置右边第一个可用，第二个已选择，有一个空位
            isAvailable = false;
        }else if (seatTable.getSeatType(row,column+1) == SEAT_TYPE_AVAILABLE
                && seatTable.getSeatType(row,column+2) == SEAT_TYPE_NOT_AVAILABLE){//当前位置右边第一个可用，第二个不可用，有一个空位
            isAvailable = false;
        }else if (seatTable.getSeatType(row,column+2) == SEAT_TYPE_AVAILABLE
                && seatTable.getSeatType(row,column+3) == SEAT_TYPE_SOLD){//当前位置右边第二个可用，第三个已售，有一个空位
            isAvailable = true;
        }else if (seatTable.getSeatType(row,column+2) == SEAT_TYPE_AVAILABLE
                && seatTable.getSeatType(row,column+3) == SEAT_TYPE_SELECTED){//当前位置右边第二个可用，第三个已选择，有一个空位
            isAvailable = true;
        }else if (seatTable.getSeatType(row,column+2) == SEAT_TYPE_AVAILABLE
                && seatTable.getSeatType(row,column+3) == SEAT_TYPE_NOT_AVAILABLE){//当前位置右边第二个可用，第三个不可用，有一个空位
            isAvailable = true;
        }else if (seatTable.getSeatType(row,column+1) == SEAT_TYPE_SOLD){//当前位置右边第一个已售，没有空位
            isAvailable = true;
        }else if (seatTable.getSeatType(row,column+1) == SEAT_TYPE_SELECTED){//当前位置右边第一个已选择，没有空位
            isAvailable = true;
        }else if (seatTable.getSeatType(row,column+1)== SEAT_TYPE_NOT_AVAILABLE){//当前座位的右边第一个不可挑选
            isAvailable = true;
        }else {
            isAvailable = true;
        }
    }



    @Override
    public void onRequestError(String msg) {
        mPresenter.loadSeatTables(MyApplication.cinemaBo.getCinemaId(),
                sessionBO.getDxId(), sessionBO.getCineUpdateTime());
    }

    @Override
    public void onRequestEnd() {
        stopProgress();
    }

    @Override
    public void getConfirmOrderInfo(ResponseBody responseBody) throws IOException, JSONException {
        String s = new String(responseBody.bytes());
        JSONObject jsonObject = new JSONObject(s);
        int status = jsonObject.optInt("status");
        if (status == 1){
            this.lockSeatsBO = JSON.parseObject(jsonObject.optString("data"),LockSeatsBO.class);
            MyApplication.orderBO = lockSeatsBO;
            MyApplication.selectedId = -1;//清空之前选择的优惠券
            Bundle bundle = new Bundle();
            bundle.putSerializable("LockSeatsBO", lockSeatsBO);
            bundle.putSerializable("MoviesSession", sessionBO);   //场次
            bundle.putSerializable("movies", movies);              //电影
            bundle.putInt("num", selector.size());                  //电影张数
            bundle.putString("seats", getSeats());
            bundle.putString("seatId", getSeatsId());
            bundle.putDouble("uploadTotalPrice",uploadTotalPrice);
            bundle.putInt("preferentialnumber", preferentialnumber);
            if (lockSeatsBO.getSeatTicket() != null) {
                bundle.putInt("ticketCouponId", lockSeatsBO.getSeatTicket().getId());
            }else {
                bundle.putInt("ticketCouponId", 0);
            }
            gotoActivity(ConfrimOrderActivity.class, bundle, false);
        }

    }
}
