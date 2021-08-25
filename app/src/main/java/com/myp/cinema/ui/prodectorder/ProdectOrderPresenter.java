package com.myp.cinema.ui.prodectorder;


import com.myp.cinema.api.HttpInterfaceIml;
import com.myp.cinema.entity.CardBO;
import com.myp.cinema.entity.PayBO;
import com.myp.cinema.entity.PayCardBO;
import com.myp.cinema.entity.ProdectBO;
import com.myp.cinema.entity.ResuBo;
import com.myp.cinema.entity.SubmitPrdectOrderBO;
import com.myp.cinema.entity.WXPayBO;
import com.myp.cinema.mvp.BasePresenterImpl;

import java.util.List;

import rx.Subscriber;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ProdectOrderPresenter extends BasePresenterImpl<ProdectOrderContract.View>
        implements ProdectOrderContract.Presenter {

    @Override
    public void getOrderPrice(String cinemaId,String merchandiseinfo,String merTicketId) {
        HttpInterfaceIml.getOrderPrice(cinemaId,merchandiseinfo,merTicketId).subscribe(new Subscriber<ProdectBO>() {
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
            public void onNext(ProdectBO s) {
                if (mView == null)
                    return;

                mView.getOrder(s);
            }
        });
    }

    @Override
    public void submitOrder(String cinemaId,String merchandiseinfo,String phone,String memo,String cinemaNumber,String merTicketId) {
        HttpInterfaceIml.submitProdectOrder(cinemaId,merchandiseinfo,phone,memo,cinemaNumber,merTicketId).subscribe(new Subscriber<SubmitPrdectOrderBO>() {
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
            public void onNext(SubmitPrdectOrderBO s) {
                if (mView == null)
                    return;

                mView.getSubmitOrderResult(s);
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
}