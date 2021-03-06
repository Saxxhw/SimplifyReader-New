package com.saxxhw.srn.di.module;

import android.app.Activity;

import com.saxxhw.srn.di.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Saxxhw on 2016/11/26.
 * 邮箱：Saxxhw@126.com
 * 功能：
 */

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @SuppressWarnings("WeakerAccess")
    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return mActivity;
    }
}
