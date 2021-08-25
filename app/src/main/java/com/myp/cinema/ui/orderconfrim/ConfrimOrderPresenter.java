package com.myp.cinema.ui.orderconfrim;

import com.myp.cinema.api.HttpInterfaceIml;
import com.myp.cinema.entity.CardBO;
import com.myp.cinema.entity.ConfirmPayBO;
import com.myp.cinema.entity.LockSeatsBO;
import com.myp.cinema.entity.OrderNumBO;
import com.myp.cinema.entity.PayBO;
import com.myp.cinema.entity.PayCardBO;
import com.myp.cinema.entity.ResuBo;
import com.myp.cinema.entity.WXPayBO;
import com.myp.cinema.mvp.BasePresenterImpl;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import rx.Subscriber;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ConfrimOrderPresenter extends BasePresenterImpl<ConfrimOrderContract.View>
        implements ConfrimOrderContract.Presenter {

    @Override
    public void loadSubmitOrder(String cinemaId,String orderName,String seats,String seatId,String ticketOriginPrice,String ticketNum,
                                String cinemaNumber,String hallId,String playId,String cineMovieNum,String seatTicketId,
                                String merchandiseInfo,String merTicketId,String orderPhone,String memo,String preferPrice,
                                String globalPreferPrice,String globalCanBuyNum) {
        HttpInterfaceIml.orderSubmit( cinemaId,orderName, seats, seatId,ticketOriginPrice,ticketNum,cinemaNumber,hallId,
                playId,cineMovieNum,seatTicketId,merchandiseInfo,merTicketId,orderPhone,memo,preferPrice,globalPreferPrice,globalCanBuyNum).subscribe(new Subscriber<ConfirmPayBO>() {
            @Override
            public void onCompleted() {
                if (mView == null)
                    return;
                mView.onRequestEnd();
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null)
                    return;
                mView.onRequestError(e.getMessage());
            }

            @Override
            public void onNext(ConfirmPayBO s) {
                if (mView == null)
                    return;
                mView.getOrder(s);
            }
        });
    }

    @Override
    public void modifyOrderPrice(String cinemaId,String partnerPrice,String marketPrice,String activityPriceNum,String ticketNum,
                                 String beforeTicketPrice,String totalDisprice,String payPrice,String seatTicketId,
                                 String merTicketId,String merchandiseInfo,String appUserId,String preferPrice,
                                 String globalPreferPrice,String globalCanBuyNum,String payType) {
        HttpInterfaceIml.modifyOrderPrice( cinemaId,partnerPrice, marketPrice, activityPriceNum, ticketNum,
                beforeTicketPrice, totalDisprice, payPrice, seatTicketId,merTicketId, merchandiseInfo,appUserId,preferPrice,globalPreferPrice,globalCanBuyNum,payType).subscribe(new Subscriber<ResponseBody>() {
            @Override
            public void onCompleted() {
                if (mView == null)
                    return;
                mView.onRequestEnd();
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null)
                    return;
                mView.onRequestError(e.getMessage());
            }

            @Override
            public void onNext(ResponseBody s) {
                if (mView == null)
                    return;
                try {
                    mView.getModifyInfo(s);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void loadZeroSubmitOrder(String cinemaId, String orderName, String seats, String seatId, String ticketOriginPrice, String ticketNum, String cinemaNumber, String hallId, String playId, String cineMovieNum, String seatTicketId, String merchandiseInfo, String merTicketId, String orderPhone, String memo, String preferPrice, String globalPreferPrice, String globalCanBuyNum) {
        HttpInterfaceIml.orderZeroSubmit( cinemaId,orderName, seats, seatId,ticketOriginPrice,ticketNum,cinemaNumber,hallId,
                playId,cineMovieNum,seatTicketId,merchandiseInfo,merTicketId,orderPhone,memo,preferPrice,globalPreferPrice,globalCanBuyNum).subscribe(new Subscriber<ConfirmPayBO>() {
            @Override
            public void onCompleted() {
                if (mView == null)
                    return;
                mView.onRequestEnd();
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null)
                    return;
                mView.onRequestError(e.getMessage());
            }

            @Override
            public void onNext(ConfirmPayBO s) {
                if (mView == null)
                    return;
                mView.getZeroOrder(s);
            }
        });
    }

    @Override
    public void loadCardUser() {
        HttpInterfaceIml.cardUser().subscribe(new Subscriber<List<CardBO>>() {
            @Override
            public void onCompleted() {
                if (mView == null)
                    return;
                mView.onRequestEnd();
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null)
                    return;
                mView.onRequestError(e.getMessage());
            }

            @Override
            public void onNext(List<CardBO> s) {
                if (mView == null)
                    return;
                mView.getCardList(s);
            }
        });
    }

    @Override
    public void loadCardPay(String orderNum, String card) {
        HttpInterfaceIml.cardPayPrice(orderNum, card).subscribe(new Subscriber<PayCardBO>() {
            @Override
            public void onCompleted() {
                if (mView == null)
                    return;
                mView.onRequestEnd();
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null)
                    return;
                mView.onRequestError(e.getMessage());

            }

            @Override
            public void onNext(PayCardBO s) {
                if (mView == null)
                    return;
                mView.getCardPrice(s);
            }
        });
    }

    @Override
    public void payCard(String orderNum, String pwd, String card) {
        HttpInterfaceIml.payCard(orderNum, pwd, card).subscribe(new Subscriber<ResuBo>() {
            @Override
            public void onCompleted() {
                if (mView == null)
                    return;
                mView.onRequestEnd();
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null)
                    return;
                mView.onRequestError(e.getMessage());
            }

            @Override
            public void onNext(ResuBo s) {
                if (mView == null)
                    return;
                mView.getPayCard(s);
            }
        });
    }

    @Override
    public void orderCancle(String orderNum) {
        HttpInterfaceIml.orderCancle(orderNum).subscribe(new Subscriber<OrderNumBO>() {
            @Override
            public void onCompleted() {
                if (mView == null)
                    return;
                mView.onRequestEnd();
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null)
                    return;
                mView.onRequestError(e.getMessage());
            }

            @Override
            public void onNext(OrderNumBO orderNumBO) {
                if (mView == null)
                    return;
                mView.getOrderCancle(orderNumBO);
            }
        });
    }

    @Override
    public void loadAliPay(String orderNum) {
        HttpInterfaceIml.payAlipay(orderNum).subscribe(new Subscriber<PayBO>() {
            @Override
            public void onCompleted() {
                if (mView == null)
                    return;
                mView.onRequestEnd();
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null)
                    return;
                mView.onRequestError(e.getMessage());
            }

            @Override
            public void onNext(PayBO s) {
                if (mView == null)
                    return;
                mView.getAliPay(s);
            }
        });
    }

    @Override
    public void loadWeChatPay(String orderNum) {
        HttpInterfaceIml.payWeChat(orderNum).subscribe(new Subscriber<WXPayBO>() {
            @Override
            public void onCompleted() {
                if (mView == null)
                    return;
                mView.onRequestEnd();
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null)
                    return;
                mView.onRequestError(e.getMessage());
            }

            @Override
            public void onNext(WXPayBO s) {
                if (mView == null)
                    return;
                mView.getWxPay(s);
            }
        });
    }

    @Override
    public void verifyCoupon(String coupon, String orderNum) {
        HttpInterfaceIml.verifyCoupon(coupon,orderNum).subscribe(new Subscriber<ResponseBody>() {
            @Override
            public void onCompleted() {
                if (mView == null)
                    return;
                mView.onRequestEnd();
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null)
                    return;
                mView.onRequestError(e.getMessage());
            }

            @Override
            public void onNext(ResponseBody s) {
                if (mView == null)
                    return;
                try {
                    mView.verifyCoupon(s);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void couponExchange(String orderNum, String coupon) {
        HttpInterfaceIml.loadcardPay(orderNum,coupon).subscribe(new Subscriber<ResponseBody>() {
            @Override
            public void onCompleted() {
                if (mView == null)
                    return;
                mView.onRequestEnd();
            }

            @Override
            public void onError(Throwable e) {
                if (mView == null)
                    return;
                mView.onRequestError(e.getMessage());
            }

            @Override
            public void onNext(ResponseBody s) {
                if (mView == null)
                    return;
                try {
                    mView.couponExchange(s);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
