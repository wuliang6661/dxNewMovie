package com.myp.cinema.api;

import com.myp.cinema.entity.ActivityBO;
import com.myp.cinema.entity.AppVersionBO;
import com.myp.cinema.entity.BaseBO;
import com.myp.cinema.entity.BaseResult;
import com.myp.cinema.entity.CardBO;
import com.myp.cinema.entity.CinemaBo;
import com.myp.cinema.entity.CommentBO;
import com.myp.cinema.entity.ConfirmPayBO;
import com.myp.cinema.entity.CouponDetailBO;
import com.myp.cinema.entity.CriticBO;
import com.myp.cinema.entity.FavourBO;
import com.myp.cinema.entity.FeedBackListBO;
import com.myp.cinema.entity.GameBO;
import com.myp.cinema.entity.HotSellBO;
import com.myp.cinema.entity.HotSellBannerBO;
import com.myp.cinema.entity.HotWireBO;

import com.myp.cinema.entity.LockSeatsBO;
import com.myp.cinema.entity.LunBoAndBO;
import com.myp.cinema.entity.LunBoBO;
import com.myp.cinema.entity.MoviesByCidBO;
import com.myp.cinema.entity.MoviesCommentBO;
import com.myp.cinema.entity.MoviesSoonBO;
import com.myp.cinema.entity.OpenCardBO;
import com.myp.cinema.entity.OrderBO;
import com.myp.cinema.entity.OrderDetailBO;
import com.myp.cinema.entity.OrderNumBO;
import com.myp.cinema.entity.PayBO;
import com.myp.cinema.entity.PayCardBO;
import com.myp.cinema.entity.PicVerificBO;
import com.myp.cinema.entity.ProdectBO;
import com.myp.cinema.entity.RecentBean;
import com.myp.cinema.entity.RechBo;
import com.myp.cinema.entity.RefundBO;
import com.myp.cinema.entity.ResuBo;
import com.myp.cinema.entity.SessionBO;
import com.myp.cinema.entity.ShareBO;
import com.myp.cinema.entity.ShopBO;
import com.myp.cinema.entity.ShopOrderBO;
import com.myp.cinema.entity.SubmitPrdectOrderBO;
import com.myp.cinema.entity.SumptionBo;
import com.myp.cinema.entity.UserBO;
import com.myp.cinema.entity.UserCouponBO;
import com.myp.cinema.entity.WXPayBO;

import com.myp.cinema.entity.preferentialnumberBo;
import com.myp.cinema.entity.threelandingBo;
import com.myp.cinema.ui.Prizesreading.HomeTopBean;
import com.myp.cinema.entity.RechatBo;

import java.util.List;


import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by wuliang on 2017/6/6.
 * <p>
 * ??????????????????
 */

public interface HttpInterface {
//      String URL = "http://hyg.happydoit.com";//??????????????????
      String URL = "https://dxm.happydoit.com";  //12???1?????????
//    String URL = "http://192.168.1.104:8080";
    /**
     * ????????????
     */
    @FormUrlEncoded
    @POST("/api/appuser/login")
    Observable<BaseResult<UserBO>> userLogin(@Field("mobile") String mobile,
                                             @Field("password") String password, @Field("uuid") String uuid);


    @FormUrlEncoded
    @POST("/api/appuser/social/login")
    Observable<threelandingBo> userLoginid(@Field("wxUserId") String wxUserId,
                                           @Field("wbUserId") String wbUserId,
                                           @Field("qqUserId") String qqUserId
                                                  );
    /**
     * ????????????
     */
    @POST("/api/appuser/logout")
    Observable<BaseResult<String>> userlogout();
    /**
     * ????????????
     */
    @FormUrlEncoded
    @POST("/api/appuser/register")
    Observable<BaseResult<UserBO>> userRegister(@Field("cinemaId") String cinemaId,@Field("mobile") String mobile, @Field("password") String password,
                                                @Field("verification") String verification, @Field("gender") String gender);

    /**
     * ???????????????
     */
    @FormUrlEncoded
    @POST("/api/appuser/verification")
    Observable<BaseResult<String>> userVerfication(@Field("mobile") String mobile,
                                                   @Field("verificationType") String verificationType,
                                                   @Field("verifycode") String verifycode);

    /**
     * ?????????????????????
     */
    @POST("/api/appuser/verifyPath")
    Observable<BaseResult<PicVerificBO>> picVerfication();

    /**
     * ????????????
     */
    @FormUrlEncoded
    @POST("/api/appuser/verifuser")
    Observable<BaseResult<UserBO>> userVerifuser(@Field("mobile") String mobile,
                                                 @Field("verification") String verification);

    /**
     * ???????????????????????????
     */
    @FormUrlEncoded
    @POST("/api/appuser/bind/mobile")
    Observable<threelandingBo> phonebinding(@Field("header") String header,
                                            @Field("mobile") String mobile,
                                            @Field("wxUserId") String wxUserId,
                                            @Field("wbUserId") String wbUserId,
                                            @Field("qqUserId") String qqUserId,
                                            @Field("verification") String verification);
    /**
     * ???????????????????????????
     * mobile,pwd,header,nickname,gender,wxUserId,wbUserId,qqUserId
     *
     */
    @FormUrlEncoded
    @POST("/api/appuser/social/signup")
    Observable<threelandingBo> thirdregist(@Field("cinemaId") String cinemaId,
                                           @Field("mobile") String mobile,
                                           @Field("password") String password,
                                           @Field("header") String header,
                                           @Field("nickName") String nickName,
                                           @Field("gender") String gender,
                                           @Field("wxUserId") String wxUserId,
                                           @Field("wbUserId") String wbUserId,
                                           @Field("qqUserId") String qqUserId);
    /**
     * ????????????
     */
    @FormUrlEncoded
    @POST("/api/appuser/update")
    Observable<BaseResult<UserBO>> userUpdatePass(@Field("password") String password, @Field("pwd") String pwd);

    /**
     * ????????????(?????????cookile?????????????????????????????????cookie????????????????????????????????????)
     */
    @Multipart
    @POST("/api/appuser/update")
    Observable<BaseResult<UserBO>> uploadFile(@Part("headerImage\"; filename=\"avatar.jpg") RequestBody file);

    /**
     * ????????????
     */
    @FormUrlEncoded
    @POST("/api/appuser/update")
    Observable<BaseResult<UserBO>> updateName(@Field("nickName") String nickName);

    /**
     * ????????????
     */
    @FormUrlEncoded
    @POST("/api/appuser/update")
    Observable<BaseResult<UserBO>> updateUserSex(@Field("gender") int sex);

    /**
     * ????????????
     */
    @FormUrlEncoded
    @POST("/api/appuser/bindcard")
    Observable<BaseResult<CardBO>> cardBindUser(@Field("cinemaId") String cinemaId, @Field("card") String card,
                                                @Field("pwd") String pwd
                                                );


    /**
     * ?????????????????????
     */
    @POST("/api/appuser/usercard")
    Observable<BaseResult<List<CardBO>>> cardUser();


    /**
     * ???????????????????????????
     */
    @FormUrlEncoded
    @POST("/api/member/card/registRule")
    Observable<BaseResult<OpenCardBO>> getCardPay(@Field("cinemaId") String cinemaId);

    /**
     * ???????????????????????????
     */
    @FormUrlEncoded
    @POST("/api/card/openCardNotice")
    Observable<BaseResult<String>> getCardNotice(@Field("cinemaId") String cinemaId);

    /**
     * ???????????????(?????????)
     */
    @FormUrlEncoded
    @POST("/api/member/card/recharge/alipay")
    Observable<BaseResult<PayBO>> bindCard(@Field("openCard") String openCard, //??? 1
                                           @Field("cinemaId") String cinemaId,
                                           @Field("cardNum") String cardNum,
                                           @Field("password") String password,
                                           @Field("username") String username,
                                           @Field("sex") String sex,//???   ???
                                           @Field("birthday") String birthday,
                                           @Field("idCard") String idCard,
                                           @Field("rechargeMoney") String rechargeMoney);

    /**
     * ???????????????(??????)
     */
    @FormUrlEncoded
    @POST("/api/member/card/recharge/wxpay")
    Observable<BaseResult<WXPayBO>> bindCardWechat(@Field("openCard") String openCard, //??? 1
                                                   @Field("cinemaId") String cinemaId,
                                                   @Field("cardNum") String cardNum,
                                                   @Field("password") String password,
                                                   @Field("username") String username,
                                                   @Field("sex") String sex,//???   ???
                                                   @Field("birthday") String birthday,
                                                   @Field("idCard") String idCard,
                                                   @Field("rechargeMoney") String rechargeMoney);


    /**
     * ????????????????????????
     */
    @FormUrlEncoded
    @POST("/api/dxInsider/unbind")
    Observable<BaseBO> unBindCard(@Field("id") String id);


    /**
     * ????????????????????????
     */
    @FormUrlEncoded
    @POST("/api/cinema/cinemas")
    Observable<BaseResult<List<CinemaBo>>> cinemaList(@Field("city") String city, @Field("longitude") String longitude,
                                                      @Field("latitude") String latitude);

    /**
     * ??????????????????
     */
    @FormUrlEncoded
    @POST("/api/cinema/cinemacity")
    Observable<BaseResult<List<String>>> cityList(@Field("source") String source);


    /**
     * ?????????????????????
     */
    @FormUrlEncoded
    @POST("/api/banner/banners")
    Observable<BaseResult<List<LunBoBO>>> lunboList(@Field("source") String source,
                                                    @Field("cinemaId") String cinemaId);

    /**
     * ?????????????????????
     */

    @FormUrlEncoded
    @POST("/api/new/banners")
    Observable<BaseResult<LunBoAndBO>> lunboandList(@Field("source") String source,
                                                    @Field("cinemaId") String cinemaId);

    /**
     * ??????????????????
     */

    @FormUrlEncoded
    @POST("/api/cinema/jumpImage")
    Observable<BaseResult<ActivityBO>> getActivity(@Field("cinemaId") String cinemaId);


    /**LunBoAndBO
     * ??????????????????????????????
     */
    @FormUrlEncoded
    @POST("/api/Movie/hotMovie")
    Observable<BaseResult<List<MoviesByCidBO>>> moviesHot(@Field("cinemaId") String cinameId);

    /**
     * ??????????????????????????????
     */
    @FormUrlEncoded
    @POST("/api/Movie/soonMovie")
    Observable<BaseResult<List<MoviesSoonBO>>> moviesSoon(@Field("cinemaId") String cinameId);

    /**
     * ??????????????????
     */
    @FormUrlEncoded
    @POST("/api/Movie/MoviesByCid")
    Observable<BaseResult<List<MoviesByCidBO>>> moviesByCid(@Field("cinemaId") String cinameId);

    /**
     * ??????????????????
     */
    @FormUrlEncoded
    @POST("/api/merchandise/merchandiseList")
    Observable<BaseResult<List<HotSellBO>>> getHotSell(@Field("cinemaId") String cinemaCode);

    /**
     * ???????????????
     */
    @FormUrlEncoded
    @POST("/api/banner/banners")
    Observable<BaseResult<List<HotSellBannerBO>>> getBanner(@Field("cinemaId") String cinemaCode,
                                                            @Field("source") String category);
    /**
     * ??????????????????
     */
    @FormUrlEncoded
    @POST("/api/order/countMerchaniseOrderPrice")
    Observable<BaseResult<ProdectBO>> getOrderPrice(@Field("cinemaId") String cinemaId,
                                                    @Field("merchandiseInfo") String merchandiseinfo,
                                                    @Field("merTicketId") String merTicketId);

    /**
     * ??????????????????
     */
    @FormUrlEncoded
    @POST("/api/order/submitMerchaniseOrder")
    Observable<BaseResult<SubmitPrdectOrderBO>> submitProdectOrder(@Field("cinemaId") String cinemaId,
                                                                   @Field("merchandiseInfo") String merchandiseinfo,
                                                                   @Field("orderPhone") String phone,
                                                                   @Field("memo") String memo,
                                                                   @Field("cinemaNumber") String cinemaNumber,
                                                                   @Field("merTicketId") String merTicketId);

    /**
     * ????????????
     */
    @FormUrlEncoded
    @POST("/api/new/order/submitorder")
    Observable<BaseResult<ConfirmPayBO>> orderSubmit(@Field("cinemaId") String cinemaId,
                                                     @Field("orderName") String orderName,
                                                     @Field("seats") String seats,
                                                     @Field("seatId") String seatId,
                                                     @Field("ticketOriginPrice") String ticketOriginPrice,
                                                     @Field("ticketNum") String ticketNum,
                                                     @Field("cinemaNumber") String cinemaNumber,
                                                     @Field("hallId") String hallId,
                                                     @Field("playId") String playId,
                                                     @Field("cineMovieNum") String cineMovieNum,
                                                     @Field("seatTicketId") String seatTicketId,
                                                     @Field("merchandiseInfo") String merchandiseInfo,
                                                     @Field("merTicketId") String merTicketId,
                                                     @Field("orderPhone") String orderPhone,
                                                     @Field("memo") String memo,
                                                     @Field("preferPrice") String preferPrice,
                                                     @Field("globalPreferPrice") String globalPreferPrice,
                                                     @Field("globalCanBuyNum") String globalCanBuyNum);

    /**
     * ????????????(0?????????)
     */
    @FormUrlEncoded
    @POST("/api/order/createOrderAndPay")
    Observable<BaseResult<ConfirmPayBO>> orderZeroSubmit(@Field("cinemaId") String cinemaId,
                                                         @Field("orderName") String orderName,
                                                         @Field("seats") String seats,
                                                         @Field("seatId") String seatId,
                                                         @Field("ticketOriginPrice") String ticketOriginPrice,
                                                         @Field("ticketNum") String ticketNum,
                                                         @Field("cinemaNumber") String cinemaNumber,
                                                         @Field("hallId") String hallId,
                                                         @Field("playId") String playId,
                                                         @Field("cineMovieNum") String cineMovieNum,
                                                         @Field("seatTicketId") String seatTicketId,
                                                         @Field("merchandiseInfo") String merchandiseInfo,
                                                         @Field("merTicketId") String merTicketId,
                                                         @Field("orderPhone") String orderPhone,
                                                         @Field("memo") String memo,
                                                         @Field("preferPrice") String preferPrice,
                                                         @Field("globalPreferPrice") String globalPreferPrice,
                                                         @Field("globalCanBuyNum") String globalCanBuyNum);

    /**
     * ??????????????????
     */
    @FormUrlEncoded
    @POST("/api/order/modifyOrderPrice")
    Observable<ResponseBody> modifyOrderPrice(@Field("cinemaId") String cinemaId,
                                              @Field("partnerPrice") String partnerPrice,
                                              @Field("marketPrice") String marketPrice,
                                              @Field("activityPriceNum") String activityPriceNum,
                                              @Field("ticketNum") String ticketNum,
                                              @Field("beforeTicketPrice") String beforeTicketPrice,
                                              @Field("totalDisprice") String totalDisprice,
                                              @Field("payPrice") String payPrice,
                                              @Field("seatTicketId") String seatTicketId,
                                              @Field("merTicketId") String merTicketId,
                                              @Field("merchandiseInfo") String merchandiseInfo,
                                              @Field("appUserId") String appUserId,
                                              @Field("preferPrice") String preferPrice,
                                              @Field("globalPreferPrice") String globalPreferPrice,
                                              @Field("globalCanBuyNum") String globalCanBuyNum,
                                              @Field("payType") String payType);// 1 ????????? 0 ??????

    /**
     * ???????????????
     */
    @FormUrlEncoded
    @POST("/api/order/coupon/pay/balance")
    Observable<ResponseBody> verifyCoupon(@Field("coupon") String coupon,
                                          @Field("orderNum") String orderNum);


    /**
     * ??????????????????
     */
    @FormUrlEncoded
    @POST("/api/order/orderlist")
    Observable<BaseResult<List<LockSeatsBO>>> orserList(@Field("orderType") String orderType,  //0????????????1 ??????
                                                    @Field("payStatus") String payStatus,  //0 ?????????  1 ?????????
                                                    @Field("orderPage") int orderPage,
                                                    @Field("orderSize") String orderSize);

    /**
     * ??????????????????
     */
    @FormUrlEncoded
    @POST("/api/order/detail")
    Observable<BaseResult<OrderDetailBO>> orderMessage(@Field("id") String id,  //0??????????????????1???????????????
                                           @Field("cinemaId") String cinemaId);


    /**
     * ??????????????????
     */
    @FormUrlEncoded
    @POST("/api/order/refund/info")
    Observable<RefundBO> refundinfo(@Field("id") String id //0??????????????????1???????????????
    );
    @FormUrlEncoded
    @POST("/api/order/refund")
    Observable<RefundBO> refund(@Field("id") String id //0??????????????????1???????????????
                                ,@Field("cinemaId") String cinemaId
    );

//    @FormUrlEncoded
//    @POST("/api/order/detail")
//    Call<requstBean> orderMessage(@Field("id") String id,  //0??????????????????1???????????????
//                                  @Field("cinemaId") String cinemaId);

    /**
     * ????????????????????????
     */
    @FormUrlEncoded
    @POST("/api/order/queryorder")
    Observable<BaseResult<OrderBO>> orderQuery(@Field("appUserId") String appUserId ,
                                               @Field("orderNum") String orderNum);


    /**
     * ?????????????????????????????????
     */
    @POST("/api/new/order/checkorder")
    Observable<BaseResult<OrderNumBO>> orderCheck();

    /**
     * ?????????????????????
     */
    @FormUrlEncoded
    @POST("/api/order/cancelorder")
    Observable<BaseResult<OrderNumBO>> orderCancel(@Field("orderNum") String orderNum);
    /**
     * ?????????????????????
     */
    @FormUrlEncoded
    @POST("/api/global/can/buy/num")
    Observable<BaseResult<preferentialnumberBo>> getsets(@Field("cinemaId") String cinemaId, @Field("playId") String playId);
    /**
     * ??????????????????
     */
    @FormUrlEncoded
    @POST("/api/order/countOrder")
    Observable<ResponseBody> lockSeats (@Field("orderName") String orderName,
                                                   @Field("seats") String seats,       //??????????????????  1???1????????????????????????\
                                                   @Field("seatId") String seatId,
                                                   @Field("ticketOriginPrice") String ticketOriginPrice,     //??????
                                                   @Field("ticketNum") String ticketNum,
                                                   @Field("cinemaNumber") String cinemaNumber,//??????????????????????????????
                                                   @Field("hallId") String hallId,  //????????????id
                                                   @Field("playId") String playId, //????????????id
                                                   @Field("cineMovieNum") String cineMovieNum,//?????????????????????????????????????????????);
                                                   @Field("preferPrice") String preferPrice,
                                                   @Field("globalPreferPrice") String globalPreferPrice,
                                                   @Field("globalCanBuyNum") String globalCanBuyNum);
    /**
     * ???????????????????????????
     */
    @FormUrlEncoded
    @POST("/api/order/cardprice")
    Observable<BaseResult<PayCardBO>> cardPayPrice(@Field("orderNum") String orderNum, @Field("card") String card);


    /**
     * ???????????????
     */
    @FormUrlEncoded
    @POST("/api/order/card")
    Observable<BaseResult<ResuBo>> payCard(@Field("orderNum") String orderNum, @Field("pwd") String pwd,
                                           @Field("card") String card);

    /**
     * ???????????????
     */
    @FormUrlEncoded
    @POST("/api/order/alipay")
    Observable<BaseResult<PayBO>> payAlipay(@Field("orderNum") String orderNum);


    /**
     * ????????????
     */
    @FormUrlEncoded
    @POST("/api/order/wechat")
    Observable<BaseResult<WXPayBO>> payWeChat(@Field("orderNum") String orderNum);
    /**
     * ???????????????
     */
    @FormUrlEncoded
    @POST("/api/order/coupon/pay")
    Observable<ResponseBody> loadcardPay(@Field("orderNum") String orderNum,@Field("coupon") String coupon);

    /**
     * ?????????????????????
     */
    @FormUrlEncoded
    @POST("/api/article/articles")
    Observable<BaseResult<List<HotWireBO>>> hotWire(@Field("articleType") String articleType,
                                                    @Field("articlePage") String articlePage,
                                                    @Field("articleSize") String articleSize,
                                                    @Field("cinemaId") String cinemaId);
    /**
     * ????????????????????????
     */
    @FormUrlEncoded
    @POST("/api/appuser/keep/article/list")
    Observable<BaseResult<List<HotWireBO>>> articlesCollection(@Field("pageNo") String pageNo,
                                                    @Field("pageSize") String pageSize);
    /**
     * ??????????????????
     */
    @FormUrlEncoded
    @POST("/api/member/card/recharge/order/list")
    Observable<BaseResult<List<RechBo>>> loadRecharge( @Field("cardNum") String cardNum,
                                                       @Field("pageNo") String pageNo,
                                                      @Field("pageSize") String pageSize



                                                     );
    /**
     * ????????????
     */
    @FormUrlEncoded
    @POST("/api/member/card/consumption/list")
    Observable<BaseResult<List<SumptionBo>>> loadcosumption(@Field("cardNum") String cardNum,
                                                            @Field("pageNo") String pageNo,
                                                            @Field("pageSize") String pageSize);

    /**
     * ??????????????????
     */
    @FormUrlEncoded
    @POST("/api/member/card/details/list")
    Observable<BaseResult<List<RecentBean>>> loadRecentCosumption(@Field("cardNum") String cardNum,
                                                                  @Field("pageNo") String pageNo,
                                                                  @Field("pageSize") String pageSize);


    /**loadcosumption
     * ????????????????????????
     */
    @FormUrlEncoded
    @POST("/api/Comment/myComment")
    Observable<BaseResult<MoviesCommentBO>> moviesComment(@Field("appUserId") String appUserId,
                                                          @Field("movieId") String movieId);


    /**
     * ????????????
     */
    @FormUrlEncoded
    @POST("/api/Comment/saveCollect")
    Observable<BaseResult<MoviesCommentBO>> moviesShouCang(@Field("appUserId") String appUserId,
                                                           @Field("movieId") String movieId,
                                                           @Field("collectStatus") String collectStatus);  //?????????1???????????????0

    /**
     * ????????????
     */
    @FormUrlEncoded
    @POST("/api/Comment/saveWantSee")
    Observable<BaseResult<MoviesCommentBO>> moviesWantSee(@Field("appUserId") String appUserId,
                                                          @Field("movieId") String movieId,
                                                          @Field("wantSee") String wantSee);  // ?????????1???????????????0

    /**
     * ????????????
     */
    @FormUrlEncoded
    @POST("/api/Comment/saveComment")
    Observable<BaseResult<Object>> moviesSummitCom(@Field("appUserId") String appUserId,
                                                   @Field("movieId") String movieId,
                                                   @Field("score") String score,//??????
                                                   @Field("comment") String comment);   //??????

    /**
     * ??????????????????
     */
    @FormUrlEncoded
    @POST("/api/game/games")
    Observable<BaseResult<List<GameBO>>> gameList(@Field("pageSize") String pageSize, @Field("pageNo") String pageNo);

    /**
     * ????????????????????????
     */
    @FormUrlEncoded
    @POST("/api/reward/article/list")
    Observable<HomeTopBean>getList(@Field("pageNo") String pageNo,
            @Field("pageSize") String pageSize );

    /**
     * ????????????
     */
    @FormUrlEncoded
    @POST("/api/suggestion/suggestions")
    Observable<BaseResult<String>> submitFeed(@Field("phone") String phone, @Field("suggestion") String suggestion);


    /**
     * ????????????????????????????????????
     */
    @FormUrlEncoded
    @POST("/api/Comment/myRecords")
    Observable<BaseResult<UserBO>> memberRecords(@Field("appUserId") String appUserId);


    /**
     * ????????????????????????
     */
    @FormUrlEncoded
    @POST("/api/appuser/getInfo")
    Observable<ResponseBody> getPersonStatus(@Field("appUserId") String appUserId);


    /**
     * ?????????????????????
     */
    @FormUrlEncoded
    @POST("/api/appuser/myScore")
    Observable<ResponseBody> getCoins(@Field("appUserId") String appUserId);


    /**
     * ?????????????????????????????????
     */
    @FormUrlEncoded
    @POST("/api/Comment/myCommentList")
    Observable<BaseResult<List<CommentBO>>> personCommentList(@Field("appUserId") String appUserId,
                                                              @Field("pageNo") String pageNo,
                                                              @Field("pageSize") String pageSize);

    @POST("/api/member/card/recharge/amount")
    Observable<BaseResult<List<RechatBo>>> rechatgejine();
    /**rechatgejine
     * ??????????????????????????????
     */
    @FormUrlEncoded
    @POST("/api/Movie/myWatchedList")
    Observable<BaseResult<List<CommentBO>>> personReadList(@Field("appUserId") String appUserId,
                                                           @Field("pageNo") String pageNo,
                                                           @Field("pageSize") String pageSize);

    /**
     * weixinpay
     * ???????????????
     */
    @FormUrlEncoded
    @POST("/api/member/card/recharge/alipay")
    Observable<BaseResult<PayBO>> zhifubaopay(@Field("cinemaId") String cinemaId,
                                              @Field("amountType") Integer amountType,
                                              @Field("amountId") Integer amountId,
                                              @Field("cardNum") String cardNum,
                                              @Field("app") Integer app
    );
    /**
     * weixinpay
     * ???????????????????????????
     */
    @FormUrlEncoded
    @POST("/api/member/card/recharge/alipay")
    Observable<BaseResult<PayBO>> zhifubaopayqita(@Field("cinemaId") String cinemaId,
                                                  @Field("amountType") Integer amountType,
                                                  @Field("rechargeMoney") Integer rechargeMoney,
                                                  @Field("cardNum") String cardNum,
                                                  @Field("app") Integer app);

    /**
     * weixinpay
     * ????????????
     */
    @FormUrlEncoded
    @POST("/api/member/card/recharge/wxpay")
    Observable<BaseResult<WXPayBO>> weixinpay(@Field("cinemaId") String cinemaId,
                                              @Field("amountType") Integer amountType,
                                              @Field("amountId") Integer amountId,
                                              @Field("cardNum") String cardNum,
                                              @Field("app") Integer app);
    /**
     * weixinpay
     * ????????????????????????
     */
    @FormUrlEncoded
    @POST("/api/member/card/recharge/wxpay")
    Observable<BaseResult<WXPayBO>> weixinpayta(@Field("cinemaId") String cinemaId,
                                                @Field("amountType") Integer amountType,
                                                @Field("rechargeMoney") Integer rechargeMoney,
                                                @Field("cardNum") String cardNum,
                                                @Field("app") Integer app);
    /**
     * ??????????????????????????????
     */
    @FormUrlEncoded
    @POST("/api/Movie/myWantSeeList")
    Observable<BaseResult<List<MoviesByCidBO>>> personWantSeeList(@Field("appUserId") String appUserId,
                                                                  @Field("cinemaId") String cinemaId,
                                                                  @Field("pageNo") String pageNo,
                                                                  @Field("pageSize") String pageSize);
    /**
     * ??????????????????????????????
     */
    @FormUrlEncoded
    @POST("/api/Movie/myCollectList")
    Observable<BaseResult<List<MoviesByCidBO>>> personCollectList(@Field("appUserId") String appUserId,
                                                                  @Field("pageNo") String pageNo,
                                                                  @Field("pageSize") String pageSize);
    /**


    /**
     * ????????????
     */
    @FormUrlEncoded
    @POST("/api/Movie/share")
    Observable<BaseResult<ShareBO>> shareMovie(@Field("movieId") String movieId);


    @FormUrlEncoded
    @POST("/api/movie/comment/list")
    Observable<BaseResult<List<CriticBO>>> criticMovie(@Field("movieId") Long movieId,
                                                 @Field("pageNo") Integer pageNo,
                                                 @Field("pageSize") Integer pageSize);
    @FormUrlEncoded
    @POST("/api/movie/comment/upvote")
    Observable<BaseResult<CriticBO>> dianZan(@Field("id") Long id);
    /**
     * ???????????????????????????
     */
    @FormUrlEncoded
    @POST("/api/game/exchanges")
    Observable<BaseResult<List<ShopBO>>> creditsShop(@Field("pageSize") String pageSize,
                                                     @Field("pageNo") String pageNo,
                                                     @Field("cinemaId") String cinemaId);

    /**
     * ??????????????????
     */
    @POST("/api/game/pointorder/list")
    Observable<BaseResult<List<ShopOrderBO>>> creditsOrder();


    /**
     * ????????????
     */
    @POST("/api/appointment/appointments")
    Observable<BaseResult<String>> personSome();

    /**
     * ???????????????
     */
    @FormUrlEncoded
    @POST("/api/ticket/userTickets")
    Observable<BaseResult<List<UserCouponBO>>> personCoupon(@Field("pageNo") int pageNo,
                                                            @Field("pageSize") int pageSize);

    /**
     * ?????????????????????
     */
    @POST("/api/ticket/validCount")
    Observable<BaseResult<String>> personCouponNum();

    /**
     * ?????????????????????
     */
    @FormUrlEncoded
    @POST("/api/ticket/ticketInfo")
    Observable<BaseResult<CouponDetailBO>> personCouponDetail(@Field("id") String id);

    /**
     * ???????????????
     */
    @FormUrlEncoded
    @POST("/api/platTicket/bindCoupon")
    Observable<ResponseBody> personAddCoupon(@Field("code") String code);

    /**
     * ??????????????????
     */
    @POST("/api/suggestion/list")
    Observable<BaseResult<List<FeedBackListBO>>> feedBackList();
    /**feedBackList
     * ???????????????????????????
     */
    @FormUrlEncoded
    @POST("/api/new/movie/screening")
    Observable<BaseResult<SessionBO>> movieScreen(@Field("cinemaId") String cinemaId,
                                                  @Field("movieId") String movieId);


    /**
     * ??????????????????
     */
    @POST("/dx/version/version.json")
    Observable<BaseResult<AppVersionBO>> loadVersion();


    /**
     * ???????????????????????????????????????
     */
    @POST("/api/price/favorinfo/list")
    Observable<BaseResult<List<FavourBO>>> favorinfo();

}

