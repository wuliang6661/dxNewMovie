package com.myp.cinema.ui.main;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myp.cinema.R;
import com.myp.cinema.api.HttpInterface;
import com.myp.cinema.base.BaseWebFragment;
import com.tencent.smtt.sdk.WebView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wuliang on 2017/6/22.
 * <p>
 * 好玩H5页面
 */

public class PlayfulWebFragment extends BaseWebFragment {

    @Bind(R.id.webview)
    WebView webview;
    @Bind(R.id.go_back)
    LinearLayout goBack;
    @Bind(R.id.title)
    TextView title;


    String url = HttpInterface.URL + "/api/Movie/fun";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_playful_web, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        goBack.setVisibility(View.GONE);
        title.setText("互动");

        initWebView(webview);

    }


    @Override
    public void onResume() {
        super.onResume();
        webview.loadUrl(url);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
