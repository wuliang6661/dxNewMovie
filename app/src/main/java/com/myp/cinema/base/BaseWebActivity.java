package com.myp.cinema.base;

import android.graphics.PixelFormat;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.myp.cinema.util.StringUtils;
import com.myp.cinema.widget.webview.WebAppInterface;
import com.myp.cinema.widget.webview.WebClient;
import com.myp.cinema.widget.webview.WebViewChromeClient;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;

/**
 * Created by wuliang on 2017/4/11.
 * <p>
 * 初始化webview的activity父类
 */

public abstract class BaseWebActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
    }

    /**
     * 初始化webview的实例
     */
    public void initWebView(WebView view) {
        initWebViewSettings(view);
        WebViewChromeClient chromeClient = new WebViewChromeClient(this);
        chromeClient.setOnReceivedListener(new WebViewChromeClient.onReceivedMessage() {   //设置标题
            @Override
            public void getTitle(String title) {
                if (StringUtils.isEmpty(title) || title.startsWith("https") || title.startsWith("dxm")) {
                    return;
                }
                setTitle(title);
            }
        });
        view.setWebChromeClient(chromeClient);
        view.setWebViewClient(new WebClient(this));
        view.addJavascriptInterface(new WebAppInterface(this, view), "Android");
        view.getView().setClickable(true);
        view.setHorizontalScrollBarEnabled(false);
        view.setVerticalScrollBarEnabled(false);
        //下面方法去掉
//        IX5WebViewExtension ix5 = view.getX5WebViewExtension();
//        if (null != ix5) {
//            ix5.setScrollBarFadingEnabled(false);
//        }
    }

    /**
     * 初始化webview的各种属性
     */
    private void initWebViewSettings(WebView mWebView) {
        WebSettings webSetting = mWebView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(true);
        // webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        // webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        // webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);

        // this.getSettingsExtension().setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);//extension
        // settings 的设计
        webSetting.setAppCachePath(this.getDir("appcache", 0).getPath());
        webSetting.setDatabasePath(this.getDir("databases", 0).getPath());
        webSetting.setGeolocationDatabasePath(this.getDir("geolocation", 0)
                .getPath());
        webSetting.setAllowUniversalAccessFromFileURLs(true);
        CookieSyncManager.createInstance(this);
        CookieSyncManager.getInstance().sync();
    }
}
