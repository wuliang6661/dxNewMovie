package com.myp.cinema.ui.balance;

import android.util.Log;

import com.myp.cinema.api.HttpInterfaceIml;
import com.myp.cinema.entity.RechBo;
import com.myp.cinema.entity.SumptionBo;
import com.myp.cinema.mvp.BasePresenterImpl;

import java.util.List;

import rx.Subscriber;

/**
 * Created by Administrator on 2018/1/28.
 */

public class onsumptiondetailPresenter extends BasePresenterImpl<onsumptiondetailContract.View>
        implements onsumptiondetailContract.Presenter {
    @Override
    public void loadcosumption( final int pageNo,String cardNum) {
        HttpInterfaceIml.loadcosumption(pageNo + "", cardNum).subscribe(new Subscriber<List<SumptionBo>>() {
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
                Log.d("chognzhimignxi", "onError: " + e);
            }

            @Override
            public void onNext(List<SumptionBo> sumptionBo) {
                Log.d("chognzhimignxi", "onNext: " + sumptionBo);//pageNo
                Log.d("chognzhimignxi", "onNext: " + pageNo);
                if (mView == null)
                    return;
                mView.getcosumption(sumptionBo,pageNo);
            }


        });
    }
}
