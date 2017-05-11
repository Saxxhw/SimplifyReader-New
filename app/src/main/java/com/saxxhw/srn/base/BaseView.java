package com.saxxhw.srn.base;

/**
 * Created by Saxxhw on 2017/1/12.
 * 邮箱：Saxxhw@126.com
 * 功能：View基类
 */

public interface BaseView {

    /**
     * show ProgressDialog
     */
    void showProgress();

    /**
     * dismiss ProgressDialog
     */
    void dismissProgress();

    /**
     * show loading
     */
    void showLoading();

    /**
     * hide loading
     */
    void showContent();

    /**
     * show empty
     */
    void showEmpty(String msg);

    /**
     * show network error
     */
    void showNetworkError();
}
