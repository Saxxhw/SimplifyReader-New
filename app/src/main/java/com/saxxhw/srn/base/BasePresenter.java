package com.saxxhw.srn.base;

/**
 * Created by Saxxhw on 2016/11/26.
 * 邮箱：Saxxhw@126.com
 * 功能：Presenter基类
 */

public interface BasePresenter<T extends BaseView> {

    void attachView(T view);

    void detachView();
}
