package com.saxxhw.srn.ui.fragment;

import android.view.View;

import com.saxxhw.srn.R;
import com.saxxhw.srn.base.BaseFragment;

/**
 * Created by Saxxhw on 2017/5/11.
 * 邮箱：Saxxhw@126.com
 * 功能：音乐轻听
 */

public class MusicFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_music;
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected void bindListener() {

    }
}
