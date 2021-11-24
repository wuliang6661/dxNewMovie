package com.myp.cinema.ui.main.home;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.myp.cinema.R;
import com.myp.cinema.base.BaseFragment;
import com.myp.cinema.base.MyApplication;
import com.myp.cinema.entity.CinemaBo;
import com.myp.cinema.ui.FragmentPaerAdapter;
import com.myp.cinema.ui.main.home.movieslist.MoviesListFragment;
import com.myp.cinema.ui.main.home.nextmovies.NextMoviesFragment;
import com.myp.cinema.ui.moviesseltor.SeltormovieActivity;
import com.myp.cinema.util.LogUtils;
import com.myp.cinema.util.viewpager.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 首页的fragment
 */

public class HomeFragment extends BaseFragment implements
        View.OnClickListener, ViewPager.OnPageChangeListener {

    @Bind(R.id.loc_layout)
    LinearLayout locLayout;
    @Bind(R.id.now_showing)
    RadioButton nowShowing;
    @Bind(R.id.next_showing)
    RadioButton nextShowing;
    @Bind(R.id.radio_layout)
    RadioGroup radioLayout;
    @Bind(R.id.hedler_layout)
    RelativeLayout hedlerLayout;
    @Bind(R.id.viewpager)
    CustomViewPager viewpager;

    @Bind(R.id.cinema_name)
    TextView cinemaName;
    @Bind(R.id.progress)
    ProgressBar progress;

    MoviesListFragment listFragment;
    NextMoviesFragment nextMoviesFragment;
    List<Fragment> list;

    CinemaBo cinemaIdBOs;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_home, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listFragment = new MoviesListFragment();
        nextMoviesFragment = new NextMoviesFragment();
        list = new ArrayList<>();
        list.add(listFragment);
        list.add(nextMoviesFragment);
        FragmentPaerAdapter adapter = new FragmentPaerAdapter(getFragmentManager(), list);
        viewpager.setAdapter(adapter);
        viewpager.setScanScroll(false);
        setListener();
    }

    /**
     * 设置监听
     */
    private void setListener() {
        locLayout.setOnClickListener(this);
        viewpager.setOnPageChangeListener(this);
        radioLayout.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.now_showing:
                        nowShowing.setTextColor(getResources().getColor(R.color.white));
                        nextShowing.setTextColor(Color.parseColor("#32b8b8"));
                        viewpager.setCurrentItem(0);
                        break;
                    case R.id.next_showing:
                        nowShowing.setTextColor(Color.parseColor("#32b8b8"));
                        nextShowing.setTextColor(getResources().getColor(R.color.white));
                        viewpager.setCurrentItem(1);
                        break;
                }
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loc_layout:   //定位地址
                Intent intent = new Intent(getActivity(), SeltormovieActivity.class);
                startActivityForResult(intent, 1);
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        switch (resultCode) {
            case 1:
                cinemaName.setVisibility(View.VISIBLE);
                progress.setVisibility(View.GONE);
                CinemaBo cinemaBo = (CinemaBo) data.getSerializableExtra("ciname");
                cinemaName.setText(cinemaBo.getCinemaName());
                MyApplication.cinemaBo = cinemaBo;

                if (MyApplication.showCinemaId.size() == 0) {
                    MyApplication.isActivityPicShow = true;
                    MyApplication.showCinemaId .add(cinemaBo.getCinemaId());
                }else {
                    if (MyApplication.showCinemaId.contains(cinemaBo.getCinemaId())){
                        MyApplication.isActivityPicShow = false;
                    }else {
                        MyApplication.isActivityPicShow = true;
                        MyApplication.showCinemaId .add(cinemaBo.getCinemaId());
                    }
                }
                listFragment.setCinemaBo(cinemaBo);
                nextMoviesFragment.setCinemaBo(cinemaBo);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                nowShowing.setChecked(true);
                nowShowing.setTextColor(getResources().getColor(R.color.white));
                nextShowing.setTextColor(Color.parseColor("#32b8b8"));
                break;
            case 1:
                nowShowing.setTextColor(Color.parseColor("#32b8b8"));
                nextShowing.setTextColor(getResources().getColor(R.color.white));
                nextShowing.setChecked(true);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


//    public void setCinemaNameStr(List<CinemaBo> cinemaNameStr) {
//        this.cinemaIdBOs = cinemaNameStr;
//        if (cinemaIdBOs != null && cinemaIdBOs.size() != 0) {
//            cinemaName.setVisibility(View.VISIBLE);
//            progress.setVisibility(View.GONE);
//            cinemaName.setText(cinemaNameStr.get(0).getCinemaName());
//            MyApplication.cinemaBo = cinemaNameStr.get(0);
//            listFragment.setCinemaBo(cinemaNameStr.get(0));
//            nextMoviesFragment.setCinemaBo(cinemaNameStr.get(0));
//        } else {
//            cinemaName.setVisibility(View.VISIBLE);
//            progress.setVisibility(View.GONE);
//            cinemaName.setText("选择城市");
//            LogUtils.showToast("当前城市暂无影院信息！");
//        }
//    }

    public void setCinemaNameStr(CinemaBo cinemaNameStr) {
        this.cinemaIdBOs = cinemaNameStr;
        if (cinemaIdBOs != null) {
            cinemaName.setVisibility(View.VISIBLE);
            progress.setVisibility(View.GONE);
            cinemaName.setText(cinemaNameStr.getCinemaName());
            MyApplication.cinemaBo = cinemaNameStr;
            if (MyApplication.showCinemaId.size() == 0) {
                MyApplication.isActivityPicShow = true;
                MyApplication.showCinemaId .add(cinemaNameStr.getCinemaId());
            }else {
                if (MyApplication.showCinemaId.contains(cinemaNameStr.getCinemaId())){
                    MyApplication.isActivityPicShow = false;
                }else {
                    MyApplication.isActivityPicShow = true;
                    MyApplication.showCinemaId .add(cinemaNameStr.getCinemaId());
                }
            }
            listFragment.setCinemaBo(cinemaNameStr);
            nextMoviesFragment.setCinemaBo(cinemaNameStr);
        } else {
            cinemaName.setVisibility(View.VISIBLE);
            progress.setVisibility(View.GONE);
            cinemaName.setText("选择城市");
            LogUtils.showToast("当前城市暂无影院信息！");
        }
    }

    public void setCinemaInfo(){
        cinemaName.setVisibility(View.VISIBLE);
        progress.setVisibility(View.GONE);
        cinemaName.setText(MyApplication.cinemaBo.getCinemaName());
        MyApplication.isActivityPicShow = false;
        if (MyApplication.showCinemaId.size() == 0) {
            MyApplication.isActivityPicShow = true;
            MyApplication.showCinemaId .add(MyApplication.cinemaBo.getCinemaId());
        }else {
            if (MyApplication.showCinemaId.contains(MyApplication.cinemaBo.getCinemaId())){
                MyApplication.isActivityPicShow = false;
            }else {
                MyApplication.isActivityPicShow = true;
                MyApplication.showCinemaId .add(MyApplication.cinemaBo.getCinemaId());
            }
        }
        listFragment.setCinemaBo(MyApplication.cinemaBo);
        nextMoviesFragment.setCinemaBo(MyApplication.cinemaBo);
    }


}
