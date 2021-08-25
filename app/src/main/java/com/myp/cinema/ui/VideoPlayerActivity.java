package com.myp.cinema.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.afollestad.easyvideoplayer.EasyVideoCallback;
import com.afollestad.easyvideoplayer.EasyVideoPlayer;
import com.myp.cinema.R;
import com.myp.cinema.base.BaseActivity;
import com.myp.cinema.util.LogUtils;

import butterknife.Bind;

/**
 * Created by wuliang on 2017/6/22.
 * <p>
 * 视频播放的页面
 */

public class VideoPlayerActivity extends BaseActivity implements EasyVideoCallback {


    @Bind(R.id.player)
    EasyVideoPlayer player;

    String videoUrl;

    @Override
    protected int getLayout() {
        return R.layout.video_player_layout;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        assert player != null;
        player.setCallback(this);
        Bundle bundle = getIntent().getExtras();
        videoUrl = bundle.getString("video");
        LogUtils.I(videoUrl);
        player.setSource(Uri.parse(videoUrl));
        player.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        player.pause();
    }

    @Override
    public void onStarted(EasyVideoPlayer player) {
    }

    @Override
    public void onPaused(EasyVideoPlayer player) {
    }

    @Override
    public void onPreparing(EasyVideoPlayer player) {
    }

    @Override
    public void onPrepared(EasyVideoPlayer player) {
    }

    @Override
    public void onBuffering(int percent) {
    }

    @Override
    public void onError(EasyVideoPlayer player, Exception e) {

    }

    @Override
    public void onCompletion(EasyVideoPlayer player) {
    }

    @Override
    public void onRetry(EasyVideoPlayer player, Uri source) {
        Toast.makeText(this, "Retry", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSubmit(EasyVideoPlayer player, Uri source) {
        Toast.makeText(this, "Submit", Toast.LENGTH_SHORT).show();
    }


}
