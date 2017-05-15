package com.saxxhw.srn.presenter;

import com.saxxhw.srn.model.bean.ImageListEntity;
import com.saxxhw.srn.model.bean.VideoListEntity;
import com.saxxhw.srn.model.http.HttpHelper;
import com.saxxhw.srn.model.http.api.Apis;
import com.saxxhw.srn.presenter.contract.VideoListContract;
import com.saxxhw.srn.rx.RxPresenter;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Saxxhw on 2017/5/11.
 * 邮箱：Saxxhw@126.com
 * 功能：
 */

public class VideoListPresenter extends RxPresenter<VideoListContract.View> implements VideoListContract.Presenter {

    private HttpHelper mHttpHelper;

    private int pageNum = 1;

    private static final int PAGE_LIMIT = 20;

    @SuppressWarnings("WeakerAccess")
    @Inject
    public VideoListPresenter(HttpHelper mHttpHelper) {
        this.mHttpHelper = mHttpHelper;
    }

    @Override
    public void getImageList(String keyword, final boolean isRefresh) {
        // 计算页码
        if (isRefresh) {
            pageNum = 0;
            if (mView.isFirstRefresh()) {
                mView.showLoading();
            }
        } else {
            ++pageNum;
        }
        // 执行网络请求
        Subscription subscription = mHttpHelper.getVideoRetrofit(Apis.class).getVideoList(keyword, pageNum, PAGE_LIMIT, "all", 0, "today", "published", "6ecd6970268b4c53")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<VideoListEntity, List<VideoListEntity.VideosBean>>() {
                    @Override
                    public List<VideoListEntity.VideosBean> call(VideoListEntity videoListEntity) {
                        return videoListEntity.getVideos();
                    }
                })
                .subscribe(new Action1<List<VideoListEntity.VideosBean>>() {
                    @Override
                    public void call(List<VideoListEntity.VideosBean> imgsBeen) {
                        if (isRefresh) {
                            if (imgsBeen.isEmpty()) {
                                mView.showEmpty(null);
                            } else {
                                mView.showImageList(imgsBeen);
                                mView.showContent();
                            }
                        } else {
                            mView.showImageList(imgsBeen);
                            if (PAGE_LIMIT > imgsBeen.size()) {
                                mView.loadEnd();
                            }
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        if (isRefresh) {
                            mView.showNetworkError();
                        } else {
                            mView.loadFail();
                        }
                    }
                });
        addSubscrebe(subscription);
    }
}
