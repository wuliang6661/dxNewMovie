package com.myp.cinema.ui;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.myp.cinema.R;
import com.myp.cinema.base.BaseWebActivity;
import com.myp.cinema.entity.ShareBO;
import com.myp.cinema.widget.ShareDialog;
import com.tencent.smtt.sdk.WebView;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wuliang on 2017/6/21.
 * <p>
 * 跳转H5的界面
 */

public class WebViewActivity extends BaseWebActivity
        implements View.OnClickListener {

    @Bind(R.id.webview)
    WebView webview;
    @Bind(R.id.go_back)
    LinearLayout goBack;
    @Bind(R.id.fenxiang_img)
    ImageView fenxiangimg;
    @Bind(R.id.linear)
    LinearLayout linear;
    String url;
    AudioManager mAudioManager;
    private String style;
    private String title;
    private String content;
    private String logo;
    private HashMap<String, String> extraHeaders;

    @Override
    protected int getLayout() {
        return R.layout.webview_layout;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        goBack.setOnClickListener(this);
        fenxiangimg.setOnClickListener(this);


        style = getIntent().getExtras().getString("fenxiang");
        if(style==null){
            url = getIntent().getExtras().getString("url");
            initWebView(webview);
            extraHeaders = new HashMap<String, String>();
            extraHeaders.put("accessType", "app_android");
            webview.loadUrl(url,extraHeaders);

        }else {
            fenxiangimg.setVisibility(View.VISIBLE);
            title=getIntent().getExtras().getString("title");//pic

            logo=getIntent().getExtras().getString("pic");
            content =getIntent().getExtras().getString("description");
            url = getIntent().getExtras().getString("url");
            Log.d("分享的内容", "onCreate: "+title);
            Log.d("分享的内容", "onCreate: "+logo);
            Log.d("分享的内容", "onCreate: "+content);
            Log.d("分享的内容", "onCreate: "+url);
            initWebView(webview);
            extraHeaders = new HashMap<String, String>();
            extraHeaders.put("accessType", "app_android");
            Log.d("H555", "onCreate: "+url);
            webview.loadUrl(url,extraHeaders);
        }



    }


    /**
     * 如果网页还有上一层，则返回上一层网页
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webview.canGoBack()) {
           String  back = getIntent().getExtras().getString("back");
            if(back==null){
                webview.goBack();
                return true;
            }
            else {

            }


        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go_back:
                if (webview.canGoBack()) {
                    String  back = getIntent().getExtras().getString("back");
                    if(back==null){
                        webview.goBack();

                    }
                    else {
                        finish();
                    }
                } else {
                    finish();
                }
                break;
            case R.id.fenxiang_img:
                ShareBO shareBO = new ShareBO(content,logo, url, title);
                new ShareDialog(this, shareBO).showAtLocation(linear, Gravity.BOTTOM, 0, 0);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        if( webview!=null) {

            // 如果先调用destroy()方法，则会命中if (isDestroyed()) return;这一行代码，需要先onDetachedFromWindow()，再
            // destory()
            ViewParent parent = webview.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(webview);
            }

            webview.stopLoading();
            // 退出时调用此方法，移除绑定的服务，否则某些特定系统会报错
            webview.getSettings().setJavaScriptEnabled(false);
            webview.clearHistory();
            webview.clearView();
            webview.removeAllViews();
            webview.destroy();

        }
        super.onDestroy();




//        webview.destroy();
//        super.onDestroy();
    }
}
