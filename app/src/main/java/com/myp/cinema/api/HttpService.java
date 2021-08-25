package com.myp.cinema.api;

import com.myp.cinema.entity.BaseDingResult;
import com.myp.cinema.entity.aCinemaSeatTableBO;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by wuliang on 2017/3/9.
 * <p>
 * 此处存放鼎新服务器的所有接口数据
 */

interface HttpService {

    String URL = "http://api.open.yinghezhong.com/";  //正式环境
    String _AUCHTODE = "a>N/+_gNYE";   //正式环境
    String PID = "11134";               //正式环境
//    String URL = "http://api.platform.yinghezhong.com/";   //测试服
//    String _AUCHTODE = "4=NA>kUIW?";   //测试
//    String PID = "90073";           //测试


    /**
     * 获取某场次座位状态
     */
    @FormUrlEncoded
    @POST("/play/seat-status")
    Observable<BaseDingResult<List<aCinemaSeatTableBO>>> getCinemaSeatStatus(
            @Field("format") String format,
            @Field("cid") String cid,
            @Field("pid") String pid,
            @Field("play_id") String playId,
            @Field("play_update_time") String updateTime,
            @Field("_sig") String _sig);


}
