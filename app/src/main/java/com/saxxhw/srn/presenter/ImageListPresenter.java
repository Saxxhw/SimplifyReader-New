package com.saxxhw.srn.presenter;

import com.saxxhw.srn.model.bean.ImageListEntity;
import com.saxxhw.srn.model.http.HttpHelper;
import com.saxxhw.srn.model.http.api.Apis;
import com.saxxhw.srn.presenter.contract.ImageListContract;
import com.saxxhw.srn.rx.RxPresenter;
import com.socks.library.KLog;

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

public class ImageListPresenter extends RxPresenter<ImageListContract.View> implements ImageListContract.Presenter {

    private HttpHelper mHttpHelper;

    private int pageNum = 0;

    private static final int PAGE_LIMIT = 20;

    @SuppressWarnings("WeakerAccess")
    @Inject
    public ImageListPresenter(HttpHelper mHttpHelper) {
        this.mHttpHelper = mHttpHelper;
    }

    @Override
    public void getImageList(String category, final boolean isRefresh) {
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
        Subscription subscription = mHttpHelper.getImageRetrofit(Apis.class).getImageList(category, "全部", pageNum * PAGE_LIMIT, PAGE_LIMIT, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<ImageListEntity, List<ImageListEntity.ImgsBean>>() {
                    @Override
                    public List<ImageListEntity.ImgsBean> call(ImageListEntity imageListEntity) {
                        List<ImageListEntity.ImgsBean> mList = imageListEntity.getImgs();
                        if (PAGE_LIMIT < mList.size()) {
                            mList.remove(mList.size() - 1);
                        }
                        return mList;
                    }
                }).subscribe(new Action1<List<ImageListEntity.ImgsBean>>() {
                    @Override
                    public void call(List<ImageListEntity.ImgsBean> imgsBeen) {
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
