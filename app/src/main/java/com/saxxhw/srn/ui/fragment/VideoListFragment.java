package com.saxxhw.srn.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.saxxhw.srn.R;
import com.saxxhw.srn.base.BaseFragment;

/**
 * Created by Saxxhw on 2017/5/15.
 * 邮箱：Saxxhw@126.com
 * 功能：
 */

public class VideoListFragment extends BaseFragment {

    private static final String TITLE = "title";
    private String title;

    public VideoListFragment() {

    }

    public static VideoListFragment newInstance(String title) {
        VideoListFragment fragment = new VideoListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != getArguments()) {
            title = getArguments().getString(TITLE);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video_list;
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
