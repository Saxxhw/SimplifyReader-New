package com.saxxhw.srn.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.saxxhw.srn.di.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Saxxhw on 2016/11/26.
 * 邮箱：Saxxhw@126.com
 * 功能：
 */

@Module
public class FragmentModule {

    private Fragment mFragment;

    public FragmentModule(Fragment mFragment) {
        this.mFragment = mFragment;
    }

    @SuppressWarnings("WeakerAccess")
    @Provides
    @FragmentScope
    public Activity provideActivity() {
        return mFragment.getActivity();
    }
}
