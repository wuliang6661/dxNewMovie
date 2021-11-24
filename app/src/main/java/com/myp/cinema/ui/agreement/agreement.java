package com.myp.cinema.ui.agreement;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.myp.cinema.R;
import com.myp.cinema.base.BaseWebActivity;

import butterknife.Bind;

/**会员卡充值协议
 * Created by Administrator on 2018/1/31.
 */

public class agreement extends BaseWebActivity {
    @Bind(R.id.go_back)
    LinearLayout goBack;
    @Bind(R.id.webview)
    com.tencent.smtt.sdk.WebView webview;
    @Override
    protected int getLayout() {
        return R.layout.bendih5;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWebView(webview);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webview.canGoBack()) {
                    webview.goBack();
                } else {
                    finish();
                }
            }
        });
        webview.loadUrl("file:///android_asset/test.html");
    }
}
