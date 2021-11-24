package com.myp.cinema.ui.moviesseltor;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myp.cinema.R;
import com.myp.cinema.base.MyApplication;
import com.myp.cinema.entity.CinemaBo;
import com.myp.cinema.entity.CityBO;
import com.myp.cinema.mvp.MVPBaseActivity;
import com.myp.cinema.ui.selectorcity.SelectorCityActivity;
import com.myp.cinema.util.LogUtils;
import com.myp.cinema.util.baidumap.BaiduMapLoctionUtils;
import com.myp.cinema.widget.lgrecycleadapter.LGRecycleViewAdapter;
import com.myp.cinema.widget.lgrecycleadapter.LGViewHolder;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.Bind;


/**
 * 选择影院界面
 */

public class SeltormovieActivity extends MVPBaseActivity<SeltormovieContract.View, SeltormoviePresenter>
        implements SeltormovieContract.View, View.OnClickListener {

    @Bind(R.id.recyle)
    RecyclerView recyle;
    @Bind(R.id.local_bg)
    LinearLayout localBg;
    @Bind(R.id.city_name)
    TextView cityName;

    @Override
    protected int getLayout() {
        return R.layout.act_select_movies;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        setStatusBarColor(2);
        setRightImage(R.drawable.guanbi, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        localBg.setOnClickListener(this);
        if (MyApplication.cityBO == null) {
            cityName.setText(BaiduMapLoctionUtils.city);
            mPresenter.loadCinemaIds(BaiduMapLoctionUtils.city, BaiduMapLoctionUtils.lontitudeNum, BaiduMapLoctionUtils.latitude);
        } else {
            cityName.setText(MyApplication.cityBO.getCity());
            mPresenter.loadCinemaIds(MyApplication.cityBO.getCity(), BaiduMapLoctionUtils.lontitudeNum, BaiduMapLoctionUtils.latitude);
        }
    }

    /**
     * 初始化控件
     */
    private void initViews() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置为横向布局
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置布局管理器
        recyle.setLayoutManager(layoutManager);
        //设置增加或删除条目的动画
        recyle.setItemAnimator(new DefaultItemAnimator());
    }


    @Override
    public void onData(final List<CinemaBo> cinemaIdBOs) {
        LGRecycleViewAdapter<CinemaBo> adapter = new LGRecycleViewAdapter<CinemaBo>(cinemaIdBOs) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_cinema_list_layout;
            }

            @Override
            public void convert(LGViewHolder holder, CinemaBo cinemaHallsBO, int position) {
                TextView cinemaName = (TextView) holder.getView(R.id.cinema_name);
                TextView cinemaAdress = (TextView) holder.getView(R.id.cinema_address);
                TextView cinemaLoc = (TextView) holder.getView(R.id.cinema_distance);
                cinemaName.setText(cinemaHallsBO.getCinemaName());
                cinemaAdress.setText(cinemaHallsBO.getAddress());
                double distance = Double.parseDouble(cinemaHallsBO.getDistance());
                if (distance < 1000) {
                    cinemaLoc.setText((int) distance + "m");
                } else {
                    DecimalFormat df = new DecimalFormat("#.00");
                    cinemaLoc.setText(df.format(distance / 1000) + "km");
                }
            }
        };
        adapter.setOnItemClickListener(R.id.item_layout, new LGRecycleViewAdapter.ItemClickListener() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent();
                intent.putExtra("ciname", cinemaIdBOs.get(position));
                setResult(1, intent);
                finish();
            }
        });
        recyle.setAdapter(adapter);
    }

    @Override
    public void onRequestError(String msg) {
        LogUtils.showToast(msg);
    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, SelectorCityActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        switch (resultCode) {
            case 1:
                CityBO cityBO = (CityBO) data.getSerializableExtra("city");
                MyApplication.cityBO = cityBO;
                cityName.setText(cityBO.getCity());
                mPresenter.loadCinemaIds(cityBO.getCity(), BaiduMapLoctionUtils.lontitudeNum, BaiduMapLoctionUtils.latitude);
                break;
        }
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
}