package com.myp.cinema.api;

import com.myp.cinema.entity.aCinemaSeatTableBO;
import com.myp.cinema.util.MD5;
import com.myp.cinema.util.rx.RxResultHelper;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import rx.Observable;

/**
 * Created by wuliang on 2017/4/19.
 * <p>
 * 所有网络请求方法
 */

public class HttpServiceIml {

    static HttpService service;

    static final String _AUCHTODE = HttpService._AUCHTODE;
    static final String PID = HttpService.PID;

    /**
     * 获取代理对象
     *
     * @return
     */
    public static HttpService getService() {
        if (service == null)
            service = ApiManager.getInstance().configRetrofit(HttpService.class, HttpService.URL);
        return service;
    }

    /**
     * 获取授权码
     *
     * @return
     */
    public static String get_Sig(String params) {
        return MD5.strToMd5Low32(MD5.strToMd5Low32(_AUCHTODE + params) + _AUCHTODE);
    }


    /**
     * 获取某场次座位状态
     */
    public static Observable<List<aCinemaSeatTableBO>> getCinemasSeatStatus(String cid, String playId, String updateTime) {
        String params = "";
        SortedMap<String, Object> keyAndValues = new TreeMap<>();
        keyAndValues.put("format", "json");
        keyAndValues.put("pid", HttpService.PID);
        keyAndValues.put("cid", cid);
        keyAndValues.put("play_id", playId);
        keyAndValues.put("play_update_time", updateTime);
        for (String key : keyAndValues.keySet()) {
            params += key + "=" + keyAndValues.get(key) + "&";
        }
        String _sig = HttpServiceIml.get_Sig(params.substring(0, params.length() - 1));

        return getService().getCinemaSeatStatus("json", cid, HttpService.PID, playId, updateTime, _sig)
                .compose(RxResultHelper.<List<aCinemaSeatTableBO>>httpDingRusult());
    }

}