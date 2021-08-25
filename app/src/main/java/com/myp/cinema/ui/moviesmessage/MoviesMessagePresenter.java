package com.myp.cinema.ui.moviesmessage;

import com.myp.cinema.api.HttpInterfaceIml;
import com.myp.cinema.entity.CriticBO;
import com.myp.cinema.entity.MoviesCommentBO;
import com.myp.cinema.entity.ShareBO;
import com.myp.cinema.mvp.BasePresenterImpl;

import java.util.List;

import rx.Subscriber;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MoviesMessagePresenter extends BasePresenterImpl<MoviesMessageContract.View>
        implements MoviesMessageContract.Presenter {

    @Override
    public void loadMoviesComment(String appUserId, String movieId) {
        HttpInterfaceIml.moviesComment(appUserId, movieId).subscribe(new Subscriber<MoviesCommentBO>() {
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
            public void onNext(MoviesCommentBO s) {
                if (mView == null)
                    return;
                mView.getMoviesComment(s);
            }
        });
    }

    @Override
    public void loadMoviesShouCang(String appUserId, String movieId, boolean isShouCang) {
        String shoucang;
        if (isShouCang) {
            shoucang = "0";
        } else {
            shoucang = "1";
        }
        HttpInterfaceIml.moviesShouCang(appUserId, movieId, shoucang).subscribe(new Subscriber<MoviesCommentBO>() {
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
            public void onNext(MoviesCommentBO moviesCommentBO) {
                if (mView == null)
                    return;
                mView.getMoviesComment(moviesCommentBO);
            }
        });
    }

    @Override
    public void loadMoviesWantSee(String appUserId, String movieId, boolean isWantSee) {
        String wangtSee;
        if (isWantSee) {
            wangtSee = "0";
        } else {
            wangtSee = "1";
        }
        HttpInterfaceIml.moviesWantSee(appUserId, movieId, wangtSee).subscribe(new Subscriber<MoviesCommentBO>() {
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
            public void onNext(MoviesCommentBO moviesCommentBO) {
                if (mView == null)
                    return;
                mView.getMoviesComment(moviesCommentBO);
            }
        });
    }

    @Override
    public void loadShareMovie(String movieId) {
        HttpInterfaceIml.shareMovie(movieId).subscribe(new Subscriber<ShareBO>() {
            @Override
            public void onCompleted() {
                if (mView != null) {
                    mView.onRequestEnd();
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView != null) {
                    mView.onRequestError(e.getMessage());
                }
            }

            @Override
            public void onNext(ShareBO s) {
                if (mView != null) {
                    mView.getShareMessage(s);
                }
            }
        });
    }

    @Override
    public void loadMoviesCritic(Long movieId) {
        HttpInterfaceIml.criticMovie(movieId,1,3).subscribe(new Subscriber<List<CriticBO>>() {
            @Override
            public void onCompleted() {
                if (mView != null) {
                    mView.onRequestEnd();
                }
            }
            @Override
            public void onError(Throwable e) {
                if (mView != null) {
                    mView.onRequestError(e.getMessage());
                }
            }
            @Override
            public void onNext(List<CriticBO> s) {
                if (mView != null) {
                    mView.getCritic(s);
                }else {
                    mView.getCritic(s);
                }
            }
        });
    }
    @Override
    public void loadDianZan(Long Id, final int position) {
        HttpInterfaceIml.dianZan(Id).subscribe(new Subscriber<CriticBO>() {
            @Override
            public void onCompleted() {
                if (mView != null) {
                    mView.onRequestEnd();
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView != null) {
                    mView.onRequestError(e.getMessage());
                }
            }

            @Override
            public void onNext(CriticBO s) {
                if (mView != null) {
                    mView.getDianZan(s,position);
                }
            }
        });
    }
}
