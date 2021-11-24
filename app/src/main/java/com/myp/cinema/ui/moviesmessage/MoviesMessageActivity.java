package com.myp.cinema.ui.moviesmessage;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hedgehog.ratingbar.RatingBar;
import com.myp.cinema.R;
import com.myp.cinema.base.MyApplication;
import com.myp.cinema.config.ConditionEnum;
import com.myp.cinema.entity.CriticBO;
import com.myp.cinema.entity.DirectorBO;
import com.myp.cinema.entity.MoviesByCidBO;
import com.myp.cinema.entity.MoviesCommentBO;
import com.myp.cinema.entity.ShareBO;
import com.myp.cinema.entity.VideoBO;
import com.myp.cinema.mvp.MVPBaseActivity;
import com.myp.cinema.ui.VideoPlayerActivity;
import com.myp.cinema.ui.allcritic.allcritic;
import com.myp.cinema.ui.moviessession.SessionActivity;
import com.myp.cinema.ui.personread.persongrade.PersonGradeActivity;
import com.myp.cinema.util.ImageUtils;
import com.myp.cinema.util.LogUtils;
import com.myp.cinema.util.ScreenUtils;
import com.myp.cinema.util.StringUtils;
import com.myp.cinema.widget.ShareDialog;
import com.myp.cinema.widget.bigimage.ImagPagerUtil;
import com.myp.cinema.widget.lgrecycleadapter.LGRecycleViewAdapter;
import com.myp.cinema.widget.lgrecycleadapter.LGViewHolder;
import com.myp.cinema.widget.pulltozoomview.PullToZoomScrollViewEx;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;


/**
 * 影片详情页面
 */
public class MoviesMessageActivity extends MVPBaseActivity<MoviesMessageContract.View,
        MoviesMessagePresenter> implements MoviesMessageContract.View, View.OnClickListener {

    @Bind(R.id.scollview)
    PullToZoomScrollViewEx scollview;
    @Bind(R.id.submit_button)
    TextView submitButton;
    @Bind(R.id.content_layout)
    RelativeLayout contentLayout;

    /**
     * 头部背景图片
     */
    ImageView movieBg;


    /**
     * 头部布局
     */
    LinearLayout goBack;
    ImageView movieImg;
    ImageView movieType;
    TextView movieName;
    TextView movieMessage;
    TextView movieClass;
    TextView movieTime;
    TextView movieAllTime;
    ImageView shouCang;   //收藏
    ImageView shareImg;   //分享
    View view;

    /**
     * 正文部分
     */
    RecyclerView personRecycle;
    RecyclerView videoRecycle;//视频列表
    RecyclerView photoRecycle;
    LinearLayout wantSee;     //想看
    ImageView wantSeeImg;    //想看的状态图
    TextView wantSeeText;
    LinearLayout haveRating;    //评论
    ImageView ratingImg;    //评论状态图
    TextView ratingText;
    TextView ratingMessage;
    TextView moviesNarrate;   //电影叙述
    TextView unfold;      //展开按键

    boolean isunfold;    // 是否展开，默认未展开
    MoviesByCidBO moviesByCidBO;
    boolean isWantSee = false;   //是否想看
    boolean isShouCang = false;    //是否收藏
    boolean isWantSeeEnable = true;   //想看是否可用,已看过不能用
    boolean isRatingEnable = false;    //评论是否可用,已看过才能用
  RecyclerView criticRecycle;
   TextView texianshi;
    private LGRecycleViewAdapter<CriticBO> adapter;
    private LinearLayout rm;
    private int ismore;

    @Override
    protected int getLayout() {
        return R.layout.act_movie_message;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadViewForPullToZoomScrollView(scollview);
        setPullToZoomViewLayoutParams(scollview);
        setTitle("影片详情");
        goBack();
        moviesByCidBO = (MoviesByCidBO) getIntent().getExtras().getSerializable("movie");
        submitButton.setOnClickListener(this);
        initView();
        initRecyle();
        setMovieMessage();
       mPresenter.loadMoviesCritic(Long.parseLong(moviesByCidBO.getId()));
        if (MyApplication.isLogin == ConditionEnum.LOGIN) {
            mPresenter.loadMoviesComment(MyApplication.user.getId(), moviesByCidBO.getId());
        }
    }


    /**
     * 填入电影数据
     */
    private void setMovieMessage() {
        moviesNarrate.setText(moviesByCidBO.getIntroduction());
        if (StringUtils.isEmpty(moviesByCidBO.getUniqueName())) {
            movieName.setText(moviesByCidBO.getMovieName());
        } else {
            movieName.setText(moviesByCidBO.getUniqueName());
        }
        movieMessage.setText(moviesByCidBO.getForeignName());
        if (!StringUtils.isEmpty(moviesByCidBO.getStartPlay())) {
            movieTime.setText(moviesByCidBO.getStartPlay().split(" ")[0] + " 上映");
        }
        movieAllTime.setText("时长 | " + moviesByCidBO.getPlayTime() + "分钟");
        movieClass.setText(moviesByCidBO.getMovieType());
        if (!StringUtils.isEmpty(moviesByCidBO.getPicture())) {
            Glide.with(this).load(moviesByCidBO.getPicture()).into(movieImg);
            setImgBG(moviesByCidBO.getPicture());
        } else {
            movieImg.setImageResource(R.color.act_bg01);
        }
        switch (moviesByCidBO.getMovieDimensional()) {
            case "2D":
                movieType.setImageResource(R.drawable.img_2d);
                break;
            case "3D":
                movieType.setImageResource(R.drawable.img_3d);
                break;
            default:
                movieType.setImageResource(R.drawable.img_3dmax);
                break;
        }
        if (isHasMovie()) {
            if ("0".equals(moviesByCidBO.getSell())) {
                submitButton.setVisibility(View.GONE);
            } else {
                submitButton.setVisibility(View.VISIBLE);
            }
        } else {
            submitButton.setVisibility(View.GONE);
        }
        setPersonAdapter();
        setVideoAdapter();
        setPhotoAdapter();
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

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                Bitmap newBitmap = ImageUtils.fastBlur((Bitmap) msg.obj, 0.3f, 25, true);
                if (newBitmap != null && movieBg != null) {
                    movieBg.setImageBitmap(newBitmap);
                }
            }
        }
    };


    /**
     * 初始化演员等列表
     */
    private void initRecyle() {
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
        layoutManager1.setOrientation(OrientationHelper.HORIZONTAL);
        personRecycle.setLayoutManager(layoutManager1);
        personRecycle.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        layoutManager2.setOrientation(OrientationHelper.HORIZONTAL);
        videoRecycle.setLayoutManager(layoutManager2);
        videoRecycle.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(this);
        layoutManager3.setOrientation(OrientationHelper.HORIZONTAL);
        photoRecycle.setLayoutManager(layoutManager3);
        photoRecycle.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager layoutManager4 = new LinearLayoutManager(this);
        layoutManager4.setOrientation(OrientationHelper.VERTICAL);
       criticRecycle.setLayoutManager(layoutManager4);
        criticRecycle.setItemAnimator(new DefaultItemAnimator());
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
                ViewGroup.LayoutParams params = personImg.getLayoutParams();
                params.width = (int) (ScreenUtils.getScreenWidth() * 0.25);
                params.height = (int) (ScreenUtils.getScreenWidth() * 0.35);
                personImg.setLayoutParams(params);

                TextView personName = (TextView) holder.getView(R.id.person_name);
                TextView personPost = (TextView) holder.getView(R.id.person_post);
                personName.setText(o.getName());
                if (position + 1 <= moviesByCidBO.getDxDirectors().size()) {
                    personPost.setText("导演");
                } else {
                    personPost.setText("演员");
                }
                Glide.with(MoviesMessageActivity.this).load(o.getPicture()).into(personImg);
            }
        };
        final List<String> photos = getPhotoList(list);
        adapter.setOnItemClickListener(R.id.person_img, new LGRecycleViewAdapter.ItemClickListener() {
            @Override
            public void onItemClicked(View view, int position) {
                setBigImageShow(photos, "演员剧照", position);
            }
        });

        personRecycle.setAdapter(adapter);
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
     * 设置视频适配器
     */
    private void setVideoAdapter() {
        LGRecycleViewAdapter adapter = new LGRecycleViewAdapter<VideoBO>(moviesByCidBO.getDxVideos()) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_movie_video_photo;
            }

            @Override
            public void convert(LGViewHolder holder, VideoBO o, int position) {
                ImageView video_img = (ImageView) holder.getView(R.id.video_img);
                ViewGroup.LayoutParams params = video_img.getLayoutParams();
                params.width = (int) (ScreenUtils.getScreenWidth() * 0.5);
                params.height = (int) (ScreenUtils.getScreenWidth() * 0.28);
                video_img.setLayoutParams(params);

                if (!StringUtils.isEmpty(o.getPicture())) {
                    Picasso.with(MoviesMessageActivity.this).load(o.getPicture()).into(video_img);
                } else {
                    Picasso.with(MoviesMessageActivity.this).load(R.color.act_bg01).into(video_img);
                }
            }
        };
        videoRecycle.setAdapter(adapter);
        adapter.setOnItemClickListener(R.id.bofang, new LGRecycleViewAdapter.ItemClickListener() {
            @Override
            public void onItemClicked(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("video", moviesByCidBO.getDxVideos().get(position).getUrl());
                gotoActivity(VideoPlayerActivity.class, bundle, false);
            }
        });
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
                params.width = (int) (ScreenUtils.getScreenWidth() * 0.5);
                params.height = (int) (ScreenUtils.getScreenWidth() * 0.28);
                video_img.setLayoutParams(params);



                if (StringUtils.isEmpty(o)) {
                    video_img.setImageResource(R.color.act_bg01);
                } else {
                    Picasso.with(MoviesMessageActivity.this).load(o).into(video_img);
                }
            }
        };
        adapter.setOnItemClickListener(R.id.video_img, new LGRecycleViewAdapter.ItemClickListener() {
            @Override
            public void onItemClicked(View view, int position) {
                setBigImageShow(photos, "影片剧照", position);
            }
        });
        photoRecycle.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go_back:
                finish();
                break;
            case R.id.texianshi:
                if(ismore>0){
                    Bundle bundles = new Bundle();
                    bundles.putSerializable("Id", moviesByCidBO.getId());
                    gotoActivity(allcritic.class, bundles, false);
                }
                break;
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
                Bundle bundle = new Bundle();
                bundle.putSerializable("movies", moviesByCidBO);
                gotoActivity(SessionActivity.class, bundle, false);
                break;
            case R.id.shoucang:   //收藏
                if (goLogin()) {
                    mPresenter.loadMoviesShouCang(MyApplication.user.getId(), moviesByCidBO.getId(), isShouCang);
                }
                break;
            case R.id.fenxiang_img:  //分享
                mPresenter.loadShareMovie(moviesByCidBO.getId());
                break;
            case R.id.want_see:   //想看
                if (goLogin()) {
                    if (isWantSeeEnable) {
                        mPresenter.loadMoviesWantSee(MyApplication.user.getId(), moviesByCidBO.getId(), isWantSee);
                    }
                }
                break;
            case R.id.have_rating:   //评论
                if (goLogin()) {
                    if (isRatingEnable) {
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("moviesId", moviesByCidBO.getId());
                        bundle1.putString("movieName", moviesByCidBO.getMovieName());
                        gotoActivity(PersonGradeActivity.class, bundle1, false);
                    } else {
                        LogUtils.showToast("无评论权限！");
                    }
                }
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


    private void loadViewForPullToZoomScrollView(PullToZoomScrollViewEx scrollView) {
        View headView = LayoutInflater.from(this).inflate(R.layout.movies_header_view, null);
        View zoomView = LayoutInflater.from(this).inflate(R.layout.movies_zoom_view, null);
        View contentView = LayoutInflater.from(this).inflate(R.layout.movies_content_view, null);
        scrollView.setScrollContentView(contentView);
        scrollView.setZoomView(zoomView);
        scrollView.setHeaderView(headView);
    }

    // 设置头部的View的宽高。
    private void setPullToZoomViewLayoutParams(PullToZoomScrollViewEx scrollView) {
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        int mScreenHeight = localDisplayMetrics.heightPixels;
        int mScreenWidth = localDisplayMetrics.widthPixels;
        LinearLayout.LayoutParams localObject = new LinearLayout.LayoutParams(mScreenWidth,
                (int) (9.0F * (mScreenWidth / 16.0F)));
//        (int) (9.0F * (mScreenWidth / 15.0F))
        scrollView.setHeaderViewSize(mScreenWidth, (int)(mScreenWidth*0.55));
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
    public void getMoviesComment(MoviesCommentBO moviesCommentBO) {
        if ("1".equals(moviesCommentBO.getCollectStatus())) {   //已收藏
            isShouCang = true;
            shouCang.setBackgroundResource(R.drawable.sc_dis);
        } else {
            isShouCang = false;
            shouCang.setBackgroundResource(R.drawable.sc);
        }
        if (moviesCommentBO.getWantSee() == null || "0".equals(moviesCommentBO.getWantSee())) {   //不想看
            wantSeeImg.setBackgroundResource(R.drawable.xk);
            wantSeeText.setText("想看");
            isWantSee = false;
            isWantSeeEnable = true;
        } else {
            wantSeeImg.setBackgroundResource(R.drawable.xk_sel);
            wantSeeText.setText("想看");
            isWantSee = true;
            isWantSeeEnable = true;
        }
        if ("1".equals(moviesCommentBO.getWatchRecord())) {   //影片看过,默认想看不能点击
            isWantSeeEnable = false;
            wantSeeImg.setBackgroundResource(R.drawable.xk_sel);
            wantSeeText.setText("看过");
            if ("1".equals(moviesCommentBO.getCommentRecord())) {    //已评论
                isRatingEnable = false;
                ratingImg.setBackgroundResource(R.drawable.pf_sel);
                ratingText.setText("已评");
                ratingMessage.setVisibility(View.VISIBLE);
            } else {
                isRatingEnable = true;
                ratingImg.setBackgroundResource(R.drawable.pf);
                ratingText.setText("评论");
                ratingMessage.setVisibility(View.GONE);
            }
        } else {    //未看过  不能评论
            isWantSeeEnable = true;
            isRatingEnable = false;
            ratingImg.setBackgroundResource(R.drawable.pf);
            ratingText.setText("未评");
            ratingMessage.setVisibility(View.GONE);
        }
    }

    @Override
    public void getShareMessage(ShareBO shareBO) {
        Log.d("ListView", "点击了分享按钮"+shareBO.getId());
        new ShareDialog(this, shareBO).showAtLocation(contentLayout, Gravity.BOTTOM, 0, 0);
    }
    @Override
    public void getCritic(final List<CriticBO> criticBO) {
       if(criticBO==null){
           rm.setVisibility(View.GONE);
       }else {
           rm.setVisibility(View.VISIBLE);
           ismore =  Integer.parseInt(criticBO.get(0).getLeftNum());
           List<CriticBO> newlist = new ArrayList<>();
           if(ismore>0){
               for(int i = 0; i < 3; i++){
                   newlist.add(criticBO.get(i));
               }
               texianshi.setText("查看更多"+criticBO.get(0).getLeftNum()+"条短评");
           }else {
               newlist = criticBO;
               texianshi.setText("已经加载完全部数据");
           }
           adapter = new LGRecycleViewAdapter<CriticBO>(newlist) {
               @Override
               public int getLayoutId(int viewType) {
                   return R.layout.item_critic_person;
               }
               @Override
               public void convert(LGViewHolder holder, CriticBO o, int position) {
                   ImageView  dianzan = (ImageView) holder.getView(R.id.dianzan);
                   TextView nickname = (TextView) holder.getView(R.id.nickname);
                   ImageView  personimg = (ImageView) holder.getView(R.id.person_img);
                   TextView personName = (TextView) holder.getView(R.id.person_name);
                   TextView score = (TextView) holder.getView(R.id.score);
                   TextView createTime = (TextView) holder.getView(R.id.createTime);
                   TextView upvoteNum = (TextView) holder.getView(R.id.upvoteNum);
                   RatingBar ratingbar = (RatingBar) holder.getView(R.id.ratingbar);
                   if(o.getUpvoteStatus()==0){//未点赞
                       dianzan.setBackgroundResource(R.drawable.mydianzan);
                   }else if(o.getUpvoteStatus()==1){
                       dianzan.setBackgroundResource(R.drawable.myzan);
                   }
                   if (!StringUtils.isEmpty(o.getDxAppUser().getHeader())) {//头像
                       Glide.with(MoviesMessageActivity.this).load(o.getDxAppUser().getHeader()).into(personimg);
                   }else {
                       personimg.setImageResource(R.drawable.default_head_img);
                   }
                   nickname.setText(o.getDxAppUser().getNickName());//昵称
                   score.setText(String.valueOf(o.getScore()));
                   personName.setText(o.getComment());
                   createTime.setText(o.getCreateTime());
                   upvoteNum.setText(String.valueOf(o.getUpvoteNum()));
                   ratingbar.setStar(Float.parseFloat(String.valueOf(o.getScore())) / 2);
               }
           };
           criticRecycle.setAdapter(adapter);
           adapter.setOnItemClickListener(R.id.dian, new LGRecycleViewAdapter.ItemClickListener() {
               @Override
               public void onItemClicked(View view, int position) {
                       mPresenter.loadDianZan(criticBO.get(position).getId(),position);
               }
           });
       }

        }
    @Override
    public void getDianZan(CriticBO criticBO,int position) {
        if(criticBO.getUpvoteStatus()==0){
            adapter.getItem(position).setUpvoteStatus(0);
        }else if(criticBO.getUpvoteStatus()==1){
            adapter.getItem(position).setUpvoteStatus(1);
        }
        adapter.getItem(position).setUpvoteNum(criticBO.getUpvoteNum());
        adapter.notifyItemChanged(position);
    }
    /**
     * 初始化控件
     */
    private void initView() {
        personRecycle = (RecyclerView) scollview.getPullRootView().findViewById(R.id.recyle);
        videoRecycle = (RecyclerView) scollview.getPullRootView().findViewById(R.id.recyle_video);
        photoRecycle = (RecyclerView) scollview.getPullRootView().findViewById(R.id.recyle_photo);
        criticRecycle = (RecyclerView) scollview.getPullRootView().findViewById(R.id.recyle_critic);
        rm = (LinearLayout) scollview.getPullRootView().findViewById(R.id.rm);
        texianshi = (TextView) scollview.getPullRootView().findViewById(R.id.texianshi);
        moviesNarrate = (TextView) scollview.getPullRootView().findViewById(R.id.movies_narrate);
        unfold = (TextView) scollview.getPullRootView().findViewById(R.id.unfold);
        wantSee = (LinearLayout) scollview.getPullRootView().findViewById(R.id.want_see);
        wantSeeImg = (ImageView) scollview.getPullRootView().findViewById(R.id.want_see_img);
        wantSeeText = (TextView) scollview.getPullRootView().findViewById(R.id.want_see_text);
        haveRating = (LinearLayout) scollview.getPullRootView().findViewById(R.id.have_rating);
        ratingImg = (ImageView) scollview.getPullRootView().findViewById(R.id.have_rating_img);
        ratingText = (TextView) scollview.getPullRootView().findViewById(R.id.rating_message);
        ratingMessage = (TextView) scollview.getPullRootView().findViewById(R.id.rating_type);
        goBack = (LinearLayout) scollview.getHeaderView().findViewById(R.id.go_back);
        movieImg = (ImageView) scollview.getHeaderView().findViewById(R.id.movie_img);
        ViewGroup.LayoutParams params = movieImg.getLayoutParams();
        params.width = (int) (ScreenUtils.getScreenWidth() * 0.3);
        params.height = (int) (ScreenUtils.getScreenWidth() * 0.42);
        movieImg.setLayoutParams(params);

        movieName = (TextView) scollview.getHeaderView().findViewById(R.id.movies_name);
        movieMessage = (TextView) scollview.getHeaderView().findViewById(R.id.movie_message);
        movieType = (ImageView) scollview.getHeaderView().findViewById(R.id.movie_type);
        movieClass = (TextView) scollview.getHeaderView().findViewById(R.id.movie_class);
        movieTime = (TextView) scollview.getHeaderView().findViewById(R.id.movie_time);
        movieAllTime = (TextView) scollview.getHeaderView().findViewById(R.id.movie_all_time);
        shouCang = (ImageView) scollview.getHeaderView().findViewById(R.id.shoucang);
        shareImg = (ImageView) scollview.getHeaderView().findViewById(R.id.fenxiang_img);
        view = scollview.getHeaderView().findViewById(R.id.view);

        ViewGroup.LayoutParams params1 = view.getLayoutParams();
        params.height = (int) (ScreenUtils.getScreenWidth() * 0.07);
        view.setLayoutParams(params1);

        movieBg = (ImageView) scollview.getZoomView().findViewById(R.id.imageView);

        ViewGroup.LayoutParams layoutParams = movieBg.getLayoutParams();
        params.height = (int) (ScreenUtils.getScreenWidth() * 0.6);
        movieBg.setLayoutParams(layoutParams);

        unfold.setOnClickListener(this);
        goBack.setOnClickListener(this);
        shouCang.setOnClickListener(this);
        shareImg.setOnClickListener(this);
        wantSee.setOnClickListener(this);
        haveRating.setOnClickListener(this);
        texianshi.setOnClickListener(this);
    }

}