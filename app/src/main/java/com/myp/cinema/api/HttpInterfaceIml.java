package com.myp.cinema.api;

import com.myp.cinema.entity.ActivityBO;
import com.myp.cinema.entity.AppVersionBO;
import com.myp.cinema.entity.BaseBO;
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
import com.myp.cinema.util.rx.RxResultHelper;
import com.myp.cinema.util.rx.RxHelper;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by wuliang on 2017/6/6.
 * <p>
 * ??????????????????????????????
 */

public class HttpInterfaceIml {

    private static HttpInterface service;
    private static String pageNum = "20";   //??????????????????????????????????????????20???

    /**
     * ??????????????????
     */
    private static HttpInterface getService() {
        if (service == null)
            service = ApiManager.getInstance().configRetrofit(HttpInterface.class, HttpInterface.URL);
        return service;
    }

    /**
     * ????????????
     */
    public static Observable<UserBO> userLogin(String phone, String password, String uuid) {
        return getService().userLogin(phone, password, uuid).compose(RxResultHelper.<UserBO>httpRusult());
    }
    /*
    * ?????????????????????
    *
    * */
public static Observable<threelandingBo> userLoginid(String wxUserId, String wbUserId, String qqUserId) {
    return getService().userLoginid(wxUserId,wbUserId,qqUserId).compose(RxHelper.<threelandingBo>httpRusult());
}
    /**
     * ????????????
     */
    public static Observable<String> userLogout() {
        return getService().userlogout().compose(RxResultHelper.<String>httpRusult());
    }


    /**
     * ????????????
     */
    public static Observable<UserBO> userRegister(String cinemaId,String phone, String password, String verification,
                                                  String sex) {
        return getService().userRegister(cinemaId,phone, password, verification, sex)
                .compose(RxResultHelper.<UserBO>httpRusult());
    }

    /**
     * ???????????????
     */
    public static Observable<String> userVerification(String phone, String verificationType,String verifycode) {
        return getService().userVerfication(phone, verificationType,verifycode).compose(RxResultHelper.<String>httpRusult());
    }

    /**
     * ?????????????????????
     */
    public static Observable<PicVerificBO> picVerification() {
        return getService().picVerfication().compose(RxResultHelper.<PicVerificBO>httpRusult());
    }

    /**
     * ????????????
     */
    public static Observable<UserBO> userVerifuser(String phone, String versition) {
        return getService().userVerifuser(phone, versition).compose(RxResultHelper.<UserBO>httpRusult());
    }
    /**
     * ???????????????????????????
     */
    public static Observable<threelandingBo> phonebinding(String header,String phone,String wxUserId,String wbUserId,String qqUserId, String versition) {
        return getService().phonebinding(header,phone,wxUserId,wbUserId ,qqUserId,versition).compose(RxHelper.<threelandingBo>httpRusult());
    }
    /**
     * ???????????????
     */
    public static Observable<threelandingBo> thirdregist(String cinemaId,String mobile,String password,String header,String nickName,String gender,String wxUserId,String wbUserId,String qqUserId) {
        return getService().thirdregist(cinemaId,mobile,password,header,nickName,gender,wxUserId,wbUserId,qqUserId).compose(RxHelper.<threelandingBo>httpRusult());
    }
    /**
     * ????????????
     */
    public static Observable<UserBO> userUpdatePass(String password, String pwd) {
        return getService().userUpdatePass(password, pwd).compose(RxResultHelper.<UserBO>httpRusult());
    }

    /**
     * ????????????
     */
    public static Observable<UserBO> userUpdateImg(RequestBody body) {
        return getService().uploadFile(body).compose(RxResultHelper.<UserBO>httpRusult());
    }

    /**
     * ????????????
     */
    public static Observable<UserBO> userUpdateName(String name) {
        return getService().updateName(name).compose(RxResultHelper.<UserBO>httpRusult());
    }

    /**
     * ????????????
     */
    public static Observable<UserBO> userUpdateSex(int sex) {
        return getService().updateUserSex(sex).compose(RxResultHelper.<UserBO>httpRusult());
    }

    /**
     * ????????????
     */
    public static Observable<CardBO> cardBindUser(String cinemaId, String card, String pwd) {
        return getService().cardBindUser(cinemaId, card, pwd).compose(RxResultHelper.<CardBO>httpRusult());
    }

    /**
     * ?????????????????????
     */
    public static Observable<List<CardBO>> cardUser() {
        return getService().cardUser().compose(RxResultHelper.<List<CardBO>>httpRusult());
    }

    /**
     *  ??????????????????????????????????????????
     */
    public static Observable<OpenCardBO> getCardPay(String cinemaId) {
        return getService().getCardPay(cinemaId).compose(RxResultHelper.<OpenCardBO>httpRusult());
    }

    /**
     *  ???????????????????????????
     */
    public static Observable<String> getCardNotice(String cinemaId) {
        return getService().getCardNotice(cinemaId).compose(RxResultHelper.<String>httpRusult());
    }

    /**
     *  ???????????????(?????????)
     */
    public static Observable<PayBO> bindCard(String openCard,String cinemaId,String cardNum,String password,String username,
                                             String sex,String birthday,String idcard,String rechargeMoney) {
        return getService().bindCard(openCard,cinemaId,cardNum,password,username,sex,birthday,idcard,rechargeMoney).compose(RxResultHelper.<PayBO>httpRusult());
    }

    /**
     *  ???????????????(??????)
     */
    public static Observable<WXPayBO>bindCardWechat(String openCard,String cinemaId,String cardNum,String password,String username,
                                                    String sex,String birthday,String idcard,String rechargeMoney) {
        return getService().bindCardWechat(openCard,cinemaId,cardNum,password,username,sex,birthday,idcard,rechargeMoney).compose(RxResultHelper.<WXPayBO>httpRusult());
    }

    /**
     * ???????????????
     */
    public static Observable<BaseBO> unBindCard(String id) {
        return getService().unBindCard(id).compose(RxHelper.<BaseBO>httpRusult());
    }


    /**
     * ??????????????????
     */
    public static Observable<List<CinemaBo>> cinemaList(String city, String longitude, String latitude) {
        return getService().cinemaList(city, longitude, latitude).compose(RxResultHelper.<List<CinemaBo>>httpRusult());
    }


    /**
     * ??????????????????
     */
    public static Observable<List<String>> cityList() {
        return getService().cityList("app").compose(RxResultHelper.<List<String>>httpRusult());
    }


    /**
     * ???????????????
     */
    public static Observable<List<LunBoBO>> lunboList(String source, String cinemaId) {
        return getService().lunboList(source, cinemaId).compose(RxResultHelper.<List<LunBoBO>>httpRusult());
    }

    public static Observable<LunBoAndBO> lunboandList(String source, String cinemaId) {
        return getService().lunboandList(source, cinemaId).compose(RxResultHelper.<LunBoAndBO>httpRusult());
    }

    /**
     * ????????????
     * @param cinemaId
     * @return
     */
    public static Observable<ActivityBO> getActivity(String cinemaId) {
        return getService().getActivity(cinemaId).compose(RxResultHelper.<ActivityBO>httpRusult());
    }

    /**
     * ????????????????????????
     */
    public static Observable<List<MoviesByCidBO>> moviesHot(String cinameId) {
        return getService().moviesHot(cinameId).compose(RxResultHelper.<List<MoviesByCidBO>>httpRusult());
    }

    /**
     * ????????????????????????
     */
    public static Observable<List<MoviesSoonBO>> moviesSoon(String cinameId) {
        return getService().moviesSoon(cinameId).compose(RxResultHelper.<List<MoviesSoonBO>>httpRusult());
    }

    /**
     * ??????????????????
     */
    public static Observable<List<MoviesByCidBO>> moviesCid(String cinemaId) {
        return getService().moviesByCid(cinemaId).compose(RxResultHelper.<List<MoviesByCidBO>>httpRusult());
    }
    /**
     * ??????????????????
     */
    public static Observable<List<HotSellBO>> getHotSell(String cinemaCode) {
        return getService().getHotSell(cinemaCode).compose(RxResultHelper.<List<HotSellBO>>httpRusult());
    }

    /**
     * ???????????????????????????
     */
    public static Observable<List<HotSellBannerBO>> getBanner(String cinemaCode, String category) {
        return getService().getBanner(cinemaCode,category).compose(RxResultHelper.<List<HotSellBannerBO>>httpRusult());
    }

    /**
     * ??????????????????
     */
    public static Observable<ProdectBO> getOrderPrice(String cinemaId,String merchandiseinfo,String merTicketId) {
        return getService().getOrderPrice(cinemaId,merchandiseinfo,merTicketId).compose(RxResultHelper.<ProdectBO>httpRusult());
    }

    /**
     * ??????????????????
     */
    public static Observable<SubmitPrdectOrderBO> submitProdectOrder(String cinemaId,String merchandiseinfo,String phone,String memo,String cinemaNumber,String merTicketId) {
        return getService().submitProdectOrder(cinemaId,merchandiseinfo,phone,memo,cinemaNumber,merTicketId).compose(RxResultHelper.<SubmitPrdectOrderBO>httpRusult());
    }

    /**
     * ????????????
     */
    public static Observable<ConfirmPayBO> orderSubmit(String cinemaId,String orderName, String seats, String seatId, String ticketOriginPrice, String ticketNum,
                                                       String cinemaNumber, String hallId, String playId, String cineMovieNum, String seatTicketId,
                                                       String merchandiseInfo, String merTicketId,String orderPhone,String memo,String preferPrice,
                                                       String globalPreferPrice,String globalCanBuyNum) {
        return getService().orderSubmit( cinemaId,orderName, seats, seatId,ticketOriginPrice,ticketNum,cinemaNumber,hallId,
                playId,cineMovieNum,seatTicketId,merchandiseInfo,merTicketId,orderPhone,memo,preferPrice,globalPreferPrice,globalCanBuyNum).compose(RxResultHelper.<ConfirmPayBO>httpRusult());
    }

    /**
     * ????????????(0?????????)
     */
    public static Observable<ConfirmPayBO> orderZeroSubmit(String cinemaId,String orderName, String seats, String seatId, String ticketOriginPrice, String ticketNum,
                                                       String cinemaNumber, String hallId, String playId, String cineMovieNum, String seatTicketId,
                                                       String merchandiseInfo, String merTicketId,String orderPhone,String memo,String preferPrice,
                                                       String globalPreferPrice,String globalCanBuyNum) {
        return getService().orderZeroSubmit( cinemaId,orderName, seats, seatId,ticketOriginPrice,ticketNum,cinemaNumber,hallId,
                playId,cineMovieNum,seatTicketId,merchandiseInfo,merTicketId,orderPhone,memo,preferPrice,globalPreferPrice,globalCanBuyNum).compose(RxResultHelper.<ConfirmPayBO>httpRusult());
    }
    /**
     * ??????????????????
     */
    public static Observable<ResponseBody> modifyOrderPrice(String cinemaId,String partnerPrice,String marketPrice,String activityPriceNum,String ticketNum,
                                                           String beforeTicketPrice,String totalDisprice,String payPrice,String seatTicketId,
                                                           String merTicketId,String merchandiseInfo,String appUserId,String preferPrice,
                                                           String globalPreferPrice,String globalCanBuyNum,String payType) {
        return getService().modifyOrderPrice( cinemaId,partnerPrice, marketPrice, activityPriceNum, ticketNum,
                beforeTicketPrice, totalDisprice, payPrice, seatTicketId, merTicketId,merchandiseInfo,appUserId,preferPrice,globalPreferPrice,globalCanBuyNum,payType).compose(RxHelper.<ResponseBody>httpRusult());
    }

    /**
     * ???????????????
     */
    public static Observable<ResponseBody> verifyCoupon(String coupon,String orderNum) {
        return getService().verifyCoupon(coupon,orderNum).compose(RxHelper.<ResponseBody>httpRusult());
    }



    /**
     * ??????????????????
     */
    public static Observable<List<LockSeatsBO>> orderList(String orderType,String payStatus,int orderPage,String orderSize) {
        return getService().orserList(orderType, payStatus, orderPage,orderSize).compose(RxResultHelper.<List<LockSeatsBO>>httpRusult());
    }


    public static Observable<RefundBO>  refundinfo(String id) {
        return getService(). refundinfo(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
    public static Observable<RefundBO>  refund(String id,String cinemaId) {
        return getService(). refund(id,cinemaId).compose(RxHelper.<RefundBO>httpRusult());
    }

    /**refund
     * ??????????????????
     */
    public static Observable<OrderDetailBO> orderMessage(String id, String cinemaId) {
        return getService().orderMessage(id,cinemaId).compose(RxResultHelper.<OrderDetailBO>httpRusult());
    }


    /**
     * ????????????????????????
     */
    public static Observable<OrderBO> orderQuery(String appUserId ,String orderNum) {
        return getService().orderQuery(appUserId,orderNum).compose(RxResultHelper.<OrderBO>httpRusult());
    }


    /**
     * ??????????????????????????????
     */
    public static Observable<OrderNumBO> orderCheck() {
        return getService().orderCheck().compose(RxResultHelper.<OrderNumBO>httpRusult());
    }

    /**
     * ????????????
     */
    public static Observable<OrderNumBO> orderCancle(String orderNum) {
        return getService().orderCancel(orderNum).compose(RxResultHelper.<OrderNumBO>httpRusult());
    }

    public static Observable<preferentialnumberBo> getsets(String cinemaId, String dxId) {
        return getService().getsets(cinemaId,dxId).compose(RxResultHelper.<preferentialnumberBo>httpRusult());
    }

    /**
     * ??????(??????)
     */
    public static Observable<ResponseBody> lockSeats(String orderName, String seats, String seatId, String ticketOriginPrice, String ticketNum,
                                                    String cinemaNumber, String hallId, String playId, String cineMovieNum,String preferPrice,
                                                    String globalPreferPrice,String globalCanBuyNum) {
        return getService().lockSeats(orderName,seats,seatId,ticketOriginPrice,ticketNum,cinemaNumber,hallId,playId,cineMovieNum,preferPrice
                                      ,globalPreferPrice,globalCanBuyNum).compose(RxHelper.<ResponseBody>httpRusult());
    }

    /**
     * ???????????????
     */
    public static Observable<PayBO> payAlipay(String orderNum) {
        return getService().payAlipay(orderNum).compose(RxResultHelper.<PayBO>httpRusult());
    }

    /**
     * ????????????
     */
    public static Observable<WXPayBO> payWeChat(String orderNum) {
        return getService().payWeChat(orderNum).compose(RxResultHelper.<WXPayBO>httpRusult());
    }

    /**
     * ???????????????
     */
    public static Observable<ResponseBody> loadcardPay(String orderNum,String coupon) {
        return getService().loadcardPay(orderNum,coupon).compose(RxHelper.<ResponseBody>httpRusult());
    }
    /**
     * ???????????????????????????
     */
    public static Observable<PayCardBO> cardPayPrice(String orderNum, String card) {
        return getService().cardPayPrice(orderNum, card).compose(RxResultHelper.<PayCardBO>httpRusult());
    }

    /**
     * ???????????????
     */
    public static Observable<ResuBo> payCard(String orderNum, String pwd, String card) {
        return getService().payCard(orderNum, pwd, card).compose(RxResultHelper.<ResuBo>httpRusult());
    }


    /**
     * ?????????????????????
     */
    public static Observable<List<HotWireBO>> hotWire(String articleType, String flpage, String cinemaId) {
        return getService().hotWire(articleType, flpage, pageNum, cinemaId).compose(RxResultHelper.<List<HotWireBO>>httpRusult());
    }
    /**
     * ??????????????????Collection of articles
     */
    public static Observable<List<HotWireBO>> articlesCollection(String pageNo) {
        return getService().articlesCollection(pageNo,pageNum).compose(RxResultHelper.<List<HotWireBO>>httpRusult());
    }
    /**
     * ????????????????????????
     */
    public static Observable<List<RechBo>> loadRecharge(String pageNo,String cardNum) {
        return getService().loadRecharge(cardNum,pageNo, "20").compose(RxResultHelper.<List<RechBo>>httpRusult());
    }
    /**loadcosumption
     * ??????????????????
     */
    public static Observable<List<SumptionBo>> loadcosumption(String pageNo, String cardNum) {
        return getService().loadcosumption(cardNum,pageNo, "20").compose(RxResultHelper.<List<SumptionBo>>httpRusult());
    }
    /**loadcosumption
     * ????????????????????????
     */
    public static Observable<List<RecentBean>> loadRecentCosumption(String pageNo, String cardNum) {
        return getService().loadRecentCosumption(cardNum,pageNo, "20").compose(RxResultHelper.<List<RecentBean>>httpRusult());
    }
    /**loadcosumption
     * ????????????????????????
     */
    public static Observable<MoviesCommentBO> moviesComment(String userId, String movieId) {
        return getService().moviesComment(userId, movieId).compose(RxResultHelper.<MoviesCommentBO>httpRusult());
    }

    /**
     * ????????????
     */
    public static Observable<MoviesCommentBO> moviesShouCang(String userId, String movieId,
                                                             String collectStatus) {
        return getService().moviesShouCang(userId, movieId, collectStatus).compose(RxResultHelper.<MoviesCommentBO>httpRusult());
    }

    /**
     * ????????????
     */
    public static Observable<MoviesCommentBO> moviesWantSee(String userId, String movieId,
                                                            String wantSee) {
        return getService().moviesWantSee(userId, movieId, wantSee).compose(RxResultHelper.<MoviesCommentBO>httpRusult());
    }

    /**
     * ????????????
     */
    public static Observable<Object> moviesSunmitCom(String userId, String movieId, String score,
                                                     String comment) {
        return getService().moviesSummitCom(userId, movieId, score, comment).compose(RxResultHelper.httpRusult());
    }


    /**
     * ??????????????????
     */
    public static Observable<List<GameBO>> gameList(String pageNo) {
        return getService().gameList(pageNum, pageNo).compose(RxResultHelper.<List<GameBO>>httpRusult());
    }

    /**
     * ????????????????????????
     */
    public static Observable<HomeTopBean>getList(String pageNo) {
        return getService().getList( pageNo,pageNum).compose(RxHelper.<HomeTopBean>httpRusult());
    }
    /**
     * ????????????
     */
    public static Observable<String> submitFeed(String phone, String suggestion) {
        return getService().submitFeed(phone, suggestion).compose(RxResultHelper.<String>httpRusult());
    }

    /**
     * ????????????????????????????????????
     */
    public static Observable<UserBO> memberRecord(String appUserId) {
        return getService().memberRecords(appUserId).compose(RxResultHelper.<UserBO>httpRusult());
    }

    /**
     * ????????????????????????????????????
     */
    public static Observable<ResponseBody> getPersonStatus(String appUserId) {
        return getService().getPersonStatus(appUserId).compose(RxHelper.<ResponseBody>httpRusult());
    }

    /**
     * ?????????????????????
     */
    public static Observable<ResponseBody> getCoins(String appUserId) {
        return getService().getCoins(appUserId).compose(RxHelper.<ResponseBody>httpRusult());
    }


    /**
     * ?????????????????????????????????
     */
    public static Observable<List<CommentBO>> personCommentList(String appUserId, String pageNo) {
        return getService().personCommentList(appUserId,pageNo,pageNum).compose(RxResultHelper.<List<CommentBO>>httpRusult());
    }


    public static Observable<List<RechatBo>> rechatgejine() {
        return getService().rechatgejine().compose(RxResultHelper.<List<RechatBo>>httpRusult());
    }
    //?????????
    public static Observable<PayBO> zhifubaopay(String cinemaId, Integer amountType, Integer amountId,String cardNum) {
        return getService().zhifubaopay(cinemaId,amountType,amountId,cardNum,0).compose(RxResultHelper.<PayBO>httpRusult());
    }
    //???????????????
    public static Observable<PayBO> zhifubaopayqita(String cinemaId, Integer amountType, Integer rechargeMoney,String cardNum) {
        return getService().zhifubaopayqita(cinemaId,amountType,rechargeMoney,cardNum,0).compose(RxResultHelper.<PayBO>httpRusult());
    }
    //??????
    public static Observable<WXPayBO> weixinpay(String cinemaId, Integer amountType, Integer amountId,String cardNum) {
        return getService().weixinpay(cinemaId,amountType,amountId,cardNum,0).compose(RxResultHelper.<WXPayBO>httpRusult());
    }
    //????????????
    public static Observable<WXPayBO> weixinpayta(String cinemaId, Integer amountType, Integer rechargeMoney,String cardNum) {
        return getService().weixinpayta(cinemaId,amountType,rechargeMoney,cardNum,0).compose(RxResultHelper.<WXPayBO>httpRusult());
    }

    /**weixinpay
     * ??????????????????????????????
     */
    public static Observable<List<CommentBO>> personReadList(String appUserId, String pageNo) {
        return getService().personReadList(appUserId, pageNo, pageNum).compose(RxResultHelper.<List<CommentBO>>httpRusult());
    }

    /**
     * ??????????????????????????????
     */
    public static Observable<List<MoviesByCidBO>> personWantSeeList(String appUserId, String cinemaId, String pageNo) {
        return getService().personWantSeeList(appUserId, cinemaId,pageNo, pageNum).compose(RxResultHelper.<List<MoviesByCidBO>>httpRusult());
    }

    /**
     * ??????????????????????????????
     */
    public static Observable<List<MoviesByCidBO>> personCollectList(String appUserId,String zzpage) {
        return getService().personCollectList(appUserId,zzpage,pageNum).compose(RxResultHelper.<List<MoviesByCidBO>>httpRusult());
    }

    /**
     * ????????????
     */
    public static Observable<ShareBO> shareMovie(String movieId) {
        return getService().shareMovie(movieId).compose(RxResultHelper.<ShareBO>httpRusult());
    }
    /**
     * ??????????????????
     */

    public static Observable<List<CriticBO>> criticMovie(Long movieId, Integer pageNo, Integer pageSize) {
        return getService().criticMovie(movieId,pageNo,pageSize).compose(RxResultHelper.<List<CriticBO>>httpRusult());
    }
    public static Observable<CriticBO> dianZan(Long Id) {
        return getService().dianZan(Id).compose(RxResultHelper.<CriticBO>httpRusult());
    }
    /**
     * ????????????????????????
     */
    public static Observable<List<ShopBO>> creditsShop(String pageNo, String cinemaId) {
        return getService().creditsShop(pageNum, pageNo, cinemaId).compose(RxResultHelper.<List<ShopBO>>httpRusult());
    }

    /**
     * ????????????????????????
     */
    public static Observable<List<ShopOrderBO>> creditsOrder() {
        return getService().creditsOrder().compose(RxResultHelper.<List<ShopOrderBO>>httpRusult());
    }

    /**
     * ????????????
     */
    public static Observable<String> personSome() {
        return getService().personSome().compose(RxResultHelper.<String>httpRusult());
    }

    /**
     * ???????????????
     */
    public static Observable<List<UserCouponBO>> personCoupon(int pageNo, int pageSize) {
        return getService().personCoupon(pageNo,pageSize).compose(RxResultHelper.<List<UserCouponBO>>httpRusult());
    }

    /**
     * ?????????????????????
     */
    public static Observable<String> personCouponNum() {
        return getService().personCouponNum().compose(RxResultHelper.<String>httpRusult());
    }


    /**
     * ???????????????
     */
    public static Observable<CouponDetailBO> personCouponDetail(String id) {
        return getService().personCouponDetail(id).compose(RxResultHelper.<CouponDetailBO>httpRusult());
    }

    /**
     * ???????????????
     */
    public static Observable<ResponseBody> personAddCoupon(String code) {
        return getService().personAddCoupon(code).compose(RxHelper.<ResponseBody>httpRusult());
    }
    /**
     * ??????????????????
     */
    public static Observable<List<FeedBackListBO>> feedBackList() {
        return getService().feedBackList().compose(RxResultHelper.<List<FeedBackListBO>>httpRusult());
    }
    /**
     * ????????????????????????
     */
    public static Observable<SessionBO> movieSereen(String cinemaId, String movieId) {
        return getService().movieScreen(cinemaId, movieId).compose(RxResultHelper.<SessionBO>httpRusult());
    }

    /**
     * ??????????????????
     */
    public static Observable<AppVersionBO> VersionCheck() {
        return getService().loadVersion().compose(RxResultHelper.<AppVersionBO>httpRusult());
    }

    /**
     * ????????????????????????
     */
    public static Observable<List<FavourBO>> forvoreinfo() {
        return getService().favorinfo().compose(RxResultHelper.<List<FavourBO>>httpRusult());
    }

}
