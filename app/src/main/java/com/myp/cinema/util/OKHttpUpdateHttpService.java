package com.myp.cinema.util;

/**
 * Created by Witness on 2020/7/14
 * Describe:
 */
import androidx.annotation.NonNull;

import com.xuexiang.xupdate.proxy.IUpdateHttpService;
import com.xuexiang.xupdate.utils.UpdateUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;

/**
 * 使用okhttp
 *
 * @author xuexiang
 * @since 2018/7/10 下午4:04
 */
public class OKHttpUpdateHttpService implements IUpdateHttpService {

    private boolean mIsPostJson;

    public OKHttpUpdateHttpService() {
        this(false);
    }

    public OKHttpUpdateHttpService(boolean isPostJson) {
        mIsPostJson = isPostJson;
    }


    @Override
    public void asyncGet(@NonNull String url, @NonNull Map<String, Object> params, final @NonNull IUpdateHttpService.Callback callBack) {
        OkHttpUtils.get()
                .url(url)
                .params(transform(params))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callBack.onError(e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        callBack.onSuccess(response);
                    }
                });
    }

    @Override
    public void asyncPost(@NonNull String url, @NonNull Map<String, Object> params, final @NonNull IUpdateHttpService.Callback callBack) {
        //这里默认post的是Form格式，使用json格式的请修改 post -> postString
        RequestCall requestCall;
        if (mIsPostJson) {
            requestCall = OkHttpUtils.postString()
                    .url(url)
                    .content(UpdateUtils.toJson(params))
                    .mediaType(MediaType.parse("application/json; charset=utf-8"))
                    .build();
        } else {
            requestCall = OkHttpUtils.post()
                    .url(url)
                    .params(transform(params))
                    .build();
        }
        requestCall
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callBack.onError(e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        callBack.onSuccess(response);
                    }
                });
    }

    @Override
    public void download(@NonNull String url, @NonNull String path, @NonNull String fileName, final @NonNull IUpdateHttpService.DownloadCallback callback) {
        OkHttpUtils.get()
                .url(url)
                .tag(url)
                .build()
                .execute(new FileCallBack(path, fileName) {
                    @Override
                    public void inProgress(float progress, long total, int id) {
                        callback.onProgress(progress, total);
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callback.onError(e);
                    }

                    @Override
                    public void onResponse(File response, int id) {
                        callback.onSuccess(response);
                    }

                    @Override
                    public void onBefore(Request request, int id) {
                        super.onBefore(request, id);
                        callback.onStart();
                    }
                });
    }

    @Override
    public void cancelDownload(@NonNull String url) {
        OkHttpUtils.getInstance().cancelTag(url);
    }

    private Map<String, String> transform(Map<String, Object> params) {
        Map<String, String> map = new TreeMap<>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            map.put(entry.getKey(), entry.getValue().toString());
        }
        return map;
    }


}
