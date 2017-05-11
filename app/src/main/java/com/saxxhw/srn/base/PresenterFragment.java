package com.saxxhw.srn.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.saxxhw.srn.App;
import com.saxxhw.srn.di.component.DaggerFragmentComponent;
import com.saxxhw.srn.di.component.FragmentComponent;
import com.saxxhw.srn.di.module.FragmentModule;

import javax.inject.Inject;

/**
 * Created by Saxxhw on 2017/4/11.
 * 邮箱：Saxxhw@126.com
 * 功能：Fragment基类
 */

public abstract class PresenterFragment<T extends BasePresenter> extends BaseFragment implements BaseView {

    @Inject
    protected T mPresenter;

    @SuppressWarnings("unchecked")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initInject();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
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

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder().appComponent(App.getAppComponent()).fragmentModule(new FragmentModule(this)).build();
    }

    protected abstract void initInject();
}
