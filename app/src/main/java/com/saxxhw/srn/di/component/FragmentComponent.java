package com.saxxhw.srn.di.component;

import android.app.Activity;

import com.saxxhw.srn.di.FragmentScope;
import com.saxxhw.srn.di.module.FragmentModule;
import com.saxxhw.srn.ui.fragment.ImageListFragment;

import dagger.Component;

/**
 * Created by Saxxhw on 2016/11/26.
 * 邮箱：Saxxhw@126.com
 * 功能：
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(ImageListFragment mImageListFragment);
}
