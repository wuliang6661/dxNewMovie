package com.myp.cinema.ui.main;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.myp.cinema.R;
import com.myp.cinema.api.HttpInterface;
import com.myp.cinema.api.HttpInterfaceIml;
import com.myp.cinema.base.MyApplication;
import com.myp.cinema.entity.CinemaBo;
import com.myp.cinema.jpush.MessageEvent;
import com.myp.cinema.mvp.MVPBaseActivity;
import com.myp.cinema.ui.InfoActivity;
import com.myp.cinema.ui.hotsellprodect.HotSellFragment;
import com.myp.cinema.ui.main.home.HomeFragment;
import com.myp.cinema.ui.main.hotwire.HotwireFragment;
import com.myp.cinema.ui.main.member.MemberFragment;
import com.myp.cinema.ui.main.store.StoreFragment;
import com.myp.cinema.ui.moviesseltor.SeltormovieActivity;
import com.myp.cinema.util.AppManager;
import com.myp.cinema.util.CustomUpdateParser;
import com.myp.cinema.util.LogUtils;
import com.myp.cinema.util.ScreenUtils;
import com.myp.cinema.util.ToastUtils;
import com.myp.cinema.util.baidumap.BaiduMapLoctionUtils;
import com.xuexiang.xupdate.XUpdate;
import com.xyz.tabitem.BottmTabItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.Bind;
import rx.Subscriber;

/**
 * ?????????
 */

public class MainActivity extends MVPBaseActivity<MainContract.View, MainPresenter>
        implements MainContract.View, View.OnClickListener {


    @Bind(R.id.home)
    BottmTabItem home;
    @Bind(R.id.haowan)
    BottmTabItem haowan;
    @Bind(R.id.product)
    BottmTabItem product;
    @Bind(R.id.haoxiaoxi)
    BottmTabItem haoxiaoxi;
    @Bind(R.id.huiyuan)
    BottmTabItem huiyuan;

    HomeFragment homeFragment;
//    PlayfulFragment playfulFragment;
    MemberFragment memberFragment;
    HotwireFragment hotwireFragment;
    PlayfulWebFragment playfulWebFragment;

    HotSellFragment hotSellFragment;
    StoreFragment storeFragment;


    BaiduMapLoctionUtils baiduMapLoctionUtils;

//    UpdateManager updateManager;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSwipeBackEnable(false);
        homeFragment = new HomeFragment();
//        playfulFragment = new PlayfulFragment();
        memberFragment = new MemberFragment();
        hotwireFragment = new HotwireFragment();
        playfulWebFragment = new PlayfulWebFragment();
        hotSellFragment = new HotSellFragment();
        storeFragment = new StoreFragment();
        goToFragment(homeFragment);
        setStatusBarColor(1);

        home.setOnClickListener(this);
        haowan.setOnClickListener(this);
        haoxiaoxi.setOnClickListener(this);
        product.setOnClickListener(this);
        huiyuan.setOnClickListener(this);
        baiduMapLoctionUtils = new BaiduMapLoctionUtils();
//        updateManager = new UpdateManager(this, "main");   //????????????
//        updateManager.checkUpdate();
        XUpdate.newBuild(this)
                .updateUrl(HttpInterface.URL + "/version/version.json")
                .updateParser(new CustomUpdateParser()) //???????????????????????????????????????
                .update();
        EventBus.getDefault().register(this);
        loadCinemas();
        if (MyApplication.spUtils.getString("read") == null
                || !MyApplication.spUtils.getString("read").equals("yes")) {
            infoDialog();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //??????????????????
        EventBus.getDefault().unregister(this);
    }

    /**
     * ??????????????????
     */
    private void getPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        } else {
            startLocation();
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull
            int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {   //????????????
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                startLocation();
                MyApplication.locateSuccess = true;
            }else if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_DENIED
                    && grantResults[1] == PackageManager.PERMISSION_DENIED){
                Intent intent = new Intent(MainActivity.this, SeltormovieActivity.class);
                startActivityForResult(intent,1);
                MyApplication.locateSuccess = false;
            }
        }else {  //???????????????????????????????????????
            mPresenter.loadCinemaIds(BaiduMapLoctionUtils.city,
                    BaiduMapLoctionUtils.lontitudeNum, BaiduMapLoctionUtils.latitude);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        if (resultCode == 1) {
            CinemaBo cinemaBo = (CinemaBo) data.getSerializableExtra("ciname");
            if (cinemaBo != null) {
                MyApplication.cinemaBo = cinemaBo;
                MyApplication.spUtils.put("cinemaId", cinemaBo.getCinemaId());
                homeFragment.setCinemaInfo();
            } else {
                loadCinemas();
            }
        }
        switch (requestCode) {
            case 200:
//                updateManager.installApk();
                break;
        }
    }

    private void loadCinemas(){
        HttpInterfaceIml.cinemaList("", "", "").subscribe(new Subscriber<List<CinemaBo>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showShortToast(e.getMessage());
            }

            @Override
            public void onNext(List<CinemaBo> s) {
                if (MyApplication.spUtils.getString("cinemaId") != null) {
                    for (int i = 0; i < s.size(); i++) {
                        if (s.get(i).getCinemaId().equals(MyApplication.spUtils.getString("cinemaId"))) {
                            homeFragment.setCinemaNameStr(s.get(i));
                            break;
                        }
                    }
                }else {
                    homeFragment.setCinemaNameStr(s.get(0));
                }
            }
        });

    }

    /**
     * ?????????????????????????????????
     */
    @Override
    public boolean shouldShowRequestPermissionRationale(@NonNull String permission) {
        return Manifest.permission.ACCESS_COARSE_LOCATION.equals(permission) ||
                super.shouldShowRequestPermissionRationale(permission);
    }



    /**
     * ??????????????????
     */
    private void startLocation() {
        baiduMapLoctionUtils.startLocation(this, new BaiduMapLoctionUtils.BaiduLocationListener() {
            @Override
            public void getData(String lontitude, String latitude, String city) {
                baiduMapLoctionUtils.stopLocation();
                mPresenter.loadCinemaIds(city, lontitude, latitude);
            }

            @Override
            public void onEroorLocation() {
                mPresenter.loadCinemaIds(BaiduMapLoctionUtils.city,
                        BaiduMapLoctionUtils.lontitudeNum, BaiduMapLoctionUtils.latitude);
            }
        });
    }

    @Override
    public void onData(final List<CinemaBo> cinemaIdBOs) {
        if (MyApplication.spUtils.getString("cinemaId") != null &&
                MyApplication.spUtils.getString("cinemaId").equals(cinemaIdBOs.get(0).getCinemaId())) {
            homeFragment.setCinemaNameStr(cinemaIdBOs.get(0));
        }else if (MyApplication.spUtils.getString("cinemaId") == null){
            homeFragment.setCinemaNameStr(cinemaIdBOs.get(0));
        }else {
            LayoutInflater factory = LayoutInflater.from(this);//?????????
            final View view = factory.inflate(R.layout.dialog_order_pay, null);//???????????????final???
            TextView cancle = (TextView) view.findViewById(R.id.off_commit);
            TextView commit = (TextView) view.findViewById(R.id.commit);
            TextView title = view.findViewById(R.id.title);
            TextView message = view.findViewById(R.id.message);
            title.setText(String.format("?????????\"%s\"???????????????",cinemaIdBOs.get(0).getCinemaName()));
            message.setText("???????????????");
            cancle.setText("??????");
            commit.setText("??????");
            final AlertDialog dialog = new AlertDialog.Builder(this).create();
            cancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    loadCinemas();
                }
            });
            commit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //??????
                    dialog.dismiss();
                    homeFragment.setCinemaNameStr(cinemaIdBOs.get(0));
                    MyApplication.spUtils.put("cinemaId", cinemaIdBOs.get(0).getCinemaId());
                }
            });
            dialog.setView(view);
            dialog.show();
        }
    }


    @Override
    public void onRequestError(String msg) {
        LogUtils.showToast(msg);
    }

    @Override
    public void onRequestEnd() {
    }

    private Fragment mContent = null;

    /**
     * ????????????????????? ??????????????????
     **/
    public void goToFragment(Fragment to) {
        if (mContent != to) {
            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction();
            if (!to.isAdded()) { // ??????????????????add???
                if (mContent != null)
                    transaction.hide(mContent).add(R.id.fragment_container, to).commitAllowingStateLoss(); // ???????????????fragment???add????????????Activity???
                else
                    transaction.add(R.id.fragment_container, to).commitAllowingStateLoss();
            } else {
                if (mContent != null)
                    transaction.hide(mContent).show(to).commitAllowingStateLoss(); // ???????????????fragment??????????????????
                else
                    transaction.show(to).commitAllowingStateLoss();
            }
            mContent = to;
        }
    }

    /**
     * ????????????????????????
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home:
                goToFragment(homeFragment);
                home.setSelectState(true);
                haowan.setSelectState(false);
                haoxiaoxi.setSelectState(false);
                product.setSelectState(false);
                huiyuan.setSelectState(false);
                setStatusBarColor(1);
                break;
            case R.id.haowan:
                goToFragment(storeFragment);
                home.setSelectState(false);
                haowan.setSelectState(true);
                haoxiaoxi.setSelectState(false);
                product.setSelectState(false);
                huiyuan.setSelectState(false);
                setStatusBarColor(1);
                break;
            case R.id.haoxiaoxi:
                goToFragment(hotwireFragment);
                home.setSelectState(false);
                haowan.setSelectState(false);
                haoxiaoxi.setSelectState(true);
                product.setSelectState(false);
                huiyuan.setSelectState(false);
                setStatusBarColor(1);
                break;
            case R.id.huiyuan:
                goToFragment(memberFragment);
                home.setSelectState(false);
                haowan.setSelectState(false);
                haoxiaoxi.setSelectState(false);
                huiyuan.setSelectState(true);
                product.setSelectState(false);
                setStatusBarColor(2);
                break;
            case R.id.product:
                goToFragment(hotSellFragment);
                home.setSelectState(false);
                haowan.setSelectState(false);
                haoxiaoxi.setSelectState(false);
                huiyuan.setSelectState(false);
                product.setSelectState(true);
                setStatusBarColor(1);
                break;
        }
    }

    //??????????????????????????????????????????
    private long firstTime = 0;

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {
                    LogUtils.showToast("????????????????????????");
                    firstTime = secondTime;
                    return true;
                } else {
                    MyApplication.SESSIONID = null;
                    AppManager.getAppManager().finishAllActivity();
                    System.exit(0);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

    /**
     * ??????Fragment
     * @param messageEvent
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent messageEvent){
        if (messageEvent.getMessageType().equals("showMain")){
            if (messageEvent.getPassValue().equals("yes")) {
                goToFragment(homeFragment);
                home.setSelectState(true);
                haowan.setSelectState(false);
                haoxiaoxi.setSelectState(false);
                huiyuan.setSelectState(false);
                product.setSelectState(false);
                setStatusBarColor(1);
            }else {
                goToFragment(hotSellFragment);
                home.setSelectState(false);
                haowan.setSelectState(false);
                haoxiaoxi.setSelectState(false);
                huiyuan.setSelectState(false);
                product.setSelectState(true);
                setStatusBarColor(1);
            }
        }else if (messageEvent.getMessageType().equals("update")){
            startInstallPermissionSettingActivity(this);
        }
    }

    /**
     * ????????????????????????????????????????????????
     * @param context
     */
    @RequiresApi (api = Build.VERSION_CODES.O)
    private void startInstallPermissionSettingActivity(Context context) {
        if (context == null){
            return;
        }
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
        ((Activity)context).startActivityForResult(intent,200);
    }

    /**
     * ???????????????????????????
     * @param i
     */
    private void setStatusBarColor(int i){
        //??????????????????
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//android6.0?????????????????????????????????????????????????????????
            if (i==1) {//?????????????????????
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }else {//?????????????????????
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            }
        }
    }


    /**
     *  ??????
     */
    private void infoDialog() {
        // ???????????????
        AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.AlertDialog);
        final LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.information_dialog_layout, null);
        TextView cancle = v.findViewById(R.id.cancle);
        TextView sure = v.findViewById(R.id.sure);
        TextView txt = v.findViewById(R.id.txt2);

        // SpannableStringBuilder ??????
        SpannableStringBuilder spannableBuilder = new SpannableStringBuilder();
        spannableBuilder.append(txt.getText().toString());
        //??????????????????????????????
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                startActivity(intent);
            }
            @Override
            public void updateDrawState(TextPaint ds) {
//                ????????????span??????????????????????????????????????????????????????
                ds.setColor(Color.parseColor("#009FFF"));
                ds.setUnderlineText(false);    //???????????????????????????
                ds.clearShadowLayer();//????????????
            }

        };
        spannableBuilder.setSpan(clickableSpan, 0, 12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        //??????????????????????????????
        ClickableSpan clickableSpan2 = new ClickableSpan() {//????????????
            @Override
            public void onClick(View widget) {
                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                startActivity(intent);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
//                ????????????span??????????????????????????????????????????????????????
                ds.setColor(Color.parseColor("#009FFF"));
                ds.setUnderlineText(false);    //???????????????????????????
                ds.clearShadowLayer();//????????????
            }

        };
        spannableBuilder.setSpan(clickableSpan2, 14, 19, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        txt.setText(spannableBuilder);
        txt.setHighlightColor(getResources().getColor(android.R.color.transparent));//???????????????????????????Android4.0????????????????????????????????????????????????
        txt.setMovementMethod(LinkMovementMethod.getInstance());

        builder.setView(v);
        builder.setCancelable(true);
        final Dialog noticeDialog = builder.create();
        noticeDialog.getWindow().setGravity(Gravity.CENTER);
        noticeDialog.setCancelable(false);
        noticeDialog.show();

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noticeDialog.dismiss();
                System.exit(0);
            }
        });

        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noticeDialog.dismiss();
                MyApplication.spUtils.put("read", "yes");
                getPermission();
            }
        });

        WindowManager.LayoutParams layoutParams = noticeDialog.getWindow().getAttributes();
        layoutParams.width = (int)(ScreenUtils.getScreenWidth()*0.75);
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        noticeDialog.getWindow().setAttributes(layoutParams);
    }

}