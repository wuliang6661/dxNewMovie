package com.myp.cinema.ui.moviespresell;


import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myp.cinema.R;
import com.myp.cinema.base.MyApplication;
import com.myp.cinema.config.ConditionEnum;
import com.myp.cinema.entity.DirectorBO;
import com.myp.cinema.entity.MoviesByCidBO;
import com.myp.cinema.entity.MoviesCommentBO;
import com.myp.cinema.entity.ShareBO;
import com.myp.cinema.mvp.MVPBaseActivity;
import com.myp.cinema.ui.VideoPlayerActivity;
import com.myp.cinema.ui.moviessession.SessionActivity;
import com.myp.cinema.util.ImageUtils;
import com.myp.cinema.util.LogUtils;
import com.myp.cinema.util.ScreenUtils;
import com.myp.cinema.util.StringUtils;
import com.myp.cinema.widget.ShareDialog;
import com.myp.cinema.widget.bigimage.ImagPagerUtil;
import com.myp.cinema.widget.lgrecycleadapter.LGRecycleViewAdapter;
import com.myp.cinema.widget.lgrecycleadapter.LGViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;


/**
 * 预售影片详情
 */

public class PresellMoviesActivity extends MVPBaseActivity<PresellMoviesContract.View,
        PresellMoviesPresenter> implements PresellMoviesContract.View, View.OnClickListener {

    @Bind(R.id.submit_button)
    TextView submitButton;
    @Bind(R.id.right_bg)
    ImageView rightBg;
    @Bind(R.id.video)
    RelativeLayout video;
    @Bind(R.id.video_img)
    ImageView videoImg;
    @Bind(R.id.video_start)
    ImageView videoStart;
    @Bind(R.id.movie_img)
    ImageView movieImg;
    @Bind(R.id.movies_name)
    TextView moviesName;
    @Bind(R.id.movie_intro)
    TextView movieIntro;
    @Bind(R.id.movie_type)
    TextView movieType;
    @Bind(R.id.movie_start_time)
    TextView movieStartTime;
    @Bind(R.id.movie_time)
    TextView movieTime;
    @Bind(R.id.movie_img_bac)
    LinearLayout movieImgBac;
    @Bind(R.id.movies_narrate)
    TextView moviesNarrate;
    @Bind(R.id.unfold)
    TextView unfold;
    @Bind(R.id.right_img)
    ImageView rightImg;
    @Bind(R.id.recyle)
    RecyclerView recyle;
    @Bind(R.id.right_img01)
    ImageView rightImg01;
    @Bind(R.id.right_img02)
    ImageView rightImg02;
    @Bind(R.id.recyle_photo)
    RecyclerView recylePhoto;
    @Bind(R.id.right_bg_01)
    ImageView rightBg01;
    @Bind(R.id.video_layout)
    LinearLayout videoLayout;
    @Bind(R.id.movie_type_img)
    ImageView movieTypeImg;
    @Bind(R.id.comment_layout)
    LinearLayout commentLayout;
    @Bind(R.id.content_layout)
    RelativeLayout contentLayout;
    @Bind(R.id.titleBg)
    View titleBg;

    boolean isunfold;    // 是否展开，默认未展开
    MoviesByCidBO moviesByCidBO;
    boolean isShouCang = false;    //是否收藏

    @Override
    protected int getLayout() {
        return R.layout.act_presell_movies;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("预售");
        setRightImage(R.drawable.fx, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.loadShareMovie(moviesByCidBO.getId());
            }
        });
        rightBg01.setBackgroundResource(R.drawable.sc);
        videoLayout.setVisibility(View.GONE);

        moviesByCidBO = (MoviesByCidBO) getIntent().getExtras().getSerializable("movie");
        setData();
        invition();
        setListener();
        if (MyApplication.isLogin == ConditionEnum.LOGIN) {
            mPresenter.loadMoviesComment(MyApplication.user.getId(), moviesByCidBO.getId());
        }
    }

    /**
     * 为页面设置值
     */
    private void setData() {
        ViewGroup.LayoutParams params = movieImg.getLayoutParams();
        params.height = (int) (ScreenUtils.getScreenWidth() * 0.42);
        params.width = (int) (ScreenUtils.getScreenWidth() * 0.3);
        movieImg.setLayoutParams(params);

        commentLayout.setVisibility(View.GONE);
        if (StringUtils.isEmpty(moviesByCidBO.getUniqueName())) {
            moviesName.setText(moviesByCidBO.getMovieName());
        } else {
            moviesName.setText(moviesByCidBO.getUniqueName());
        }
        movieIntro.setText(moviesByCidBO.getForeignName());
        if (!StringUtils.isEmpty(moviesByCidBO.getStartPlay())) {
            movieStartTime.setText(moviesByCidBO.getStartPlay().split(" ")[0] + " 上映");
        }
        movieTime.setText("时长 | " + moviesByCidBO.getPlayTime() + "分钟");
        movieType.setText(moviesByCidBO.getMovieType());
        if (!StringUtils.isEmpty(moviesByCidBO.getPicture())) {
            Glide.with(this).load(moviesByCidBO.getPicture()).into(movieImg);
            setImgBG(moviesByCidBO.getPicture());
        } else {
            movieImg.setImageResource(R.color.act_bg01);
        }
        switch (moviesByCidBO.getMovieDimensional()) {
            case "2D":
                movieTypeImg.setImageResource(R.drawable.img_2d);
                break;
            case "3D":
                movieTypeImg.setImageResource(R.drawable.img_3d);
                break;
            default:
                movieTypeImg.setImageResource(R.drawable.img_3dmax);
                break;
        }
        moviesNarrate.setText(moviesByCidBO.getIntroduction());
        if (moviesByCidBO.getDxVideos() != null && moviesByCidBO.getDxVideos().size() != 0 ) {
            if(!StringUtils.isEmpty(moviesByCidBO.getDxVideos().get(0).getPicture())){
                Picasso.with(this).load(moviesByCidBO.getDxVideos().get(0).getPicture()).into(videoImg);
            }else {
                video.setVisibility(View.GONE);
            }
        } else {
            video.setVisibility(View.GONE);
        }
        if (isHasMovie()) {
            if ("0".equals(moviesByCidBO.getSell())) {
                submitButton.setVisibility(View.GONE);
            } else if ("1".equals(moviesByCidBO.getSell()) || "2".equals(moviesByCidBO.getSell())) {
                submitButton.setVisibility(View.VISIBLE);
            }
        } else {
            submitButton.setVisibility(View.GONE);
        }
    }


    /**
     * 判断轮播图进来的电影，是否在所有电影列表里
     */
    private boolean isHasMovie() {
        List<MoviesByCidBO> movies = MyApplication.movies;
        if (movies == null || movies.size() == 0) {
            return false;
        }
        for (MoviesByCidBO movie : movies) {
            if (moviesByCidBO.getCineMovieNum().equals(movie.getCineMovieNum())) {
                return true;
            }
        }
        return false;
    }


    /**
     * 设置背景模糊图片
     */
    private void setImgBG(final String headerImageUrl) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = ImageUtils.getBitMBitmap(headerImageUrl);
                Message msg = Message.obtain();
                msg.what = 0;
                msg.obj = bitmap;
                handler.sendMessage(msg);
            }
        }).start();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                Bitmap newBitmap = ImageUtils.fastBlur((Bitmap) msg.obj, 0.3f, 25, true);
                if (newBitmap != null && titleBg != null) {
//                    movieImgBac.setBackground(new BitmapDrawable(newBitmap));
                    titleBg.setBackground(new BitmapDrawable(newBitmap));
                }
            }
        }
    };

    /**
     * 初始化控件
     */
    private void invition() {
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
        //设置为横向布局
        layoutManager1.setOrientation(OrientationHelper.HORIZONTAL);
        //设置布局管理器
        recyle.setLayoutManager(layoutManager1);
        recyle.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(this);
        layoutManager3.setOrientation(OrientationHelper.HORIZONTAL);
        recylePhoto.setLayoutManager(layoutManager3);
        recylePhoto.setItemAnimator(new DefaultItemAnimator());

        setPersonAdapter();
        setPhotoAdapter();
    }


    /**
     * 设置监听
     */
    private void setListener() {
        unfold.setOnClickListener(this);
        submitButton.setOnClickListener(this);
        videoStart.setOnClickListener(this);
        rightBg01.setOnClickListener(this);
    }


    /**
     * 设置演员名单适配器
     */
    private void setPersonAdapter() {
        List<DirectorBO> list = new ArrayList<>();
        list.addAll(moviesByCidBO.getDxDirectors());
        list.addAll(moviesByCidBO.getDxActors());
        LGRecycleViewAdapter adapter = new LGRecycleViewAdapter<DirectorBO>(list) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_movie_person;
            }

            @Override
            public void convert(LGViewHolder holder, DirectorBO o, int position) {
                ImageView personImg = (ImageView) holder.getView(R.id.person_img);
                TextView personName = (TextView) holder.getView(R.id.person_name);
                TextView personPost = (TextView) holder.getView(R.id.person_post);
                ViewGroup.LayoutParams params = personImg.getLayoutParams();
                params.height = (int) (ScreenUtils.getScreenWidth() * 0.35);
                params.width = (int) (ScreenUtils.getScreenWidth() * 0.25);
                personImg.setLayoutParams(params);

                personName.setText(o.getName());
                if (position + 1 <= moviesByCidBO.getDxDirectors().size()) {
                    personPost.setText("导演");
                } else {
                    personPost.setText("演员");
                }
                Glide.with(PresellMoviesActivity.this).load(o.getPicture()).into(personImg);
            }
        };
        final List<String> photos = getPhotoList(list);
        adapter.setOnItemClickListener(R.id.person_img, new LGRecycleViewAdapter.ItemClickListener() {
            @Override
            public void onItemClicked(View view, int position) {
                setBigImageShow(photos, "演员剧照", position);
            }
        });
        recyle.setAdapter(adapter);
    }

    /**
     * 获取演员图片列表
     */
    private List<String> getPhotoList(List<DirectorBO> directorBOs) {
        List<String> photos = new ArrayList<>();
        for (int i = 0; i < directorBOs.size(); i++) {
            photos.add(directorBOs.get(i).getPicture());
        }
        return photos;
    }


    /**
     * 设置剧照适配器
     */
    private void setPhotoAdapter() {
        if (StringUtils.isEmpty(moviesByCidBO.getPhotos())) {
            return;
        }
        String[] pic = moviesByCidBO.getPhotos().split(",");
        final List<String> photos = Arrays.asList(pic);
        LGRecycleViewAdapter adapter = new LGRecycleViewAdapter<String>(photos) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_movie_video_photo;
            }

            @Override
            public void convert(LGViewHolder holder, String o, int position) {
                holder.getView(R.id.bofang).setVisibility(View.GONE);
                ImageView video_img = (ImageView) holder.getView(R.id.video_img);

                ViewGroup.LayoutParams params = video_img.getLayoutParams();
                params.height = (int) (ScreenUtils.getScreenWidth() * 0.28);
                params.width = (int) (ScreenUtils.getScreenWidth() * 0.5);
                video_img.setLayoutParams(params);


                if (StringUtils.isEmpty(o)) {
                    video_img.setImageResource(R.color.act_bg01);
                } else {
                    Picasso.with(PresellMoviesActivity.this).load(o).into(video_img);
                }
            }
        };
        adapter.setOnItemClickListener(R.id.video_img, new LGRecycleViewAdapter.ItemClickListener() {
            @Override
            public void onItemClicked(View view, int position) {
                setBigImageShow(photos, "影片剧照", position);
            }
        });
        recylePhoto.setAdapter(adapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.unfold:
                if (!isunfold) {
                    moviesNarrate.setMaxLines(50);
                    unfold.setText("收起");
                    isunfold = true;
                } else {
                    moviesNarrate.setMaxLines(4);
                    unfold.setText("展开");
                    isunfold = false;
                }
                break;
            case R.id.submit_button:
                Bundle bundle1 = new Bundle();
                bundle1.putSerializable("movies", moviesByCidBO);
                gotoActivity(SessionActivity.class, bundle1, false);
                break;
            case R.id.video_start:
                Bundle bundle = new Bundle();
                bundle.putString("video", moviesByCidBO.getDxVideos().get(0).getUrl());
                gotoActivity(VideoPlayerActivity.class, bundle, false);
                break;
            case R.id.right_bg_01:
                mPresenter.loadMoviesShouCang(MyApplication.user.getId(), moviesByCidBO.getId(), isShouCang);
                break;
        }
    }

    /**
     * 设置大图显示
     *
     * @param images   图片列表
     * @param position 当前查看第几张
     */
    private void setBigImageShow(List<String> images, String content, int position) {
        ImagPagerUtil imagPagerUtil = new ImagPagerUtil(this, images);
        imagPagerUtil.setContentText(content);
        imagPagerUtil.show();
        imagPagerUtil.mViewPager.setCurrentItem(position);
    }


    @Override
    public void getMoviesComment(MoviesCommentBO moviesCommentBO) {
        if ("1".equals(moviesCommentBO.getCollectStatus())) {   //已收藏
            isShouCang = true;
            rightBg01.setBackgroundResource(R.drawable.sc_dis);
        } else {
            isShouCang = false;
            rightBg01.setBackgroundResource(R.drawable.sc);
        }
    }

    @Override
    public void getShareMessage(ShareBO shareBO) {
        new ShareDialog(this, shareBO).showAtLocation(contentLayout, Gravity.BOTTOM, 0, 0);
    }


    @Override
    public void onRequestError(String msg) {
        LogUtils.showToast(msg);
    }

    @Override
    public void onRequestEnd() {

    }
}
