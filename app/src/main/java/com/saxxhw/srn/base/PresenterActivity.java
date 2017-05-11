package com.saxxhw.srn.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.saxxhw.srn.App;
import com.saxxhw.srn.di.component.ActivityComponent;
import com.saxxhw.srn.di.component.DaggerActivityComponent;
import com.saxxhw.srn.di.module.ActivityModule;

import javax.inject.Inject;

/**
 * Created by Saxxhw on 2017/4/11.
 * 邮箱：Saxxhw@126.com
 * 功能：Activity基类
 */

public abstract class PresenterActivity<T extends BasePresenter>  extends BaseActivity implements BaseView {

    @Inject
    protected T mPresenter;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initInject();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
    }

    /**
     * show ProgressDialog
     */
    @Override
    public void showProgress() {
        showProgressDialog();
    }

    /**
     * dismiss ProgressDialog
     */
    @Override
    public void dismissProgress() {
        hideProgressDialog();
    }

    /**
     * show loading
     */
    @Override
    public void showLoading() {
        toggleShowLoading(true);
    }

    /**
     * hide loading
     */
    @Override
    public void showContent() {
        toggleShowLoading(false);
    }

    /**
     * show empty
     */
    @Override
    public void showEmpty(String msg) {
        toggleShowEmpty(true, msg);
    }

    /**
     * show network error
     */
    @Override
    public void showNetworkError() {
        toggleNetworkError(true);
    }

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder().appComponent(App.getAppComponent()).activityModule(new ActivityModule(this)).build();
    }

    protected abstract void initInject();
}
