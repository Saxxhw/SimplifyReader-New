package com.saxxhw.srn.di.component;

import android.app.Activity;

import com.saxxhw.srn.di.ActivityScope;
import com.saxxhw.srn.di.module.ActivityModule;

import dagger.Component;

/**
 * Created by Saxxhw on 2016/11/26.
 * 邮箱：Saxxhw@126.com
 * 功能：
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();
}
