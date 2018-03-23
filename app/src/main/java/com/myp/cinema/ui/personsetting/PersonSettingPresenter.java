package com.myp.cinema.ui.personsetting;

import com.myp.cinema.api.HttpInterface;
import com.myp.cinema.api.HttpInterfaceIml;
import com.myp.cinema.entity.BaseResult;
import com.myp.cinema.entity.UserBO;
import com.myp.cinema.mvp.BasePresenterImpl;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class PersonSettingPresenter extends BasePresenterImpl<PersonSettingContract.View>
        implements PersonSettingContract.Presenter {

    @Override
    public void updataUserImage(File file) {
        // 创建 RequestBody，用于封装构建RequestBody
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
        HttpInterfaceIml.userUpdateImg(requestBody).subscribe(new Subscriber<UserBO>() {
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
            public void onNext(UserBO userBO) {
                if (mView == null)
                    return;
                mView.getUserImage(userBO);
            }
        });
    }

    @Override
    public void updateUserSex(int sex) {
        HttpInterfaceIml.userUpdateSex(sex).subscribe(new Subscriber<UserBO>() {
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
            public void onNext(UserBO userBO) {
                if (mView == null)
                    return;
                mView.getUserSex(userBO);
            }
        });
    }
}
