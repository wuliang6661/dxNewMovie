package com.myp.cinema.ui.phonecode2two;

import com.myp.cinema.api.HttpInterfaceIml;
import com.myp.cinema.entity.threelandingBo;
import com.myp.cinema.mvp.BasePresenterImpl;

import rx.Subscriber;

/**
 * Created by Administrator on 2018/1/14.
 */

public class phonecode2twoPresenter extends BasePresenterImpl<phonecode2twoContract.View>
        implements phonecode2twoContract.Presenter {
    @Override
    public void thirdregist(String cinemaId,String mobile,String password,String header,String nickName,String gender,String wxUserId,String wbUserId,String qqUserId) {
        HttpInterfaceIml.thirdregist(cinemaId,mobile,password,header,nickName,gender,wxUserId,wbUserId,qqUserId).subscribe(new Subscriber<threelandingBo>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (mView == null)
                    return;
                mView.onRequestError(e.getMessage());
            }

            @Override
            public void onNext(threelandingBo thirdregistBo) {
                if (mView == null)
                    return;
                mView.getThirdregistBo(thirdregistBo);

            }
        });
    }
}
